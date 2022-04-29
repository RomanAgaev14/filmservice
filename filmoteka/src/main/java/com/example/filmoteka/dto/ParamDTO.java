package com.example.filmoteka.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Component
public class ParamDTO {
    String order;
    String type;
    int ratingFrom;
    int ratingTo;
    int yearFrom;
    int yearTo;
    int page;
}
