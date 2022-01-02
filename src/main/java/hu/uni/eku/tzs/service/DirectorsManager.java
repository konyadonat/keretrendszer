package hu.uni.eku.tzs.service;

import hu.uni.eku.tzs.model.Directors;
import hu.uni.eku.tzs.service.exceptions.DirectorsAlreadyExistsException;
import hu.uni.eku.tzs.service.exceptions.DirectorsNotFoundException;

import java.util.Collection;

public interface DirectorsManager {
    Directors record(Directors directors) throws DirectorsAlreadyExistsException;

    Directors readById(int id) throws DirectorsNotFoundException;

    Collection<Directors> readAll();

    Directors modify(Directors directors);

    void delete(Directors directors) throws DirectorsNotFoundException;
}
