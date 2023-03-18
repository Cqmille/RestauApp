package com.restauapp.bll;

import com.restauapp.bo.*;
import com.restauapp.dal.ArticleDAO;
import com.restauapp.dal.CarteDAO;
import com.restauapp.dal.CommandeDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.restauapp.badcontentcomponent.BadContentDetector;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class RestauManagerImpl implements RestauManager {

    @Autowired
    ArticleDAO articleDAO;

    @Autowired
    CarteDAO carteDAO;

    @Autowired
    CommandeDAO commandeDAO;

    @Autowired
    private BadContentDetector badContentDetector;

    // ---------------------
    // Méthodes pour Article
    // ---------------------

    @Override
    @Transactional
    public void addArticle(Article a) throws BllException {
        // Modération contenu
        String intitule = badContentDetector.detectBadContent(a.getIntitule());
        String description = badContentDetector.detectBadContent(a.getDescription());

        a.setIntitule(intitule);
        a.setDescription(description);

        articleDAO.save(a);
        System.out.println("Enregistré | " + a);
    }

    @Override
    public List<Article> getAllArticles() {
        return (List<Article>) articleDAO.findAll();
    }

    @Override
    public List<Article> getArticlesWithMaxPrice(Double d) {
        List<Article> articles = getAllArticles();
        for (int i = articles.size() - 1; i >= 0; i--) {
            Article article = articles.get(i);
            if (article.getPrix() > d) {
                articles.remove(i);
            }
        }
        return articles;
    }

    @Override
    public Article getArticleByIntitule(String i) {
        List<Article> articles = getAllArticles();

        for (Article article : articles) {
            if (article.getIntitule().equals(i)) {
                return article;
            }
        }
        return null;
    }

    @Override
    public Article getArticleById(Long id) {
        Optional<Article> article = articleDAO.findById(id);
        return article.orElse(null);
    }

    // -------------------
    // Méthodes pour Carte
    // -------------------

    @Override
    @Transactional
    public void addCarte(Carte c) {
        carteDAO.save(c);
        System.out.println("Enregistré | " + c);
    }

    @Override
    public List<Carte> getAllCartes() {
        return (List<Carte>) carteDAO.findAll();
    }

    @Override
    public Carte getCarteByType(Type type) {
        List<Carte> cartes = getAllCartes();
        for (Carte carte : cartes) {
            if (carte.getType() == type) {
                return carte;
            }
        }
        return null;
    }

    @Override
    @Transactional
    public void addArticleToCarte(Article a, Carte c) throws BllException {
        // Get the list of existing articles in the carte
        List<Article> existingArticles = c.getArticles();

        // Check if the article already exists in the carte
        for (Article existingArticle : existingArticles) {
            if (existingArticle.getId().equals(a.getId())) {
                throw new BllException("L'Article existe déjà dans la carte.");
            }
        }

        // Get all the articles
        List<Article> allArticles = getAllArticles();

        // Check if an article with the same name already exists in another carte
        for (Article article : allArticles) {
            if (article.getIntitule().equals(a.getIntitule()) && article.getCarte() != null) {
                throw new BllException("L'article est associé à une autre carte.");
            }
        }

        // Add the article to the carte
        existingArticles.add(a);

        // Update the carte with the new list of articles
        c.setArticles(existingArticles);

        // Set Carte to Article
        a.setCarte(c);

        // Save the updated carte
        carteDAO.save(c);
        articleDAO.save(a);
        System.out.println("Mis à jour | " + c.getTitre() + " : 1 Article ajouté");
    }

    // -----------------------
    // Méthodes pour Commande
    // -----------------------

    @Override
    @Transactional
    public void addCommande(List<Article> articlesRandom, int numTable) throws BllException {
        Commande c = new Commande();

        // Fill in the information for the Commande object
        c.setDateCommande(new Date());
        c.setNumCommande(UUID.randomUUID().toString());
        c.setNumTable(Integer.toString(numTable));
        c.setArticles(articlesRandom);

        // Calculate the total price of the articles
        double total = 0;
        for (Article a : articlesRandom) {
            total += a.getPrix();
        }
        c.setMontantTotal(total);

        // Save the Commande object to the database
        commandeDAO.save(c);
    }

}