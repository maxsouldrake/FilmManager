package org.souldrake.filmmanager.service;

import org.junit.jupiter.api.Test;
import org.souldrake.filmmanager.TestData;
import org.souldrake.filmmanager.config.AppConfig;
import org.souldrake.filmmanager.config.PersistenceConfig;
import org.souldrake.filmmanager.model.Film;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.SqlConfig;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;

import java.util.List;
import java.util.NoSuchElementException;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.souldrake.filmmanager.TestData.*;

@SpringJUnitConfig({AppConfig.class, PersistenceConfig.class})
@Sql(scripts = "classpath:database/populateDB.sql", config = @SqlConfig(encoding = "UTF-8"))
public class FilmServiceTest {
    @Autowired
    FilmService filmService;

    @Test
    void findAll() {
        List<Film> films = filmService.findAll();
        assertThat(films).isEqualTo(TestData.films);
    }

    @Test
    void get() {
        Film film = filmService.get(FILM_ID);
        assertEquals(film, film1);
    }

    @Test
    void getNotFound() {
        assertThrows(NoSuchElementException.class, () -> filmService.get(NOT_FOUND_ID));
    }

    @Test
    void create() {
        Film newFilm = getNew();
        Film created = filmService.create(getNew());
        Integer newId = created.getId();
        newFilm.setId(newId);
        assertEquals(created, newFilm);
        assertEquals(filmService.get(newId), newFilm);
    }

    @Test
    void update() {
        Film updated = filmService.update(getUpdated());
        assertEquals(filmService.get(FILM_ID), updated);
    }

    @Test
    void updateNotFound() {
        assertThrows(NoSuchElementException.class, () -> filmService.get(NOT_FOUND_ID));
    }

    @Test
    void delete() {
        filmService.delete(FILM_ID);
        assertThrows(NoSuchElementException.class, () -> filmService.get(FILM_ID));
    }

    @Test
    void deleteNotFound() {
        assertThrows(EmptyResultDataAccessException.class, () -> filmService.delete(NOT_FOUND_ID));
    }
}