package com.transaction.statistics.usecases;

import com.transaction.statistics.entities.Transaction;
import lombok.extern.slf4j.Slf4j;

import java.time.Instant;
import java.util.Timer;
import java.util.TimerTask;

import static com.transaction.statistics.repository.TransactionRepository.transactions;


@Slf4j
public class ClearExpiredTransactionsTimer extends TimerTask {

    private static final int EXPIRE_SECONDS = 60;

    static Timer timer = new Timer();

    public final void run() {
        removeExpiredTransactions();
    }

    public static void start(){
        timer.schedule(new ClearExpiredTransactionsTimer(), 0, 1000);
    }

    public static void removeExpiredTransactions() {
        transactions.values().removeIf(transactionItem -> checkIfOneMinuteOld(transactionItem));
    }

    private static boolean checkIfOneMinuteOld(Transaction mapItem) {
        try {
            return isOneMinuteOld(mapItem.getTimestamp());
        } catch (Exception ex) {
            log.error("Failed to check for expired transactions", ex);
            return true;
        }
    }

    static boolean isOneMinuteOld(Instant TransactionTimestamp) {
        Instant now = Instant.now();
        Instant maxTime = now.minusSeconds(EXPIRE_SECONDS);
        return !(!TransactionTimestamp.isBefore(maxTime)) && TransactionTimestamp.isBefore(now);
    }
}
