package com.rozzer.adventure.unit.enemy.type;

import com.rozzer.adventure.unit.RaceType;
import com.rozzer.adventure.unit.enemy.AbstractEnemy;

/**
 * Created by Rozzer on 15.11.2016.
 */
public class HumanEnemy extends AbstractEnemy {
    public HumanEnemy() {
        setRace(RaceType.HUMAN);
    }
}
