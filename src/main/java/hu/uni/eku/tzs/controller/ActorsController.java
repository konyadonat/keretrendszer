package hu.uni.eku.tzs.controller;

import hu.uni.eku.tzs.controller.dto.ActorsDto;
import hu.uni.eku.tzs.controller.dto.ActorsMapper;
import hu.uni.eku.tzs.model.Actors;
import hu.uni.eku.tzs.service.ActorsManager;
import hu.uni.eku.tzs.service.exceptions.ActorsAlreadyExistsException;
import hu.uni.eku.tzs.service.exceptions.ActorsNotFoundException;
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

@Api(tags = "Actors")
@RequestMapping("/actors")
@RestController
@RequiredArgsConstructor
public class ActorsController {

    private final ActorsManager manager;

    private final ActorsMapper mapper;

    @ApiOperation("Read All")
    @GetMapping(value = { ""})
    public Collection<ActorsDto> readAllActors() {
        return manager.readAll()
                .stream()
                .map(mapper::actors2ActorsDto)
                .collect(Collectors.toList());
    }

    @ApiOperation("Record")
    @PostMapping(value = {"", "/"})
    public ActorsDto create(@Valid @RequestBody ActorsDto recordRequestDto) {
        Actors actors = mapper.actorsDto2Actors(recordRequestDto);
        try {
            Actors recordedActors = manager.record(actors);
            return mapper.actors2ActorsDto(recordedActors);
        } catch (ActorsAlreadyExistsException e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage());
        }
    }

    @ApiOperation("Update")
    @PutMapping(value = {"", "/"})
    public ActorsDto update(@Valid @RequestBody ActorsDto updateRequestDto) {
        Actors actors = mapper.actorsDto2Actors(updateRequestDto);
        Actors updatedActors = manager.modify(actors);
        return mapper.actors2ActorsDto(updatedActors);
    }

    @ApiOperation("Delete")
    @DeleteMapping(value = {""})
    public void delete(@RequestParam int id) {
        try {
            manager.delete(manager.readById(id));
        } catch (ActorsNotFoundException e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage());
        }
    }
}
