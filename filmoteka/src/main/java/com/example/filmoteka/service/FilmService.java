package com.example.filmoteka.service;

import com.example.filmoteka.model.Film;
import com.example.filmoteka.model.FilmMapper;
import com.example.filmoteka.repositories.FilmRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.net.URISyntaxException;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class FilmService {

    private final KinopoiskClient kinopoiskClient;
    private final FilmMapper filmMapper;
    private final FilmRepo filmRepo;

    @Autowired
    public FilmService(FilmMapper filmMapper, KinopoiskClient kinopoiskClient, FilmRepo filmRepo) {
        this.filmMapper = filmMapper;
        this.kinopoiskClient = kinopoiskClient;
        this.filmRepo = filmRepo;
    }

    public List<Film> saveAllFilms(List<Film> filmList){filmRepo.saveAll(filmList);
        return filmList;
    }

    public List<Film> findAll() throws URISyntaxException {
        return kinopoiskClient.getFilms().stream()
                .map(filmMapper::toFilm)
                .collect(Collectors.toList());
    }



}
