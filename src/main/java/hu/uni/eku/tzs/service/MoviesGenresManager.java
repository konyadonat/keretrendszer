package hu.uni.eku.tzs.service;

import hu.uni.eku.tzs.model.MoviesGenres;
import hu.uni.eku.tzs.service.exceptions.MoviesGenresAlreadyExistsException;
import hu.uni.eku.tzs.service.exceptions.MoviesGenresNotFoundException;

import java.util.Collection;

public interface MoviesGenresManager {
    MoviesGenres record(MoviesGenres moviesGenres) throws MoviesGenresAlreadyExistsException;

    MoviesGenres readById(int id) throws MoviesGenresNotFoundException;

    Collection<MoviesGenres> readAll();

    MoviesGenres modify(MoviesGenres moviesGenres);

    void delete(MoviesGenres moviesGenres) throws MoviesGenresNotFoundException;
}
