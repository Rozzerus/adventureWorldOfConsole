package com.rozzer.quest.unit;

import com.rozzer.quest.core.Constants;

/**
 * Created by Rozzer on 15.11.2016.
 */
public enum RaceType {
    ORC("Орк", 1, 5, 3, 5, 1, 0, false),
    HUMAN("Человек", 3, 1, 5, 4, 1, 1, false),
    ELF("Эльф", 5, 3, 1, 0, 2, 5, false),
    OGRE("Огр", 7, 1, 1, 5, 5, 0, true);

    private String name;
    private int agility;
    private int strength;
    private int guile;
    private int axeBonus;
    private int swordBonus;
    private int bowBonus;
    private boolean isBoss;

    RaceType(String state, int agility, int strength, int guile, int axeBonus, int swordBonus, int bowBonus, boolean isBoss) {
        this.name = state;
        this.agility = agility;
        this.strength = strength;
        this.guile = guile;
        this.axeBonus = axeBonus;
        this.swordBonus = swordBonus;
        this.bowBonus = bowBonus;
        this.isBoss = isBoss;
    }


    @Override
    public String toString() {
        return name;
    }

    public int getAgility() {
        return agility;
    }

    public int getStrength() {
        return strength;
    }

    public int getGuile() {
        return guile;
    }

    public int getAxeBonus() {
        return axeBonus;
    }

    public int getSwordBonus() {
        return swordBonus;
    }

    public int getBowBonus() {
        return bowBonus;
    }

    public boolean isBoss() {
        return isBoss;
    }

    public static RaceType fromString(String state) {
        for (RaceType race : values()) {
            if (race.name.equalsIgnoreCase(state)) {
                return race;
            }
            if (race.name().equalsIgnoreCase(state)) {
                return race;
            }
        }
        return null;
    }

    public static boolean validString(String state) {
        for (RaceType race : values()) {
            if (race.name.equalsIgnoreCase(state)) {
                return true;
            }
            if (race.name().equalsIgnoreCase(state)) {
                return true;
            }
        }
        return false;
    }

    public static RaceType getRandom(){
        return RaceType.valueOf(Constants.RANDOM.nextInt(RaceType.values().length));
    }
    public static RaceType getRandomBoss(){
        while (true) {
            RaceType raceType = RaceType.valueOf(Constants.RANDOM.nextInt(RaceType.values().length));
            if (raceType.isBoss) {
                return raceType;
            }
        }
    }
    public static RaceType getRandomNotBoss(){
        while (true) {
            RaceType raceType = RaceType.valueOf(Constants.RANDOM.nextInt(RaceType.values().length));
            if (!raceType.isBoss) {
                return raceType;
            }
        }
    }

    public static RaceType valueOf(int number){
        RaceType[] reces = RaceType.values();
        return reces[number];
    }
}
