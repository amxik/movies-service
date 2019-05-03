package me.max.moviesservice.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Created by amxik on 30.04.2019.
 */
@ResponseStatus(code = HttpStatus.NOT_FOUND, reason = "Movie not found")
public class MovieNotFoundException extends Exception {

    public MovieNotFoundException(long id) {
        super("Movie by " + id +" doesn't exist");
    }
}
