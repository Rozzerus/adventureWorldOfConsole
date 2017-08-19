package com.rozzer.quest.world;

import com.rozzer.quest.core.Constants;
import com.rozzer.quest.core.Depiction;
import com.rozzer.quest.core.Speaker;
import com.rozzer.quest.unit.Unit;
import com.rozzer.quest.unit.enemy.AbstractEnemy;
import com.rozzer.quest.unit.enemy.EnemiesFactory;

import java.util.ArrayList;

/**
 * Created by Rozzer on 16.11.2016.
 */
public abstract class AbstractSite implements Constants {
    private String description;
    private ArrayList<Unit> unitSet = new ArrayList<>();
    private int x;
    private int y;

    public AbstractSite(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public String getDescription() {
        return this.description;
    }

    public String getUnitsDescription() {
        String description = Depiction.HERE;
        if (unitSet.size() > 0) {
            for (Unit unit : unitSet) {
                if (unit.isLive()) {
                    description = description.concat("\n").concat(unit.getDescription().concat("("+unit.getHealth()+")"));
                } else {
                    description = description.concat("\n").concat(Depiction.DEAD.concat("- ").concat(
                            String.format(DESCRIPTION_RACE_WITH_WEAPON,
                                    unit.getRace().toString(),
                                    unit.getWeapon().toString())));
                }
            }
        } else {
            description = description.concat("\n").concat(Depiction.NOBODY_HERE);
        }
        return description;
    }

    public void addUnitsByRandom(){
        int amountUnits = RANDOM.nextInt(3);
        for (int i = 0; i < amountUnits; i++ ){
            addUnit(EnemiesFactory.getEnemiesFactory().getEnemyByRandom());
        }
    }

    public void addUnit(Unit unit) {
        unitSet.add(unit);
    }

    public boolean isHaveLiveEnemy(){
        for (Unit unit : unitSet) {
            if (unit instanceof AbstractEnemy && unit.isLive()){
                return true;
            }
        }
        return false;
    }

    public AbstractEnemy getRandomEnemy(){//need real random
        for (Unit unit : unitSet) {
            if (unit instanceof AbstractEnemy && unit.isLive()){
                return (AbstractEnemy) unit;
            }
        }
        Speaker.getSpeaker().say(Depiction.NON_ENEMIES);
        return null;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        y = y;
    }

    public AbstractSite getNorthSite() {
        return World.getWorld().getSiteByCoordinate(this.x,this.y+1);
    }

    public AbstractSite getSouthSite() {
        return World.getWorld().getSiteByCoordinate(this.x,this.y-1);
    }

    public AbstractSite getWestSite() {
        return World.getWorld().getSiteByCoordinate(this.x-1,this.y);
    }

    public AbstractSite getEastSite() {
        return World.getWorld().getSiteByCoordinate(this.x+1,this.y);
    }

}
