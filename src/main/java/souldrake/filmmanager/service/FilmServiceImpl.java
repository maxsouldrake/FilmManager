package souldrake.filmmanager.service;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import souldrake.filmmanager.dao.FilmDAO;
import souldrake.filmmanager.model.Film;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.net.URLEncoder;
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
    public List<Film> allFilms(int page) {
        return filmDAO.allFilms(page);
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
    public int filmsCount() {
        return filmDAO.filmsCount();
    }

    @Override
    @Transactional
    public boolean checkTitle(String title) {
        return filmDAO.checkTitle(title);
    }

    @Override
    public Film getFilmFromWeb(String webSearchQuery) {
        Film film = new Film();
        String url = "https://www.kinopoisk.ru";
        Document doc;
        try {
            String queryValue = URLEncoder.encode(webSearchQuery, "windows-1251");
            doc = Jsoup.connect(url + "/index.php?kp_query=" + queryValue)
                    .userAgent("Mozilla/5.0 (Windows NT 10.0; Win64; x64) " +
                            "AppleWebKit/537.36 (KHTML, like Gecko) " +
                            "Chrome/71.0.3578.98 Safari/537.36 OPR/57.0.3098.116")
                    .referrer("http://localhost:8080/")
                    .get();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }

        String filmLink = "";
        try {
            filmLink = doc.select("div.most_wanted div.info a.js-serp-metrika").first().attr("href");
            doc = Jsoup.connect(url + filmLink)
                    .userAgent("Mozilla/5.0 (Windows NT 10.0; Win64; x64) " +
                            "AppleWebKit/537.36 (KHTML, like Gecko) " +
                            "Chrome/71.0.3578.98 Safari/537.36 OPR/57.0.3098.116")
                    .referrer("http://localhost:8080/")
                    .get();
        } catch (Exception e) {
            e.printStackTrace();
        }

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
            film.setCountry("mistake");
            film.setTitle(url + filmLink);
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
                String actor = doc.select("div#actorList.clearfix ul li a").get(i).text();
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
            URL website = new URL(url + filmPosterLink);
            try (InputStream in = website.openStream()) {
                Files.copy(in, Paths.get("D:\\other\\JAVA\\FilmManager\\src\\main\\webapp\\res\\posters\\" + filmPoster), StandardCopyOption.REPLACE_EXISTING);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
            film.setPoster(filmPoster);
        } catch (IOException e) {
            e.printStackTrace();
        }
        film.setDate(new Date(System.currentTimeMillis()));
        film.setPriority((byte)5);

        return film;
    }
}
