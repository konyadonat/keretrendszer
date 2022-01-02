package hu.uni.eku.tzs.controller;

import hu.uni.eku.tzs.controller.dto.MoviesGenresDto;
import hu.uni.eku.tzs.controller.dto.MoviesGenresMapper;
import hu.uni.eku.tzs.model.MoviesGenres;
import hu.uni.eku.tzs.service.MoviesGenresManager;
import hu.uni.eku.tzs.service.exceptions.MoviesGenresAlreadyExistsException;
import hu.uni.eku.tzs.service.exceptions.MoviesGenresNotFoundException;
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

@Api(tags = "MoviesGenres")
@RequestMapping("/moviesGenres")
@RestController
@RequiredArgsConstructor
public class MoviesGenresController {

    private final MoviesGenresManager manager;

    private final MoviesGenresMapper mapper;

    @ApiOperation("Read All")
    @GetMapping(value = {"/", ""})
    public Collection<MoviesGenresDto> readAllMoviesGenres() {
        return manager.readAll()
                .stream()
                .map(mapper::moviesGenres2MoviesGenresDto)
                .collect(Collectors.toList());
    }

    @ApiOperation("Record")
    @PostMapping(value = {"", "/"})
    public MoviesGenresDto create(@Valid @RequestBody MoviesGenresDto recordRequestDto) {
        MoviesGenres moviesGenres = mapper.moviesGenresDto2MoviesGenres(recordRequestDto);
        try {
            MoviesGenres recordedMoviesGenres = manager.record(moviesGenres);
            return mapper.moviesGenres2MoviesGenresDto(recordedMoviesGenres);
        } catch (MoviesGenresAlreadyExistsException e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage());
        }
    }

    @ApiOperation("Update")
    @PutMapping(value = {"", "/"})
    public MoviesGenresDto update(@Valid @RequestBody MoviesGenresDto updateRequestDto) {
        MoviesGenres moviesGenres = mapper.moviesGenresDto2MoviesGenres(updateRequestDto);
        MoviesGenres updatedMoviesGenres = manager.modify(moviesGenres);
        return mapper.moviesGenres2MoviesGenresDto(updatedMoviesGenres);
    }

    @ApiOperation("Delete")
    @DeleteMapping(value = {""})
    public void delete(@RequestParam int id) {
        try {
            manager.delete(manager.readById(id));
        } catch (MoviesGenresNotFoundException e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage());
        }
    }
}
