package com.example.filmoteka.service;

import com.example.filmoteka.model.Films;
import com.example.filmoteka.model.KinopoiskFilm;
import org.springframework.http.*;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.Collections;
import java.util.List;

@Component
public class KinopoiskClient {

    public List<KinopoiskFilm> getFilms() throws URISyntaxException {
        RestTemplate restTemplate = new RestTemplate();
        String url = "https://kinopoiskapiunofficial.tech/api/v2.2/films?order=RATING&type=ALL&ratingFrom=8&ratingTo=10&yearFrom=2001&yearTo=2002&page=1";
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
        headers.set("X-API-KEY", "f984ce4d-1e3c-47e2-8a75-d38f8e13a417");
        ResponseEntity<Films> films = restTemplate.exchange(RequestEntity.get(new URI(url)).headers(headers).build(), Films.class);
        return films.getBody().getItems();
    }
}
