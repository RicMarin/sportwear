package it.contrader.dto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class UserDTO {

    private int id;

    private Date dob;

    private String name;

    private int id_login;
}