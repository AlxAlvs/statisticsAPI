package com.transaction.statistics.entities;

import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.math.BigDecimal;
import java.time.Instant;

@Data
@Builder
@RequiredArgsConstructor
public class Transaction {
    private final BigDecimal amount;
    private final Instant timestamp;
}
