package com.transaction.statistics.utils;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class AmountConverter {
    public static BigDecimal stringToBigDecimal(String value) {
        return new BigDecimal(value).setScale(2, RoundingMode.HALF_UP).stripTrailingZeros();
    }

    public static String bigDecimalToString(BigDecimal value) {
        return value.toString();
    }
}
