package hu.uni.eku.tzs.service;

import hu.uni.eku.tzs.dao.DirectorsRepository;
import hu.uni.eku.tzs.dao.entity.DirectorsEntity;
import hu.uni.eku.tzs.model.Directors;
import hu.uni.eku.tzs.service.exceptions.DirectorsAlreadyExistsException;
import hu.uni.eku.tzs.service.exceptions.DirectorsNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class DirectorsManagerImpl implements DirectorsManager {

    private final DirectorsRepository directorsRepository;

    private static Directors convertDirectorsEntity2Model(DirectorsEntity directorsEntity) {
        return new Directors(
                directorsEntity.getId(),
                directorsEntity.getFirstName(),
                directorsEntity.getLastName()
        );
    }

    private static DirectorsEntity convertDirectorsEntity2Model(Directors directors) {
        return DirectorsEntity.builder()
                .id(directors.getId())
                .firstName(directors.getFirstName())
                .lastName(directors.getLastName())
                .build();
    }

    @Override
    public Directors record(Directors directors) throws DirectorsAlreadyExistsException {
        if (directorsRepository.findById(directors.getId()).isPresent()) {
            throw new DirectorsAlreadyExistsException();
        }
        DirectorsEntity directorsEntity = directorsRepository.save(
                DirectorsEntity.builder()
                        .id(directors.getId())
                        .firstName(directors.getFirstName())
                        .lastName(directors.getLastName())
                        .build()
        );
        return convertDirectorsEntity2Model(directorsEntity);
    }

    @Override
    public Directors readById(int id) throws DirectorsNotFoundException {
        Optional<DirectorsEntity> entity = directorsRepository.findById(id);
        if (entity.isEmpty()) {
            throw new DirectorsNotFoundException(String.format("Cannot find chapter with ID %s", id));
        }
        return convertDirectorsEntity2Model(entity.get());
    }

    @Override
    public Collection<Directors> readAll() {
        return directorsRepository.findAll()
                .stream()
                .map(DirectorsManagerImpl::convertDirectorsEntity2Model)
                .collect(Collectors.toList());
    }

    @Override
    public Directors modify(Directors directors) {
        DirectorsEntity entity = convertDirectorsEntity2Model(directors);
        return convertDirectorsEntity2Model(directorsRepository.save(entity));
    }

    @Override
    public void delete(Directors directors) throws DirectorsNotFoundException {
        directorsRepository.delete(convertDirectorsEntity2Model(directors));
    }
}
