package com.transaction.statistics.usecases;

import com.transaction.statistics.entities.Transaction;
import com.transaction.statistics.entities.dtos.TransactionDTO;
import com.transaction.statistics.exceptions.InvalidFieldException;
import com.transaction.statistics.exceptions.OldTransactionException;
import com.transaction.statistics.mapper.TransactionMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.Instant;


@Service
@Slf4j
public class ValidateTransactionFields {

    private static final int EXPIRE_SECONDS = 60;

    public Transaction execute(TransactionDTO transactionDTO) {
        Transaction transaction = validateConversionType(transactionDTO);
        validateOldTransaction(transaction);
        validateFutureTransaction(transaction);
        return transaction;
    }

    private static void validateOldTransaction(Transaction transaction) {
        if(isOneMinuteOld(transaction)) {
            log.error("Transaction is old");
            throw new OldTransactionException.Builder().build();
        }
    }

    private static Transaction validateConversionType(TransactionDTO transactionDTO) {
        try {
            return TransactionMapper.dtoToEntity(transactionDTO);
        } catch (Exception ex) {
            log.error("Error while parsing transaction fields.", ex);
            throw new InvalidFieldException.Builder().build();
        }
    }

    private static void validateFutureTransaction(Transaction transaction) {
        if(isFutureTransaction(transaction)) {
            log.error("Transaction is in the future");
            throw new InvalidFieldException.Builder().build();
        }
    }

    private static boolean isFutureTransaction(Transaction transaction) {
        return transaction.getTimestamp().isAfter(Instant.now());
    }

    public static boolean isOneMinuteOld(Transaction transaction) {
        Instant now = Instant.now();
        Instant maxTime = now.minusSeconds(EXPIRE_SECONDS);
        return !(!transaction.getTimestamp().isBefore(maxTime)) && transaction.getTimestamp().isBefore(now);
    }
}
