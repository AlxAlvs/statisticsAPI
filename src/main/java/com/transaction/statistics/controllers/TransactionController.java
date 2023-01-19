package com.transaction.statistics.controllers;

import com.transaction.statistics.entities.Transaction;
import com.transaction.statistics.entities.dtos.TransactionDTO;
import com.transaction.statistics.entities.dtos.TransactionResponseDTO;
import com.transaction.statistics.mapper.TransactionMapper;
import com.transaction.statistics.services.TransactionService;
import com.transaction.statistics.usecases.ClearExpiredTransactionsTimer;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import java.util.concurrent.atomic.AtomicBoolean;

@RestController
@Slf4j
@RequestMapping(path = "/api/v1")
@Api(value = "Statistics", tags = {"Statistics"})
@RequiredArgsConstructor
public class TransactionController {

    private AtomicBoolean isInitialized = new AtomicBoolean(false);

    private final TransactionService transactionService;

    @ApiOperation(
        value = "Save transaction",
        consumes = MediaType.APPLICATION_JSON_VALUE,
        produces = MediaType.APPLICATION_JSON_VALUE
    )
    @ApiResponses(
        value = {
            @ApiResponse(code = 201, message = "Transaction saved successfully"),
            @ApiResponse(code = 400, message = "Bad request"),
            @ApiResponse(code = 204, message = "The transaction is older than 60 seconds"),
            @ApiResponse(code = 422, message = "Failed to parse/transaction date is in the future")
        }
    )
    @ResponseStatus(value = HttpStatus.CREATED)
    @PostMapping(
        path = "/transaction",
        produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<TransactionResponseDTO> saveTransaction (
            @RequestHeader("idempotency-key")
            @NotBlank(message = "Idempotency key is empty") final String idempotencyKey,
            @RequestBody @Valid TransactionDTO transactionDTO) {
        beginClearExpiredTransactionsRoutine();
        Transaction transaction = transactionService.save(
                TransactionMapper.dtoToEntity(transactionDTO), idempotencyKey);
        return new ResponseEntity<>(TransactionMapper.entityToResponse(transaction), HttpStatus.CREATED);
    }

    private void beginClearExpiredTransactionsRoutine() {
        if (isInitialized.compareAndSet(false, true)) {
            ClearExpiredTransactionsTimer.start();
        }
    }
}
