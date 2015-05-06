package com.example.tacotruck.sushigo;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by taco truck on 5/5/2015.
 */
public class Player {
    private String name;
    private int makiRolls, tempura, sashimi, dumplings, puddings, squidNigiri, salmonNigiri, eggNigiri;
    public Map<Integer, Integer> nigiriToWasabiMap = new HashMap<>();
    public static final Map<Integer, Integer> dumplingMap = new HashMap<Integer, Integer>(){
        {
            put(1,1);
            put(2,3);
            put(3,6);
            put(4,10);
            put(5,15);
        }
    };

    public Player() {
        name = "";
        makiRolls = 0;
        tempura = 0;
        sashimi = 0;
        dumplings = 0;
        puddings = 0;
        squidNigiri = 0;
        salmonNigiri = 0;
        eggNigiri = 0;
        initializeNigiriToWasabiMap();
    }

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
        initializeNigiriToWasabiMap();
    }

    private void initializeNigiriToWasabiMap(){
        nigiriToWasabiMap = new HashMap<>();
        nigiriToWasabiMap.put(0,0);
        nigiriToWasabiMap.put(1,0);
        nigiriToWasabiMap.put(2,0);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getMakiRolls() {
        return makiRolls;
    }

    public void setMakiRolls(int makiRolls) {
        this.makiRolls = makiRolls;
        System.out.println(this.name + " makiRolls = " + this.makiRolls);
    }

    public int getTempura() {
        return tempura;
    }

    public void setTempura(int tempura) {
        this.tempura = tempura;
    }

    public int getSashimi() {
        return sashimi;
    }

    public void setSashimi(int sashimi) {
        this.sashimi = sashimi;
    }

    public int getDumplings() {
        return dumplings;
    }

    public void setDumplings(int dumplings) {
        this.dumplings = dumplings;
    }

    public int getPuddings() {
        return puddings;
    }

    public void setPuddings(int puddings) {
        this.puddings = puddings;
    }

    public int getSquidNigiri() {
        return squidNigiri;
    }

    public void setSquidNigiri(int squidNigiri) {
        this.squidNigiri = squidNigiri;
    }

    public int getSalmonNigiri() {
        return salmonNigiri;
    }

    public void setSalmonNigiri(int salmonNigiri) {
        this.salmonNigiri = salmonNigiri;
    }

    public int getEggNigiri() {
        return eggNigiri;
    }

    public void setEggNigiri(int eggNigiri) {
        this.eggNigiri = eggNigiri;
    }

    public void resetScore(){
        this.makiRolls = 0;
        this.tempura = 0;
        this.sashimi = 0;
        this.dumplings = 0;
        this.salmonNigiri = 0;
        this.squidNigiri = 0;
        this.eggNigiri = 0;
        this.puddings = 0;
        initializeNigiriToWasabiMap();
    }
}
