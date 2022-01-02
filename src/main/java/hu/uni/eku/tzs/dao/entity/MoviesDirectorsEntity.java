package hu.uni.eku.tzs.dao.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "movies_directors")
public class MoviesDirectorsEntity {

    @Id
    @Column(name = "director_id")
    private int directorId;

    @Column(name = "movie_id")
    private int movieId;
}
