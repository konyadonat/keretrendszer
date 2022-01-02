package hu.uni.eku.tzs.service;

import hu.uni.eku.tzs.dao.ActorsRepository;
import hu.uni.eku.tzs.dao.entity.ActorsEntity;
import hu.uni.eku.tzs.model.Actors;
import hu.uni.eku.tzs.service.exceptions.ActorsAlreadyExistsException;
import hu.uni.eku.tzs.service.exceptions.ActorsNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ActorsManagerImpl implements ActorsManager {

    private final ActorsRepository repository;

    private static Actors convertActorsEntity2Model(ActorsEntity actorsEntity) {
        return new Actors(
                actorsEntity.getId(),
                actorsEntity.getFirstName(),
                actorsEntity.getLastName(),
                actorsEntity.getGender()
        );
    }

    private static ActorsEntity convertActorsModel2Entity(Actors actors) {
        return ActorsEntity.builder()
                .id(actors.getId())
                .firstName(actors.getFirstName())
                .lastName(actors.getLastName())
                .gender(actors.getGender())
                .build();
    }

    @Override
    public Actors record(Actors actors) throws ActorsAlreadyExistsException {
        if (repository.findById(actors.getId()).isPresent()) {
            throw new ActorsAlreadyExistsException();
        }
        ActorsEntity actorsEntity = repository.save(
                ActorsEntity.builder()
                        .id(actors.getId())
                        .firstName(actors.getFirstName())
                        .lastName(actors.getLastName())
                        .gender(actors.getGender())
                        .build()
        );
        return convertActorsEntity2Model(actorsEntity);
    }

    @Override
    public Actors readById(int id) throws ActorsNotFoundException {
        Optional<ActorsEntity> entity = repository.findById(id);
        if (entity.isEmpty()) {
            throw new ActorsNotFoundException(String.format("Cannot find actor with Id %s", id));
        }
        return convertActorsEntity2Model(entity.get());
    }

    @Override
    public Collection<Actors> readAll() {
        return repository.findAll()
                .stream()
                .map(ActorsManagerImpl::convertActorsEntity2Model)
                .collect(Collectors.toList());
    }

    @Override
    public Actors modify(Actors actors) {
        ActorsEntity entity = convertActorsModel2Entity(actors);
        return convertActorsEntity2Model(repository.save(entity));
    }

    @Override
    public void delete(Actors actors) throws ActorsNotFoundException {
        repository.delete(convertActorsModel2Entity(actors));
    }
}
