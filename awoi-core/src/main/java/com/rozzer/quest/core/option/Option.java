package com.rozzer.quest.core.option;

/**
 * Created by Rozzer on 15.11.2016.
 */
public enum Option {
    START("старт"),
    EMPTY_LINE(""),
    HELP("помощь"),
    EXIT("выйти");

    private String order;

    Option(String state) {
        this.order = state;
    }


    @Override
    public String toString() {
        return order;
    }

    public static Option fromString(String state) {
        for (Option option : values()) {
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
        for (Option option : values()) {
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
