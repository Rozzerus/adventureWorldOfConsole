package com.rozzer.adventure.core.option;

/**
 * Created by Rozzer on 15.11.2016.
 */
public enum OptionEnemy {
    ATTACK("сражаться"),
    SLIP("скрываться"),
    TALK("говорить"),
    GO("уйти");

    private String order;

    OptionEnemy(String state) {
        this.order = state;
    }


    @Override
    public String toString() {
        return order;
    }

    public static OptionEnemy fromString(String state) {
        for (OptionEnemy option : values()) {
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
        for (OptionEnemy option : values()) {
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
