package com.restauapp;

import com.restauapp.bll.BllException;
import com.restauapp.bll.RestauManager;
import com.restauapp.bo.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.List;

@SpringBootApplication
public class RestauAppApplication implements CommandLineRunner {

    @Autowired
    RestauManager manager;

    public static void main(String[] args) {
        SpringApplication.run(RestauAppApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        // Création d'une nouvelle carte principale
        Carte carteMain = new Carte();
        carteMain.setTitre("Carte principale");
        carteMain.setDescriptif("Les plats et boissons de notre restaurant");
        carteMain.setType(Type.PLAT_ET_BOISSON);

        // Création de nouveaux articles
        Article poulet = new Article();
        poulet.setIntitule("Poulet rôti");
        poulet.setDescription("Poulet rôti avec légumes et sauce au choix");
        poulet.setPrix(12.99);

        Article coca = new Article();
        coca.setIntitule("Coca");
        coca.setDescription("Du coca");
        coca.setPrix(1.99);

        // Enregistrement des articles
        manager.addArticle(poulet);
        manager.addArticle(coca);

        // Enregistrement de la carte principale
        manager.addCarte(carteMain);

        // Ajout des articles à la carte principale
        manager.addArticleToCarte(poulet, carteMain);
        manager.addArticleToCarte(coca, carteMain);

        // Tentative d'ajout d'un article déjà présent dans la carte principale
        try {
            manager.addArticleToCarte(coca, carteMain);
        } catch (BllException e) {
            System.out.println("Article "
                    + coca.getIntitule() + " - Exception BLL : "
                    + e.getMessage() + "\n");
        }

        // Création d'une nouvelle carte des desserts
        Carte carteDesserts = new Carte();
        carteDesserts.setTitre("Carte desserts");
        carteDesserts.setDescriptif("Les desserts de notre restaurant");
        carteDesserts.setType(Type.DESSERTS);

        // Enregistrement de la carte des desserts
        manager.addCarte(carteDesserts);

        // Tentative d'ajout du coca à la carte des desserts
        try {
            manager.addArticleToCarte(coca, carteDesserts);
        } catch (BllException e) {
            System.out.println("Article "
                    + coca.getIntitule() + " - Exception BLL : "
                    + e.getMessage() + "\n");
        }

        // Récupération des articles à 10€ ou moins
        List<Article> articlesPasCher = manager.getArticlesWithMaxPrice(10.0);

        // Affichage des articles à 10€ ou moins
        System.out.println("Articles à 10€ ou moins :");
        for (Article article : articlesPasCher) {
            System.out.println(article.getIntitule() + " - " + article.getPrix() + "€");
        }

        // Récupération de la liste des articles associés à la carte principale
        List<Article> articles = manager.getArticlesFromCarte(carteMain);

        // Affichage de la liste des articles
        System.out.println("Articles dans " + carteMain.getTitre() + " :");
        for (Article article : articles) {
            System.out.println(article.getIntitule() + " - " + article.getPrix() + "€");
        }

        // Création d'un nouvel article avec un mot à filter
        Article riz = new Article();
        riz.setIntitule("Riz de merde");
        riz.setDescription("Riz, légumes et pute au choix");
        riz.setPrix(12.99);

        // Enregistrement de l'article
        manager.addArticle(riz);

        // Afficher l'intitulé et la description modifiés
        System.out.println(riz);

    }
}