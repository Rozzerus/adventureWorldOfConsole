package com.rozzer.adventure;

import com.rozzer.adventure.core.GameEngine;
import com.rozzer.adventure.core.Player;
import com.rozzer.adventure.unit.hero.MainHero;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * Created by Rozzer on 15.11.2016.
 */
public class PlayerImpl implements Player {
    private MainHero playerHero;
    private InputStreamReader streamReader = new InputStreamReader(System.in);
    private BufferedReader reader = new BufferedReader(streamReader);
    private final GameEngine gameEngine;

    public PlayerImpl(GameEngine gameEngine) {
        this.gameEngine = gameEngine;
        playerHero = new MainHero(gameEngine);
    }

    public String answer(){
        try {
            return reader.readLine();
        } catch (IOException e) {
            return answer();
        }
    }

    public MainHero getPlayerHero() {
        return playerHero;
    }
}
