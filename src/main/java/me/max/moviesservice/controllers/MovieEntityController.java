package me.max.moviesservice.controllers;


import me.max.moviesservice.movie.MovieEntity;
import me.max.moviesservice.service.MovieEntityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

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


 




}
