package com.restauapp.dal;

import com.restauapp.bo.Carte;
import org.springframework.data.repository.CrudRepository;

public interface CarteDAO extends CrudRepository<Carte, Long> {
}
