package com.example.filmoteka.controller;

import com.example.filmoteka.model.Film;
import com.example.filmoteka.service.FilmService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.net.URISyntaxException;
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
}
