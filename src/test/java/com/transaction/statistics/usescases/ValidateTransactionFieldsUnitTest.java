package com.transaction.statistics.usescases;


import com.transaction.statistics.entities.Transaction;
import com.transaction.statistics.exceptions.InvalidFieldException;
import com.transaction.statistics.exceptions.OldTransactionException;
import com.transaction.statistics.usecases.ClearExpiredTransactionsTimer;
import com.transaction.statistics.usecases.ValidateTransactionFields;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.Instant;

import static com.transaction.statistics.usecases.ValidateTransactionFields.isOneMinuteOld;
import static com.transaction.statistics.usescases.fixtures.ValidateTransactionFieldsFixture.*;


@DisplayName("Validate transaction fields unit test")
@ExtendWith(MockitoExtension.class)
public class ValidateTransactionFieldsUnitTest {

    @InjectMocks
    private ValidateTransactionFields ValidateTransactionFields;

    @Test
    @DisplayName("Should validate fields successfully")
    void shouldValidateTransactionFields() {
        Transaction transaction = ValidateTransactionFields.execute(getValidTransactionDTO());
        Assertions.assertThat(transaction.getAmount()).isEqualTo(
            new BigDecimal("123123.12").setScale(2, RoundingMode.DOWN));
        Assertions.assertThat(transaction.getTimestamp()).isInstanceOf(Instant.class);
    }

    @Test
    @DisplayName("Should throw exception when invalid timestamp")
    void shouldThrowExceptionWhenInvalidTimestamp() {
        Assertions.assertThatExceptionOfType(OldTransactionException.class)
                .isThrownBy(() -> ValidateTransactionFields.execute(getOldTransactionDTO()));
    }

    @Test
    @DisplayName("Should throw exception when failed to parse")
    void shouldThrowExceptionWhenFailedToParse() {
        Assertions.assertThatExceptionOfType(InvalidFieldException.class)
                .isThrownBy(() -> ValidateTransactionFields.execute(getFutureTransactionDTO()));
    }

    @Test
    @DisplayName("Should validate old transaction successfully")
    void shouldValidateOldTransactionSuccessfully() {
        Assertions.assertThat(isOneMinuteOld(getOldTransaction())).isEqualTo(true);
    }
}
