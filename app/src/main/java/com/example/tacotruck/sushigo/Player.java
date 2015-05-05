package com.example.tacotruck.sushigo;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by taco truck on 5/5/2015.
 */
public class Player {
//    public static Player player1 = null;
//    public static Player player2 = null;
    private static String name;
    private static int makiRolls, tempura, sashimi, dumplings, puddings, squidNigiri, salmonNigiri, eggNigiri;
    public static final Map<Integer, Integer> dumplingMap = new HashMap<Integer, Integer>(){
        {
            put(1,1);
            put(2,3);
            put(3,6);
            put(4,10);
            put(5,15);
        }
    };

    public Player(String myname) {
        name = myname;
        makiRolls = 0;
        tempura = 0;
        sashimi = 0;
        dumplings = 0;
        puddings = 0;
        squidNigiri = 0;
        salmonNigiri = 0;
        eggNigiri = 0;
    }

//    public static void initializePlayers(){
//        if(player1 == null){
//            player1 = new Player("player1");
//            nameToPlayer.put("player1", player1);
//            System.out.println("created player 1...");
//            System.out.println(player1.getName());
//
//            player2 = new Player("player2");
//            nameToPlayer.put("player2", player2);
//            System.out.println("created player 2...");
//            System.out.println(player2.getName());
//        }
//        if(player2 == null){
//            player2 = new Player("player2");
//            nameToPlayer.put("player2", player2);
//            System.out.println(player2.getName());
//            System.out.println("creating player 2...");
//        }
//
//        System.out.println(player1.getName() + " " + player2.getName());
//    }

    public static String getName() {
        return name;
    }

    public static int getMakiRolls() {
        return makiRolls;
    }

    public static void setMakiRolls(int makiRolls) {
        Player.makiRolls = makiRolls;
    }

    public static int getTempura() {
        return tempura;
    }

    public static void setTempura(int tempura) {
        Player.tempura = tempura;
    }

    public static int getSashimi() {
        return sashimi;
    }

    public static void setSashimi(int sashimi) {
        Player.sashimi = sashimi;
    }

    public static int getDumplings() {
        return dumplings;
    }

    public static void setDumplings(int dumplings) {
        Player.dumplings = dumplings;
    }

    public static int getPuddings() {
        return puddings;
    }

    public static void setPuddings(int puddings) {
        Player.puddings = puddings;
    }

    public static int getSquidNigiri() {
        return squidNigiri;
    }

    public static void setSquidNigiri(int squidNigiri) {
        Player.squidNigiri = squidNigiri;
    }

    public static int getSalmonNigiri() {
        return salmonNigiri;
    }

    public static void setSalmonNigiri(int salmonNigiri) {
        Player.salmonNigiri = salmonNigiri;
    }

    public static int getEggNigiri() {
        return eggNigiri;
    }

    public static void setEggNigiri(int eggNigiri) {
        Player.eggNigiri = eggNigiri;
    }

    public static void resetScore(Player playerx){
        playerx.makiRolls = 0;
        playerx.tempura = 0;
        playerx.sashimi = 0;
        playerx.dumplings = 0;
        playerx.salmonNigiri = 0;
        playerx.squidNigiri = 0;
        playerx.eggNigiri = 0;
        playerx.puddings = 0;
    }
}
