package com.restauapp.bo;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "facture")
public class Facture {

    @Id
    @GeneratedValue
    private Long id;

    @OneToMany
    private List<Article> articles;

    private double prixTotal;

    private String numeroCarteBleue;
}