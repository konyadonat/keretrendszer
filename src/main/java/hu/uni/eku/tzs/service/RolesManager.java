package hu.uni.eku.tzs.service;

import hu.uni.eku.tzs.model.Roles;
import hu.uni.eku.tzs.service.exceptions.RolesAlreadyExistsException;
import hu.uni.eku.tzs.service.exceptions.RolesNotFoundException;

import java.util.Collection;

public interface RolesManager {

    Roles record(Roles roles) throws RolesAlreadyExistsException;

    Roles readById(int id) throws RolesNotFoundException;

    Collection<Roles> readAll();

    Roles modify(Roles roles);

    void delete(Roles roles) throws RolesNotFoundException;
}
