package me.max.moviesservice.service;

import me.max.moviesservice.movie.MovieEntity;
import me.max.moviesservice.repositories.MovieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by amxik on 23.04.2019.
 */


@Service
public class MovieEntityService {

    @Autowired
    private MovieRepository movieRepository;


    public MovieEntity createMovie(MovieEntity movieEntity){

        MovieEntity movie = movieRepository.save(movieEntity);
        return movie;
    }

    public MovieEntity updateMovie(MovieEntity movieEntity){
        MovieEntity movie = movieRepository.save(movieEntity);
        return movie;
    }

    public void deleteMovie(long id){
        movieRepository.deleteById(id);
    }

    public List<MovieEntity> getAll(){

        List<MovieEntity> list = new ArrayList<>();
        movieRepository.findAll().forEach(list::add);
        return list;

    }

    public MovieEntity getMovieById(long id){
        return movieRepository.findById(id).get();
    }

    public MovieEntity getMovieByTitle(String title){
        return movieRepository.findByTitle(title);
    }

    public List<MovieEntity> getMoviesByReleaseDate(Date releaseDate){
        return movieRepository.findAllByReliaseDate(releaseDate);
    }


}
