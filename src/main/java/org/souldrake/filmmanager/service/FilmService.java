package org.souldrake.filmmanager.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
    private final Logger log = LoggerFactory.getLogger(getClass());
    private final FilmRepository filmRepository;

    public FilmService(FilmRepository filmRepository) {
        log.debug("FilmRepository initialize");
        this.filmRepository = filmRepository;
    }

    public List<Film> findAll(Sort sort) {
        log.debug("get all films sort by {}", sort);
        return filmRepository.findAll(sort);
    }

    public List<Film> findAll() {
        log.debug("get all films with default sort");
        return findAll(Sort.by("year"));
    }

    public Film get(int id) {
        log.debug("get film with id {}", id);
        return filmRepository.findById(id).get();
    }

    @Transactional
    public Film create(Film film) {
        log.debug("create new film {}", film);
        return filmRepository.save(film);
    }

    @Transactional
    public Film update(Film film) {
        log.debug("update film {}", film);
        return filmRepository.save(film);
    }

    @Transactional
    public void delete(int id) {
        log.debug("delete film with id {}", id);
        filmRepository.deleteById(id);
    }
}
