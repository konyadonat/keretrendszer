package hu.uni.eku.tzs.controller;

import hu.uni.eku.tzs.controller.dto.RolesDto;
import hu.uni.eku.tzs.controller.dto.RolesMapper;
import hu.uni.eku.tzs.model.Roles;
import hu.uni.eku.tzs.service.RolesManager;
import hu.uni.eku.tzs.service.exceptions.RolesAlreadyExistsException;
import hu.uni.eku.tzs.service.exceptions.RolesNotFoundException;
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

@Api(tags = "Roles")
@RequestMapping("/roles")
@RestController
@RequiredArgsConstructor
public class RolesController {

    private final RolesManager manager;

    private final RolesMapper mapper;

    @ApiOperation("Read All")
    @GetMapping(value = {"/", ""})
    public Collection<RolesDto> readAllRoles() {
        return manager.readAll()
                .stream()
                .map(mapper::roles2RolesDto)
                .collect(Collectors.toList());
    }

    @ApiOperation("Record")
    @PostMapping(value = {"", "/"})
    public RolesDto create(@Valid @RequestBody RolesDto recordRequestDto) {
        Roles roles = mapper.rolesDto2Roles(recordRequestDto);
        try {
            Roles recordedRoles = manager.record(roles);
            return mapper.roles2RolesDto(recordedRoles);
        } catch (RolesAlreadyExistsException e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage());
        }
    }

    @ApiOperation("Update")
    @PutMapping(value = {"", "/"})
    public RolesDto update(@Valid @RequestBody RolesDto updateRequestDto) {
        Roles roles = mapper.rolesDto2Roles(updateRequestDto);
        Roles updatedRoles = manager.modify(roles);
        return mapper.roles2RolesDto(updatedRoles);
    }

    @ApiOperation("Delete")
    @DeleteMapping(value = {""})
    public void delete(@RequestParam int id) {
        try {
            manager.delete(manager.readById(id));
        } catch (RolesNotFoundException e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage());
        }
    }

}
