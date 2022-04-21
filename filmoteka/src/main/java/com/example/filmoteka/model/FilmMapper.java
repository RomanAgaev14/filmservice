package com.example.filmoteka.model;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring", uses = KinopoiskFilm.class)
public interface FilmMapper{
    FilmMapper FILM_MAPPER = Mappers.getMapper(FilmMapper.class);
    Film toFilm(KinopoiskFilm kinopoiskFilm);
}
