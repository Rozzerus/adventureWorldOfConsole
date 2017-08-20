package com.rozzer.adventure.menu;

import com.rozzer.adventure.core.Player;
import com.rozzer.adventure.core.Speaker;
import com.rozzer.adventure.core.exception.EndGame;
import com.rozzer.adventure.unit.player.MainHero;
import com.rozzer.adventure.world.World;

/**
 * Created by Rozzer on 15.11.2016.
 */
public class Main {
    public static void main(String[] args) {
        Speaker speaker = Speaker.getSpeaker();
        MainHero hero = Player.getPlayer().getPlayerHero();
        World world = World.getWorld();

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
