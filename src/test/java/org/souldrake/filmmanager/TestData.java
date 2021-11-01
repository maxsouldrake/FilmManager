package org.souldrake.filmmanager;

import org.souldrake.filmmanager.model.Film;

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
    public static final int NOT_FOUND_ID = 10;

    public static final Film FILM1 = new Film(FILM_ID, "title1", 1, "genre1", "country1",
            "01011111", (byte) 1, "actors1", "description1", "posterUrl1");
    public static final Film FILM2 = new Film(FILM_ID + 1, "title2", 2, "genre2", "country2",
            "02021111", (byte) 2, "actors2", "description2", "posterUrl2");
    public static final Film FILM3 = new Film(FILM_ID + 2, "title3", 3, "genre3", "country3",
            "03031111", (byte) 3, "actors3", "description3", "posterUrl3");
    public static final Film FILM4 = new Film(FILM_ID + 3, "title4", 4, "genre4", "country4",
            "04041111", (byte) 4, "actors4", "description4", "posterUrl4");
    public static final Film FILM5 = new Film(FILM_ID + 4, "title5", 5, "genre5", "country5",
            "05051111", (byte) 5, "actors5", "description5", "posterUrl5");

    public static final List<Film> FILMS = Arrays.asList(FILM1, FILM2, FILM3, FILM4, FILM5);

    public static Film getNew() {
        return new Film(null, "newTitle", 3000, "newGenre", "newCountry", "newDate",
                (byte) 7, "newActors", "newDesc", "newPoster");
    }

    public static Film getUpdated() {
        Film updated = new Film(FILM_ID, FILM1.getTitle(), FILM1.getYear(), FILM1.getGenre(), FILM1.getCountry(),
                FILM1.getDate(), FILM1.getPriority(), FILM1.getActors(), FILM1.getDescription(), FILM1.getPosterUrl());
        updated.setTitle("updatedTitle");
        updated.setYear(2555);
        updated.setGenre("updGenre");
        updated.setCountry("updCountry");
        updated.setDate("updDate");
        updated.setPriority((byte) 6);
        updated.setActors("updActors");
        updated.setDescription("updDescription");
        updated.setPosterUrl("updPoster");
        updated.setDescription("Updated");
        return updated;
    }

}
