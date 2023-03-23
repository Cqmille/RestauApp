package com.restauapp.bo;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "carte")
public class Carte {

    @Id
    @GeneratedValue
    private Long id;

    @Column(length = 100, unique = true)
    private String titre;

    @Column(length = 255)
    private String descriptif;

    @Enumerated(EnumType.STRING)
    private Type type;

    @OneToMany(mappedBy = "carte", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<Article> articles = new ArrayList<>();

    @Override
    public String toString() {
        return "Carte{" +
                "id=" + id +
                ", titre='" + titre + '\'' +
                '}';
    }

}