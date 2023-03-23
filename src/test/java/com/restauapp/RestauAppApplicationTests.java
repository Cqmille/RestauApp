//package com.restauapp;
//
//import com.restauapp.bll.BllException;
//import com.restauapp.bll.RestauService;
//import com.restauapp.bo.*;
//import org.junit.jupiter.api.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.junit.jupiter.api.Assertions;
//
//
//import java.util.List;
//import java.util.NoSuchElementException;
//
//@SpringBootTest
//public class RestauAppApplicationTests {
//
//    @Autowired
//    RestauService manager;
//
//    @Test
//    void scenario_01() throws BllException {
//        creerArticles();
//        creerCarte();
//        testerFeatures();
//    }
//
//    void creerArticles() throws BllException {
//        // Création de nouveaux articles
//        PlatPrincipal poulet = new PlatPrincipal();
//        poulet.setIntitule("Poulet rôti");
//        poulet.setDescription("Poulet rôti avec légumes et sauce au choix");
//        poulet.setPrix(12.99);
//
//        Boisson coca = new Boisson();
//        coca.setIntitule("Coca");
//        coca.setDescription("Du coca");
//        coca.setPrix(1.99);
//
//        Entree carottesRapees = new Entree();
//        carottesRapees.setIntitule("Carottes râpées");
//        carottesRapees.setDescription("Carottes râpées avec vinaigrette");
//        carottesRapees.setPrix(5.99);
//
//        Dessert ileFlotante = new Dessert();
//        ileFlotante.setIntitule("Île flottante");
//        ileFlotante.setDescription("Un dessert à base de blancs d'oeufs et de caramel");
//        ileFlotante.setPrix(6.99);
//
//        PlatPrincipal rizAuBeurre = new PlatPrincipal();
//        rizAuBeurre.setIntitule("Riz au beurre");
//        rizAuBeurre.setDescription("Du riz cuit avec du beurre");
//        rizAuBeurre.setPrix(8.99);
//
//        // Enregistrement des articles en base
//        manager.addArticle(poulet);
//        manager.addArticle(coca);
//        manager.addArticle(carottesRapees);
//        manager.addArticle(ileFlotante);
//        manager.addArticle(rizAuBeurre);
//
//        // Récupérer l'article carottesRapees par Id pour vérifier s'il est présent dans la base de données
//        Article carottesRapeesFromDb = manager.getArticleById(carottesRapees.getId());
//
//        // Vérifie que l'article récupéré n'est pas null
//        Assertions.assertNotNull(carottesRapeesFromDb);
//
//        // Vérifie que l'article récupéré a le même intitulé que l'article créé
//        Assertions.assertEquals(carottesRapees.getIntitule(), carottesRapeesFromDb.getIntitule());
//
//        // Vérifie que l'article récupéré a le même prix que l'article créé
//        Assertions.assertEquals(carottesRapees.getPrix(), carottesRapeesFromDb.getPrix());
//
//        System.out.println("Fin des opérations sur les articles\n");
//    }
//
//    void creerCarte() throws BllException {
//        // Création carte principale
//        Carte carteMain = new Carte();
//        carteMain.setTitre("Carte principale");
//        carteMain.setDescriptif("Les plats et boissons de notre restaurant");
//        carteMain.setType(Type.PLATS_ET_BOISSONS);
//
//        // Création carte dessert
//        Carte carteDessert = new Carte();
//        carteDessert.setTitre("Carte dessert");
//        carteDessert.setDescriptif("Les desserts de notre restaurant");
//        carteDessert.setType(Type.DESSERTS);
//
//        //Enregistrer les cartes en base
//        manager.addCarte(carteMain);
//        manager.addCarte(carteDessert);
//
//        // Récupération de tous les Articles
//        List<Article> articles = manager.getAllArticles();
//
//        // Associer les articles aux différentes cartes
//        for(Article article: articles) {
//            if(article instanceof PlatPrincipal || article instanceof Boisson) {
//                manager.addArticleToCarte(article, carteMain);
//            } else if(article instanceof Entree) {
//                manager.addArticleToCarte(article, carteMain);
//            } else if(article instanceof Dessert) {
//                manager.addArticleToCarte(article, carteDessert);
//            }
//        }
//
//        System.out.println("Fin des opérations sur les cartes \n");
//
//    }
//
//    void testerFeatures() throws BllException {
//        // Récupérer l'article coca
//        Article coca = manager.getArticleByIntitule("Coca");
//
//        // Tenter d'ajouter le coca à la carte principale
//        try {
//            Carte carteMain = manager.getCarteByType(Type.PLATS_ET_BOISSONS);
//            manager.addArticleToCarte(coca, carteMain);
//            System.out.println("Coca ajouté à la carte principale");
//        } catch (BllException e) {
//            System.out.println("Exception lors de l'ajout de l'article "
//                    + coca.getIntitule() + " : " + e.getMessage());
//        } catch (NoSuchElementException e) {
//            System.out.println("Aucune carte principale trouvée : " + e.getMessage());
//        }
//
//        // Tentative d'ajout du coca à la carte des desserts
//        try {
//            Carte carteDesserts = manager.getCarteByType(Type.DESSERTS);
//            manager.addArticleToCarte(coca, carteDesserts);
//        } catch (BllException e) {
//            System.out.println("Exception lors de l'ajout de l'article "
//                    + coca.getIntitule() + " : " + e.getMessage());
//        }
//
//        // Récupération des articles à 10€ ou moins
//        List<Article> articlesPasCher = manager.getArticlesWithMaxPrice(10.0);
//
//        // Affichage des articles à 10€ ou moins
//        System.out.println("\nArticles à 10€ ou moins :");
//        for (Article article : articlesPasCher) {
//            System.out.println(article.getIntitule() + " - " + article.getPrix() + "€");
//        }
//
//        // Récupération de la liste des articles associés à une carte
//        Carte carteMain = manager.getCarteByType(Type.PLATS_ET_BOISSONS);
//        List<Article> articles = carteMain.getArticles();
//
//        // Affichage de la liste des articles
//        System.out.println("\nArticles dans " + carteMain.getTitre() + " :");
//        for (Article article : articles) {
//            System.out.println(article.getIntitule() + " - " + article.getPrix() + "€");
//        }
//
//        // Création d'un nouvel article avec un mot à filter
//        PlatPrincipal riz = new PlatPrincipal();
//        riz.setIntitule("Riz de merde");
//        riz.setDescription("Riz, légumes et pute au choix");
//        riz.setPrix(12.99);
//        System.out.println("\nArticle avant enregistrement : " + riz);
//
//        // Enregistrement de l'article
//        manager.addArticle(riz);
//
//        // Mise à jour de l'objet
//        Long rizId = riz.getId();
//        riz = (PlatPrincipal) manager.getArticleById(rizId);
//
//        // Afficher l'intitulé et la description modifiés
//        System.out.println("Article après enregistrement : " + riz);
//        System.out.println("Fin du test des fonctionnalités\n");
//
//    }
//
//}