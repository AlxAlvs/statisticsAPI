package com.transaction.statistics.services;

import com.transaction.statistics.entities.Transaction;


public interface TransactionService {
    Transaction save(Transaction transaction, String idempotencyKey);
}
