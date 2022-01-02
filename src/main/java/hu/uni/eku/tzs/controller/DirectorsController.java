package hu.uni.eku.tzs.controller;

import hu.uni.eku.tzs.controller.dto.DirectorsDto;
import hu.uni.eku.tzs.controller.dto.DirectorsMapper;
import hu.uni.eku.tzs.model.Directors;
import hu.uni.eku.tzs.service.DirectorsManager;
import hu.uni.eku.tzs.service.exceptions.DirectorsAlreadyExistsException;
import hu.uni.eku.tzs.service.exceptions.DirectorsNotFoundException;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.Valid;
import java.util.Collection;
import java.util.stream.Collectors;

@Api(tags = "Directors")
@RequestMapping("/directors")
@RestController
@RequiredArgsConstructor
public class DirectorsController {

    private final DirectorsManager directorsManager;

    private final DirectorsMapper directorsMapper;

    @ApiOperation("Read All")
    @GetMapping(value = {"/", ""})
    public Collection<DirectorsDto> readAllBooks() {
        return directorsManager.readAll()
                .stream()
                .map(directorsMapper::directors2DirectorsDto)
                .collect(Collectors.toList());

    }

    @ApiOperation("Record")
    @PostMapping(value = {"", "/"})
    public DirectorsDto create(@Valid @RequestBody DirectorsDto recordRequestDto) {
        Directors directors = directorsMapper.directorsDto2Directors(recordRequestDto);
        try {
            Directors recordedDirectors = directorsManager.record(directors);
            return directorsMapper.directors2DirectorsDto(recordedDirectors);
        } catch (DirectorsAlreadyExistsException e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage());
        }
    }

    @ApiOperation("Update")
    @PutMapping(value = {"", "/"})
    public DirectorsDto update(@Valid @RequestBody DirectorsDto updateRequestDto) {
        Directors directors = directorsMapper.directorsDto2Directors(updateRequestDto);
        Directors updatedDirectors = directorsManager.modify(directors);
        return directorsMapper.directors2DirectorsDto(updatedDirectors);
    }

    @ApiOperation("Delete")
    @DeleteMapping(value = {""})
    public void delete(@RequestParam int id) {
        try {
            directorsManager.delete(directorsManager.readById(id));
        } catch (DirectorsNotFoundException e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage());
        }
    }
}


