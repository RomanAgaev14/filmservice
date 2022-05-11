package com.example.filmoteka.service;

import com.example.filmoteka.dto.IdDTO;
import com.example.filmoteka.dto.ParamDTO;
import com.example.filmoteka.model.Films;
import com.example.filmoteka.model.KinopoiskFilm;
import lombok.extern.slf4j.Slf4j;
import lombok.var;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.*;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URISyntaxException;
import java.util.*;

@Slf4j
@Configuration
public class KinopoiskClient {

    String url = "https://kinopoiskapiunofficial.tech/api/v2.2/films/";
    String urlId = "https://kinopoiskapiunofficial.tech/api/v2.2/films/{id}";

    public String getUrl(ParamDTO paramDTO){
        UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(url);
        final var optionalParam = Optional.ofNullable(paramDTO);
        optionalParam.map(ParamDTO::getOrder).ifPresent(order -> builder.queryParam("order", order));
        optionalParam.map(ParamDTO::getType).ifPresent(tp -> builder.queryParam("type", tp));
        optionalParam.map(ParamDTO::getRatingFrom).ifPresent(rfr -> builder.queryParam("ratingFrom", rfr));
        optionalParam.map(ParamDTO::getRatingTo).ifPresent(rto -> builder.queryParam("ratingTo", rto));
        optionalParam.map(ParamDTO::getYearFrom).ifPresent(yfr -> builder.queryParam("yearFrom", yfr));
        optionalParam.map(ParamDTO::getYearTo).ifPresent(yto -> builder.queryParam("yearTo", yto));
        optionalParam.map(ParamDTO::getPage).ifPresent(pg -> builder.queryParam("page", pg));
        final var request = builder.build().toUriString();
        log.info("request to kinopoiskclient looks like {}", request);
        return request;
    }


    @Bean
    RestTemplate restTemplate(){
        return new RestTemplate();
    }

    public List<KinopoiskFilm> getFilms(ParamDTO paramDTO) throws URISyntaxException {

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
        headers.set("X-API-KEY", "f984ce4d-1e3c-47e2-8a75-d38f8e13a417");
        ResponseEntity<Films> films = restTemplate().exchange(RequestEntity.get(getUrl(paramDTO)).headers(headers).build(), Films.class);
        final var response = films.getBody().getItems();
        log.info("response to kinopoiskclient looks like {}", response);
        return response;
    }

    public KinopoiskFilm getFilm(String idDTO) throws URISyntaxException {

        Map<String, String> par = new HashMap<>();
        par.put("id", idDTO);

        UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(urlId);

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
        headers.set("X-API-KEY", "f984ce4d-1e3c-47e2-8a75-d38f8e13a417");

        ResponseEntity<KinopoiskFilm> film = restTemplate().exchange(RequestEntity.get(builder.buildAndExpand(par).toUri()).headers(headers).build(), KinopoiskFilm.class);

        final var response = film.getBody();
        log.info("response to FILM looks like {}", response);
        return response;
    }
}
