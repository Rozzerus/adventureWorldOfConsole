package com.rozzer.adventure.unit.npc.enemy.type;

import com.rozzer.adventure.core.GameEngine;
import com.rozzer.adventure.core.RaceType;
import com.rozzer.adventure.unit.npc.enemy.AbstractEnemy;

/**
 * Created by Rozzer on 18.11.2016.
 */
public class OgreEnemy extends AbstractEnemy {
    public OgreEnemy(GameEngine gameEngine) {
        super(gameEngine);
        setRace(RaceType.OGRE);
    }
}
