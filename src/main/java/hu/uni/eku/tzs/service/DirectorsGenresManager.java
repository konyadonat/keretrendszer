package hu.uni.eku.tzs.service;

import hu.uni.eku.tzs.model.DirectorsGenres;
import hu.uni.eku.tzs.service.exceptions.DirectorsGenresAlreadyExistsException;
import hu.uni.eku.tzs.service.exceptions.DirectorsGenresNotFoundException;

import java.util.Collection;

public interface DirectorsGenresManager {
    DirectorsGenres record(DirectorsGenres directorsGenres) throws DirectorsGenresAlreadyExistsException;

    DirectorsGenres readById(int id) throws DirectorsGenresNotFoundException;

    Collection<DirectorsGenres> readAll();

    DirectorsGenres modify(DirectorsGenres directorsGenres);

    void delete(DirectorsGenres directorsGenres) throws DirectorsGenresNotFoundException;
}
