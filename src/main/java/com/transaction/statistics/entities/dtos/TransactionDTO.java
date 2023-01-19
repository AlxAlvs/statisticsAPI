package com.transaction.statistics.entities.dtos;

import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import javax.validation.constraints.NotBlank;

@Data
@Builder
@RequiredArgsConstructor
public class TransactionDTO {

    @NotBlank(message = "Amount is empty")
    private final String amount;

    @NotBlank(message = "Timestamp is empty")
    private final String timestamp;

}
