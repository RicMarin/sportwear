package it.contrader.controller;

import it.contrader.dto.ClothesDTO;
import it.contrader.service.ClothesService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/clothes")
@Slf4j
@RequiredArgsConstructor
public class ClothesController {

    @Autowired
    private final ClothesService clothesService;

    @GetMapping( value = "/getAllClothes")
    public List<ClothesDTO> getAll(){
        return clothesService.getAll();
    }

    @GetMapping(value = "/getClothe/{id}")
    public ClothesDTO getById(@PathVariable int id){
        return clothesService.findById(id);
    }
    @PostMapping(value ="/updateClothe")
    public void updateClothe(@RequestBody ClothesDTO clothesDTO){
        clothesService.update(clothesDTO);
    }

    @PostMapping( value = "/save")
    public void insertClothe(@RequestBody ClothesDTO clothesDTO){
        clothesService.insertClothe(clothesDTO);
    }

    @DeleteMapping(value = "/delete")
    public void deleteClothe(@RequestBody ClothesDTO clothesDTO){
        clothesService.delete(clothesDTO);
    }

    @GetMapping(value = "/existsById/{id}")
    public boolean existsById(@PathVariable int id){
        return clothesService.existsById(id);
    }

    @GetMapping(value = "/test")
    public String test(){
        return "funziona";
    }
}
