package com.example.filmoteka.model;

import lombok.Data;

import java.util.List;
@Data
public class Films {
    private List<KinopoiskFilm> items;

    @Override
    public String toString() {
        return "Items{" +
                "items=" + items +
                '}';
    }
}
