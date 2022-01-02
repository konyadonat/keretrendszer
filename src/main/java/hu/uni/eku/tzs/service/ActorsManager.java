package hu.uni.eku.tzs.service;

import hu.uni.eku.tzs.model.Actors;
import hu.uni.eku.tzs.service.exceptions.ActorsAlreadyExistsException;
import hu.uni.eku.tzs.service.exceptions.ActorsNotFoundException;

import java.util.Collection;

public interface ActorsManager {

    Actors record(Actors actors) throws ActorsAlreadyExistsException;

    Actors readById(int id) throws ActorsNotFoundException;

    Collection<Actors> readAll();

    Actors modify(Actors actors);

    void delete(Actors actors) throws ActorsNotFoundException;
}
