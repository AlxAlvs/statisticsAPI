package com.transaction.statistics.exceptions;

import org.springframework.http.HttpStatus;

public class OldTransactionException extends BaseBusinessException {

    protected OldTransactionException(Builder builder) {
        super(builder);
    }

    public static class Builder extends BaseBusinessException.Builder<OldTransactionException.Builder> {
        public Builder() {
            super(HttpStatus.NO_CONTENT.value(), "The transaction is older than 60 seconds");
        }

        @Override
        public OldTransactionException.Builder getThis() {
            return this;
        }

        @Override
        public OldTransactionException build() {
            return new OldTransactionException(this);
        }
    }
}
