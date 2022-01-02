package hu.uni.eku.tzs.service;

import hu.uni.eku.tzs.dao.RolesRepository;
import hu.uni.eku.tzs.dao.entity.RolesEntity;
import hu.uni.eku.tzs.model.Roles;
import hu.uni.eku.tzs.service.exceptions.RolesAlreadyExistsException;
import hu.uni.eku.tzs.service.exceptions.RolesNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class RolesManagerImpl implements RolesManager {

    private final RolesRepository repository;

    private static Roles convertRolesEntity2Model(RolesEntity rolesEntity) {
        return new Roles(
                rolesEntity.getActorId(),
                rolesEntity.getMovieId(),
                rolesEntity.getRole()
        );
    }

    private static RolesEntity convertRolesModel2Entity(Roles roles) {
        return RolesEntity.builder()
                .actorId(roles.getActorId())
                .movieId(roles.getMovieId())
                .role(roles.getRole())
                .build();
    }

    @Override
    public Roles record(Roles roles) throws RolesAlreadyExistsException {
        if (repository.findById(roles.getActorId()).isPresent()) {
            throw new RolesAlreadyExistsException();
        }
        RolesEntity rolesEntity = repository.save(
                RolesEntity.builder()
                        .actorId(roles.getActorId())
                        .movieId(roles.getMovieId())
                        .role(roles.getRole())
                        .build()
        );
        return convertRolesEntity2Model(rolesEntity);
    }

    @Override
    public Roles readById(int id) throws RolesNotFoundException {
        Optional<RolesEntity> entity = repository.findById(id);
        if (entity.isEmpty()) {
            throw new RolesNotFoundException(String.format("Cannot find a role with that actor ID %s", id));
        }
        return convertRolesEntity2Model(entity.get());
    }

    @Override
    public Collection<Roles> readAll() {
        return repository.findAll()
                .stream()
                .map(RolesManagerImpl::convertRolesEntity2Model)
                .collect(Collectors.toList());
    }

    @Override
    public Roles modify(Roles roles) {
        RolesEntity entity = convertRolesModel2Entity(roles);
        return convertRolesEntity2Model(repository.save(entity));
    }

    @Override
    public void delete(Roles roles) throws RolesNotFoundException {
        repository.delete(convertRolesModel2Entity(roles));
    }
}
