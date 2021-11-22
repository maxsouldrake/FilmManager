package org.souldrake.filmmanager.service;

import org.souldrake.filmmanager.model.Film;
import org.souldrake.filmmanager.repository.FilmRepository;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Service class for films
 *
 * @author SoulDrake
 * @create 2021-11-19 14:30
 **/

@Service
@Transactional(readOnly = true)
public class FilmService {
    private final FilmRepository filmRepository;

    public FilmService(FilmRepository filmRepository) {
        this.filmRepository = filmRepository;
    }

    public List<Film> findAll(Sort sort) {
        return filmRepository.findAll(sort);
    }

    public Film get(int id) {
        return filmRepository.findById(id).get();
    }

    @Transactional
    public Film create(Film film) {
        return filmRepository.save(film);
    }

    @Transactional
    public Film update(Film film) {
        return filmRepository.save(film);
    }

    @Transactional
    public void delete(int id) {
        filmRepository.deleteById(id);
    }
}
