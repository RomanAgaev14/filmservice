package com.example.filmoteka.service;

import com.example.filmoteka.model.Film;
import com.example.filmoteka.model.Films;
import com.example.filmoteka.model.KinopoiskFilm;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

@Component
public class KinopoiskClient {
    private RestTemplate restTemplate = new RestTemplate();

    public List<KinopoiskFilm> getFilms() throws URISyntaxException {
        String url = "https://kinopoiskapiunofficial.tech/api/v2.2/films?order=RATING&type=FILM&ratingFrom=8&ratingTo=10&yearFrom=2000&yearTo=2003&page=1";
        Films films = restTemplate.getForObject(new URI(url), Films.class);
        return films.getFilmList();
    }
}
