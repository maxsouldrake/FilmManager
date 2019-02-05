package souldrake.filmmanager.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import souldrake.filmmanager.model.Film;
import souldrake.filmmanager.service.FilmService;

import java.util.List;

@Controller
public class FilmController {
    private int page;
    String titleSearch;
    String yearSearch;
    String genreSearch;
    String countrySearch;

    private FilmService filmService;

    @Autowired
    public void setFilmService(FilmService filmService) {
        this.filmService = filmService;
    }

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public ModelAndView allFilms(@RequestParam(defaultValue = "1") int page,
                                 @RequestParam(required = false, defaultValue = "") String titleSearch,
                                 @RequestParam(required = false, defaultValue = "") String yearSearch,
                                 @RequestParam(required = false, defaultValue = "") String genreSearch,
                                 @RequestParam(required = false, defaultValue = "") String countrySearch) {
        List<Film> films = filmService.allFilms(page, titleSearch, yearSearch, genreSearch, countrySearch);
        int filmsCount = filmService.filmsCount(titleSearch, yearSearch, genreSearch, countrySearch);
        int pagesCount = (filmsCount + 9)/10;
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("films");
        modelAndView.addObject("page", page);
        modelAndView.addObject("titleSearch", titleSearch);
        modelAndView.addObject("yearSearch", yearSearch);
        modelAndView.addObject("genreSearch", genreSearch);
        modelAndView.addObject("countrySearch", countrySearch);
        modelAndView.addObject("filmsList", films);
        modelAndView.addObject("filmsCount", filmsCount);
        modelAndView.addObject("pagesCount", pagesCount);
        this.page = page;
        this.titleSearch = titleSearch;
        this.yearSearch = yearSearch;
        this.genreSearch = genreSearch;
        this.countrySearch = countrySearch;
        return modelAndView;
    }

    @RequestMapping(value = "/info/{id}", method = RequestMethod.GET)
    public ModelAndView filmInfo(@PathVariable("id") int id) {
        Film film = filmService.getById(id);
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("filmInfo");
        modelAndView.addObject("film", film);
        return modelAndView;
    }

    @RequestMapping(value = "/add", method = RequestMethod.GET)
    public ModelAndView addPage(@ModelAttribute("webSearchQuery") String webSearchQuery,
                                @ModelAttribute("message") String message) {
        Film film = filmService.getFilmFromWeb(webSearchQuery);
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("editPage");
        modelAndView.addObject("film", film);
        return modelAndView;
    }

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public ModelAndView addFilm(@ModelAttribute("film") Film film) {
        ModelAndView modelAndView = new ModelAndView();
        if (film.getCountry().equals("mistake")) {
            modelAndView.setViewName("redirect:" + film.getTitle());
        } else
        if (filmService.checkTitle(film.getTitle())) {
            modelAndView.setViewName("redirect:/");
            modelAndView.addObject("page", page);
            filmService.add(film);
        } else {
            modelAndView.addObject("message","part with title \"" + film.getTitle() + "\" already exists");
            modelAndView.setViewName("redirect:/");
        }
        return modelAndView;
    }


    @RequestMapping(value = "/edit/{id}", method = RequestMethod.GET)
    public ModelAndView editPage(@PathVariable("id") int id,
                                 @ModelAttribute("message") String message) {
        Film film = filmService.getById(id);
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("editPage");
        modelAndView.addObject("film", film);
        return modelAndView;
    }

    @RequestMapping(value = "/edit", method = RequestMethod.POST)
    public ModelAndView editFilm(@ModelAttribute("film") Film film) {
        ModelAndView modelAndView = new ModelAndView();
        if (filmService.checkTitle(film.getTitle()) || filmService.getById(film.getId()).getTitle().equals(film.getTitle())) {
            modelAndView.setViewName("redirect:/");
            modelAndView.addObject("page", page);
            filmService.edit(film);
        } else {
            modelAndView.addObject("message","part with title \"" + film.getTitle() + "\" already exists");
            modelAndView.setViewName("redirect:/edit/" +  + film.getId());
        }
        return modelAndView;
    }

    @RequestMapping(value="/delete/{id}", method = RequestMethod.GET)
    public ModelAndView deleteFilm(@PathVariable("id") int id) {
        ModelAndView modelAndView = new ModelAndView();
        int filmsCount = filmService.filmsCount(titleSearch, yearSearch, genreSearch, countrySearch);
        int page = ((filmsCount - 1) % 10 == 0 && filmsCount > 10 && this.page == (filmsCount + 9)/10) ?
                this.page - 1 : this.page;
        modelAndView.setViewName("redirect:/");
        modelAndView.addObject("page", page);
        Film film = filmService.getById(id);
        filmService.delete(film);
        return modelAndView;
    }
}
