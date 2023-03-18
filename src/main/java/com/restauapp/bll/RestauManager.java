package com.restauapp.bll;

import com.restauapp.bo.Article;
import com.restauapp.bo.Carte;
import com.restauapp.bo.Type;

import java.util.List;

public interface RestauManager {

    // Article
    void addArticle(Article a) throws BllException;

    List<Article> getArticlesWithMaxPrice(Double d);

    List<Article> getAllArticles();

    Article getArticleByIntitule(String i);

    Article getArticleById(Long id);

    // Carte
    void addCarte(Carte a) throws BllException;

    List<Carte> getAllCartes();

    Carte getCarteByType(Type t);

    void addArticleToCarte(Article a, Carte c) throws BllException;

    // Commande
    void addCommande(List<Article> articles, int numTable) throws BllException;

}