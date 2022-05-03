package com.example.filmoteka.service;

import com.example.filmoteka.dto.ParamDTO;
import com.example.filmoteka.model.Films;
import com.example.filmoteka.model.KinopoiskFilm;
import lombok.var;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Component
public class KinopoiskClient {

    ParamDTO paramDTO = new ParamDTO();

    String url = "https://kinopoiskapiunofficial.tech/api/v2.2/";

    public String getUrl(ParamDTO paramDTO){
        UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(url);
        builder.pathSegment("films");
        final var optionalParam = Optional.ofNullable(paramDTO);
        optionalParam.map(ParamDTO::getOrder).ifPresent(order -> builder.queryParam("order", order));
        optionalParam.map(ParamDTO::getType).ifPresent(tp -> builder.queryParam("type", tp));
        optionalParam.map(ParamDTO::getRatingFrom).ifPresent(rfr -> builder.queryParam("ratingFrom", rfr));
        optionalParam.map(ParamDTO::getRatingTo).ifPresent(rto -> builder.queryParam("ratingTo", rto));
        optionalParam.map(ParamDTO::getYearFrom).ifPresent(yfr -> builder.queryParam("yearFrom", yfr));
        optionalParam.map(ParamDTO::getYearTo).ifPresent(yto -> builder.queryParam("yearTo", yto));
        optionalParam.map(ParamDTO::getPage).ifPresent(pg -> builder.queryParam("page", pg));
        return builder.build().toUriString();
    }

    public List<KinopoiskFilm> getFilms() throws URISyntaxException {

        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
        headers.set("X-API-KEY", "f984ce4d-1e3c-47e2-8a75-d38f8e13a417");
        ResponseEntity<Films> films = restTemplate.exchange(RequestEntity.get(getUrl(paramDTO)).headers(headers).build(), Films.class);
        return films.getBody().getItems();
    }
}
