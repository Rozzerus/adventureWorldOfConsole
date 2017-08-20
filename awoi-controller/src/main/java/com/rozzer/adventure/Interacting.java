package com.rozzer.adventure;

import com.rozzer.adventure.core.Interaction;
import com.rozzer.adventure.core.exception.EndGame;

public interface Interacting  extends Interaction{

    void startGame() throws EndGame;
    void endGame() throws EndGame;

}
