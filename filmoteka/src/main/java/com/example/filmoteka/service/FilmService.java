package com.example.filmoteka.service;

import com.example.filmoteka.model.Film;
import com.example.filmoteka.model.Films;
import com.example.filmoteka.model.KinopoiskFilm;
import lombok.NonNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.net.URISyntaxException;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class FilmService {

    @Autowired
    private KinopoiskClient kinopoiskClient;

    public List<Film> findAll() throws URISyntaxException {
        return kinopoiskClient.getFilms().stream()
                .map(this::toFilm)
                .collect(Collectors.toList());
    }

    private Film toFilm(@NonNull KinopoiskFilm kinopoiskFilm){
        return new Film(1l,
                kinopoiskFilm.getKinopoiskId(),
                kinopoiskFilm.getRatingImdb(),
                kinopoiskFilm.getRatingKinopoisk(),
                kinopoiskFilm.getNameRu(),
                kinopoiskFilm.getYear(),
                kinopoiskFilm.getDescription());
    };
}
