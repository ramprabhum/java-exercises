package com.prudencia.java.stream;

import com.prudencia.java.domain.FootballClub;
import com.prudencia.java.lambda.FootballClubLambdaMain;

import java.util.AbstractList;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

public class FootballClubStreamMain {

    public static void main(String[] args) {

        FootballClub footballClub1 = FootballClub.builder().team_name("barca").team_points(100).team_position("1").build();
        FootballClub footballClub2 = FootballClub.builder().team_name("arsenal").team_points(98).team_position("3").build();
        List<FootballClub> clubList = new ArrayList<FootballClub>();
        clubList.add(footballClub1);
        clubList.add(footballClub2);


        //Filter streams
        clubList.stream().filter(t -> t.getTeam_points() >99).forEach(a -> System.out.println(a.getTeam_points()+ a.getTeam_name()));

        FootballClub footballClub3 = FootballClub.builder().team_name("bayern").team_points(99).team_position("2").build();
        List<FootballClub> clubList2 = new ArrayList<FootballClub>();
        clubList2.add(footballClub3);
        List<List<FootballClub>> lstClubList = new ArrayList<List<FootballClub>>();
        lstClubList.add(clubList);
        lstClubList.add(clubList2);

        // Stream collections
        Stream<FootballClub> stream2 = lstClubList.stream().flatMap(s -> s.stream());
        stream2.forEach(p -> System.out.println(p.getTeam_name()));
        lstClubList.stream().flatMap(s -> s.stream()).map(f -> f.getTeam_points()).forEach(System.out::println);

        //Optional

        Optional<FootballClub> optional = Optional.of(FootballClub.builder().team_name("barca").team_points(100).team_position("1").build());

        optional
                .map(a -> a.getTeam_position())
                .filter(p -> Integer.valueOf(p) <= 1)
                .ifPresent(System.out::println);


  }
}
