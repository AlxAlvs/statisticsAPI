package com.transaction.statistics.entities.dtos;

import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.math.BigDecimal;
import java.time.Instant;


@Data
@Builder
@RequiredArgsConstructor
public class TransactionResponseDTO {
    private final BigDecimal amount;
    private final Instant timestamp;
}
