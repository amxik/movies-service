package me.max.moviesservice.service;

import me.max.moviesservice.dto.MovieDTO;
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


    public MovieDTO createMovie(MovieDTO movieDTO){
        MovieEntity movieEntity = toEntity(movieDTO);
        MovieEntity movie = movieRepository.save(movieEntity);
        return toDto(movie);
    }

    public MovieDTO updateMovie(MovieDTO movieDTO){
        MovieEntity movieEntity = toEntity(movieDTO);
        MovieEntity movie = movieRepository.save(movieEntity);
        return toDto(movie);
    }

    public void deleteMovie(long id){

        movieRepository.deleteById(id);
    }

    public List<MovieDTO> getAllMovies(int offset, int limit){
        List<MovieDTO> list = new ArrayList<>();
        toDtoList(movieRepository.findAll(new PageRequest(offset, limit)).getContent()).forEach(list::add);
        return list;

    }

    public MovieDTO getMovieById(long id){
        return toDto(movieRepository.findById(id).get());
    }

    public List<MovieDTO> getMovieByTitle(String title, int offset, int limit){
        return toDtoList(movieRepository.findByTitleContaining(title, new PageRequest(offset, limit)));
    }

    public List<MovieDTO> getMoviesByReleaseDate(Date releaseDate, int offset, int limit){
        return toDtoList(movieRepository.findAllByReleaseDate(releaseDate, new PageRequest(offset,limit)));
    }


    public static MovieDTO toDto(MovieEntity entity){
        MovieDTO dto = new MovieDTO();
        dto.setId(entity.getId());
        dto.setTitle(entity.getTitle());
        dto.setGenre(entity.getGenre());
        dto.setDescription(entity.getDescription());
        dto.setDuration(entity.getDuration());
        dto.setReleaseDate(entity.getReleaseDate());

        return dto;
    }

    public static MovieEntity toEntity(MovieDTO dto){
        MovieEntity movieEntity = new MovieEntity();
        movieEntity.setId(dto.getId());
        movieEntity.setTitle(dto.getTitle());
        movieEntity.setGenre(dto.getGenre());
        movieEntity.setDescription(dto.getDescription());
        movieEntity.setDuration(dto.getDuration());
        movieEntity.setReleaseDate(dto.getReleaseDate());
        return movieEntity;
    }

    public static List<MovieDTO> toDtoList(List<MovieEntity> list){
        List<MovieDTO> dtoList = new ArrayList<>();
        for (MovieEntity entity : list){
            dtoList.add(toDto(entity));
        }
        return dtoList;
    }

    public static List<MovieEntity> toEntityList(List<MovieDTO> list){
        List<MovieEntity> entityList = new ArrayList<>();
        for (MovieDTO dto : list){
            entityList.add(toEntity(dto));
        }
        return entityList;
    }


}
