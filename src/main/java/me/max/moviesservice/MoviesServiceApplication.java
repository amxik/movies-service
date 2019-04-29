package me.max.moviesservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class MoviesServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(MoviesServiceApplication.class, args);
    }

}
