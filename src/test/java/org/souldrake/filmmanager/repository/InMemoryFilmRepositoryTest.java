package org.souldrake.filmmanager.repository;

import org.junit.Before;
import org.junit.Test;
import org.souldrake.filmmanager.TestData;
import org.souldrake.filmmanager.model.Film;

import java.util.List;

import static org.junit.Assert.*;
import static org.souldrake.filmmanager.TestData.*;

/**
 * Test class for testing in memory repository
 *
 * @author SoulDrake
 * @create 2021-11-01 10:13
 **/

public class InMemoryFilmRepositoryTest {
    private static FilmRepository repository;


    @Before
    public void setUp() {
        repository = new InMemoryFilmRepository();
    }

    @Test
    public void getAll() {
        List<Film> films = repository.getAll();
        assertEquals(films, TestData.FILMS);
    }

    @Test
    public void create() {
        Film newFilm = getNew();
        Film created = repository.create(getNew());
        Integer newId = created.getId();
        newFilm.setId(newId);
        assertEquals(created, newFilm);
        assertEquals(repository.get(newId), newFilm);
    }

    @Test
    public void update() {
        Film updated = getUpdated();
        repository.update(updated);
        assertEquals(repository.get(FILM_ID), updated);
    }

    @Test
    public void updateNotFound() {
        assertNull(repository.get(NOT_FOUND_ID));
    }

    @Test
    public void delete() {
        assertTrue(repository.delete(FILM_ID));
        assertNull(repository.get(FILM_ID));
    }

    @Test
    public void deleteNotFound() {
        assertFalse(repository.delete(NOT_FOUND_ID));
    }

    @Test
    public void get() {
        Film film = repository.get(FILM_ID);
        assertEquals(film, FILM1);
    }

    @Test
    public void getNotFound() {
        Film film = repository.get(NOT_FOUND_ID);
        assertNull(film);
    }
}