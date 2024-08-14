package edu.avada.course.configuration;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebAppConfig implements WebMvcConfigurer {
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        WebMvcConfigurer.super.addResourceHandlers(registry);
        registry.addResourceHandler("/pictures/**")
                .addResourceLocations("file:c:\\Repos\\Java\\Avada\\Task-20-Royal_House\\src\\main\\resources\\static\\pictures\\");
    }
}
