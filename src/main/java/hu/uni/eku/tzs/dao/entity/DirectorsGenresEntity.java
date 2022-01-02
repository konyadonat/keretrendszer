package hu.uni.eku.tzs.dao.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Column;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "directors_genres")
public class DirectorsGenresEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int directorId;

    @Column(name = "genre")
    private String genre;

    @Column(name = "prob")
    private double prob;
}
