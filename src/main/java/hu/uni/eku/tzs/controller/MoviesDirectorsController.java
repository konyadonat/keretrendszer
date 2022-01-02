package hu.uni.eku.tzs.controller;

import hu.uni.eku.tzs.controller.dto.MoviesDirectorsDto;
import hu.uni.eku.tzs.controller.dto.MoviesDirectorsMapper;
import hu.uni.eku.tzs.model.MoviesDirectors;
import hu.uni.eku.tzs.service.MoviesDirectorsManager;
import hu.uni.eku.tzs.service.exceptions.MoviesDirectorsAlreadyExistsException;
import hu.uni.eku.tzs.service.exceptions.MoviesDirectorsNotFoundException;
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

@Api(tags = "MoviesDirectors")
@RequestMapping("/moviesDirectors")
@RestController
@RequiredArgsConstructor
public class MoviesDirectorsController {

    private final MoviesDirectorsManager manager;

    private final MoviesDirectorsMapper mapper;

    @ApiOperation("Read All")
    @GetMapping(value = {"/", ""})
    public Collection<MoviesDirectorsDto> readAllMoviesDirectors() {
        return manager.readAll()
                .stream()
                .map(mapper::moviesDirectors2MoviesDirectorsDto)
                .collect(Collectors.toList());
    }

    @ApiOperation("Record")
    @PostMapping(value = {"", "/"})
    public MoviesDirectorsDto create(@Valid @RequestBody MoviesDirectorsDto recordRequestDto) {
        MoviesDirectors moviesDirectors = mapper.moviesDirectorsDto2MoviesDirectors(recordRequestDto);
        try {
            MoviesDirectors recordedMoviesDirectors = manager.record(moviesDirectors);
            return mapper.moviesDirectors2MoviesDirectorsDto(recordedMoviesDirectors);
        } catch (MoviesDirectorsAlreadyExistsException e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage());
        }
    }

    @ApiOperation("Update")
    @PutMapping(value = {"", "/"})
    public  MoviesDirectorsDto update(@Valid @RequestBody MoviesDirectorsDto updateRequestDto) {
        MoviesDirectors moviesDirectors = mapper.moviesDirectorsDto2MoviesDirectors(updateRequestDto);
        MoviesDirectors updatedMoviesDirectors = manager.modify(moviesDirectors);
        return mapper.moviesDirectors2MoviesDirectorsDto(updatedMoviesDirectors);
    }

    @ApiOperation("Delete")
    @DeleteMapping(value = {""})
    public void delete(@RequestParam int id) {
        try {
            manager.delete(manager.readyById(id));
        } catch (MoviesDirectorsNotFoundException e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage());
        }
    }
}
