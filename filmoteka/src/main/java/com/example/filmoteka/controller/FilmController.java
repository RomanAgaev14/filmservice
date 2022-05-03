package com.example.filmoteka.controller;

import com.example.filmoteka.dto.ParamDTO;
import com.example.filmoteka.model.Film;
import com.example.filmoteka.service.FilmService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.Arrays;
import java.util.List;

@RestController
public class FilmController {

    private final FilmService filmService;
    @Autowired
    public FilmController(FilmService filmService) {
        this.filmService = filmService;
    }


    @RequestMapping("/films")
    public List<Film> getWithoutRequestParam(ParamDTO paramDTO) throws URISyntaxException {
        Arrays.asList(
                paramDTO.getOrder(),
                paramDTO.getType(),
                paramDTO.getRatingFrom(),
                paramDTO.getRatingTo(),
                paramDTO.getYearFrom(),
                paramDTO.getYearTo(),
                paramDTO.getPage());
        return filmService.findAll();
    }
}
