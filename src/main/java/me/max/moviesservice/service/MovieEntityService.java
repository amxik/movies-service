package me.max.moviesservice.service;

import me.max.moviesservice.dto.MovieDTO;
import me.max.moviesservice.exception.MovieNotFoundException;
import me.max.moviesservice.movie.MovieEntity;
import me.max.moviesservice.repositories.MovieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by amxik on 23.04.2019.
 */


@Service
public class MovieEntityService {

    @Autowired
    private MovieRepository movieRepository;


    public MovieDTO createMovie(MovieDTO movieDTO) {
        MovieEntity movieEntity = toEntity(movieDTO);
        MovieEntity movie = movieRepository.save(movieEntity);
        return toDto(movie);
    }


    public MovieDTO replaceMovieById(long id, MovieDTO movieDTO) throws MovieNotFoundException {
        MovieDTO movieFromStorage = getMovieById(id);
        MovieDTO movie = new MovieDTO();
        movie.setId(id);
        movie.setTitle(movieDTO.getTitle());
        movie.setGenre(movieDTO.getGenre());
        movie.setDescription(movieDTO.getDescription());
        movie.setDuration(movieDTO.getDuration());
        movie.setReleaseDate(movieDTO.getReleaseDate());

        MovieEntity movieEntity = toEntity(movie);
        MovieEntity returnMovie = movieRepository.save(movieEntity);
        return toDto(returnMovie);

    }

    public MovieDTO updateMovie(long id, MovieDTO movieDTO) throws MovieNotFoundException {

        MovieDTO movieFromStorage = getMovieById(id);


        MovieDTO movie = new MovieDTO();


        movie.setId(id);

        if (movieDTO.getTitle() != null) {
            movie.setTitle(movieDTO.getTitle());
        } else {
            movie.setTitle(movieFromStorage.getTitle());
        }
        if (movieDTO.getGenre() != null) {
            movie.setGenre(movieDTO.getGenre());
        } else {
            movie.setGenre(movieFromStorage.getGenre());
        }
        if (movieDTO.getDescription() != null) {
            movie.setDescription(movieDTO.getDescription());
        } else {
            movie.setDescription(movieFromStorage.getDescription());
        }
        if (movieDTO.getDuration() != null) {
            movie.setDuration(movieDTO.getDuration());
        } else {
            movie.setDuration(movieFromStorage.getDuration());
        }
        if (movieDTO.getReleaseDate() != null) {
            movie.setReleaseDate(movieDTO.getReleaseDate());
        } else {
            movie.setReleaseDate(movieFromStorage.getReleaseDate());
        }

        MovieEntity movieEntity = toEntity(movie);
        MovieEntity returnMovie = movieRepository.save(movieEntity);
        return toDto(returnMovie);
    }

    public void deleteMovie(long id) {

        movieRepository.deleteById(id);
    }

    public List<MovieDTO> getAllMovies(int offset, int limit) {
        List<MovieDTO> list = new ArrayList<>();
        List<MovieEntity> movieEntityList = movieRepository.findAll(new PageRequest(offset, limit)).getContent();
        if (!CollectionUtils.isEmpty(movieEntityList)) {
            toDtoList(movieEntityList).forEach(list::add);
        }
        return list;

    }

    public MovieDTO getMovieById(long id) throws MovieNotFoundException {
        return toDto(movieRepository.findById(id).orElseThrow(()->new MovieNotFoundException(id)));
    }

    public List<MovieDTO> getMovieByTitle(String title, int offset, int limit) {
        List<MovieDTO> list = new ArrayList<>();
        List<MovieEntity> movieEntityList = movieRepository.findByTitleContaining(title, new PageRequest(offset, limit));
        if (!CollectionUtils.isEmpty(movieEntityList)) {
            list = toDtoList(movieEntityList);
        }
        return list;
    }

    public List<MovieDTO> getMoviesByReleaseDate(LocalDate releaseDate, int offset, int limit) {
        List<MovieDTO> list = new ArrayList<>();
        List<MovieEntity> movieEntityList = movieRepository.findAllByReleaseDate(releaseDate, new PageRequest(offset, limit));
        if (!CollectionUtils.isEmpty(movieEntityList)) {
            list = toDtoList(movieEntityList);
        }
        return list;
    }


    public static MovieDTO toDto(MovieEntity entity) {
        MovieDTO dto = new MovieDTO();
        dto.setId(entity.getId());
        dto.setTitle(entity.getTitle());
        dto.setGenre(entity.getGenre());
        dto.setDescription(entity.getDescription());
        dto.setDuration(entity.getDuration());
        dto.setReleaseDate(entity.getReleaseDate());

        return dto;
    }

    public static MovieEntity toEntity(MovieDTO dto) {
        MovieEntity movieEntity = new MovieEntity();
        movieEntity.setId(dto.getId());
        movieEntity.setTitle(dto.getTitle());
        movieEntity.setGenre(dto.getGenre());
        movieEntity.setDescription(dto.getDescription());
        movieEntity.setDuration(dto.getDuration());
        movieEntity.setReleaseDate(dto.getReleaseDate());
        return movieEntity;
    }

    public static List<MovieDTO> toDtoList(List<MovieEntity> list) {
        List<MovieDTO> dtoList = new ArrayList<>();
        for (MovieEntity entity : list) {
            dtoList.add(toDto(entity));
        }
        return dtoList;
    }


}
