package com.example.filmoteka.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Film {
 @Id
 @GeneratedValue(strategy = GenerationType.AUTO)
 private long id;
 private int kinopoiskId;
 private float ratingImdb;
 private float ratingKinopoisk;
 private String nameRu;
 private int year;
 private String  description;
}
