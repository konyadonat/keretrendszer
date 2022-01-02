package hu.uni.eku.tzs.model;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.AllArgsConstructor;

@Getter
@Setter
@Data
@NoArgsConstructor
@AllArgsConstructor
public class DirectorsGenres {

    private int directorId;

    private String genre;

    private double prob;
}
