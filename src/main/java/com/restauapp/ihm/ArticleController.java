package com.restauapp.ihm;

import com.restauapp.bll.BllException;
import com.restauapp.bll.RestauService;
import com.restauapp.bo.Article;
import com.restauapp.bo.Carte;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;

import java.util.List;
import java.util.stream.Collectors;

@Controller
public class ArticleController {

    @Autowired
    RestauService service;

    @GetMapping("/article")
    //public String list(Model model, @ModelAttribute("article") Article article) {
    public String list(Model model, Article article) {
        List<Article> articles = service.getAllArticles();
        List<Carte> cartes = service.getAllCartes();
        model.addAttribute("articles", articles);
        model.addAttribute("cartes", cartes);
        return "article";
    }

    @PostMapping("/article")
    public String addArticle(@Valid @ModelAttribute("article") Article article, BindingResult result, Model model) throws BllException {
        if (result.hasErrors()) {
            List<Article> articles = service.getAllArticles();
            List<Carte> cartes = service.getAllCartes();
            model.addAttribute("articles", articles);
            model.addAttribute("cartes", cartes);
            return "article";
        }
        service.addArticle(article);
        return "redirect:/article"; // n'appelle pas l'html mais l'url
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable("id") Long id, Article article, Model model) {
        try {
            service.deleteArticle(id);
        } catch (BllException e) {
            model.addAttribute("articles", service.getAllArticles());
            model.addAttribute("message", e.getMessage());
            return "article";
        }
        return "redirect:/article";
    }

//    @PostMapping("/article")
//    public String addArticle(@Valid @ModelAttribute("article") Article article, BindingResult result, Model model) {
//        if(result.hasErrors()){
//            model.addAttribute("cartes", service.getAllCartes());
//            return "article";
//        }
//        try {
//            service.addArticle(article);
//        } catch (BllException e) {
//            List<Carte> cartes = service.getAllCartes();
//            model.addAttribute("cartes", cartes);
//            model.addAttribute("errorMessage", e.getMessage());
//            return "article";
//        }
//        return "redirect:/article";
//    }

}
