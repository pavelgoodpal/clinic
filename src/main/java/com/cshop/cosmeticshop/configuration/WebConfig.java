package com.cshop.cosmeticshop.configuration;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * Configuration class for pages without business logic
 *
 * @author Pave1Pal
 */
@Configuration
public class WebConfig implements WebMvcConfigurer {

    /**
     * Configuration method for matching URL address and view name.
     * For URL / page is index.
     * For URL /login page is login.
     *
     * @param registry ViewControllerRegistry
     */
    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/").setViewName("index");
        registry.addViewController("/login").setViewName("login");
    }
}
