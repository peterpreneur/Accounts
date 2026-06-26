package com.peterpreneur.accounts.dto;

import java.time.LocalDate;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class CreateAccountRequest {

    @NotBlank(message = "accountNumber is required")
    private String accountNumber;

    @NotBlank(message = "statusName is required")
    private String statusName;

    private String statusReasonName;

    @NotNull(message = "accountOpenDate is required")
    private LocalDate accountOpenDate;

}
