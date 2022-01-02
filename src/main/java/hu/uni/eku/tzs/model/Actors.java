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
public class Actors {
    private int id;

    private String firstName;

    private String lastName;

    private String gender;
}
