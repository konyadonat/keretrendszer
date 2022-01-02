package hu.uni.eku.tzs.controller.dto;

import hu.uni.eku.tzs.model.Directors;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface DirectorsMapper {

    DirectorsDto directors2DirectorsDto(Directors directors);

    Directors directorsDto2Directors(DirectorsDto dto);
}
