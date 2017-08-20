package com.rozzer.quest.unit.enemy.type;

import com.rozzer.quest.unit.RaceType;
import com.rozzer.quest.unit.enemy.AbstractEnemy;

/**
 * Created by Rozzer on 15.11.2016.
 */
public class HumanEnemy extends AbstractEnemy {
    public HumanEnemy() {
        setRace(RaceType.HUMAN);
    }
}
