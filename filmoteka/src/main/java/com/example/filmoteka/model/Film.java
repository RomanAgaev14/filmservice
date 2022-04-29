package com.example.filmoteka.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "films")
public class Film {
 @Id
 @GeneratedValue(strategy = GenerationType.IDENTITY)
 @PrimaryKeyJoinColumn
 private long id;

 private int kinopoiskId;
 private float ratingImdb;
 private float ratingKinopoisk;
 private String nameRu;
 private int year;
 private String  description;
}
