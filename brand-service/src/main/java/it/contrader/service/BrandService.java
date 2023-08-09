package it.contrader.service;

import it.contrader.dao.BrandRepository;
import it.contrader.dto.BrandDTO;
import it.contrader.model.Brand;
import jakarta.transaction.Transactional;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class BrandService {

    private final BrandRepository brandRepository;
    private final WebClient.Builder webClient;

    public List<BrandDTO> getAll() {
        List<Brand> brands =  brandRepository.findAll();
        return brands.stream().map(this::mapToDTO).toList();
    }

    public BrandDTO getBrandById(int id){
        return this.mapToDTO(brandRepository.findById(id));
    }

    public void insert(BrandDTO brandDTO){
        Brand brand = Brand.builder()
                .brand_name(brandDTO.getBrand_name())
                .description(brandDTO.getDescription())
                .dob(brandDTO.getDob())
                .id_login(brandDTO.getId_login())
                .build();

        brandRepository.save(brand);
    }

    public void delete(BrandDTO brandDTO){
        Brand brand = Brand.builder()
                .id(brandDTO.getId())
                .brand_name(brandDTO.getBrand_name())
                .description(brandDTO.getDescription())
                .dob(brandDTO.getDob())
                .id_login(brandDTO.getId_login())
                .build();
        brandRepository.delete(brand);
    }

    public void update(BrandDTO brandDTO){
        Brand brand = Brand.builder()
                .id(brandDTO.getId())
                .brand_name(brandDTO.getBrand_name())
                .description(brandDTO.getDescription())
                .dob(brandDTO.getDob())
                .id_login(brandDTO.getId_login())
                .build();
        brandRepository.save(brand);
    }


     private BrandDTO mapToDTO(Brand brand){
        return BrandDTO.builder()
                .id(brand.getId())
                .brand_name(brand.getBrand_name())
                .description(brand.getDescription())
                .id_login(brand.getId_login())
                .dob(brand.getDob())
                .build();
     }

    public boolean existsById(int id) {
        return brandRepository.existsById(id);
    }
}
