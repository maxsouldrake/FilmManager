package org.souldrake.filmmanager.repository;

import org.souldrake.filmmanager.model.Film;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Film repository interface
 *
 * @author SoulDrake
 * @create 2021-10-26 18:35
 **/

public interface FilmRepository extends JpaRepository<Film, Integer> {
}
