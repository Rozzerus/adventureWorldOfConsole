package com.rozzer.adventure.world;

import com.rozzer.adventure.core.Constants;
import com.rozzer.adventure.core.GameEngine;
import com.rozzer.adventure.core.Site;
import com.rozzer.adventure.core.World;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by Rozzer on 16.11.2016.
 */
public class WorldImpl implements World {
    private static WorldImpl world;
    private GameEngine gameEngine;
    private Set<AbstractSite> sites;

    private WorldImpl() {
        sites = new HashSet<>();
    }

    public static WorldImpl getWorld(){
        if (world == null) {
            world = new WorldImpl();
        }
        return world;
    }

    public void setGameEngine(GameEngine gameEngine){
        this.gameEngine = gameEngine;
    }

    public GameEngine getGameEngine() {
        return gameEngine;
    }

    public void addSite(AbstractSite site){
        this.sites.add(site);
    }

    public void removeSite(AbstractSite site){
        this.sites.remove(site);
    }

    public Site getFirstSite(){
        return SitesFactory.getSitesFactory().getCityByRandom();
    }

    public AbstractSite getSiteByCoordinate(int x, int y){
        if (sites.size() == Constants.MAX_AMOUNT_SITES_BEFOR_BOSS){
            return SitesFactory.getSitesFactory().getBossSiteByCoordinate(x, y);
        }
        for (AbstractSite site :  sites) {
            if (site.getX() == x && site.getY() == y){
                return site;
            }
        }
        return SitesFactory.getSitesFactory().getSiteByCoordinate(x, y);
    }
}
