package com.restauapp.dal;

import com.restauapp.bo.Article;
import org.springframework.data.repository.CrudRepository;

public interface ArticleDAO extends CrudRepository<Article, Long> {
}
