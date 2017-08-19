package com.rozzer.quest.core.option;

/**
 * Created by Rozzer on 15.11.2016.
 */
public enum OptionTravel {
    GO_TO_EAST("восток"),
    GO_TO_WEST("запад"),
    GO_TO_NORTH("север"),
    GO_TO_SOUTH("юг");

    private String order;

    OptionTravel(String state) {
        this.order = state;
    }


    @Override
    public String toString() {
        return order;
    }

    public static OptionTravel fromString(String state) {
        for (OptionTravel option : values()) {
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
        for (OptionTravel option : values()) {
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
