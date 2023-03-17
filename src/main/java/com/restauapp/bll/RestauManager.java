package com.restauapp.bll;

import com.restauapp.bo.Article;
import com.restauapp.bo.Carte;
import com.restauapp.bo.Type;

import java.util.List;

public interface RestauManager {
    // Article
    public void addArticle(Article a) throws BllException;
    public List<Article> getArticlesWithMaxPrice(Double d);
    public List<Article> getAllArticles();


    // Carte
    public void addCarte(Carte a);
    public void addArticleToCarte(Article a, Carte c) throws BllException;
    public List<Article> getArticlesFromCarte(Carte c);

    Carte getCarteByType(Type platsEtBoissons);

    Article getArticleByIntitule(String coca);

    void addCommande(List<Article> articlesRandom, int numTable) throws BllException;
}
