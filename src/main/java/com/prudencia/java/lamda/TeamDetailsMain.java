package com.prudencia.java.lamda;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;

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

    public  static Team getSupplierTeam(){
        Supplier<Team> team = () -> new Team();
        return team.get();
    }

    public static void main(String[] args) {

        //Lambda implementaion
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

        //Lambda implementation - Sorting
        getSortedTeams(lstTeam);
        lstTeam.forEach((p) -> {
            System.out.println(p.getTeam_name());
        });

        getSortedTeamsByPoints(lstTeam);
        lstTeam.forEach((p) -> {
            System.out.println(p.getTeam_points());
        });

        // Predicate
        Predicate<Team> namePredicate = p -> p.getTeam_name().equals("barca");
        Predicate<Team> pointsPredicate = p -> p.getTeam_points() > 10;
        System.out.println(namePredicate.test(new Team("barca","1",100)));
        System.out.println(namePredicate.test(new Team("barca","1",100)));

        //Supplier
        getSupplierTeam();

        //Consumer Consumer<T> andThen(Consumer<? super T> after)
        Consumer<Team> consumer1 = t -> System.out.println("Team "+t.getTeam_name() + " Points "+ t.getTeam_points());
        Consumer<Team> consumer2 = t -> System.out.println("Team "+t.getTeam_name() + " Position "+ t.getTeam_position());
        consumer1.andThen(consumer2).accept(new Team("arsenal","2",98));


        //Functions
        Function<String, Integer> lengthFunction = input -> input.length();

        // Function which adds 10 to the given element.
        Function<Integer, Integer> increment = x -> x + 10;
        // Function which doubles the given element.
        Function<Integer, Integer> multiply = y -> y * 2;

        System.out.println("compose result: " + increment.apply(3));

        //compose(), multiplication will be done first and then increment will be done. --> return (V v) -> apply(before.apply(v));
        System.out.println("compose result: " + increment.compose(multiply).apply(3));

        // Since we are using andThen(), increment will be done first and then multiplication will be done. (T t) -> after.apply(apply(t));
        System.out.println("andThen result: " + increment.andThen(multiply).apply(3));



    }

}
