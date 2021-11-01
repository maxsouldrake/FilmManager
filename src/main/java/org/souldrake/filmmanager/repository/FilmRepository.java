package org.souldrake.filmmanager.repository;

import org.souldrake.filmmanager.model.Film;

import java.util.List;

/**
 * Film repository interface
 *
 * @author SoulDrake
 * @create 2021-10-26 18:35
 **/

public interface FilmRepository {
    List<Film> getAll();

    Film create(Film film);

    Film update(Film film);

    boolean delete(int id);

    Film get(int id);

    int getId();
}
