package me.max.moviesservice.service;

import me.max.moviesservice.movie.MovieEntity;
import me.max.moviesservice.repositories.MovieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
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

    public List<MovieEntity> getAllMovies(int offset, int limit){

        List<MovieEntity> list = new ArrayList<>();
        movieRepository.findAll(new PageRequest(offset, limit)).forEach(list::add);
        return list;

    }

    public MovieEntity getMovieById(long id){
        return movieRepository.findById(id).get();
    }

    public List<MovieEntity> getMovieByTitle(String title,  int offset, int limit){
        return movieRepository.findByTitleContaining(title, new PageRequest(offset, limit));
    }

    public List<MovieEntity> getMoviesByReleaseDate(Date releaseDate, int offset, int limit){
        return movieRepository.findAllByReleaseDate(releaseDate, new PageRequest(offset,limit));
    }


}
