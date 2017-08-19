package com.rozzer.quest.unit;

import com.rozzer.quest.unit.thing.WeaponType;

/**
 * Created by Rozzer on 15.11.2016.
 */
public interface Unit {
    public int getHealth();
    public void setHealth(int health);
    public String getDescription();
    public void setDescription(String description);
    public RaceType getRace();
    public void setRace(RaceType race);
    public WeaponType getWeapon();
    public void setWeapon(WeaponType weapon);
    public int attack();
    public boolean defense(int damage);
    public boolean run();
    public int addHealth(int health);
    public int removeHealth(int damage);
    public boolean isLive();
    public void die();
}
