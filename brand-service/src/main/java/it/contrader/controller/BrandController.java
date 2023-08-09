package it.contrader.controller;

import it.contrader.dto.BrandDTO;
import it.contrader.service.BrandService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Slf4j
@RequestMapping("/brand")
@RequiredArgsConstructor
public class BrandController {

    private final BrandService brandService;

    @GetMapping(value = "/getAllBrands")
    public List<BrandDTO> getAll(){
        return brandService.getAll();
    }

    @GetMapping(value = "/getBrand/{id}")
    public BrandDTO getById(@PathVariable int id){
        return brandService.getBrandById(id);
    }

    @PostMapping(value = "/save")
    public void insertBrand(@RequestBody BrandDTO brandDTO){
        brandService.insert(brandDTO);
    }

    @PostMapping(value = "/update")
    public void updateBrand(@RequestBody BrandDTO brandDTO){
        brandService.update(brandDTO);
    }

    @DeleteMapping(value = "/delete")
    public void deleteBrand(@RequestBody BrandDTO brandDTO){
        brandService.delete(brandDTO);
    }

    @GetMapping(value = "/existsById/{id}")
    public boolean existsById(@PathVariable int id){
        return brandService.existsById(id);
    }
}
