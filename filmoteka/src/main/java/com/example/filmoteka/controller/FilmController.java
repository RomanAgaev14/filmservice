package com.example.filmoteka.controller;

import com.example.filmoteka.dto.ParamDTO;
import com.example.filmoteka.model.Film;
import com.example.filmoteka.service.FilmService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("films")
public class FilmController {
    @Autowired
    private FilmService filmService;

    @GetMapping
    public List<Film> getFilms() throws URISyntaxException {
        List<Film> filmList = filmService.findAll();
        return filmService.saveAllFilms(filmList);
    }
    @RequestMapping("/getFilms")
    public List<Object> getWithoutRequestParam(@RequestParam(value = "films") String paramDTO)
        throws IOException {
            final ParamDTO param =
                    new ObjectMapper().readValue(paramDTO, ParamDTO.class);
        return Arrays.asList(
                param.getOrder(),
                param.getType(),
                param.getRatingFrom(),
                param.getRatingTo(),
                param.getYearFrom(),
                param.getYearTo(),
                param.getPage());
    }
}
