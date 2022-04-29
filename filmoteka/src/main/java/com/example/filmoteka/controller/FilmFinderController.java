package com.example.filmoteka.controller;

import com.example.filmoteka.service.FilmService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("findfilm")
public class FilmFinderController {

    private final FilmService filmService;

    @Autowired
    public FilmFinderController(FilmService filmService) {
        this.filmService = filmService;
    }


}
