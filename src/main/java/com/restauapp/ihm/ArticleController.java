package com.restauapp.ihm;

import com.restauapp.bll.RestauService;
import com.restauapp.bo.Article;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

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
        List<ArticleForm> articleForms = articles.stream()
                .map(article -> {
                    ArticleForm form = new ArticleForm();
                    form.setId(article.getId());
                    form.setIntitule(article.getIntitule());
                    form.setDescription(article.getDescription());
                    form.setPrix(article.getPrix());
                    form.setCarteId(article.getCarte().getId());
                    form.setType(article.getClass().getSimpleName());
                    return form;
                })
                .collect(Collectors.toList());
        model.addAttribute("articleForms", articleForms);
        return "article";
    }

    // Complete the code the manage CRUD.
    // Use AvionController as example

}
