package com.peterpreneur.accounts.service.impl;

import java.util.UUID;

import org.springframework.stereotype.Service;

import com.peterpreneur.accounts.dto.AccountResponse;
import com.peterpreneur.accounts.dto.CreateAccountRequest;
import com.peterpreneur.accounts.dto.UpdateAccountRequest;
import com.peterpreneur.accounts.entity.Account;
import com.peterpreneur.accounts.repository.AccountRepository;
import com.peterpreneur.accounts.service.AccountService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AccountServiceImpl implements AccountService {

    private final AccountRepository accountRepository;

    @Override
    public AccountResponse createAccount(CreateAccountRequest request) {
        Account account = Account.builder()
                .accountNumber(request.getAccountNumber())
                .statusName(request.getStatusName())
                .statusReasonName(request.getStatusReasonName())
                .accountOpenDate(request.getAccountOpenDate())
                .build();

        Account savedAccount = accountRepository.save(account);
        return mapToResponse(savedAccount);
    }

    @Override
    public AccountResponse updateAccount(UUID id, UpdateAccountRequest request) {
        Account existingAccount = accountRepository.findById(id).orElseThrow(() -> new RuntimeException("Account not found with id: " + id));

        existingAccount.setAccountNumber(request.getAccountNumber());
        existingAccount.setStatusName(request.getStatusName());
        existingAccount.setStatusReasonName(request.getStatusReasonName());
        existingAccount.setAccountOpenDate(request.getAccountOpenDate());

        Account updatedAccount = accountRepository.save(existingAccount);
        return mapToResponse(updatedAccount);
    }

    @Override
    public AccountResponse getAccountById(UUID id) {
        Account account = accountRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Account not found with id: " + id));

        return mapToResponse(account);
    }

    private AccountResponse mapToResponse(Account account) {
        return AccountResponse.builder()
                .id(account.getId())
                .accountNumber(account.getAccountNumber())
                .statusName(account.getStatusName())
                .statusReasonName(account.getStatusReasonName())
                .accountOpenDate(account.getAccountOpenDate())
                .createdAt(account.getCreatedAt())
                .updatedAt(account.getUpdatedAt())
                .build();
    }

}
