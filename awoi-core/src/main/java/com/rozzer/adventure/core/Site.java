package com.rozzer.adventure.core;

public interface Site {

    String getDescription();

    Unit getRandomEnemy();

    boolean isHaveLiveEnemy();

    Site getEastSite();

    Site getNorthSite();

    Site getSouthSite();

    Site getWestSite();

    String getUnitsDescription();

}
