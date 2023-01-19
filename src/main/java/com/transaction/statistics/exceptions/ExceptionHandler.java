package com.transaction.statistics.exceptions;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;

import javax.servlet.http.HttpServletRequest;
import java.time.Instant;

@ControllerAdvice
@Slf4j
public class ExceptionHandler {
    private static final String INTERNAL_SERVER_ERROR_MESSAGE = "Internal Server Error";

    @org.springframework.web.bind.annotation.ExceptionHandler
    public ResponseEntity<Void> handleNullPointerException(final NullPointerException ex) {
         log.error("Null pointer exception", ex);
         return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
    }

    @org.springframework.web.bind.annotation.ExceptionHandler
    public ResponseEntity<ErrorMessage> handleBaseBusinessException(
            final BaseBusinessException ex,
            final HttpServletRequest request) {
        log.error("BaseBusinessException", ex);

        int httpStatus = ex.getErrorStatusCode() != null ?
                ex.getErrorStatusCode() : HttpStatus.UNPROCESSABLE_ENTITY.value();

        return ResponseEntity.status(httpStatus)
                .body(ErrorMessage.builder()
                        .path(request.getRequestURI())
                        .status(httpStatus)
                        .timestamp(Instant.now().toString())
                        .message(ex.getMessage())
                        .build()
                );
    }
}
