package com.example.filmoteka.model;

import lombok.Data;

@Data
public class KinopoiskFilm {
    private Integer kinopoiskId;
    private float ratingImdb;
    private float ratingKinopoisk;
    private String nameRu;
    private int year;
    private String  description;
}
