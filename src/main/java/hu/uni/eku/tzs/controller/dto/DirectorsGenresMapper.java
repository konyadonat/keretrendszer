package hu.uni.eku.tzs.controller.dto;

import hu.uni.eku.tzs.model.DirectorsGenres;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface DirectorsGenresMapper {
    DirectorsGenresDto directorGenres2DirectorGenresDto(DirectorsGenres directorsGenres);

    DirectorsGenres directorGenresDto2DirectorGenres(DirectorsGenresDto dto);
}
