package com.example.filmoteka.controller;

import com.example.filmoteka.dto.ParamDTO;
import com.example.filmoteka.model.Film;
import com.example.filmoteka.service.FilmService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.net.URISyntaxException;
import java.util.List;


@RestController
@RequestMapping("/films")
public class FilmController {

    private final FilmService filmService;
    @Autowired
    public FilmController(FilmService filmService) {
        this.filmService = filmService;
    }

    @GetMapping
    public List<Film> getWithoutRequestParam(ParamDTO paramDTO) throws URISyntaxException {
        return filmService.findAll(paramDTO);
    }

    @GetMapping(value = "/{idDTO}")
    public Film getFilm(@PathVariable String idDTO) throws URISyntaxException {
        return filmService.findFilm(idDTO);
    }
}
