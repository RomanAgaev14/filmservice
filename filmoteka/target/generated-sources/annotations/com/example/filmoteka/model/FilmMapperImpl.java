package com.example.filmoteka.model;

import javax.annotation.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2022-04-21T18:42:48+0300",
    comments = "version: 1.4.2.Final, compiler: javac, environment: Java 11.0.4 (Oracle Corporation)"
)
@Component
public class FilmMapperImpl implements FilmMapper {

    @Override
    public Film toFilm(KinopoiskFilm kinopoiskFilm) {
        if ( kinopoiskFilm == null ) {
            return null;
        }

        Film film = new Film();

        film.setKinopoiskId( kinopoiskFilm.getKinopoiskId() );
        film.setRatingImdb( kinopoiskFilm.getRatingImdb() );
        film.setRatingKinopoisk( kinopoiskFilm.getRatingKinopoisk() );
        film.setNameRu( kinopoiskFilm.getNameRu() );
        film.setYear( kinopoiskFilm.getYear() );
        film.setDescription( kinopoiskFilm.getDescription() );

        return film;
    }
}
