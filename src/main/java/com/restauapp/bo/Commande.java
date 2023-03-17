package com.restauapp.bo;

import jakarta.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "commande")
public class Commande {
    @Id
    @GeneratedValue
    private Long id;

    @Column(name = "date_commande")
    private Date dateCommande;

    @Column(name = "num_commande")
    private String numCommande;

    @Column(name = "num_table")
    private String numTable;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(
            name = "commande_article",
            joinColumns = @JoinColumn(name = "commande_id"),
            inverseJoinColumns = @JoinColumn(name = "article_id")
    )
    private List<Article> articles = new ArrayList<>();

    @Column(name = "montant_total")
    private double montantTotal;
}