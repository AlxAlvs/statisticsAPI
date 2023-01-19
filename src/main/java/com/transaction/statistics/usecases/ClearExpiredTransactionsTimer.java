package com.transaction.statistics.usecases;

import lombok.extern.slf4j.Slf4j;
import java.util.Timer;
import java.util.TimerTask;

import static com.transaction.statistics.repository.TransactionRepository.transactions;
import static com.transaction.statistics.usecases.ValidateTransactionFields.isOneMinuteOld;


@Slf4j
public class ClearExpiredTransactionsTimer extends TimerTask {

    static Timer timer = new Timer();

    public final void run() {
        removeExpiredTransactions();
    }

    public static void start(){
        timer.schedule(new ClearExpiredTransactionsTimer(), 0, 1000);
    }

    public static void removeExpiredTransactions() {
        transactions.values().removeIf(transactionItem -> isOneMinuteOld(transactionItem));
    }
}
