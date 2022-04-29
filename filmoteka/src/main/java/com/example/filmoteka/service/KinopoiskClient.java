package com.example.filmoteka.service;

import com.example.filmoteka.dto.ParamDTO;
import com.example.filmoteka.model.Films;
import com.example.filmoteka.model.KinopoiskFilm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Component
public class KinopoiskClient {

    private final ParamDTO paramDTO;
    @Autowired
    public KinopoiskClient(ParamDTO paramDTO) {
        this.paramDTO = paramDTO;
    }


    public List<KinopoiskFilm> getFilms() throws URISyntaxException {
        Optional.ofNullable(paramDTO).map(ParamDTO::getOrder).isPresent(ord -> );
        Optional.ofNullable(paramDTO).map(ParamDTO::getType).isPresent(tp -> );
        Optional.ofNullable(paramDTO).map(ParamDTO::getRatingFrom).isPresent(rfr -> );
        Optional.ofNullable(paramDTO).map(ParamDTO::getRatingTo).isPresent(rto -> );
        Optional.ofNullable(paramDTO).map(ParamDTO::getYearFrom).isPresent(yfr -> );
        Optional.ofNullable(paramDTO).map(ParamDTO::getYearTo).isPresent(yto -> );
        Optional.ofNullable(paramDTO).map(ParamDTO::getPage).isPresent(pg -> );


        RestTemplate restTemplate = new RestTemplate();
        String url = "https://kinopoiskapiunofficial.tech/api/v2.2/films?order=RATING&type=FILM&ratingFrom=8&ratingTo=10&yearFrom=2000&yearTo=2001&page=1";
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
        headers.set("X-API-KEY", "f984ce4d-1e3c-47e2-8a75-d38f8e13a417");
        ResponseEntity<Films> films = restTemplate.exchange(RequestEntity.get(new URI(url)).headers(headers).build(), Films.class);
        return films.getBody().getItems();
    }
}
