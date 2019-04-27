package me.max.moviesservice.repositories;

import me.max.moviesservice.movie.MovieEntity;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

/**
 * Created by amxik on 22.04.2019.
 */
@Repository
public interface MovieRepository extends PagingAndSortingRepository<MovieEntity, Long> {

    List<MovieEntity> findByTitleContaining(String title, Pageable pageable);

    List<MovieEntity> findAllByReleaseDate(Date releaseDate, Pageable pageable);

}
