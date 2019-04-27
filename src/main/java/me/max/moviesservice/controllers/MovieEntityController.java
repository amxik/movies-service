package me.max.moviesservice.controllers;


import me.max.moviesservice.dto.MovieDTO;
import me.max.moviesservice.service.MovieEntityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.Min;
import java.util.Date;
import java.util.List;

/**
 * Created by amxik on 24.04.2019.
 */

@RestController
@RequestMapping("/movies")
public class MovieEntityController {

    @Autowired
    private MovieEntityService movieEntityService;


    @RequestMapping(method = RequestMethod.GET,
            produces = {"application/json", "application/xml"})
    public @ResponseBody
    List<me.max.moviesservice.dto.MovieDTO> getMovies(@RequestParam(value = "offset", required = false, defaultValue = "0") int offset,
                                                      @RequestParam(value = "limit", required = false, defaultValue = "20") int limit) {
        return movieEntityService.getAllMovies(offset, limit);
    }


    @RequestMapping(method = RequestMethod.POST,
            produces = {"application/json", "application/xml"})
    @ResponseStatus(code = HttpStatus.CREATED)
    public @ResponseBody
    MovieDTO createMovie(@Valid @RequestBody MovieDTO movieToCreate) {
        return movieEntityService.createMovie(movieToCreate);
    }

    @RequestMapping(method = RequestMethod.GET, value = "/{id}",
            produces = {"application/json", "application/xml"})
    public @ResponseBody
    MovieDTO getMovie(@Valid @Min(0) @PathVariable("id") long movieId) {
        return movieEntityService.getMovieById(movieId);
    }

    @RequestMapping(method = RequestMethod.PUT, value = "/{id}",
            produces = {"application/json", "application/xml"})
    @ResponseStatus(code = HttpStatus.CREATED)
    public @ResponseBody
    MovieDTO replaceMovie(@Valid @Min(0) @PathVariable("id") long movieId,
                          @Valid @RequestBody MovieDTO newMovie) {
        MovieDTO movieDTO = new MovieDTO();
        movieDTO.setId(movieId);
        movieDTO.setTitle(newMovie.getTitle());
        movieDTO.setGenre(newMovie.getGenre());
        movieDTO.setDescription(newMovie.getDescription());
        movieDTO.setDuration(newMovie.getDuration());
        movieDTO.setReleaseDate(newMovie.getReleaseDate());
        return movieEntityService.updateMovie(movieDTO);
    }

    @RequestMapping(method = RequestMethod.PATCH, value = "/{id}",
            produces = {"application/json", "application/xml"})
    @ResponseStatus(code = HttpStatus.CREATED)
    public @ResponseBody
    MovieDTO updateMovie(@Valid @Min(0) @PathVariable("id") long movieId, @Valid @RequestBody
            MovieDTO newMovie) {
        MovieDTO movieFromStorage = movieEntityService.getMovieById(movieId);


        MovieDTO movieDTO = new MovieDTO();


        movieDTO.setId(movieId);

        if (newMovie.getTitle() != null) {
            movieDTO.setTitle(newMovie.getTitle());
        } else {
            movieDTO.setTitle(movieFromStorage.getTitle());
        }
        if (newMovie.getGenre() != null) {
            movieDTO.setGenre(newMovie.getGenre());
        } else {
            movieDTO.setGenre(movieFromStorage.getGenre());
        }
        if (newMovie.getDescription() != null) {
            movieDTO.setDescription(newMovie.getDescription());
        } else {
            movieDTO.setDescription(movieFromStorage.getDescription());
        }
        if (newMovie.getDuration() != null) {
            movieDTO.setDuration(newMovie.getDuration());
        } else {
            movieDTO.setDuration(movieFromStorage.getDuration());
        }
        if (newMovie.getReleaseDate() != null) {
            movieDTO.setReleaseDate(newMovie.getReleaseDate());
        } else {
            movieDTO.setReleaseDate(movieFromStorage.getReleaseDate());
        }

        return movieEntityService.updateMovie(movieDTO);

    }


    @RequestMapping(method = RequestMethod.DELETE, value = "/{id}",
            produces = {"application/json", "application/xml"})
    @ResponseStatus(code = HttpStatus.NO_CONTENT)
    public void deleteMovie(@Valid @Min(0) @PathVariable("id") long movieId) {
        movieEntityService.deleteMovie(movieId);
    }


    @RequestMapping(method = RequestMethod.GET, value = "/searchbytitle/{title}",
            produces = {"application/json", "application/xml"})
    public @ResponseBody
    List<MovieDTO> searchMoviesByTitle(@Valid @PathVariable("title") String title,
                                       @RequestParam(value = "offset", required = false, defaultValue = "0") int offset,
                                       @RequestParam(value = "limit", required = false, defaultValue = "20") int limit) {

        return movieEntityService.getMovieByTitle(title, offset, limit);
    }

    @RequestMapping(method = RequestMethod.GET, value = "/searchbydate/{releaseDate}",
            produces = {"application/json", "application/xml"})
    public @ResponseBody
    List<MovieDTO> searchMoviesByReleaseDate(@Valid @PathVariable("releaseDate") @DateTimeFormat(pattern = "yyyy-MM-dd") Date releaseDate,
                                             @RequestParam(value = "offset", required = false, defaultValue = "0") int offset,
                                             @RequestParam(value = "limit", required = false, defaultValue = "20") int limit) {

        return movieEntityService.getMoviesByReleaseDate(releaseDate, offset, limit);
    }

}