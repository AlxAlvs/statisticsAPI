package com.transaction.statistics.repository;

import com.transaction.statistics.entities.Transaction;
import com.transaction.statistics.services.TransactionService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

@Slf4j
@Service
public class TransactionRepository implements TransactionService {
    public static ConcurrentMap<String, Transaction> transactions = new ConcurrentHashMap<>();

    @Override
    public Transaction save(Transaction transaction, String idempotencyKey) {
        transactions.putIfAbsent(idempotencyKey, transaction);
        return transaction;
    }
}
