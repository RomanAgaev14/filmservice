package com.example.filmoteka.service;

import com.example.filmoteka.criteria.HibernateUtil;
import com.example.filmoteka.dto.ParamDTO;
import com.example.filmoteka.model.Film;
import com.example.filmoteka.model.FilmMapper;
import com.example.filmoteka.model.KinopoiskFilm;
import com.example.filmoteka.repositories.FilmRepo;
import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestParam;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Transactional
public class FilmService {

    private final KinopoiskClient kinopoiskClient;
    private final FilmMapper filmMapper;
    private final FilmRepo filmRepo;

    @Autowired
    public FilmService(FilmMapper filmMapper, KinopoiskClient kinopoiskClient, FilmRepo filmRepo) {
        this.filmMapper = filmMapper;
        this.kinopoiskClient = kinopoiskClient;
        this.filmRepo = filmRepo;
    }

    public Film findFilm(String idDTO) throws URISyntaxException {
        KinopoiskFilm film = kinopoiskClient.getFilm(idDTO);
        Film film1 = filmMapper.toFilm(film);
        if(!filmRepo.existsByKinopoiskId(film.getKinopoiskId())){
           return filmRepo.save(film1);
        }else {
             filmRepo.updateByDescription(film1.getDescription(), film1.getKinopoiskId());
        }
        return filmRepo.findByKinopoiskId(film1.getKinopoiskId());
    }



    public void saveOrSkip(Film film) {
        if (!filmRepo.existsByKinopoiskId(film.getKinopoiskId())) {
            filmRepo.save(film);
        }
    }

    public List<Film> findAll(ParamDTO paramDTO) throws URISyntaxException {
        return kinopoiskClient.getFilms(paramDTO).stream()
                .map(filmMapper::toFilm)
                .distinct()
                .peek(this::saveOrSkip)
                .collect(Collectors.toList());
    }

    public Film findFilmById(Long id){
        return filmRepo.getFilmById(id);
    }

    public List<Film> getFilmsWithFilter(Float ratingImdbMin, Float ratingImdbMax, Float ratingKinopoiskMin, Float ratingKinopoiskMax, Integer yearMin, Integer yearMax){
        Session session = HibernateUtil.getHibernateSession();
        CriteriaBuilder cb = session.getCriteriaBuilder();
        CriteriaQuery<Film> cr = cb.createQuery(Film.class);
        Root<Film> root = cr.from(Film.class);
        List<Predicate> predicateList = new ArrayList<>();

        if(ratingImdbMin != null && ratingImdbMax != null){
            predicateList.add(cb.between(root.get("ratingImdb"), ratingImdbMin, ratingImdbMax));
        }
        else if(ratingImdbMin != null && ratingImdbMax == null) {
            predicateList.add(cb.greaterThanOrEqualTo(root.get("ratingImdb"), ratingImdbMin));
        }
        else if(ratingImdbMin == null && ratingImdbMax != null){
            predicateList.add(cb.le(root.get("ratingImdb"), ratingImdbMax));
        }

        if(ratingKinopoiskMin != null && ratingKinopoiskMax != null){
            predicateList.add(cb.between(root.get("ratingKinopoisk"), ratingKinopoiskMin, ratingKinopoiskMax));
        }
        else if(ratingKinopoiskMin != null && ratingKinopoiskMax == null) {
            predicateList.add(cb.greaterThanOrEqualTo(root.get("ratingKinopoisk"), ratingKinopoiskMin));
        }
        else if(ratingKinopoiskMin == null && ratingKinopoiskMax != null){
            predicateList.add(cb.le(root.get("ratingKinopoisk"), ratingKinopoiskMax));
        }

        if(yearMin != null && yearMax != null){
            predicateList.add(cb.between(root.get("year"), yearMin, yearMax));
        }
        else if(yearMin != null && yearMax == null) {
            predicateList.add(cb.greaterThanOrEqualTo(root.get("year"), yearMin));
        }
        else if(yearMin == null && yearMax != null){
            predicateList.add(cb.le(root.get("year"), yearMax));
        }

        cr.select(root).where(cb.and(predicateList.toArray(new Predicate[0])));
        Query<Film> query = session.createQuery(cr);
        List<Film> list = query.getResultList();
        return list;
    }

}
