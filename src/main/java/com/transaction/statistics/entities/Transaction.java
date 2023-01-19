package com.transaction.statistics.entities;

import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@Builder
@RequiredArgsConstructor
public class Transaction {
    private final String amount;
    private final String timestamp;
}
