package org.souldrake.filmmanager.controller;

import org.junit.jupiter.api.Test;

import static org.souldrake.filmmanager.TestData.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

class FilmControllerTest extends AbstractControllerTest {

    @Test
    void getAll() throws Exception {
        mockMvc.perform(get("/films"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(view().name("films"))
                .andExpect(forwardedUrl("/WEB-INF/pages/films.jsp"))
                .andExpect(model().attribute("films", films));
    }

    @Test
    void getCreateForm() throws Exception {
        mockMvc.perform(get("/films/create"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(view().name("filmForm"))
                .andExpect(forwardedUrl("/WEB-INF/pages/filmForm.jsp"));
    }

    @Test
    void getUpdateForm() throws Exception {
        mockMvc.perform(get("/films/update/{1}", FILM_ID))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(view().name("filmForm"))
                .andExpect(forwardedUrl("/WEB-INF/pages/filmForm.jsp"))
                .andExpect(model().attribute("film", film1));
    }
}