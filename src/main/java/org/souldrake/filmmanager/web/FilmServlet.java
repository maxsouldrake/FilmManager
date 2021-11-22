package org.souldrake.filmmanager.web;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.souldrake.filmmanager.config.PersistenceConfig;
import org.souldrake.filmmanager.config.SpringApp;
import org.souldrake.filmmanager.model.Film;
import org.souldrake.filmmanager.service.FilmService;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.data.domain.Sort;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDate;
import java.util.Objects;

/**
 * Film Servlet
 *
 * @author SoulDrake
 * @create 2021-10-27 13:28
 **/

@WebServlet(urlPatterns = "/films", name = "filmServlet")
public class FilmServlet extends HttpServlet {
    private final Logger log = LoggerFactory.getLogger(getClass());

    private ConfigurableApplicationContext applicationContext;

    private FilmService filmService;


    @Override
    public void init() {
        applicationContext = new AnnotationConfigApplicationContext(SpringApp.class, PersistenceConfig.class);
        filmService = applicationContext.getBean(FilmService.class);
    }

    @Override
    public void destroy() {
        applicationContext.close();
        super.destroy();
    }

    @Override
    @Transactional
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter("action");
        switch (action == null ? "all" : action) {
            case "delete" -> {
                log.debug("delete film with id {}", getId(req));
                filmService.delete(getId(req));
                resp.sendRedirect("films");
            }
            case "create" -> {
                log.debug("get form for new film");
                req.setAttribute("film", new Film(null, "newTitle", 2222, "newGenre", "newCountry",
                        LocalDate.now(), (byte) 1, "jillian", "newDescription", "newPosterUrl"));
                req.getRequestDispatcher("/filmForm.jsp").forward(req, resp);
            }
            case "update" -> {
                log.debug("get form for update film with id {}", getId(req));
                Film film = filmService.get(getId(req));
                log.debug("get film - {}", film);
                req.setAttribute("film", filmService.get(getId(req)));
                req.getRequestDispatcher("/filmForm.jsp").forward(req, resp);
            }
            default -> {
                log.debug("get all films");
                req.setAttribute("films", filmService.findAll(Sort.by("year")));
                req.getRequestDispatcher("/films.jsp").forward(req, resp);
            }
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        req.setCharacterEncoding("UTF-8");
        String id = req.getParameter("id");

        Film film = new Film(id.isEmpty() ? null : Integer.valueOf(id),
                req.getParameter("title"),
                Integer.parseInt(req.getParameter("year")),
                req.getParameter("genre"),
                req.getParameter("country"),
                LocalDate.parse(req.getParameter("date")),
                Byte.parseByte(req.getParameter("priority")),
                req.getParameter("actors"),
                req.getParameter("description"),
                req.getParameter("posterUrl"));

        if (film.isNew()) {
            log.debug("create new film");
            filmService.create(film);
        } else {
            log.debug("update film with id {}", getId(req));
            filmService.update(film);
        }
        resp.sendRedirect("films");
    }

    private int getId(HttpServletRequest request) {
        String paramId = Objects.requireNonNull(request.getParameter("id"));
        return Integer.parseInt(paramId);
    }
}
