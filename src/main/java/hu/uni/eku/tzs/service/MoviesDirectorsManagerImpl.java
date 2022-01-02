package hu.uni.eku.tzs.service;

import hu.uni.eku.tzs.dao.MoviesDirectorsRepository;
import hu.uni.eku.tzs.dao.entity.MoviesDirectorsEntity;
import hu.uni.eku.tzs.model.MoviesDirectors;
import hu.uni.eku.tzs.service.exceptions.MoviesDirectorsAlreadyExistsException;
import hu.uni.eku.tzs.service.exceptions.MoviesDirectorsNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class MoviesDirectorsManagerImpl implements MoviesDirectorsManager {

    private final MoviesDirectorsRepository moviesDirectorsRepository;

    private static MoviesDirectors convertMoviesDirectorsEntity2Model(MoviesDirectorsEntity entity) {
        return new MoviesDirectors(
                entity.getDirectorId(),
                entity.getMovieId()
        );
    }

    private static  MoviesDirectorsEntity convertMoviesDirectorsModel2Entity(MoviesDirectors moviesDirectors) {
        return MoviesDirectorsEntity.builder()
                .directorId(moviesDirectors.getDirectorId())
                .movieId(moviesDirectors.getMovieId())
                .build();
    }

    @Override
    public MoviesDirectors record(MoviesDirectors moviesDirectors) throws MoviesDirectorsAlreadyExistsException {
        if (moviesDirectorsRepository.findById(moviesDirectors.getDirectorId()).isPresent()) {
            throw new MoviesDirectorsAlreadyExistsException();
        }
        MoviesDirectorsEntity moviesDirectorsEntity = moviesDirectorsRepository.save(
                MoviesDirectorsEntity.builder()
                        .directorId(moviesDirectors.getDirectorId())
                        .movieId(moviesDirectors.getMovieId())
                        .build()
        );
        return convertMoviesDirectorsEntity2Model(moviesDirectorsEntity);
    }

    @Override
    public MoviesDirectors readyById(int id) throws MoviesDirectorsNotFoundException {
        Optional<MoviesDirectorsEntity>  entity = moviesDirectorsRepository.findById(id);
        if (entity.isEmpty()) {
            throw new MoviesDirectorsNotFoundException(String.format("Cannot find directorId WITH id %S", id));
        }
        return convertMoviesDirectorsEntity2Model(entity.get());
    }

    @Override
    public Collection<MoviesDirectors> readAll() {
        return moviesDirectorsRepository.findAll()
                .stream()
                .map(MoviesDirectorsManagerImpl::convertMoviesDirectorsEntity2Model)
                .collect(Collectors.toList());
    }

    @Override
    public MoviesDirectors modify(MoviesDirectors moviesDirectors) {
        MoviesDirectorsEntity entity = convertMoviesDirectorsModel2Entity(moviesDirectors);
        return convertMoviesDirectorsEntity2Model(moviesDirectorsRepository.save(entity));
    }

    @Override
    public void delete(MoviesDirectors moviesDirectors) throws MoviesDirectorsNotFoundException {
        moviesDirectorsRepository.delete(convertMoviesDirectorsModel2Entity(moviesDirectors));
    }
}
