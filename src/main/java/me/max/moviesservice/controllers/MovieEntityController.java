package me.max.moviesservice.controllers;


import me.max.moviesservice.movie.MovieEntity;
import me.max.moviesservice.service.MovieEntityService;
import org.springframework.beans.factory.annotation.Autowired;
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


}
