package com.restauapp.bo;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "article")
public class Article {

    @Id
    @GeneratedValue
    private Long id;

    @Column(length = 100, unique = true)
    private String intitule;

    @Column(length = 255)
    private String description;

    private double prix;

    @ManyToOne
    //@JoinColumn(name = "carte_id")
    private Carte carte;

    public Long getIdArticle() {
        return this.id;
    }

}