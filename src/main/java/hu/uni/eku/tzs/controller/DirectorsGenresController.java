package hu.uni.eku.tzs.controller;

import hu.uni.eku.tzs.controller.dto.DirectorsGenresDto;
import hu.uni.eku.tzs.controller.dto.DirectorsGenresMapper;
import hu.uni.eku.tzs.model.DirectorsGenres;
import hu.uni.eku.tzs.service.DirectorsGenresManager;
import hu.uni.eku.tzs.service.exceptions.DirectorsGenresAlreadyExistsException;
import hu.uni.eku.tzs.service.exceptions.DirectorsGenresNotFoundException;
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

@Api(tags = "DirectorsGenres")
@RequestMapping("/directorGenres")
@RestController
@RequiredArgsConstructor
public class DirectorsGenresController {

    private final DirectorsGenresManager directorGenresManager;

    private final DirectorsGenresMapper directorGenresMapper;

    @ApiOperation("Read All")
    @GetMapping(value = {"/", ""})
    public Collection<DirectorsGenresDto> readAllBooks() {
        return directorGenresManager.readAll()
                .stream()
                .map(directorGenresMapper::directorGenres2DirectorGenresDto)
                .collect(Collectors.toList());

    }

    @ApiOperation("Record")
    @PostMapping(value = {"", "/"})
    public DirectorsGenresDto create(@Valid @RequestBody DirectorsGenresDto recordRequestDto) {
        DirectorsGenres directorGenres = directorGenresMapper.directorGenresDto2DirectorGenres(recordRequestDto);
        try {
            DirectorsGenres recordedDirectorsGenres = directorGenresManager.record(directorGenres);
            return directorGenresMapper.directorGenres2DirectorGenresDto(recordedDirectorsGenres);
        } catch (DirectorsGenresAlreadyExistsException e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage());
        }
    }

    @ApiOperation("Update")
    @PutMapping(value = {"", "/"})
    public DirectorsGenresDto update(@Valid @RequestBody DirectorsGenresDto updateRequestDto) {
        DirectorsGenres directorsGenres = directorGenresMapper.directorGenresDto2DirectorGenres(updateRequestDto);
        DirectorsGenres updatedDirectorsGenres = directorGenresManager.modify(directorsGenres);
        return directorGenresMapper.directorGenres2DirectorGenresDto(updatedDirectorsGenres);
    }

    @ApiOperation("Delete")
    @DeleteMapping(value = {""})
    public void delete(@RequestParam int id) {
        try {
            directorGenresManager.delete(directorGenresManager.readById(id));
        } catch (DirectorsGenresNotFoundException e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage());
        }
    }
}

