package com.rozzer.adventure.world;

import com.rozzer.adventure.unit.npc.enemy.EnemiesFactory;

/**
 * Created by Rozzer on 18.11.2016.
 */
public class BossSite extends AbstractSite {
    public BossSite(int x, int y) {
        super(x, y);
    }
    public void addBoss(){
        addUnit(EnemiesFactory.getEnemiesFactory().getBossEnemyByRandom());
    }
}
