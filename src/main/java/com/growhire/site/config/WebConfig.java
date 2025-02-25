package com.growhire.site.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**") // Adjust mapping as needed
                .allowedOrigins("https://growhire.up.railway.app", "http://127.0.0.1:5500", "http://localhost:8080")
                .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS")
                .allowedHeaders("*")
                .allowCredentials(true);

        registry.addMapping("/**") // Adjust mapping as needed
                .allowedOrigins("https://itay-olivcovitz.up.railway.app")
                .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS")
                .allowedHeaders("*")
                .allowCredentials(true);
    }

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/growhire/**")
                // Notice 'projectwebsite/growhire/' and the trailing slash
                .addResourceLocations("classpath:/projectwebsite/growhire/");
    }

    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        // Forward /growhire to /growhire/index.html
        registry.addViewController("/growhire").setViewName("forward:/growhire/index.html");
        registry.addViewController("/growhire/").setViewName("forward:/growhire/index.html");
    }

}
