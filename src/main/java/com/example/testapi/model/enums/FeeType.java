package com.example.testapi.model.enums;

public enum FeeType {
    SHA,
    OUR,
    BEN;



    public static boolean isValid(FeeType value) {
        for (FeeType type : values()) {
            if (type.name().equalsIgnoreCase(value.name())) {
                return true;
            }
        }
        return false;
    }

    }
