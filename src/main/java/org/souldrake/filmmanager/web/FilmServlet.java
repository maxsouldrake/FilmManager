package org.souldrake.filmmanager.web;

import org.souldrake.filmmanager.model.Film;
import org.souldrake.filmmanager.repository.FilmRepository;
import org.souldrake.filmmanager.repository.InMemoryFilmRepository;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Date;
import java.util.Objects;

/**
 * Film Servlet
 *
 * @author SoulDrake
 * @create 2021-10-27 13:28
 **/

@WebServlet(urlPatterns = "/films", name = "filmServlet")
public class FilmServlet  extends HttpServlet {
    private FilmRepository filmRepository;

    @Override
    public void init() {
        filmRepository = new InMemoryFilmRepository();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter("action");

        switch (action == null ? "all" : action) {
            case "delete" -> {
                int id = getId(req);
                filmRepository.delete(id);
                resp.sendRedirect("films");
            }
            case "create" -> {
                req.setAttribute("film", new Film(null, "newTitle", 2222, "newGenre", "newCountry",
                        (new Date()).toString(), (byte) 1, "jillian", "newDescription", "newPosterUrl"));
                req.getRequestDispatcher("/filmForm.jsp").forward(req, resp);
            }
            case "update" -> {
                req.setAttribute("film", filmRepository.get(getId(req)));
                req.getRequestDispatcher("/filmForm.jsp").forward(req, resp);
            }
            default -> {
                req.setAttribute("films", filmRepository.getAll());
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
                req.getParameter("date"),
                Byte.parseByte(req.getParameter("priority")),
                req.getParameter("actors"),
                req.getParameter("description"),
                req.getParameter("posterUrl"));

        if (film.isNew()) {
            filmRepository.create(film);
        } else {
            filmRepository.update(film);
        }
        resp.sendRedirect("films");
    }

    private int getId(HttpServletRequest request) {
        String paramId = Objects.requireNonNull(request.getParameter("id"));
        return Integer.parseInt(paramId);
    }
}
