package com.example.filmoteka.model;

import lombok.Data;

import java.util.List;

@Data
public class Films {
    private List<KinopoiskFilm> filmList;
}
