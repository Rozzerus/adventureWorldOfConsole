package com.rozzer.adventure.unit.npc.enemy;


import com.rozzer.adventure.core.Constants;
import com.rozzer.adventure.core.RaceType;
import com.rozzer.adventure.core.Unit;
import com.rozzer.adventure.core.WeaponType;
import com.rozzer.adventure.unit.npc.enemy.type.ElfEnemy;
import com.rozzer.adventure.unit.npc.enemy.type.HumanEnemy;
import com.rozzer.adventure.unit.npc.enemy.type.OgreEnemy;
import com.rozzer.adventure.unit.npc.enemy.type.OrcEnemy;
import com.rozzer.adventure.world.WorldImpl;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Rozzer on 15.11.2016.
 */
public class EnemiesFactory implements Constants {
    private static final EnemiesFactory factory = new EnemiesFactory();
    private static Map<RaceType, AbstractEnemy> enemyMapping;

    private EnemiesFactory() {
        enemyMapping = new HashMap<>();
        enemyMapping.put(RaceType.ORC, new OrcEnemy(WorldImpl.getWorld().getGameEngine()));
        enemyMapping.put(RaceType.HUMAN, new HumanEnemy(WorldImpl.getWorld().getGameEngine()));
        enemyMapping.put(RaceType.ELF, new ElfEnemy(WorldImpl.getWorld().getGameEngine()));
    }

    public static EnemiesFactory getEnemiesFactory(){
        return factory;
    }

    public AbstractEnemy getEnemyByRace(RaceType race){
        AbstractEnemy enemy = enemyMapping.get(race);
        while (enemy.getHealth() < 20){
            enemy.setHealth(RANDOM.nextInt(50));//need normal generate HP
        }
        enemy.setWeapon(WeaponType.getRandom());
        enemy.setDescription(String.format(DESCRIPTION_RACE_WITH_WEAPON,
                enemy.getRace().toString(),
                enemy.getWeapon().toString()));
        return enemy;
    }

    public AbstractEnemy getEnemyByRandom() {
//        AbstractEnemy enemy = enemyMapping.get(RaceType.getRandom());
        AbstractEnemy enemy;
        RaceType raceType = RaceType.getRandomNotBoss();
        if (raceType.equals(RaceType.ELF)){//// FIXME: 18.11.2016
            enemy = new ElfEnemy(WorldImpl.getWorld().getGameEngine());
        } else if (raceType.equals(RaceType.HUMAN)){
            enemy = new HumanEnemy(WorldImpl.getWorld().getGameEngine());
        } else {
            enemy = new OrcEnemy(WorldImpl.getWorld().getGameEngine());
        }
        while (enemy.getHealth() < 20){
            enemy.setHealth(RANDOM.nextInt(50));//need normal generate HP
        }
        enemy.setWeapon(WeaponType.getRandom());
        enemy.setDescription(String.format(DESCRIPTION_RACE_WITH_WEAPON,
                enemy.getRace().toString(),
                enemy.getWeapon().toString()));
        return enemy;
    }

    public Unit getBossEnemyByRandom() {
        AbstractEnemy enemy = new OgreEnemy(WorldImpl.getWorld().getGameEngine());
        RaceType raceType = RaceType.getRandomBoss();
        if (raceType.equals(RaceType.OGRE)) {//// FIXME: 18.11.2016
            enemy = new OgreEnemy(WorldImpl.getWorld().getGameEngine());
        }
        enemy.setHealth(HEALTH_BOSS);
        enemy.setWeapon(WeaponType.getRandom());
        enemy.setDescription(String.format(DESCRIPTION_RACE_WITH_WEAPON,
                enemy.getRace().toString(),
                enemy.getWeapon().toString()));
        return enemy;
    }
}
