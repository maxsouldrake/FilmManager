package org.souldrake.filmmanager;

import org.souldrake.filmmanager.model.Film;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

/**
 * Data class for testing
 *
 * @author SoulDrake
 * @create 2021-11-01 10:13
 **/

public class TestData {
    public static final int FILM_ID = 1;
    public static final int NOT_FOUND_ID = 30;

    public static final Film FILM1 = new Film(FILM_ID, "title1", 2001, "genre1", "country1",
            LocalDate.of(2011, 1, 1), (byte) 1, "actors1", "description1", "posterUrl1");
    public static final Film FILM2 = new Film(FILM_ID + 1, "title2", 2002, "genre2", "country2",
            LocalDate.of(2012, 2, 2), (byte) 2, "actors2", "description2", "posterUrl2");
    public static final Film FILM3 = new Film(FILM_ID + 2, "title3", 2003, "genre3", "country3",
            LocalDate.of(2013, 3, 3), (byte) 3, "actors3", "description3", "posterUrl3");
    public static final Film FILM4 = new Film(FILM_ID + 3, "title4", 2004, "genre4", "country4",
            LocalDate.of(2014, 4, 4), (byte) 4, "actors4", "description4", "posterUrl4");
    public static final Film FILM5 = new Film(FILM_ID + 4, "title5", 2005, "genre5", "country5",
            LocalDate.of(2015, 5, 5), (byte) 5, "actors5", "description5", "posterUrl5");


    public static final List<Film> FILMS = Arrays.asList(FILM1, FILM2, FILM3, FILM4, FILM5);

    public static Film getNew() {
        return new Film(null, "newTitle", 1999, "newGenre", "newCountry", LocalDate.of(2009, 12, 12),
                (byte) 7, "newActors", "newDesc", "newPoster");
    }

    public static Film getUpdated() {
        Film updated = new Film(FILM_ID, FILM1.getTitle(), FILM1.getYear(), FILM1.getGenre(), FILM1.getCountry(),
                FILM1.getDate(), FILM1.getPriority(), FILM1.getActors(), FILM1.getDescription(), FILM1.getPosterUrl());
        updated.setTitle("updatedTitle");
        updated.setYear(2555);
        updated.setGenre("updGenre");
        updated.setCountry("updCountry");
        updated.setDate(LocalDate.of(2022, 2, 2));
        updated.setPriority((byte) 6);
        updated.setActors("updActors");
        updated.setDescription("updDescription");
        updated.setPosterUrl("updPoster");
        updated.setDescription("Updated");
        return updated;
    }

}
