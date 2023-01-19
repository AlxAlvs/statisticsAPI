package com.transaction.statistics.usecases;

import com.transaction.statistics.entities.Transaction;
import com.transaction.statistics.entities.dtos.TransactionDTO;
import com.transaction.statistics.exceptions.InvalidFieldException;
import com.transaction.statistics.exceptions.OldTransactionException;
import com.transaction.statistics.mapper.TransactionMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import static com.transaction.statistics.usecases.ClearExpiredTransactionsTimer.isOneMinuteOld;


@Service
@Slf4j
public class ValidateFields {

    public Transaction execute(TransactionDTO transactionDTO) {
        Transaction transaction = validateConversionType(transactionDTO);
        validateOldTransaction(transaction);
        return transaction;
    }

    private static void validateOldTransaction(Transaction transaction) {
        if(isOneMinuteOld(transaction.getTimestamp())) {
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
}
