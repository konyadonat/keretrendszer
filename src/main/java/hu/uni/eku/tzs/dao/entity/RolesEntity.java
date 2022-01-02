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
@Entity(name = "roles")
public class RolesEntity {

    @Id
    @Column(name = "actor_id")
    private int actorId;

    @Column(name = "movie_id")
    private int movieId;

    @Column(name = "role")
    private String role;
}
