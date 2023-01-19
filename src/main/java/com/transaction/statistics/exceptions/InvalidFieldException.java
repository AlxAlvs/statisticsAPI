package com.transaction.statistics.exceptions;

import org.springframework.http.HttpStatus;

public class InvalidFieldException extends BaseBusinessException {

    protected InvalidFieldException(Builder builder) {
        super(builder);
    }

    public static class Builder extends BaseBusinessException.Builder<InvalidFieldException.Builder> {
        public Builder() {
            super(HttpStatus.UNPROCESSABLE_ENTITY.value(), "The transaction is in the future or could not be parsed");
        }

        @Override
        public InvalidFieldException.Builder getThis() {
            return this;
        }

        @Override
        public InvalidFieldException build() {
            return new InvalidFieldException(this);
        }
    }
}
