package com.restauapp.dal;

import com.restauapp.bo.Article;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface ArticleDAO extends CrudRepository<Article, Long> {
    List<Article> findByIntitule(String intitule);
}
