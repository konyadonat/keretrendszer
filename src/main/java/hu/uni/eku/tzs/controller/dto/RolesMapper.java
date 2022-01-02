package hu.uni.eku.tzs.controller.dto;

import hu.uni.eku.tzs.model.Roles;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface RolesMapper {

    RolesDto roles2RolesDto(Roles roles);

    Roles rolesDto2Roles(RolesDto dto);
}
