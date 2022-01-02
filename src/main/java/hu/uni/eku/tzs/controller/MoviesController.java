package hu.uni.eku.tzs.controller;

import hu.uni.eku.tzs.controller.dto.MoviesDto;
import hu.uni.eku.tzs.controller.dto.MoviesMapper;
import hu.uni.eku.tzs.model.Movies;
import hu.uni.eku.tzs.service.MoviesManager;
import hu.uni.eku.tzs.service.exceptions.MoviesAlreadyExistsException;
import hu.uni.eku.tzs.service.exceptions.MoviesNotFoundException;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.Valid;
import java.util.Collection;
import java.util.stream.Collectors;

@Api(tags = "Movies")
@RequestMapping("/movies")
@RestController
@RequiredArgsConstructor
public class MoviesController {

    private final MoviesManager manager;

    private final MoviesMapper mapper;

    @ApiOperation("Read All")
    @GetMapping(value = {"/", ""})
    public Collection<MoviesDto> readAllMovies() {
        return manager.readAll()
                .stream()
                .map(mapper::movies2MoviesDto)
                .collect(Collectors.toList());
    }

    @ApiOperation("Record")
    @PostMapping(value = {"", "/"})
    public MoviesDto create(@Valid @RequestBody MoviesDto recordRequestDto) {
        Movies movies = mapper.moviesDto2Movies(recordRequestDto);
        try {
            Movies recordedMovies = manager.record(movies);
            return mapper.movies2MoviesDto(recordedMovies);
        } catch (MoviesAlreadyExistsException e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage());
        }
    }

    @ApiOperation("Update")
    @PutMapping(value = {"", "/"})
    public MoviesDto update(@Valid @RequestBody MoviesDto updateMoviesDto) {
        Movies movies = mapper.moviesDto2Movies(updateMoviesDto);
        Movies updatedMovies = manager.modify(movies);
        return mapper.movies2MoviesDto(updatedMovies);
    }

    @ApiOperation("Delete")
    @DeleteMapping(value = {""})
    public void delete(@RequestParam int id) {
        try {
            manager.delete(manager.readById(id));
        } catch (MoviesNotFoundException e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage());
        }
    }
}
