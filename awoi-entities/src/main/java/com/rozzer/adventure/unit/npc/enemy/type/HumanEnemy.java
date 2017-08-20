package com.rozzer.adventure.unit.npc.enemy.type;

import com.rozzer.adventure.unit.RaceType;
import com.rozzer.adventure.unit.npc.enemy.AbstractEnemy;

/**
 * Created by Rozzer on 15.11.2016.
 */
public class HumanEnemy extends AbstractEnemy {
    public HumanEnemy() {
        setRace(RaceType.HUMAN);
    }
}
