package com.prudencia.java.domain;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class MapFunctions {


    private Map<Integer,Integer>  map = new HashMap<Integer,Integer>();

    public  void add(int number) {
       map.put(number, map.getOrDefault(number,0)+1);
    }

    public  void printMap() {
        System.out.println(map);
    }

    public  boolean find(int number) {
        for(int key: map.keySet()){
            System.out.println("key  "+ key);
            int target = number - key;

            if(map.containsKey(target)) {
                if (key == number && map.get(number) < 2) {
                    continue;
                }
                return true;
            }
        }
        return  false;
    }


    public static void main(String[] args) {
        MapFunctions mapFunctions = new MapFunctions();
        mapFunctions.add(2);
        mapFunctions.add(3);
        mapFunctions.add(3);
        mapFunctions.add(1);
        mapFunctions.add(2);
        mapFunctions.printMap();
        System.out.println( mapFunctions.find(8));

    }
}


