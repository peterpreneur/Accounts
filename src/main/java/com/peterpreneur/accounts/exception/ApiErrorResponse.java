package com.peterpreneur.accounts.exception;

import java.time.Instant;
import java.util.Map;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ApiErrorResponse {

    private Instant timestamp;
    private int status;
    private String error;
    private String message;
    private String path;
    private Map<String, String> validationErrors;
}
