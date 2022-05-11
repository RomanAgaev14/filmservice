package com.example.filmoteka.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Objects;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "films", uniqueConstraints = @UniqueConstraint(columnNames = {"kinopoiskId"}))
public class Film {
 @Id
 @GeneratedValue(strategy = GenerationType.IDENTITY)
 @PrimaryKeyJoinColumn
 private long id;

 private Integer kinopoiskId;
 private float ratingImdb;
 private float ratingKinopoisk;
 private String nameRu;
 private int year;
 private String  description;

 @Override
 public boolean equals(Object o) {
  if (this == o) return true;
  if (o == null || getClass() != o.getClass()) return false;
  Film film = (Film) o;
  return id == film.id && kinopoiskId == film.kinopoiskId && Float.compare(film.ratingImdb, ratingImdb) == 0 && Float.compare(film.ratingKinopoisk, ratingKinopoisk) == 0 && year == film.year && Objects.equals(nameRu, film.nameRu) && Objects.equals(description, film.description);
 }

 @Override
 public int hashCode() {
  return Objects.hash(id, kinopoiskId, ratingImdb, ratingKinopoisk, nameRu, year, description);
 }
}
