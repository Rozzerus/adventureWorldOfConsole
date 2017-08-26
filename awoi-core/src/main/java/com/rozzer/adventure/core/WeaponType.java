package com.rozzer.adventure.core;


/**
 * Created by Rozzer on 15.11.2016.
 */
public enum WeaponType {
    AXE("Топор", 6),
    SWORD("Меч", 5),
    BOW("Лук", 4);

    private String name;
    private int attack;

    WeaponType(String state, int attack) {
        this.name = state;
        this.attack = attack;
    }


    @Override
    public String toString() {
        return name;
    }

    public int getAttack() {
        return attack;
    }

    public static WeaponType fromString(String state) {
        for (WeaponType weapon : values()) {
            if (weapon.name.equalsIgnoreCase(state)) {
                return weapon;
            }
            if (weapon.name().equalsIgnoreCase(state)) {
                return weapon;
            }
        }
        return null;
    }

    public static WeaponType getRandom(){
        return WeaponType.valueOf(Constants.RANDOM.nextInt(WeaponType.values().length));
    }

    public static WeaponType valueOf(int number){
        WeaponType[] reces = WeaponType.values();
        return reces[number];
    }
}
