package com.transaction.statistics.mapper;

import com.transaction.statistics.entities.Transaction;
import com.transaction.statistics.entities.dtos.TransactionDTO;
import com.transaction.statistics.entities.dtos.TransactionResponseDTO;


public class TransactionMapper {

    public static TransactionDTO entityToDto(Transaction transaction) {

        return TransactionDTO.builder()
                .amount(transaction.getAmount())
                .timestamp(transaction.getTimestamp())
                .build();
    };

    public static Transaction dtoToEntity(TransactionDTO transactionDTO) {
        return Transaction.builder()
                .amount(transactionDTO.getAmount())
                .timestamp(transactionDTO.getTimestamp())
                .build();
    };

    public static TransactionResponseDTO entityToResponse(Transaction transaction) {
        return TransactionResponseDTO.builder()
                .amount(transaction.getAmount())
                .timestamp(transaction.getTimestamp())
                .build();
    };
}
