package it.contrader.service;

import it.contrader.dao.ClothesRepository;
import it.contrader.dto.ClothesDTO;
import it.contrader.event.ClotId;
import it.contrader.model.Clothes;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class ClothesService {

    private final ClothesRepository clothesRepository;
    private final WebClient.Builder webClient;
    private final KafkaTemplate<String, ClotId> kafkaTemplate;


    public List<ClothesDTO> getAll() {
        List<Clothes> clothes = (List<Clothes>) clothesRepository.findAll();
        return clothes.stream().map(this::mapToDTO).toList();
    }

    public void insertClothe(ClothesDTO clothesDTO) {

        //System.out.println(transactionDTO);
        boolean brand = webClient.build().get()
                .uri("http://brand-service/brand/existsById/" + clothesDTO.getId_brand())
                .retrieve()
                .bodyToMono(Boolean.class)
                .block();
        if (brand) {
            Clothes clothe = Clothes.builder()
                    .name(clothesDTO.getName())
                    .description(clothesDTO.getDescription())
                    .id_brand(clothesDTO.getId_brand())
                    .price(clothesDTO.getPrice())
                    .build();
            clothesRepository.save(clothe);
        }
        else System.out.println("no socio");
    }


    public ClothesDTO findById(int id){
       return this.mapToDTO(clothesRepository.findById(id));
    }

    public void update(ClothesDTO clothesDTO){
        Clothes clothe = Clothes.builder()
                .id(clothesDTO.getId())
                .name(clothesDTO.getName())
                .description(clothesDTO.getDescription())
                .id_brand(clothesDTO.getId_brand())
                .price(clothesDTO.getPrice())
                .build();

        clothesRepository.save(clothe);
    }

    public void delete(ClothesDTO clothesDTO){
        Clothes clothe = Clothes.builder()
                .id(clothesDTO.getId())
                .name(clothesDTO.getName())
                .description(clothesDTO.getDescription())
                .id_brand(clothesDTO.getId_brand())
                .price(clothesDTO.getPrice())
                .build();
        clothesRepository.delete(clothe);
    }

    public boolean existsById(int id){
        kafkaTemplate.send("clothesTopic",new ClotId(id));
        return clothesRepository.existsById(id);
    }

    private ClothesDTO mapToDTO(Clothes clothe) {
        return ClothesDTO.builder()
                .id(clothe.getId())
                .name(clothe.getName())
                .description(clothe.getDescription())
                .id_brand(clothe.getId_brand())
                .price(clothe.getPrice())
                .build();
    }
}
