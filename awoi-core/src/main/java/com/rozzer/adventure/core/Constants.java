package com.rozzer.adventure.core;

import java.util.Random;

/**
 * Created by Rozzer on 15.11.2016.
 */
public interface Constants {
    Random RANDOM = new Random();
    double FACTOR_ATTACK_AGILITY = 0.1;
    double FACTOR_DEFENSE_AGILITY = 0.3;
    int STANDART_BONUS = 1;
    int RATE_DEFENSE = 10;
    int RATE_TALK = 7;
    int RATE_SLIP = 7;
    int MAX_AMOUNT_SITES_BEFOR_BOSS = 7;
    int HEALTH_HERO = 200;
    int HEALTH_BOSS = 100;
    int HEALING_HERO_AFTER_VICTORY = 100;
    String DESCRIPTION_RACE_AND_WEAPON = "%s в руках у него %s ";
    String DESCRIPTION_RACE_WITH_WEAPON = "%s с %sом ";
    String [] ALSO_HERE = new String[]{
            "так же тут ",
            "ещё здесь ",
            "и здесь же "
    };
    String CITY = "город";
    String LOCATION = "местность";
    String [] DESCRIPTION_SITES = new String[]{
            "%s скалистые вершины",
            "%s утёс орла",
            "%s великие горы",
            "%s мёртвые топи",
            "%s городские руины",
            "%s песочные барханы",
            "%s волшебный лес",
            "%s илистые озёра",
            "%s великие поля",
            "%s мелкая деревушка",
            "%s тёмный лес"
    };
    String [] DESCRIPTION_CITIES = new String[]{
            "%s Роззеруск"
    };
    String [] DESCRIPTION_BOSS_SITE = new String[]{
            "%s гора с пещерой"
    };
    double [] RANDOM_FACTOR = new double[]{
            0.5,
            0.9,
            1.1,
            1.2,
            1.3,
            1.4,
            1.5,
            1.6,
            1.7,
            1.8,
            1.9,
            2.2
    };
    int [] RANDOM_BONUS = new int[]{
            2,
            3,
            4
    };
}
