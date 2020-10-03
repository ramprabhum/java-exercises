package com.prudencia.java.lamda;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.function.Predicate;

public class TeamDetailsMain {

    public static void getTeamDetails(TeamDetails team, String str) {
        team.getTeamDetails(str);
    }

    public static void getSortedTeams(List<Team> teams){
        Collections.sort(teams, (t1,t2) -> t1.getTeam_name().compareTo(t2.getTeam_name()));
    }

    public  static void getSortedTeamsByPoints(List<Team> teams){
        Collections.sort(teams, new Comparator<Team>() {
            @Override
            public int compare(Team o1, Team o2) {
                return (o1.getTeam_points() > o2.getTeam_points() ? 1:0);
            }
        });
    }

    public static void main(String[] args) {
        getTeamDetails(new TeamDetails() {
            @Override
            public void getTeamDetails(String str) {
                System.out.println(str);
            }
        },"Barcelona");

        getTeamDetails((str) -> System.out.println(str+"- Lambada"),"Arsenal");
        List<Team> lstTeam = new ArrayList<Team>();
        lstTeam.add(new Team("barca","1",100));
        lstTeam.add(new Team("arsenal","2",98));
        getSortedTeams(lstTeam);
        Predicate<Team> namePredicate = p -> p.getTeam_name().equals("barca");
        Predicate<Team> pointsPredicate = p -> p.getTeam_points() > 10;
        System.out.println(namePredicate.test(new Team("barca","1",100)));
        System.out.println(namePredicate.test(new Team("barca","1",100)));

    }

}
