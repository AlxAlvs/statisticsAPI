package com.transaction.statistics.utils;


import java.time.Instant;

public class TimestampConverter {
    public static Instant stringToInstant(String value) {
        return Instant.parse(value);
    }

    public static String InstantToString(Instant value) {
        return value.toString();
    }
}
