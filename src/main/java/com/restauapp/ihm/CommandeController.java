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
        List<Commande> actions = service.getAllCommandes();
        model.addAttribute("actions", actions);
        return "commande";
    }
}
