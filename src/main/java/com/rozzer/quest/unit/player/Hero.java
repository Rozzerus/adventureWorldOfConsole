package com.rozzer.quest.unit.player;

import com.rozzer.quest.unit.RaceType;
import com.rozzer.quest.world.AbstractSite;
import com.rozzer.quest.world.Site;

/**
 * Created by Rozzer on 15.11.2016.
 */
public interface Hero {
    public String getName();
    public void setName(String name);
    public AbstractSite getCurrentSite();
    public void setCurrentSite(AbstractSite site);
    public boolean isFirstFight();
    public boolean slip();
    public boolean talk();
    public boolean go();

}
