package com.peterpreneur.accounts.service;

import java.util.UUID;

import com.peterpreneur.accounts.dto.AccountResponse;
import com.peterpreneur.accounts.dto.CreateAccountRequest;
import com.peterpreneur.accounts.dto.UpdateAccountRequest;

public interface AccountService {

    AccountResponse createAccount(CreateAccountRequest request);

    AccountResponse updateAccount(UUID id, UpdateAccountRequest request);

    AccountResponse getAccountById(UUID id);

}
