package com.rozzer.quest.core.option;

/**
 * Created by Rozzer on 15.11.2016.
 */
public enum OptionFight {
    SMASH("смэш"),
    HIT("удар"),
    RUN("бег");

    private String order;

    OptionFight(String state) {
        this.order = state;
    }


    @Override
    public String toString() {
        return order;
    }

    public static OptionFight fromString(String state) {
        for (OptionFight option : values()) {
            if (option.order.equalsIgnoreCase(state)) {
                return option;
            }
            if (option.name().equalsIgnoreCase(state)) {
                return option;
            }
        }
        return null;
    }

    public static boolean validString(String state) {
        for (OptionFight option : values()) {
            if (option.order.equalsIgnoreCase(state)) {
                return true;
            }
            if (option.name().equalsIgnoreCase(state)) {
                return true;
            }
        }
        return false;
    }
}
