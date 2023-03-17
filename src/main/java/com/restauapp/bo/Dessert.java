package com.restauapp.bo;

import com.restauapp.bo.Article;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import lombok.Data;

@Entity
@Data
@DiscriminatorValue("Dessert")
public class Dessert extends Article {
    @Override
    public String toString() {
        return super.toString();
    }

}