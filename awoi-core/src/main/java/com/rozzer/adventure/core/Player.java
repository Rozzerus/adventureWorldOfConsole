package com.rozzer.adventure.core;

import com.rozzer.adventure.unit.player.MainHero;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * Created by Rozzer on 15.11.2016.
 */
public class Player  {
    private static Player player;
    private MainHero playerHero;
    private InputStreamReader streamReader = new InputStreamReader(System.in);
    private BufferedReader reader = new BufferedReader(streamReader);

    private Player() {
        playerHero = new MainHero();
    }

    public static Player getPlayer(){
        if (player == null) {
            player = new Player();
        }
        return player;
    }


    public String answer(){
        try {
            return reader.readLine();
        } catch (IOException e) {
            Speaker.getSpeaker().error();
            return answer();
        }
    }

    public MainHero getPlayerHero() {
        return playerHero;
    }
}
