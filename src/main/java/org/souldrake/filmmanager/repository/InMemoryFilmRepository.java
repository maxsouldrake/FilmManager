package org.souldrake.filmmanager.repository;

import org.souldrake.filmmanager.model.Film;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * In memory Film Repository implementation
 *
 * @author SoulDrake
 * @create 2021-10-27 12:46
 **/

@Repository
public class InMemoryFilmRepository implements FilmRepository {
    private final Map<Integer, Film> repository;
    private final AtomicInteger counter = new AtomicInteger(0);

    {
        Film film1 = new Film(counter.incrementAndGet(), "title1", 1, "genre1", "country1",
                "01011111", (byte) 1, "actors1", "description1", "posterUrl1");
        Film film2 = new Film(counter.incrementAndGet(), "title2", 2, "genre2", "country2",
                "02021111", (byte) 2, "actors2", "description2", "posterUrl2");
        Film film3 = new Film(counter.incrementAndGet(), "title3", 3, "genre3", "country3",
                "03031111", (byte) 3, "actors3", "description3", "posterUrl3");
        Film film4 = new Film(counter.incrementAndGet(), "title4", 4, "genre4", "country4",
                "04041111", (byte) 4, "actors4", "description4", "posterUrl4");
        Film film5 = new Film(counter.incrementAndGet(), "title5", 5, "genre5", "country5",
                "05051111", (byte) 5, "actors5", "description5", "posterUrl5");
        repository = Stream.of(film1, film2, film3, film4, film5).collect(Collectors.toMap(Film::getId, Function.identity()));
    }


    @Override
    public List<Film> getAll() {
        return repository.values().stream().toList();
    }

    @Override
    public Film create(Film film) {
        film.setId(counter.incrementAndGet());
        repository.put(film.getId(), film);
        return film;
    }

    @Override
    public Film update(Film film) {
        return repository.computeIfPresent(film.getId(), (id, olfFilm) -> film);
    }

    @Override
    public boolean delete(int id) {
        return repository.keySet().removeIf(Predicate.isEqual(id));
    }

    @Override
    public Film get(int id) {
        return repository.get(id);
    }

    public int getId() {
        return counter.incrementAndGet();
    }
}
