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

@Controller
public class ArticleController {

    @Autowired
    RestauService service;

    @GetMapping()
    public String list(Article article, Model model) {
        model.addAttribute("avions", service.getAllArticles());
        return "article";
    }

}
