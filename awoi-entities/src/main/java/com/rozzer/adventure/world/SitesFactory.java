package com.rozzer.adventure.world;

import com.rozzer.adventure.core.Constants;

/**
 * Created by Rozzer on 16.11.2016.
 */
public class SitesFactory {
    private static SitesFactory factory;
    private SitesFactory() {
    }

    public static SitesFactory getSitesFactory(){
        if (factory == null) {
            factory = new SitesFactory();
        }
        return factory;
    }
    public AbstractSite getSiteByRandom(){
        SiteImpl site = new SiteImpl(0,0);
        site.setDescription(String.format(Constants.DESCRIPTION_SITES[Constants.RANDOM.nextInt(Constants.DESCRIPTION_SITES.length)], Constants.LOCATION));
        site.addUnitsByRandom();
        WorldImpl.getWorld().addSite(site);
        return  site;
    }

    public AbstractSite getSiteByCoordinate(int x, int y){
        SiteImpl site = new SiteImpl(x,y);
        site.setDescription(String.format(Constants.DESCRIPTION_SITES[Constants.RANDOM.nextInt(Constants.DESCRIPTION_SITES.length)], Constants.LOCATION));
        site.addUnitsByRandom();
        WorldImpl.getWorld().addSite(site);
        return  site;
    }

    public AbstractSite getCityByRandom() {
        City city = new City(0,0);
        city.setDescription(String.format(Constants.DESCRIPTION_CITIES[Constants.RANDOM.nextInt(Constants.DESCRIPTION_CITIES.length)], Constants.CITY));
        WorldImpl.getWorld().addSite(city);
        return  city;
    }

    public AbstractSite getBossSiteByCoordinate(int x, int y) {
        BossSite bossSite = new BossSite(x,y);
        bossSite.setDescription(String.format(Constants.DESCRIPTION_BOSS_SITE[Constants.RANDOM.nextInt(Constants.DESCRIPTION_BOSS_SITE.length)], Constants.LOCATION));
        bossSite.addBoss();
        WorldImpl.getWorld().addSite(bossSite);
        return  bossSite;
    }
}
