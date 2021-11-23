package org.souldrake.filmmanager.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.souldrake.filmmanager.TestData;
import org.souldrake.filmmanager.config.AppConfig;
import org.souldrake.filmmanager.config.PersistenceConfig;
import org.souldrake.filmmanager.model.Film;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.SqlConfig;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;
import java.util.NoSuchElementException;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThrows;
import static org.souldrake.filmmanager.TestData.*;

@ContextConfiguration(classes = {AppConfig.class, PersistenceConfig.class})
@RunWith(SpringRunner.class)
@Sql(scripts = "classpath:database/populateDB.sql", config = @SqlConfig(encoding = "UTF-8"))
public class FilmServiceTest {
    @Autowired
    FilmService filmService;

    @Test
    public void findAll() {
        List<Film> films = filmService.findAll();
        assertThat(films).isEqualTo(TestData.FILMS);
    }

    @Test
    public void get() {
        Film film = filmService.get(FILM_ID);
        assertEquals(film, FILM1);
    }

    @Test
    public void getNotFound() {
        assertThrows(NoSuchElementException.class, () -> filmService.get(NOT_FOUND_ID));
    }

    @Test
    public void create() {
        Film newFilm = getNew();
        Film created = filmService.create(getNew());
        Integer newId = created.getId();
        newFilm.setId(newId);
        assertEquals(created, newFilm);
        assertEquals(filmService.get(newId), newFilm);
    }

    @Test
    public void update() {
        Film updated = filmService.update(getUpdated());
        assertEquals(filmService.get(FILM_ID), updated);
    }

    @Test
    public void updateNotFound() {
        assertThrows(NoSuchElementException.class, () -> filmService.get(NOT_FOUND_ID));
    }

    @Test
    public void delete() {
        filmService.delete(FILM_ID);
        assertThrows(NoSuchElementException.class, () -> filmService.get(FILM_ID));
    }

    @Test
    public void deleteNotFound() {
        assertThrows(EmptyResultDataAccessException.class, () -> filmService.delete(NOT_FOUND_ID));
    }
}