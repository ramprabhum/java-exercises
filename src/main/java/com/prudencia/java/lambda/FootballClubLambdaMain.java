package com.prudencia.java.lambda;

import com.prudencia.java.domain.FootballClub;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;

public class FootballClubLambdaMain {

    public static void getTeamDetails(FootballClubDetails team, String str) {
        team.getTeamDetails(str);
    }

    public static void getSortedTeams(List<FootballClub> teams){
        Collections.sort(teams, (t1,t2) -> t1.getTeam_name().compareTo(t2.getTeam_name()));
    }

    public  static void getSortedTeamsByPoints(List<FootballClub> teams){
        Collections.sort(teams, new Comparator<FootballClub>() {
            @Override
            public int compare(FootballClub o1, FootballClub o2) {
                return (o1.getTeam_points() > o2.getTeam_points() ? 1:0);
            }
        });
    }

    public static FootballClub getSupplierTeam(){
        Supplier<FootballClub> team = () -> new FootballClub();
        return team.get();
    }

    public static void main(String[] args) {

        //Lambda implementaion
        getTeamDetails(new FootballClubDetails() {
            @Override
            public void getTeamDetails(String str) {
                System.out.println(str);
            }
        },"Barcelona");

        getTeamDetails((str) -> System.out.println(str+"- Lambada"),"Arsenal");


        List<FootballClub> lstTeam = new ArrayList<FootballClub>();
        lstTeam.add(new FootballClub("barca","1",100));
        lstTeam.add(new FootballClub("arsenal","2",98));

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
        Predicate<FootballClub> namePredicate = p -> p.getTeam_name().equals("barca");
        Predicate<FootballClub> pointsPredicate = p -> p.getTeam_points() > 10;
        System.out.println(namePredicate.test(new FootballClub("barca","1",100)));
        System.out.println(namePredicate.test(new FootballClub("barca","1",100)));

        //Supplier
        getSupplierTeam();

        //Consumer Consumer<T> andThen(Consumer<? super T> after)
        Consumer<FootballClub> consumer1 = t -> System.out.println("Team "+t.getTeam_name() + " Points "+ t.getTeam_points());
        Consumer<FootballClub> consumer2 = t -> System.out.println("Team "+t.getTeam_name() + " Position "+ t.getTeam_position());
        consumer1.andThen(consumer2).accept(new FootballClub("arsenal","2",98));


        //Functions
        Function<String, Integer> lengthFunction = input -> input.length();

        // Function which adds 10 to the given element.
        Function<Integer, Integer> increment = x -> x + 10;
        // Function which doubles the given element.
        Function<Integer, Integer> multiply = y -> y * 2;
        Function<FootballClub, FootballClub> footballClubFunction = f -> {f.setTeam_name(f.getTeam_name().toUpperCase()); return f;};

        System.out.println("compose result: " + increment.apply(3));

        //compose(), multiplication will be done first and then increment will be done. --> return (V v) -> apply(before.apply(v));
        System.out.println("compose result: " + increment.compose(multiply).apply(3));

        // Since we are using andThen(), increment will be done first and then multiplication will be done. (T t) -> after.apply(apply(t));
        System.out.println("andThen result: " + increment.andThen(multiply).apply(3));

        System.out.println("custom object result: "+ footballClubFunction.apply(FootballClub.builder().team_name("barca").team_points(100).team_position("1").build()));



    }

}
