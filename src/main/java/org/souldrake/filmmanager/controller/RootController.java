package org.souldrake.filmmanager.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author SoulDrake
 * @create 2021-12-06 10:34
 **/

@Controller
@RequestMapping("/")
public class RootController {
    private final Logger log = LoggerFactory.getLogger(getClass());

    @GetMapping
    public String root() {
        log.info("root");
        return "index";
    }
}
