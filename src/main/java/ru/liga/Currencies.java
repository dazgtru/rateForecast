package ru.liga;

import java.util.Arrays;

public enum Currencies {
    EURO,
    TRY,
    USD,
    BGN,
    AMD;

    public static String[] getNames(Class<? extends Enum<?>> e) {
        return Arrays.stream(e.getEnumConstants()).map(Enum::name).toArray(String[]::new);
    }
}


