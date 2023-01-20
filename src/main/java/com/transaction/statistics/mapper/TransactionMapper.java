package com.transaction.statistics.mapper;

import com.transaction.statistics.entities.Transaction;
import com.transaction.statistics.entities.dtos.TransactionDTO;

import static com.transaction.statistics.utils.AmountConverter.stringToBigDecimal;
import static com.transaction.statistics.utils.TimestampConverter.stringToInstant;


public class TransactionMapper {

    public static Transaction dtoToEntity(TransactionDTO transactionDTO) {
        return Transaction.builder()
                .amount(stringToBigDecimal(transactionDTO.getAmount()))
                .timestamp(stringToInstant(transactionDTO.getTimestamp()))
                .build();
    }
}
