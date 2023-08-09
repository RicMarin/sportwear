package it.contrader.service;

import it.contrader.dao.TransactionRepository;
import it.contrader.dto.TransactionDTO;
import it.contrader.event.TransId;
import it.contrader.model.Transaction;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
@Transactional
public class TransactionService {

    private final TransactionRepository transactionRepository;
    private final WebClient.Builder webClient;
    private final KafkaTemplate<String, TransId> kafkaTemplate;

    public List<TransactionDTO> getAll(){
        List<Transaction> transactions = transactionRepository.findAll();
        return transactions.stream().map(this::mapToDTO).toList();
    }

    public TransactionDTO getById(int id){
        return this.mapToDTO(transactionRepository.findById(id));
    }

    public List<TransactionDTO> getByUserId(int id){
        List<Transaction> transactions = transactionRepository.findByIdUser(id);
        return transactions.stream().map(this::mapToDTO).toList();
    }

    public void insertTransaction(TransactionDTO transactionDTO){

        //System.out.println(transactionDTO);
        boolean user = webClient.build().get()
                .uri("http://user-service/user/existsById/" + transactionDTO.getIdUser())
                .retrieve()
                .bodyToMono(Boolean.class)
                .block();
        boolean clothes = webClient.build().get()
                .uri("http://clothes-service/clothes/existsById/" + transactionDTO.getIdClothes())
                .retrieve()
                .bodyToMono(Boolean.class)
                .block();

        //System.out.println(user + " " + clothes);
        if(user && clothes)
        {

            /*transactionRepository.save(Transaction.builder()
                    .idUser(transactionDTO.getIdUser())
                    .idClothes(transactionDTO.getIdClothes())
                    .date(transactionDTO.getDate())
                    .build());*/
            kafkaTemplate.send("transactionTopic",new TransId(transactionDTO.getIdUser()));
        }
        else System.out.println("no socio");
    }

    public void updateTransaction(TransactionDTO transactionDTO){
        transactionRepository.save(Transaction.builder()
                .id(transactionDTO.getId())
                .idUser(transactionDTO.getIdUser())
                .idClothes(transactionDTO.getIdClothes())
                .date(transactionDTO.getDate())
                .build());
    }

    public void deleteTransaction(TransactionDTO transactionDTO){
        transactionRepository.delete(Transaction.builder()
                .id(transactionDTO.getId())
                .idUser(transactionDTO.getIdUser())
                .idClothes(transactionDTO.getIdClothes())
                .date(transactionDTO.getDate())
                .build());
    }

    private TransactionDTO mapToDTO(Transaction transaction) {
        return TransactionDTO.builder()
                .id(transaction.getId())
                .idUser(transaction.getIdUser())
                .idClothes(transaction.getIdClothes())
                .date(transaction.getDate())
                .build();
    }
}
