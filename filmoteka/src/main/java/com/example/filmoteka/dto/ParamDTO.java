package com.example.filmoteka.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ParamDTO {
    String order;
    String type;
    Integer ratingFrom;
    Integer ratingTo;
    Integer yearFrom;
    Integer yearTo;
    Integer page;
}
