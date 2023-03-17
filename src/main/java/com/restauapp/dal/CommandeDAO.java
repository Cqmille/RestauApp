package com.restauapp.dal;

import com.restauapp.bo.Commande;
import org.springframework.data.repository.CrudRepository;

public interface CommandeDAO extends CrudRepository<Commande, Long> {
}
