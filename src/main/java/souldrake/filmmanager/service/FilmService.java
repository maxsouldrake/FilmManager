package souldrake.filmmanager.service;

import souldrake.filmmanager.model.Film;

import java.util.List;

public interface FilmService {
    List<Film> allFilms(int page, String titleSearch, String yearSearch, String genreSearch, String countrySearch);
    void add(Film film);
    void delete(Film film);
    void edit(Film film);
    Film getById(int id);

    int filmsCount(String titleSearch, String yearSearch, String genreSearch, String countrySearch);
    int allFilmsCount();

    boolean isUnique(String title, short year);

    Film getFilmFromWeb(String webSearchQuery);
}
