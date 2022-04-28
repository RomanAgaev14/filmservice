package com.example.filmoteka.model;

import org.mapstruct.Mapper;
import org.springframework.stereotype.Component;

@Mapper(componentModel = "spring", uses = KinopoiskFilm.class)
@Component
public interface FilmMapper{
    Film toFilm(KinopoiskFilm kinopoiskFilm);
}
