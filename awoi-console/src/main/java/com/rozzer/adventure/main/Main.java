package com.rozzer.adventure.main;

import com.rozzer.adventure.Speaker;
import com.rozzer.adventure.core.World;
import com.rozzer.adventure.core.exception.EndGame;
import com.rozzer.adventure.unit.hero.MainHero;

/**
 * Created by Rozzer on 15.11.2016.
 */
public class Main {
    public static void main(String[] args) {
        Speaker speaker = new Speaker();
        MainHero hero = (MainHero) speaker.getPlayer().getPlayerHero();
        World world = speaker.getWorld();

        try {
            speaker.startGame();
            speaker.greeting();
            speaker.askRace();
            hero.setCurrentSite(world.getFirstSite());
            speaker.launchPlayerInWorld();
            while (true) {
                speaker.whereToGo();
            }

        } catch (EndGame endGame) {
            speaker.gameOver();
        }

    }
}
