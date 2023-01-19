package com.transaction.statistics.exceptions;

import io.swagger.annotations.ApiModel;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
@ApiModel(value = "ErrorMessage", description = "Standard error model")
public class ErrorMessage {

    private final String path;
    private final Integer status;
    private final String timestamp;
    private final String message;
}
