package com.rozzer.adventure.core;


/**
 * Created by Rozzer on 15.11.2016.
 */
public interface Hero extends Unit {
    public String getName();
    public void setName(String name);
    public Site getCurrentSite();
    public void setCurrentSite(Site site);
    public boolean isFirstFight();
    public boolean slip();
    public boolean talk();
    public boolean go();

}
