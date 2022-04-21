package com.example.filmoteka;

import com.ulisesbocchio.jasyptspringboot.annotation.EnableEncryptableProperties;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@EnableEncryptableProperties
public class FilmotekaApplication {

	public static void main(String[] args) {
		SpringApplication.run(FilmotekaApplication.class, args);
	}

}
