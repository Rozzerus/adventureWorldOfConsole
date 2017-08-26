package com.rozzer.adventure.unit.hero;

import com.rozzer.adventure.core.Constants;
import com.rozzer.adventure.core.GameEngine;
import com.rozzer.adventure.core.Hero;
import com.rozzer.adventure.core.Site;
import com.rozzer.adventure.unit.AbstractUnit;
import com.rozzer.adventure.world.WorldImpl;


/**
 * Created by Rozzer on 15.11.2016.
 */
public class MainHero extends AbstractUnit implements Hero, Constants {

    private String name;
    private boolean isFirstFight = true;
    private Site currentSite;
    private final GameEngine gameEngine;

    public MainHero(GameEngine gameEngine) {
        super(gameEngine);
        //FIXME it's hack.
        WorldImpl.getWorld().setGameEngine(gameEngine);
        this.gameEngine = gameEngine;
        setHealth(HEALTH_HERO);
    }

    @Override
    public Site getCurrentSite() {
        return this.currentSite;
    }

    @Override
    public void setCurrentSite(Site site) {
        this.currentSite = site;
    }

    @Override
    public boolean isFirstFight() {
        if (isFirstFight) {
            isFirstFight = false;
            return true;
        }
        return false;
    }

    @Override
    public boolean slip() {
        int slip = (int) (this.getRace().getAgility() * RANDOM_FACTOR[RANDOM.nextInt(RANDOM_FACTOR.length)]);
        if (RANDOM.nextInt(RATE_SLIP) > slip) {
            return false;
        }
        return true;
    }

    @Override
    public boolean talk() {
        int talk = (int) (this.getRace().getGuile() * RANDOM_FACTOR[RANDOM.nextInt(RANDOM_FACTOR.length)]);
        if (RANDOM.nextInt(RATE_TALK) > talk) {
            return false;
        }
        return true;
    }

    @Override
    public boolean go() {
        return RANDOM.nextBoolean();
    }

    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public void setName(String name) {
        this.name = name;
    }

    @Override
    public void die() {
        super.die();
        gameEngine.heroDies(this);
    }
}
