package com.transaction.statistics.usecases;

import com.transaction.statistics.entities.Transaction;
import lombok.extern.slf4j.Slf4j;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Iterator;
import java.util.Map;
import java.util.Timer;
import java.util.TimerTask;

import static com.transaction.statistics.repository.TransactionRepository.*;
import static com.transaction.statistics.usecases.ValidateTransactionFields.isOneMinuteOld;


@Slf4j
public class ClearExpiredTransactionsTimer extends TimerTask {

    static Timer timer = new Timer();

    public final void run() {
        removeExpiredTransactionsAndGenerateStatistics();
    }

    public static void start(){
        timer.schedule(new ClearExpiredTransactionsTimer(), 0, 1000);
    }

    public static void removeExpiredTransactionsAndGenerateStatistics() {
        BigDecimal sum = BigDecimal.ZERO;
        BigDecimal avg;
        BigDecimal max = BigDecimal.ZERO;
        BigDecimal min = BigDecimal.ZERO;

        for(Iterator<Map.Entry<String, Transaction>> it = transactions.entrySet().iterator(); it.hasNext(); ) {
            Map.Entry<String, Transaction> entry = it.next();
            if (isOneMinuteOld(entry.getValue())) {
                it.remove();
                continue;
            }
            sum = sumAmount(sum, entry);
            max = getMaximumValue(max, entry);
            min = getMinimumValue(min, entry);
        }
        avg = getAverageAmount(sum);
        setCurrentStatisticValues(sum, avg, max, min);
    }

    private static BigDecimal getMinimumValue(BigDecimal min, Map.Entry<String, Transaction> entry) {
        if (min.compareTo(BigDecimal.ZERO) == 0) {
            min = entry.getValue().getAmount();
        }
        return entry.getValue().getAmount().compareTo(min) < 0 ? entry.getValue().getAmount() : min;
    }

    private static BigDecimal getMaximumValue(BigDecimal max, Map.Entry<String, Transaction> entry) {
        return entry.getValue().getAmount().compareTo(max) > 0 ? entry.getValue().getAmount() : max;
    }

    private static BigDecimal getAverageAmount(BigDecimal sum) {
        return sum.compareTo(BigDecimal.ZERO) > 0
                ? sum.divide(new BigDecimal(transactions.size()), RoundingMode.DOWN)
                : BigDecimal.ZERO;
    }

    private static BigDecimal sumAmount(BigDecimal sum, Map.Entry<String, Transaction> entry) {
        return sum.add(entry.getValue().getAmount());
    }
}
