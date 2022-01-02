package hu.uni.eku.tzs.controller.dto;

import hu.uni.eku.tzs.model.Movies;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface MoviesMapper {
    MoviesDto movies2MoviesDto(Movies movies);

    Movies moviesDto2Movies(MoviesDto dto);
}
