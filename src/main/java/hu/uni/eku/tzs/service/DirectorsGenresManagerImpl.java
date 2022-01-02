package hu.uni.eku.tzs.service;

import hu.uni.eku.tzs.dao.DirectorsGenresRepository;
import hu.uni.eku.tzs.dao.entity.DirectorsGenresEntity;
import hu.uni.eku.tzs.model.DirectorsGenres;
import hu.uni.eku.tzs.service.exceptions.DirectorsGenresAlreadyExistsException;
import hu.uni.eku.tzs.service.exceptions.DirectorsGenresNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class DirectorsGenresManagerImpl implements DirectorsGenresManager {

    private final DirectorsGenresRepository directorsGenresRepository;

    private static DirectorsGenres convertDirectorsGenresEntity2Model(DirectorsGenresEntity directorsEntity) {
        return new DirectorsGenres(
                directorsEntity.getDirectorId(),
                directorsEntity.getGenre(),
                directorsEntity.getProb()
        );
    }

    private static DirectorsGenresEntity convertDirectorsGenresModel2Entity(DirectorsGenres directorsGenres) {
        return DirectorsGenresEntity.builder()
                .directorId(directorsGenres.getDirectorId())
                .genre(directorsGenres.getGenre())
                .prob(directorsGenres.getProb())
                .build();
    }

    @Override
    public DirectorsGenres record(DirectorsGenres directorsGenres) throws DirectorsGenresAlreadyExistsException {
        if (directorsGenresRepository.findById(directorsGenres.getDirectorId()).isPresent()) {
            throw new DirectorsGenresAlreadyExistsException();
        }
        DirectorsGenresEntity directorsGenresEntity = directorsGenresRepository.save(
                DirectorsGenresEntity.builder()
                        .directorId(directorsGenres.getDirectorId())
                        .genre(directorsGenres.getGenre())
                        .prob(directorsGenres.getProb())
                        .build()
        );
        return convertDirectorsGenresEntity2Model(directorsGenresEntity);
    }

    @Override
    public DirectorsGenres readById(int id) throws DirectorsGenresNotFoundException {
        Optional<DirectorsGenresEntity> entity = directorsGenresRepository.findById(id);
        if (entity.isEmpty()) {
            throw new DirectorsGenresNotFoundException(String.format("Cannot find chapter with ID %s", id));
        }
        return convertDirectorsGenresEntity2Model(entity.get());
    }

    @Override
    public Collection<DirectorsGenres> readAll() {
        return directorsGenresRepository.findAll()
                .stream()
                .map(DirectorsGenresManagerImpl::convertDirectorsGenresEntity2Model)
                .collect(Collectors.toList());
    }

    @Override
    public DirectorsGenres modify(DirectorsGenres directorsGenres) {
        DirectorsGenresEntity entity = convertDirectorsGenresModel2Entity(directorsGenres);
        return convertDirectorsGenresEntity2Model(directorsGenresRepository.save(entity));
    }

    @Override
    public void delete(DirectorsGenres directorsGenres) throws DirectorsGenresNotFoundException {
        directorsGenresRepository.delete(convertDirectorsGenresModel2Entity(directorsGenres));
    }
}
