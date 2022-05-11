package com.example.filmoteka;

import com.example.filmoteka.repositories.FilmRepo;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
public class FilmotekaApplication {

	public static void main(String[] args) {
		SpringApplication.run(FilmotekaApplication.class, args);
	}

}
