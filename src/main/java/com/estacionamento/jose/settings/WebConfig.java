package com.estacionamento.jose.settings;
import org.jetbrains.annotations.NotNull;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @author Eduardo Sganderla
 *
 * @since 1.0.0, 15/06/2023
 * @version 1.0.0
 */
@Configuration
@EnableWebMvc
@CrossOrigin("http://localhost:8080")
public class WebConfig implements WebMvcConfigurer {

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/api/brand")
                .allowedOrigins("http://localhost:8080/marca")
                .allowedMethods("GET, POST, PUT, DELETE")
                .allowCredentials(true);

        registry.addMapping("/api/model")
                .allowedOrigins("http://localhost:8080/modelo")
                .allowedMethods("GET, POST, PUT, DELETE")
                .allowCredentials(true);


        registry.addMapping("/api/movement")
                .allowedOrigins("http://localhost:8080/movimentacao")
                .allowedMethods("GET, POST, PUT, DELETE")
                .allowCredentials(true);


        registry.addMapping("/api/vehicle")
                .allowedOrigins("http://localhost:8080/veiculo")
                .allowedMethods("GET, POST, PUT, DELETE")
                .allowCredentials(true);


        registry.addMapping("/api/conductor")
                .allowedOrigins("http://localhost:8080/condutor")
                .allowedMethods("GET, POST, PUT, DELETE")
                .allowCredentials(true);
    }
}
