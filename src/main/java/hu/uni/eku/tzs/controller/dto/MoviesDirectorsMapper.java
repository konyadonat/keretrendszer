package hu.uni.eku.tzs.controller.dto;

import hu.uni.eku.tzs.model.MoviesDirectors;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface MoviesDirectorsMapper {
    MoviesDirectorsDto moviesDirectors2MoviesDirectorsDto(MoviesDirectors moviesDirectors);

    MoviesDirectors moviesDirectorsDto2MoviesDirectors(MoviesDirectorsDto dto);

}
