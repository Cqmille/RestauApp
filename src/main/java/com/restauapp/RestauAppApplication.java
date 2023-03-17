package com.restauapp;

import com.restauapp.bll.BllException;
import com.restauapp.bll.RestauManager;
import com.restauapp.bo.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Random;

@SpringBootApplication
public class RestauAppApplication implements CommandLineRunner {

    @Autowired
    RestauManager manager;

    public static void main(String[] args) {
        SpringApplication.run(RestauAppApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        creerArticles();
        creerCarte();
        testerFonctionnalites();
        //creerCommande(); //En cours
        //creerFacture();
    }

    private void creerArticles() throws BllException {
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
        manager.addArticle(poulet);
        manager.addArticle(coca);
        manager.addArticle(carottesRapees);
        manager.addArticle(ileFlotante);
        manager.addArticle(rizAuBeurre);

        System.out.println("Fin des opérations sur les articles");

    }

    private void creerCarte() throws BllException {
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
        manager.addCarte(carteMain);
        manager.addCarte(carteDessert);

        // Récupération de tous les Articles
        List<Article> articles = manager.getAllArticles();

        // Associer les articles aux différentes cartes
        for(Article article: articles) {
            if(article instanceof PlatPrincipal || article instanceof Boisson) {
                manager.addArticleToCarte(article, carteMain);
            } else if(article instanceof Entree) {
                manager.addArticleToCarte(article, carteMain);
            } else if(article instanceof Dessert) {
                manager.addArticleToCarte(article, carteDessert);
            }
        }

        System.out.println("Fin des opérations sur les cartes");
    }

    private void testerFonctionnalites() throws BllException {

        // Récupérer l'article coca
        Article coca = manager.getArticleByIntitule("Coca");

        // Tenter d'ajouter le coca à la carte principale
        try {
            Carte carteMain = manager.getCarteByType(Type.PLATS_ET_BOISSONS);
            manager.addArticleToCarte(coca, carteMain);
            System.out.println("Coca ajouté à la carte principale");
        } catch (BllException e) {
            System.out.println("Exception lors de l'ajout de l'article "
                    + coca.getIntitule() + " : " + e.getMessage());
        } catch (NoSuchElementException e) {
            System.out.println("Aucune carte principale trouvée : " + e.getMessage());
        }

        // Tentative d'ajout du coca à la carte des desserts
        try {
            Carte carteDesserts = manager.getCarteByType(Type.DESSERTS);
            manager.addArticleToCarte(coca, carteDesserts);
        } catch (BllException e) {
            System.out.println("Exception lors de l'ajout de l'article "
                    + coca.getIntitule() + " : " + e.getMessage());
        }

        // Récupération des articles à 10€ ou moins
        List<Article> articlesPasCher = manager.getArticlesWithMaxPrice(10.0);

        // Affichage des articles à 10€ ou moins
        System.out.println("Articles à 10€ ou moins :");
        for (Article article : articlesPasCher) {
            System.out.println(article.getIntitule() + " - " + article.getPrix() + "€");
        }

        // Récupération de la liste des articles associés à une carte
        Carte carteMain = manager.getCarteByType(Type.PLATS_ET_BOISSONS);
        List<Article> articles = manager.getArticlesFromCarte(carteMain);

        // Affichage de la liste des articles
        System.out.println("Articles dans " + carteMain.getTitre() + " :");
        for (Article article : articles) {
            System.out.println(article.getIntitule() + " - " + article.getPrix() + "€");
        }

        // Création d'un nouvel article avec un mot à filter
        PlatPrincipal riz = new PlatPrincipal();
        riz.setIntitule("Riz de merde");
        riz.setDescription("Riz, légumes et pute au choix");
        riz.setPrix(12.99);

        // Enregistrement de l'article
        manager.addArticle(riz);

        // Afficher l'intitulé et la description modifiés
        System.out.println(riz);

    }

//    private void creerCommande() {
//        // Récupérer tous les articles
//        List<Article> articles = manager.getAllArticles();
//
//        // Créer une liste d'articles random (x articles)
//        List<Article> articlesRandom = new ArrayList<>();
//        int nombreArticles = 5; // nombre d'articles de la commande
//        Random rand = new Random();
//        for (int i = 0; i < nombreArticles; i++) {
//            int index = rand.nextInt(articles.size());
//            articlesRandom.add(articles.get(index));
//        }
//
//        // Enregistrer la commande avec manager.addCommande(articles, int numTable)
//        int numTable = 2; // numéro de la table
//        try {
//            manager.addCommande(articlesRandom, numTable);
//            System.out.println("Commande enregistrée avec succès");
//        } catch (BllException e) {
//            System.out.println("Erreur lors de l'enregistrement de la commande : " + e.getMessage());
//        }
//    }

}
