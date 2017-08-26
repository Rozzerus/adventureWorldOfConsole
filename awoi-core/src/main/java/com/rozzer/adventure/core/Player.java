package com.rozzer.adventure.core;

import com.rozzer.adventure.core.exception.NoHeroException;

public interface Player {

    String answer();

    Hero getPlayerHero() throws NoHeroException;
}
