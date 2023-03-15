package com.restauapp.bll;

import com.restauapp.badcontentcomponent.BadContentDetector;
import com.restauapp.bo.Article;
import com.restauapp.bo.Carte;
import com.restauapp.dal.ArticleDAO;
import com.restauapp.dal.CarteDAO;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RestauManagerImpl implements RestauManager {

    @Autowired
    ArticleDAO articleDAO;

    @Autowired
    CarteDAO carteDAO;

    @Autowired
    private BadContentDetector badContentDetector;

    @Override
    @Transactional
    public void addArticle(Article a) throws BllException {
        // Check for bad content
        String intitule = badContentDetector.detectBadContent(a.getIntitule());
        String description = badContentDetector.detectBadContent(a.getDescription());
        a.setIntitule(intitule);
        a.setDescription(description);

        articleDAO.save(a);
        System.out.println("+1 Article saved");
    }
    @Override
    @Transactional
    public List<Article> getArticlesWithMaxPrice(Double d) {
        List<Article> articles = getAllArticles();
        for (Article article : articles) {
            if (article.getPrix() > d) {
                articles.remove(article);
            }
        }
        return articles;
    }

    @Override
    public List<Article> getAllArticles() {
        return (List<Article>) articleDAO.findAll();
    }

    @Override
    @Transactional
    public void addCarte(Carte c) {
        carteDAO.save(c);
        System.out.println("+1 Carte saved");
    }

    @Override
    @Transactional
    public void addArticleToCarte(Article a, Carte c) throws BllException {
        // Get the list of existing articles in the carte
        List<Article> existingArticles = c.getArticles();

        // Check if the article already exists in the carte
        for (Article existingArticle : existingArticles) {
            if (existingArticle.getId().equals(a.getId())) {
                throw new BllException("Article already exists in the carte.");
            }
        }

        // Get all the articles
        List<Article> allArticles = getAllArticles();

        // Check if an article with the same name already exists in another carte
        for (Article article : allArticles) {
            if (article.getIntitule().equals(a.getIntitule()) && article.getCarte() != null) {
                throw new BllException("Article with the same name already exists in another carte.");
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
        System.out.println(c.getTitre() + " updated + 1 article");
    }

    @Override
    @Transactional
    public List<Article> getArticlesFromCarte(Carte c) {
        return c.getArticles();
    }
}
