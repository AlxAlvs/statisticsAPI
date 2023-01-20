package com.transaction.statistics.entities.dtos;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class TransactionStatisticsDTO {
    private BigDecimal transactionSum;
    private BigDecimal transactionAvg;
    private BigDecimal transactionMax;
    private BigDecimal transactionMin;
    private Long transactionCount;
}
