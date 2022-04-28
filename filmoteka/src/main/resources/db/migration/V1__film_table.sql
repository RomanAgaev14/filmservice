CREATE TABLE films (
                       id INT PRIMARY KEY AUTO_INCREMENT,
                       kinopoiskId INT,
                       ratingImdb FLOAT,
                       ratingKinopoisk FLOAT,
                       nameRu VARCHAR(250),
                       year INT,
                       description VARCHAR(1000));