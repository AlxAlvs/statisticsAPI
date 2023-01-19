package com.transaction.statistics.entities.dtos;

import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;


@Data
@Builder
@RequiredArgsConstructor
public class TransactionResponseDTO {
    private final String amount;
    private final String timestamp;
}
