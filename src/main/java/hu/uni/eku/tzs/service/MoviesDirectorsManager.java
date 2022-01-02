package hu.uni.eku.tzs.service;

import hu.uni.eku.tzs.model.MoviesDirectors;
import hu.uni.eku.tzs.service.exceptions.MoviesDirectorsAlreadyExistsException;
import hu.uni.eku.tzs.service.exceptions.MoviesDirectorsNotFoundException;

import java.util.Collection;

public interface MoviesDirectorsManager {
    MoviesDirectors record(MoviesDirectors moviesDirectors) throws MoviesDirectorsAlreadyExistsException;

    MoviesDirectors readyById(int id) throws MoviesDirectorsNotFoundException;

    Collection<MoviesDirectors> readAll();

    MoviesDirectors modify(MoviesDirectors moviesDirectors);

    void delete(MoviesDirectors moviesDirectors) throws MoviesDirectorsNotFoundException;
}
