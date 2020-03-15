package souldrake.filmmanager.controller;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import souldrake.filmmanager.model.Film;
import souldrake.filmmanager.service.FilmService;

import java.util.List;

@Controller
public class FilmController {
    private static final Logger log = Logger.getLogger(FilmController.class);
    private int page;
    private String titleSearch;
    private String yearSearch;
    private String genreSearch;
    private String countrySearch;
    private String sortBy;
    private String sortDirection;

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
                                 @RequestParam(required = false, defaultValue = "") String countrySearch,
                                 @RequestParam(required = false, defaultValue = "id") String sortBy,
                                 @RequestParam(required = false, defaultValue = "asc") String sortDirection) {
        log.info("allFilms by searching terms: title - " + titleSearch +
                ", year - " + yearSearch + ", genre - " + genreSearch +
                ", country - " + countrySearch + ", sorted by " + sortBy +
                " " + sortDirection + ", page - " + page);
        List<Film> filmList = filmService.allFilms(page, titleSearch, yearSearch, genreSearch, countrySearch, sortBy, sortDirection);
        int filmsCount = filmService.filmsCount(titleSearch, yearSearch, genreSearch, countrySearch);
        int pagesCount = (filmsCount + 9)/10;
        int allFilmsCount = filmService.allFilmsCount();
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("films");
        modelAndView.addObject("page", page);
        modelAndView.addObject("filmList", filmList);
        modelAndView.addObject("filmsCount", filmsCount);
        modelAndView.addObject("allFilmsCount", allFilmsCount);
        modelAndView.addObject("pagesCount", pagesCount);
        modelAndView.addObject("sortBy", sortBy);
        modelAndView.addObject("sortDirection", sortDirection);
        if(!titleSearch.isEmpty()) modelAndView.addObject("titleSearch", titleSearch);
        if(!yearSearch.isEmpty()) modelAndView.addObject("yearSearch", yearSearch);
        if(!genreSearch.isEmpty()) modelAndView.addObject("genreSearch", genreSearch);
        if(!countrySearch.isEmpty()) modelAndView.addObject("countrySearch", countrySearch);
        if ("asc".equals(sortDirection)) {
            this.sortDirection = "desc";
        } else {
            this.sortDirection = "asc";
        }
        this.sortBy = sortBy;
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
        modelAndView.addObject("page", page);
        if(!titleSearch.isEmpty()) modelAndView.addObject("titleSearch", titleSearch);
        if(!yearSearch.isEmpty()) modelAndView.addObject("yearSearch", yearSearch);
        if(!genreSearch.isEmpty()) modelAndView.addObject("genreSearch", genreSearch);
        if(!countrySearch.isEmpty()) modelAndView.addObject("countrySearch", countrySearch);
        if(!sortBy.isEmpty()) modelAndView.addObject("sortBy", sortBy);
        if(!sortDirection.isEmpty()) modelAndView.addObject("sortDirection", sortDirection);
        return modelAndView;
    }

    @RequestMapping(value = "/film", method = RequestMethod.GET)
    public ModelAndView getRandomFilm() {
        Film film = filmService.getRandomFilm();
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
        if (filmService.isUnique(film.getTitle(), film.getYear())) {
            modelAndView.setViewName("redirect:/");
            modelAndView.addObject("page", page);
            if(!titleSearch.isEmpty()) modelAndView.addObject("titleSearch", titleSearch);
            if(!yearSearch.isEmpty()) modelAndView.addObject("yearSearch", yearSearch);
            if(!genreSearch.isEmpty()) modelAndView.addObject("genreSearch", genreSearch);
            if(!countrySearch.isEmpty()) modelAndView.addObject("countrySearch", countrySearch);
            if(!sortBy.isEmpty()) modelAndView.addObject("sortBy", sortBy);
            if(!sortDirection.isEmpty()) modelAndView.addObject("sortDirection", sortDirection);
            filmService.add(film);
        } else {
            modelAndView.addObject("message","film with title \"" + film.getTitle() + "\" already exists");
            modelAndView.setViewName("redirect:/add");
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
        if (filmService.isUnique(film.getTitle(), film.getYear()) || filmService.getById(film.getId()).getTitle().equals(film.getTitle())) {
            modelAndView.setViewName("redirect:/");
            modelAndView.addObject("page", page);
            if(!titleSearch.isEmpty()) modelAndView.addObject("titleSearch", titleSearch);
            if(!yearSearch.isEmpty()) modelAndView.addObject("yearSearch", yearSearch);
            if(!genreSearch.isEmpty()) modelAndView.addObject("genreSearch", genreSearch);
            if(!countrySearch.isEmpty()) modelAndView.addObject("countrySearch", countrySearch);
            if(!sortBy.isEmpty()) modelAndView.addObject("sortBy", sortBy);
            if(!sortDirection.isEmpty()) modelAndView.addObject("sortDirection", sortDirection);
            filmService.edit(film);
        } else {
            modelAndView.addObject("message","film with title \"" + film.getTitle() + "\" already exists");
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
        if(!titleSearch.isEmpty()) modelAndView.addObject("titleSearch", titleSearch);
        if(!yearSearch.isEmpty()) modelAndView.addObject("yearSearch", yearSearch);
        if(!genreSearch.isEmpty()) modelAndView.addObject("genreSearch", genreSearch);
        if(!countrySearch.isEmpty()) modelAndView.addObject("countrySearch", countrySearch);
        if(!sortBy.isEmpty()) modelAndView.addObject("sortBy", sortBy);
        if(!sortDirection.isEmpty()) modelAndView.addObject("sortDirection", sortDirection);
        Film film = filmService.getById(id);
        filmService.delete(film);
        return modelAndView;
    }
}
