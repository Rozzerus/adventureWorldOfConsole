package com.rozzer.adventure.unit.npc.enemy.type;

import com.rozzer.adventure.core.GameEngine;
import com.rozzer.adventure.core.RaceType;
import com.rozzer.adventure.unit.npc.enemy.AbstractEnemy;

/**
 * Created by Rozzer on 15.11.2016.
 */
public class ElfEnemy extends AbstractEnemy {
    public ElfEnemy(GameEngine gameEngine) {
        super(gameEngine);
        setRace(RaceType.ELF);
    }
}
