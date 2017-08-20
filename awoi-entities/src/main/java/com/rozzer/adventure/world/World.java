package com.rozzer.adventure.world;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by Rozzer on 16.11.2016.
 */
public class World {
    private static World world;
    private Set<AbstractSite> sites;

    private World() {
        sites = new HashSet<>();
    }
    public static World getWorld(){
        if (world == null) {
            world = new World();
        }
        return world;
    }
    public void addSite(AbstractSite site){
        this.sites.add(site);
    }
    public void removeSite(AbstractSite site){
        this.sites.remove(site);
    }
    public AbstractSite getFirstSite(){
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
