package com.example.filmoteka.controller;

import com.example.filmoteka.criteria.HibernateUtil;
import com.example.filmoteka.model.Film;
import com.example.filmoteka.service.FilmService;
import org.hibernate.Session;
import org.hibernate.query.Query;
import org.hibernate.query.criteria.HibernateCriteriaBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.provider.HibernateUtils;
import org.springframework.web.bind.annotation.*;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.List;

@RestController
@RequestMapping("/fromDB")
public class ControllerFromDB {

    private final FilmService filmService;
    @Autowired
    public ControllerFromDB(FilmService filmService) {
        this.filmService = filmService;
    }

    @GetMapping(value = "/{id}")
    public Film getFilmById(@PathVariable Long id){
        return filmService.findFilmById(id);
    }

    @GetMapping
    public List<Film> getFilmsWithFilters(@RequestParam (value = "ratingImdbMin", required = false) Float ratingImdbMin,
                                          @RequestParam (value = "ratingImdbMax", required = false) Float ratingImdbMax,
                                          @RequestParam (value = "ratingKinopoiskMin", required = false) Float ratingKinopoiskMin,
                                          @RequestParam (value = "ratingKinopoiskMax", required = false) Float ratingKinopoiskMax,
                                          @RequestParam (value = "yearMin", required = false) Integer yearMin,
                                          @RequestParam (value = "yearMax", required = false) Integer yearMax){
        return filmService.getFilmsWithFilter(ratingImdbMin, ratingImdbMax, ratingKinopoiskMin,ratingKinopoiskMax, yearMin, yearMax);
    }



}
