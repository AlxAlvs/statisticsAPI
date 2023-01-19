package com.transaction.statistics.exceptions;

import lombok.Getter;
import lombok.extern.slf4j.Slf4j;


@Slf4j
@Getter
public class BaseBusinessException extends RuntimeException {
    private final Integer errorStatusCode;
    private final String message;

    protected BaseBusinessException(Builder<?> builder) {
        errorStatusCode = builder.errorStatusCode;
        message = builder.message;
        log.error("Error. status: {}, message: {}", builder.errorStatusCode, builder.message);
    }

    public abstract static class Builder<T extends Builder<T>> {
        private final Integer errorStatusCode;
        private final String message;

        protected Builder(Integer errorStatusCode, String message) {
            this.errorStatusCode = errorStatusCode;
            this.message = message;
        }

        public abstract T getThis();

        public BaseBusinessException build() {
            return new BaseBusinessException(this);
        }
    }


}
