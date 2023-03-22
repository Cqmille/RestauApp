package com.restauapp.bll;

import com.restauapp.bo.Article;
import com.restauapp.bo.Carte;
import com.restauapp.bo.Commande;
import com.restauapp.bo.Type;

import java.util.Date;
import java.util.List;

public interface RestauService {

    // Article
    void addArticle(Article a) throws BllException;
    void updateArticle(Article a) throws BllException;
    void deleteArticle(Long id) throws BllException;
    List<Article> getArticlesWithMaxPrice(Double d);
    List<Article> getAllArticles();
    Article getArticleByIntitule(String i);
    Article getArticleById(Long id);

    // Carte
    void addCarte(Carte a) throws BllException;
    void updateCarte(Carte a) throws BllException;
    void deleteCarte(Long id) throws BllException;
    List<Carte> getAllCartes();
    Carte getCarteByType(Type t);
    Carte getCarteById(Long id);
    void addArticleToCarte(Article a, Carte c) throws BllException;
    void removeArticleFromCarte(Article a, Carte c) throws BllException;

    // Commande
    void addCommande(List<Article> articles, int numTable) throws BllException;
    void updateCommande(Commande c) throws BllException;
    void deleteCommande(Long id) throws BllException;
    List<Commande> getAllCommandes();
    Commande getCommandeById(Long id);
    List<Commande> getCommandesByTable(int numTable);
    List<Commande> getCommandesByDate(Date date);
    double getTotalMontant(Commande c);
}