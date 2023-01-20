package com.transaction.statistics.services;

import com.transaction.statistics.entities.Transaction;
import com.transaction.statistics.entities.dtos.TransactionStatisticsDTO;


public interface TransactionService {
    Transaction save(Transaction transaction, String idempotencyKey);
    TransactionStatisticsDTO getStatistics();
}
