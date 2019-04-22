package me.max.moviesservice.repositories;

import me.max.moviesservice.movie.MovieEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by amxik on 22.04.2019.
 */
@Repository
public interface MovieRepository extends CrudRepository<MovieEntity,Long> {
}
