package com.rozzer.quest.world;

import com.rozzer.quest.unit.enemy.EnemiesFactory;

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
