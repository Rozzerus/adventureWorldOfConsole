package com.rozzer.quest.unit.player;

import com.rozzer.quest.core.Depiction;
import com.rozzer.quest.core.Speaker;
import com.rozzer.quest.unit.AbstractUnit;
import com.rozzer.quest.world.AbstractSite;

/**
 * Created by Rozzer on 15.11.2016.
 */
public class MainHero extends AbstractUnit implements Hero {

    private String name;
    private boolean isFirstFight = true;
    private AbstractSite currentSite;

    public MainHero() {
        setHealth(HEALTH_HERO);
    }

    @Override
    public AbstractSite getCurrentSite() {
        return this.currentSite;
    }

    @Override
    public void setCurrentSite(AbstractSite site) {
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
        Speaker.getSpeaker().say(String.format(Depiction.HERO_FAIL,this.name));
    }
}
