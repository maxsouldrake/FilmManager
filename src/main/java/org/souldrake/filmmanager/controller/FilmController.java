package org.souldrake.filmmanager.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.souldrake.filmmanager.model.Film;
import org.souldrake.filmmanager.service.FilmService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

/**
 * @author SoulDrake
 * @create 2021-12-06 10:19
 **/

@Controller
@RequestMapping("/films")
public class FilmController {
    private final Logger log = LoggerFactory.getLogger(getClass());

    private final FilmService filmService;

    public FilmController(FilmService filmService) {
        log.debug("FilmService initialize");
        this.filmService = filmService;
    }

    @GetMapping
    public String getAll(Model model) {
        log.info("get all films");
        List<Film> films = filmService.findAll();
        model.addAttribute("films", films);
        return "films";
    }

    @GetMapping("/create")
    public String getCreateForm(Model model) {
        log.info("get create film form");
        final Film film = new Film("", 2000, "", "", LocalDate.now(), (byte) 1, "", "", "");
        model.addAttribute("film", film);
        return "filmForm";
    }

    @GetMapping("/update/{id}")
    public String getUpdateForm(Model model, @PathVariable int id) {
        log.info("get update form for film with id {}", id);
        final Film film = filmService.get(id);
        model.addAttribute("film", film);
        return "filmForm";
    }

    @PostMapping
    public String createOrUpdate(@ModelAttribute Film film) {
        if (film.getId() == null) {
            log.info("create new film");
            filmService.create(film);
        } else {
            log.info("update film with id {}", film.getId());
            filmService.update(film);
        }
        return "redirect:/films";
    }


    @GetMapping("/delete/{id}")
    public String delete(@PathVariable("id") int id) {
        log.info("delete film with id {}", id);
        filmService.delete(id);
        return "redirect:/films";
    }
}
