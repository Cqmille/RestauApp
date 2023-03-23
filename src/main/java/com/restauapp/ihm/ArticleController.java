package com.restauapp.ihm;

import com.restauapp.bll.BllException;
import com.restauapp.bll.RestauService;
import com.restauapp.bo.Article;
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
    public String list(Model model) {
        List<Article> articles = service.getAllArticles();
        model.addAttribute("articles", articles);
        return "article";
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

    @PostMapping("/article")
    public String addArticle(@ModelAttribute("article") Article article, BindingResult result, Model model) {
        try {
            service.addArticle(article);
        } catch (BllException e) {
            model.addAttribute("cartes", service.getAllCartes());
            model.addAttribute("errorMessage", e.getMessage());
            return "add-article";
        }
        return "redirect:/article";
    }

}
