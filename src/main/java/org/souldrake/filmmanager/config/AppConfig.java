package org.souldrake.filmmanager.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

/**
 * Spring configuration class
 *
 * @author SoulDrake
 * @create 2021-11-09 17:33
 **/

@Configuration
@ComponentScan("org.souldrake.**.service")
public class AppConfig {
}
