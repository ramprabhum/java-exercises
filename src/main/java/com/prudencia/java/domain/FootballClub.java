package com.prudencia.java.domain;

import lombok.*;
import lombok.experimental.Accessors;


@Accessors(chain = true)
@Data
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@Builder
public class FootballClub {
    private String team_name;
    private String team_position;
    private int team_points;
}
