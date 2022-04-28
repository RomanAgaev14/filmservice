package com.example.filmoteka.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Film {
 @Id
 @GeneratedValue(strategy = GenerationType.AUTO)
 @PrimaryKeyJoinColumn
 private long id;

 private int kinopoiskId;
 private float ratingImdb;
 private float ratingKinopoisk;
 private String nameRu;
 private int year;
 private String  description;
}
