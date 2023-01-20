package com.transaction.statistics.services;

import com.transaction.statistics.entities.Transaction;
import com.transaction.statistics.entities.dtos.TransactionStatisticsDTO;


public interface TransactionService {
    void save(Transaction transaction, String idempotencyKey);
    TransactionStatisticsDTO getStatistics();
    void deleteTransactions();
}
