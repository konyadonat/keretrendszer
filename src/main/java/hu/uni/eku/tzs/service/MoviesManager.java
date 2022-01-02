package hu.uni.eku.tzs.service;

import hu.uni.eku.tzs.model.Movies;
import hu.uni.eku.tzs.service.exceptions.MoviesAlreadyExistsException;
import hu.uni.eku.tzs.service.exceptions.MoviesNotFoundException;

import java.util.Collection;

public interface MoviesManager {
    Movies record(Movies movies) throws MoviesAlreadyExistsException;

    Movies readById(int id) throws MoviesNotFoundException;

    Collection<Movies> readAll();

    Movies modify(Movies movies);

    void delete(Movies movies) throws MoviesNotFoundException;
}
