package com.restauapp.bo;

import com.restauapp.bo.Article;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import lombok.Data;

@Entity
@Data
@DiscriminatorValue("Entree")
public class Entree extends Article {
    @Override
    public String toString() {
        return super.toString();
    }

}