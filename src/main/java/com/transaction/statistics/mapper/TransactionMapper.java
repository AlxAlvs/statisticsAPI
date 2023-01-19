package com.transaction.statistics.mapper;

import com.transaction.statistics.entities.Transaction;
import com.transaction.statistics.entities.dtos.TransactionDTO;
import com.transaction.statistics.entities.dtos.TransactionResponseDTO;

import static com.transaction.statistics.utils.AmountConverter.bigDecimalToString;
import static com.transaction.statistics.utils.AmountConverter.stringToBigDecimal;
import static com.transaction.statistics.utils.TimestampConverter.instantToString;
import static com.transaction.statistics.utils.TimestampConverter.stringToInstant;


public class TransactionMapper {

    public static TransactionDTO entityToDto(Transaction transaction) {

        return TransactionDTO.builder()
                .amount(bigDecimalToString(transaction.getAmount()))
                .timestamp(instantToString(transaction.getTimestamp()))
                .build();
    }

    public static Transaction dtoToEntity(TransactionDTO transactionDTO) {
        return Transaction.builder()
                .amount(stringToBigDecimal(transactionDTO.getAmount()))
                .timestamp(stringToInstant(transactionDTO.getTimestamp()))
                .build();
    }

    public static TransactionResponseDTO entityToResponse(Transaction transaction) {
        return TransactionResponseDTO.builder()
                .amount(transaction.getAmount())
                .timestamp(transaction.getTimestamp())
                .build();
    }
}
