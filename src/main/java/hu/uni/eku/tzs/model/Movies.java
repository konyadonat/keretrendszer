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
public class Movies {

    private int id;

    private String name;

    private int movieYear;

    private float rank;
}
