package it.contrader.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "brands")
public class Brand {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_brands")
    private int id;

    @Column(name = "brand_name")
    private String brand_name;

    @Column(name = "description")
    private String description;

    @Column( name = "dob_account")
    private Date dob;

    @Column(name="id_login")
    private int id_login;
}
