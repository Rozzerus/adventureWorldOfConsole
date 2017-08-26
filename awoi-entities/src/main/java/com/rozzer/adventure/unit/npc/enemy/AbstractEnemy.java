package com.rozzer.adventure.unit.npc.enemy;

import com.rozzer.adventure.core.GameEngine;
import com.rozzer.adventure.core.Unit;
import com.rozzer.adventure.unit.AbstractUnit;

/**
 * Created by Rozzer on 15.11.2016.
 */
public abstract class AbstractEnemy extends AbstractUnit implements Enemy, Unit {


    protected AbstractEnemy(GameEngine gameEngine) {
        super(gameEngine);
    }
}
