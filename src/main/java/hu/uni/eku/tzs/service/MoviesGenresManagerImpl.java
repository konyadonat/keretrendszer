package hu.uni.eku.tzs.service;

import hu.uni.eku.tzs.dao.MoviesGenresRepository;
import hu.uni.eku.tzs.dao.entity.MoviesGenresEntity;
import hu.uni.eku.tzs.model.MoviesGenres;
import hu.uni.eku.tzs.service.exceptions.MoviesGenresAlreadyExistsException;
import hu.uni.eku.tzs.service.exceptions.MoviesGenresNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class MoviesGenresManagerImpl implements MoviesGenresManager {

    private final MoviesGenresRepository repository;

    private static MoviesGenres convertMoviesGenresEntity2Model(MoviesGenresEntity moviesGenresEntity) {
        return new MoviesGenres(
                moviesGenresEntity.getMovieId(),
                moviesGenresEntity.getGenre()
        );
    }

    private static MoviesGenresEntity convertMoviesGenresModel2Entity(MoviesGenres moviesGenres) {
        return MoviesGenresEntity.builder()
                .movieId(moviesGenres.getMovieId())
                .genre(moviesGenres.getGenre())
                .build();
    }

    @Override
    public MoviesGenres record(MoviesGenres moviesGenres) throws MoviesGenresAlreadyExistsException {
        if (repository.findById(moviesGenres.getMovieId()).isPresent()) {
            throw new MoviesGenresAlreadyExistsException();
        }
        MoviesGenresEntity moviesGenresEntity = repository.save(
                MoviesGenresEntity.builder()
                        .movieId(moviesGenres.getMovieId())
                        .genre(moviesGenres.getGenre())
                        .build()
        );
        return convertMoviesGenresEntity2Model(moviesGenresEntity);
    }

    @Override
    public MoviesGenres readById(int id) throws MoviesGenresNotFoundException {
        Optional<MoviesGenresEntity> entity = repository.findById(id);
        if (entity.isEmpty()) {
            throw new MoviesGenresNotFoundException(String.format("Cannot find movie_genres with Id %s", id));
        }
        return convertMoviesGenresEntity2Model(entity.get());
    }

    @Override
    public Collection<MoviesGenres> readAll() {
        return repository.findAll()
                .stream()
                .map(MoviesGenresManagerImpl::convertMoviesGenresEntity2Model)
                .collect(Collectors.toList());
    }

    @Override
    public MoviesGenres modify(MoviesGenres moviesGenres) {
        MoviesGenresEntity entity = convertMoviesGenresModel2Entity(moviesGenres);
        return convertMoviesGenresEntity2Model(repository.save(entity));
    }

    @Override
    public void delete(MoviesGenres moviesGenres) throws MoviesGenresNotFoundException {
        repository.delete(convertMoviesGenresModel2Entity(moviesGenres));
    }
}
