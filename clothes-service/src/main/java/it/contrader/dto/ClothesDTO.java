package it.contrader.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class ClothesDTO {
    private int id;

    private String name;

    private String description;

    private int id_brand;

    private double price;
}
