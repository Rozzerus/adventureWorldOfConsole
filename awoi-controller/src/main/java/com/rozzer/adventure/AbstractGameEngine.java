package com.rozzer.adventure;

import com.rozzer.adventure.core.GameEngine;
import com.rozzer.adventure.core.World;
import com.rozzer.adventure.core.exception.EndGame;
import com.rozzer.adventure.core.exception.NoWorldException;
import com.rozzer.adventure.world.WorldImpl;

import java.util.Objects;

public abstract class AbstractGameEngine implements GameEngine {


    public void startGame() throws EndGame {

    }

    public void endGame() throws EndGame {
        throw new EndGame();
    }

    @Override
    public World getWorld() throws NoWorldException {
        WorldImpl world = WorldImpl.getWorld();
        if (Objects.isNull(world)){
            throw new NoWorldException();
        }
        return world;
    }

}
