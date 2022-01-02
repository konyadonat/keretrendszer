package hu.uni.eku.tzs.controller.dto;

import hu.uni.eku.tzs.model.Actors;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ActorsMapper {

    ActorsDto actors2ActorsDto(Actors actors);

    Actors actorsDto2Actors(ActorsDto dto);
}
