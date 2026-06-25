package com.peterpreneur.accounts.dto;

import java.time.Instant;
import java.time.LocalDate;
import java.util.UUID;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
@AllArgsConstructor
public class AccountResponse {

    private UUID id;
    private String accountNumber;
    private String statusName;
    private String statusReasonName;
    private LocalDate accountOpenDate;
    private Instant createdAt;
    private Instant updatedAt;
}
