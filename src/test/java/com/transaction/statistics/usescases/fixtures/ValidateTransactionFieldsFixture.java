package com.transaction.statistics.usescases.fixtures;

import com.transaction.statistics.entities.Transaction;
import com.transaction.statistics.entities.dtos.TransactionDTO;

import java.math.BigDecimal;
import java.time.Instant;

public class ValidateTransactionFieldsFixture {

    public static TransactionDTO getValidTransactionDTO() {
        return TransactionDTO.builder()
                .amount("123123.12")
                .timestamp(Instant.now().minusSeconds(20).toString())
                .build();
    }

    public static TransactionDTO getOldTransactionDTO() {
        return TransactionDTO.builder()
                .amount("123123.12")
                .timestamp("2015-06-28T10:13:14.743Z")
                .build();
    }

    public static TransactionDTO getFutureTransactionDTO() {
        return TransactionDTO.builder()
                .amount("123123.12")
                .timestamp(Instant.now().plusSeconds(2000000).toString())
                .build();
    }

    public static Transaction getOldTransaction() {
        return Transaction.builder()
                .amount(new BigDecimal("123123.12"))
                .timestamp(Instant.now().minusSeconds(61))
                .build();
    }

    public static Transaction getValidTransaction() {
        return Transaction.builder()
                .amount(new BigDecimal("123123.12"))
                .timestamp(Instant.now().minusSeconds(59))
                .build();
    }
}
