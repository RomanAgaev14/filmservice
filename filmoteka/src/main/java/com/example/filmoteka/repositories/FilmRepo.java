package com.example.filmoteka.repositories;

import com.example.filmoteka.model.Film;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FilmRepo extends JpaRepository<Film, Long> {
    @Override
    List<Film> findAll();
}
