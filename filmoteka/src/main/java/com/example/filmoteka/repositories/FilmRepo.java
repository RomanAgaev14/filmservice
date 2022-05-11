package com.example.filmoteka.repositories;

import com.example.filmoteka.model.Film;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FilmRepo extends JpaRepository<Film, Long> {

    boolean existsByKinopoiskId(Integer kinopoiskId);

    Film findByKinopoiskId(Integer kinopoiskId);

    @Query("select f from Film f where f.id = :id")
    Film getFilmById(Long id);

    @Modifying
    @Query("update Film f set f.description = :description where f.kinopoiskId = :kinopoiskId")
    void updateByDescription(String description, Integer kinopoiskId);
}
