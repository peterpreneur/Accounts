package com.peterpreneur.accounts.dto;

import java.time.LocalDate;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class CreateAccountRequest {

    private String accountNumber;
    private String statusName;
    private String statusReasonName;
    private LocalDate accountOpenDate;

}
