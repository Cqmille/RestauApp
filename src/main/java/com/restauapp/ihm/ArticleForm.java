package com.restauapp.ihm;

import com.restauapp.bo.*;
import lombok.Data;

@Data
public class ArticleForm {

    private Long id;
    private String intitule;
    private String description;
    private double prix;
    private Long carteId;
    private String type;

    public Article toArticle(Carte carte) {
        switch (type) {
            case "Boisson":
                Boisson boisson = new Boisson();
                boisson.setIntitule(intitule);
                boisson.setDescription(description);
                boisson.setPrix(prix);
                boisson.setCarte(carte);
                return boisson;
            case "Dessert":
                Dessert dessert = new Dessert();
                dessert.setIntitule(intitule);
                dessert.setDescription(description);
                dessert.setPrix(prix);
                dessert.setCarte(carte);
                return dessert;
            case "PlatPrincipal":
                PlatPrincipal plat = new PlatPrincipal();
                plat.setIntitule(intitule);
                plat.setDescription(description);
                plat.setPrix(prix);
                plat.setCarte(carte);
                return plat;
            default:
                throw new IllegalArgumentException("Invalid article type: " + type);
        }
    }
}