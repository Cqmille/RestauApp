package com.restauapp;

import com.restauapp.bll.BllException;
import com.restauapp.bll.RestauService;
import com.restauapp.bo.*;

import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.List;
import java.util.NoSuchElementException;

@SpringBootApplication
public class RestauAppApplication implements CommandLineRunner {

    @Autowired
    RestauService service;

    public static void main(String[] args) {
        SpringApplication.run(RestauAppApplication.class, args);
    }

    @Override
    @Transactional
    public void run(String... args) throws Exception {
        // Création de nouveaux articles
        PlatPrincipal poulet = new PlatPrincipal();
        poulet.setIntitule("Poulet rôti");
        poulet.setDescription("Poulet rôti avec légumes et sauce au choix");
        poulet.setPrix(12.99);

        Boisson coca = new Boisson();
        coca.setIntitule("Coca");
        coca.setDescription("Du coca");
        coca.setPrix(1.99);

        Entree carottesRapees = new Entree();
        carottesRapees.setIntitule("Carottes râpées");
        carottesRapees.setDescription("Carottes râpées avec vinaigrette");
        carottesRapees.setPrix(5.99);

        Dessert ileFlotante = new Dessert();
        ileFlotante.setIntitule("Île flottante");
        ileFlotante.setDescription("Un dessert à base de blancs d'oeufs et de caramel");
        ileFlotante.setPrix(6.99);

        PlatPrincipal rizAuBeurre = new PlatPrincipal();
        rizAuBeurre.setIntitule("Riz au beurre");
        rizAuBeurre.setDescription("Du riz cuit avec du beurre");
        rizAuBeurre.setPrix(8.99);

        // Enregistrement des articles en base
        service.addArticle(poulet);
        service.addArticle(coca);
        service.addArticle(carottesRapees);
        service.addArticle(ileFlotante);
        service.addArticle(rizAuBeurre);

        // Création carte principale
        Carte carteMain = new Carte();
        carteMain.setTitre("Carte principale");
        carteMain.setDescriptif("Les plats et boissons de notre restaurant");
        carteMain.setType(Type.PLATS_ET_BOISSONS);

        // Création carte dessert
        Carte carteDessert = new Carte();
        carteDessert.setTitre("Carte dessert");
        carteDessert.setDescriptif("Les desserts de notre restaurant");
        carteDessert.setType(Type.DESSERTS);

        //Enregistrer les cartes en base
        service.addCarte(carteMain);
        service.addCarte(carteDessert);

        // Récupération de tous les Articles
        List<Article> articles = service.getAllArticles();

        // Associer les articles aux différentes cartes
        for(Article article: articles) {
            if(article instanceof PlatPrincipal || article instanceof Boisson) {
                service.addArticleToCarte(article, carteMain);
            } else if(article instanceof Entree) {
                service.addArticleToCarte(article, carteMain);
            } else if(article instanceof Dessert) {
                service.addArticleToCarte(article, carteDessert);
            }
        }
    }

}
