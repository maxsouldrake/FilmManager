package souldrake.filmmanager.service;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import souldrake.filmmanager.dao.FilmDAO;
import souldrake.filmmanager.model.Film;

import java.io.FileNotFoundException;
import java.io.InputStream;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.sql.Date;
import java.util.List;

@Service
public class FilmServiceImpl implements FilmService {

    private FilmDAO filmDAO;

    @Autowired
    public void setFilmDAO(FilmDAO filmDAO) {
        this.filmDAO = filmDAO;
    }

    @Override
    @Transactional
    public List<Film> allFilms(int page,
                               String titleSearch, String yearSearch, String genreSearch, String countrySearch,
                               String sortBy, String sortDirection) {
        return filmDAO.allFilms(page, titleSearch, yearSearch, genreSearch, countrySearch, sortBy, sortDirection);
    }

    @Override
    @Transactional
    public void add(Film film) {
        filmDAO.add(film);
    }

    @Override
    @Transactional
    public void delete(Film film) {
        filmDAO.delete(film);
    }

    @Override
    @Transactional
    public void edit(Film film) {
        filmDAO.edit(film);
    }

    @Override
    @Transactional
    public Film getById(int id) {
        return filmDAO.getById(id);
    }

    @Override
    @Transactional
    public Film getRandomFilm() {
        return filmDAO.getRandomFilm();
    }

    @Override
    @Transactional
    public int filmsCount(String titleSearch, String yearSearch, String genreSearch, String countrySearch) {
        return filmDAO.filmsCount(titleSearch, yearSearch, genreSearch, countrySearch);
    }

    @Override
    @Transactional
    public int allFilmsCount() {
        return filmDAO.allFilmsCount();
    }

    @Override
    @Transactional
    public boolean isUnique(String title, short year) {
        return filmDAO.isUnique(title, year);
    }

    @Override
    public Film getFilmFromWeb(String webSearchQuery) {
        Document doc;
        String url = "http://www.google.com";
        try {
            doc = Jsoup.connect(url + "/search?q=кинопоиск " + webSearchQuery)
                    .userAgent("Mozilla/5.0 (Windows NT 10.0; Win64; x64) " +
                            "AppleWebKit/537.36 (KHTML, like Gecko) Chrome/71.0.3578.98 Safari/537.36")
                    .referrer(url)
                    .get();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }

        String filmLink;
        try {
            filmLink = doc.select("div.rc div.r a").first().attr("href");
            doc = Jsoup.connect(filmLink)
                    .userAgent("Mozilla/5.0 (Windows NT 10.0; Win64; x64) " +
                            "AppleWebKit/537.36 (KHTML, like Gecko) Chrome/71.0.3578.98 Safari/537.36")
                    .referrer(url)
                    .get();
        } catch (Exception e) {
            e.printStackTrace();
        }

        Film film = new Film();
        try {
            String filmTitle = doc.select("h1.moviename-big").text();
            film.setTitle(filmTitle);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }

        try {
            String filmYear = doc.select("td.type:contains(год)").first().parent().select("a").first().text();
            film.setYear(Short.parseShort(filmYear));
        } catch (Exception e) {
            e.printStackTrace();
            film.setTitle("mistake");
            film. setDescription("Кажется что-то пошло не так");
            return film;
        }
        try {
            String filmCountry = doc.select("td.type:contains(страна)").first().parent().select("a").first().text();
            film.setCountry(filmCountry);
        } catch (Exception e) {
            e.printStackTrace();
        }
        try {
            String filmGenre = doc.select("td.type:contains(жанр)").first().parent().select("a").first().text();
            film.setGenre(filmGenre);
        } catch (Exception e) {
            e.printStackTrace();
        }
        try {
            StringBuilder stringBuilder = new StringBuilder();
            for (int i = 0; i < 5; i++) {
                String actor;
                try {
                    actor = doc.select("div#actorList.clearfix ul li a").get(i).text();
                } catch (Exception e) {
                    break;
                }
                stringBuilder.append(actor);
                if (i < 4 && !actor.equals("")) {
                    stringBuilder.append(", ");
                }
            }
            String filmActors = stringBuilder.toString();
            film.setActors(filmActors);
        } catch (Exception e) {
            e.printStackTrace();
        }
        try {
            String filmDescription = doc.select("div.brand_words.film-synopsys").first().text();
            film.setDescription(filmDescription);
        } catch (Exception e) {
            e.printStackTrace();
        }
        try {
            String filmPosterLink = doc.select("a.popupBigImage").first().attr("onclick").split("'")[1];
            String filmPoster = filmPosterLink.split("/")[filmPosterLink.split("/").length - 1];
            URL website = new URL("https://www.kinopoisk.ru" + filmPosterLink);
            try (InputStream in = website.openStream()) {
                Files.copy(in, Paths.get("D:\\other\\JAVA\\FilmManager\\src\\main\\webapp\\res\\posters\\" + filmPoster), StandardCopyOption.REPLACE_EXISTING);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
            film.setPoster(filmPoster);
        } catch (Exception e) {
            e.printStackTrace();
        }
        film.setDate(new Date(System.currentTimeMillis()));
        film.setPriority((byte)5);

        return film;
    }
}
