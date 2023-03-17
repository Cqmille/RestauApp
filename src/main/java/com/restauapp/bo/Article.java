package com.restauapp.bo;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "article_type")
@Data
public abstract class Article {

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
    public double getPrix() {return this.prix; }

    @Override
    public String toString() {
        return "Article{" +
                "id=" + id +
                ", intitule='" + intitule + '\'' +
                ", description='" + description + '\'' +
                ", prix=" + prix +
                ", carte=" + carte +
                '}';
    }
}