package com.prudencia.java.lamda;

import lombok.*;
import lombok.experimental.Accessors;


@Accessors(chain = true)
@Data
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
public class Team {
    private String team_name;
    private String team_position;
    private int team_points;
}
