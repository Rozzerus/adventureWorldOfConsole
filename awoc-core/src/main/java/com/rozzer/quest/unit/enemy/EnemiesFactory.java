package com.rozzer.quest.unit.enemy;

import com.rozzer.quest.core.Constants;
import com.rozzer.quest.unit.RaceType;
import com.rozzer.quest.unit.Unit;
import com.rozzer.quest.unit.enemy.type.OgreEnemy;
import com.rozzer.quest.unit.enemy.type.ElfEnemy;
import com.rozzer.quest.unit.enemy.type.HumanEnemy;
import com.rozzer.quest.unit.enemy.type.OrcEnemy;
import com.rozzer.quest.unit.thing.WeaponType;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Rozzer on 15.11.2016.
 */
public class EnemiesFactory implements Constants {
    private static EnemiesFactory factory;
    private static final Map<RaceType, AbstractEnemy> enemyMapping;
    static//// FIXME: 18.11.2016
    {
        enemyMapping = new HashMap<>();
        enemyMapping.put(RaceType.ORC, new OrcEnemy());
        enemyMapping.put(RaceType.HUMAN, new HumanEnemy());
        enemyMapping.put(RaceType.ELF, new ElfEnemy());
    }
    private EnemiesFactory() {
    }

    public static EnemiesFactory getEnemiesFactory(){
        if (factory == null) {
            factory = new EnemiesFactory();
        }
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
            enemy = new ElfEnemy();
        } else if (raceType.equals(RaceType.HUMAN)){
            enemy = new HumanEnemy();
        } else {
            enemy = new OrcEnemy();
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
        AbstractEnemy enemy = new OgreEnemy();
        RaceType raceType = RaceType.getRandomBoss();
        if (raceType.equals(RaceType.OGRE)) {//// FIXME: 18.11.2016
            enemy = new OgreEnemy();
        }
        enemy.setHealth(HEALTH_BOSS);
        enemy.setWeapon(WeaponType.getRandom());
        enemy.setDescription(String.format(DESCRIPTION_RACE_WITH_WEAPON,
                enemy.getRace().toString(),
                enemy.getWeapon().toString()));
        return enemy;
    }
}
