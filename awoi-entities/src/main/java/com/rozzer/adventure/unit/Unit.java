package com.rozzer.adventure.unit;

import com.rozzer.adventure.unit.thing.WeaponType;

/**
 * Created by Rozzer on 15.11.2016.
 */
public interface Unit {
    int getHealth();
    void setHealth(int health);
    String getDescription();
    void setDescription(String description);
    RaceType getRace();
    void setRace(RaceType race);
    WeaponType getWeapon();
    void setWeapon(WeaponType weapon);
    int attack();
    boolean defense(int damage);
    boolean run();
    int addHealth(int health);
    int removeHealth(int damage);
    boolean isLive();
    void die();
}
