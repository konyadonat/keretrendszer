package hu.uni.eku.tzs.controller.dto;

import hu.uni.eku.tzs.model.MoviesGenres;
import org.mapstruct.Mapper;

import javax.validation.Valid;

@Mapper(componentModel = "spring")
public interface MoviesGenresMapper {

    MoviesGenresDto moviesGenres2MoviesGenresDto(MoviesGenres moviesGenres);

    MoviesGenres moviesGenresDto2MoviesGenres(@Valid MoviesGenresDto dto);
}
