package com.transaction.statistics.usescases;


import com.transaction.statistics.usecases.ClearExpiredTransactionsTimer;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import static com.transaction.statistics.repository.TransactionRepository.transactions;
import static com.transaction.statistics.usescases.fixtures.ValidateTransactionFieldsFixture.getOldTransaction;
import static com.transaction.statistics.usescases.fixtures.ValidateTransactionFieldsFixture.getValidTransaction;


@DisplayName("Validate transaction fields unit test")
@ExtendWith(MockitoExtension.class)
public class ClearExpiredTransactionsTimerUnitTest {

    @InjectMocks
    private ClearExpiredTransactionsTimer clearExpiredTransactionsTimer;

    @Test
    @DisplayName("Should remove element successfully")
    void shouldRemoveElementSuccessfully() {
        transactions.put(
            "a12312", getOldTransaction()
        );
        clearExpiredTransactionsTimer.removeExpiredTransactionsAndGenerateStatistics();
        Assertions.assertThat(transactions.size()).isEqualTo(0);
    }

    @Test
    @DisplayName("Should not remove element")
    void shouldNotRemoveElement() {
        transactions.put(
            "a12312",
            getValidTransaction()
        );
        clearExpiredTransactionsTimer.removeExpiredTransactionsAndGenerateStatistics();
        Assertions.assertThat(transactions.size()).isEqualTo(1);
    }
}
