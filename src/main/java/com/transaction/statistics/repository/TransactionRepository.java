package com.transaction.statistics.repository;

import com.transaction.statistics.entities.Transaction;
import com.transaction.statistics.entities.dtos.TransactionStatisticsDTO;
import com.transaction.statistics.services.TransactionService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

import static com.transaction.statistics.utils.AmountConverter.stringToBigDecimal;

@Slf4j
@Service
public class TransactionRepository implements TransactionService {
    public static ConcurrentMap<String, Transaction> transactions = new ConcurrentHashMap<>();

    private static TransactionStatisticsDTO statisticsDTO;

    @Override
    public Transaction save(Transaction transaction, String idempotencyKey) {
        transactions.putIfAbsent(idempotencyKey, transaction);
        return transaction;
    }

    @Override
    public TransactionStatisticsDTO getStatistics() {
        return statisticsDTO;
    }

    public static void setCurrentStatisticValues(BigDecimal sum, BigDecimal avg, BigDecimal max, BigDecimal min) {
        statisticsDTO = TransactionStatisticsDTO.builder()
                .transactionAvg(stringToBigDecimal(avg.toPlainString()))
                .transactionSum(stringToBigDecimal(sum.toPlainString()))
                .transactionMin(stringToBigDecimal(min.toPlainString()))
                .transactionMax(stringToBigDecimal(max.toPlainString()))
                .transactionCount(Long.valueOf(transactions.size()))
                .build();
    }
}
