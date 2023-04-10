package com.mediclick;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class MvcConfig implements WebMvcConfigurer {

    // Views access
    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController( "/" ).setViewName( "forward:home_principal.html" );
//        registry.addViewController( "/" ).setViewName( "forward:/login" );
        registry.addViewController( "/home.html" ).setViewName( "forward:/login" );
        registry.addViewController("/login").setViewName("login");
        registry.addViewController("/home.html").setViewName("home.html");
        registry.addViewController("/home_principal.html").setViewName("home_principal.html");
        registry.addViewController("/docs.html").setViewName("docs.html");
//        registry.addViewController( "/home_principal.html" ).setViewName( "forward:/logout" );
    }
}
