package com.restauapp.ihm;

import com.restauapp.bll.RestauService;
import com.restauapp.bo.Commande;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class CommandeController {

    @Autowired
    RestauService service;

    @GetMapping("/commande")
    public String getAll(Model model) {
        List<Commande> commandes = service.getAllCommandes();
        model.addAttribute("commandes", commandes);
        return "commande";
    }

    // Create commande.html
    // Displays all commands. 1 table = 1 command
    // commande.dateCommande, commande.numCommande, commande.id, commande.numTable
    // Also, show all articles of each command
    // each="article : ${commande.articles}" th:text="${article.intitule}"
    // Use bootstrap
}
