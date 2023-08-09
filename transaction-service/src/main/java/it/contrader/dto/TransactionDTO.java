package it.contrader.dto;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TransactionDTO {
    private int id;

    private int idUser;

    private int idClothes;

    private Date date;
}
