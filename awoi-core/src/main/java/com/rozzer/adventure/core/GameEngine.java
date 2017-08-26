package com.rozzer.adventure.core;

import com.rozzer.adventure.core.exception.EndGame;
import com.rozzer.adventure.core.exception.NoPlayerException;
import com.rozzer.adventure.core.exception.NoWorldException;

public interface GameEngine {

    void startGame() throws EndGame;

    void endGame() throws EndGame;

    Player getPlayer() throws NoPlayerException;

    World getWorld() throws NoWorldException;

    void heroDies(Hero hero) throws NoPlayerException;

    void unitDies(Unit unit);

    void unitAttack(Unit unit, int attack);

    void unitDefense(Unit unit, int defense, int damage, boolean isSuccess);

    void unitRun(Unit unit, boolean isSuccess);
}
