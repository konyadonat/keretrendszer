package hu.uni.eku.tzs.service;

import hu.uni.eku.tzs.dao.MoviesRepository;
import hu.uni.eku.tzs.dao.entity.MoviesEntity;
import hu.uni.eku.tzs.model.Movies;
import hu.uni.eku.tzs.service.exceptions.MoviesAlreadyExistsException;
import hu.uni.eku.tzs.service.exceptions.MoviesNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class MoviesManagerImpl implements MoviesManager {

    private final MoviesRepository repository;

    private static Movies convertMoviesEntity2Model(MoviesEntity moviesEntity) {
        return new Movies(
                moviesEntity.getId(),
                moviesEntity.getName(),
                moviesEntity.getMovieYear(),
                moviesEntity.getRank()
        );
    }

    private static MoviesEntity convertMoviesModel2Entity(Movies movies) {
        return MoviesEntity.builder()
                .id(movies.getId())
                .name(movies.getName())
                .movieYear(movies.getMovieYear())
                .rank(movies.getRank())
                .build();
    }

    @Override
    public Movies record(Movies movies) throws MoviesAlreadyExistsException {
        if (repository.findById(movies.getId()).isPresent()) {
            throw new MoviesAlreadyExistsException();
        }
        MoviesEntity moviesEntity = repository.save(
                MoviesEntity.builder()
                        .id(movies.getId())
                        .name(movies.getName())
                        .movieYear(movies.getMovieYear())
                        .rank(movies.getRank())
                        .build()
        );
        return convertMoviesEntity2Model(moviesEntity);
    }

    @Override
    public Movies readById(int id) throws MoviesNotFoundException {
        Optional<MoviesEntity> entity = repository.findById(id);
        if (entity.isEmpty()) {
            throw new MoviesNotFoundException(String.format("Cannot find a movie with ID %s", id));
        }
        return convertMoviesEntity2Model(entity.get());
    }

    @Override
    public Collection<Movies> readAll() {
        return repository.findAll()
                .stream()
                .map(MoviesManagerImpl::convertMoviesEntity2Model)
                .collect(Collectors.toList());
    }

    @Override
    public Movies modify(Movies movies) {
        MoviesEntity entity = convertMoviesModel2Entity(movies);
        return convertMoviesEntity2Model(repository.save(entity));
    }

    @Override
    public void delete(Movies movies) throws MoviesNotFoundException {
        repository.delete(convertMoviesModel2Entity(movies));
    }
}
