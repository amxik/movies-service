package me.max.moviesservice.controllers;


import me.max.moviesservice.movie.MovieEntity;
import me.max.moviesservice.service.MovieEntityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

/**
 * Created by amxik on 24.04.2019.
 */

@RestController
public class MovieEntityController {

    @Autowired
    private MovieEntityService movieEntityService;


 @RequestMapping(method = RequestMethod.GET,
 path = "movies", produces = {"application/json","application/xml"})
    public @ResponseBody List<MovieEntity> getMovies(@RequestParam(value = "offset",required = false,defaultValue = "0") int offset,
                                                     @RequestParam(value = "limit",required = false,defaultValue = "20") int limit){
       return movieEntityService.getAllMovies(offset,limit);
 }


@RequestMapping(method = RequestMethod.POST,path = "movies",
                produces = {"application/json","application/xml"})
    @ResponseStatus(code = HttpStatus.CREATED)
 public @ResponseBody MovieEntity createMovie(@RequestBody MovieEntity movieToCreate){
     return movieEntityService.createMovie(movieToCreate);
 }

    @RequestMapping(method = RequestMethod.GET, path = "movies/{id}",
            produces = {"application/json", "application/xml"})
    public @ResponseBody MovieEntity getMovie(@PathVariable("id") long movieId) {
        return movieEntityService.getMovieById(movieId);
    }

    @RequestMapping(method = RequestMethod.PUT, path = "movies/{id}",
                    produces = {"application/json","application/xml"})
    @ResponseStatus(code = HttpStatus.CREATED)
    public @ResponseBody
    MovieEntity replaceMovie(@PathVariable("id") long movieId,
                             @RequestBody MovieEntity newMovie){
     MovieEntity movieEntity = new MovieEntity();
     movieEntity.setId(movieId);
     movieEntity.setTitle(newMovie.getTitle());
     movieEntity.setGenre(newMovie.getGenre());
     movieEntity.setDescription(newMovie.getDescription());
     movieEntity.setDuration(newMovie.getDuration());
     movieEntity.setReleaseDate(newMovie.getReleaseDate());

     return movieEntityService.updateMovie(movieEntity);
 }

    @RequestMapping(method = RequestMethod.PATCH, path = "movies/{id}",
            produces = {"application/json","application/xml"})
    @ResponseStatus(code = HttpStatus.CREATED)
    public @ResponseBody
    MovieEntity updateMovie(@PathVariable("id") long movieId, @RequestBody
                            MovieEntity newMovie){
        MovieEntity movieFromStorage = movieEntityService.getMovieById(movieId);


        MovieEntity movieEntity = new MovieEntity();


        movieEntity.setId(movieId);
        if(newMovie.getTitle() != null){
        movieEntity.setTitle(newMovie.getTitle());
        } else {
            movieEntity.setTitle(movieFromStorage.getTitle());
        }
        if(newMovie.getGenre() != null) {
            movieEntity.setGenre(newMovie.getGenre());
        } else {
            movieEntity.setGenre(movieFromStorage.getGenre());
        }
        if(newMovie.getDescription() != null) {
            movieEntity.setDescription(newMovie.getDescription());
        } else {
            movieEntity.setDescription(movieFromStorage.getDescription());
        }
        if(newMovie.getDuration() != null) {
            movieEntity.setDuration(newMovie.getDuration());
        } else {
            movieEntity.setDuration(movieFromStorage.getDuration());
        }
        if(newMovie.getReleaseDate() != null) {
            movieEntity.setReleaseDate(newMovie.getReleaseDate());
        } else {
            movieEntity.setReleaseDate(movieFromStorage.getReleaseDate());
        }

        return movieEntityService.updateMovie(movieEntity);

    }


    @RequestMapping(method = RequestMethod.DELETE, path = "movies/{id}",
                    produces = {"application/json", "application/xml"})

    public void deleteMovie(@PathVariable("id") long movieId){
      movieEntityService.deleteMovie(movieId);
    }


    @RequestMapping(method = RequestMethod.GET, path = "movies/search/{title}",
            produces = {"application/json", "application/xml"})
    public @ResponseBody List<MovieEntity> searchMoviesByTitle(@PathVariable("title") String title,
                                                            @RequestParam(value = "offset",required = false,defaultValue = "0") int offset,
                                                            @RequestParam(value = "limit",required = false,defaultValue = "20") int limit){

     return movieEntityService.getMovieByTitle(title,offset,limit);
    }

    @RequestMapping(method = RequestMethod.GET, path = "movies/search/{releaseDate}",
                        produces = {"application/json","application/xml"})
    public @ResponseBody List<MovieEntity> searchMoviesByReleaseDate(@PathVariable("releaseDate") @DateTimeFormat(pattern="yyyy-MM-dd") Date releaseDate,
                                                                     @RequestParam(value = "offset",required = false,defaultValue = "0") int offset,
                                                                     @RequestParam(value = "limit",required = false,defaultValue = "20") int limit){

     return movieEntityService.getMoviesByReleaseDate(releaseDate,offset,limit);
    }

}
