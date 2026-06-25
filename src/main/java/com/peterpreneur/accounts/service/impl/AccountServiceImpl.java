package com.peterpreneur.accounts.service.impl;

import java.util.UUID;

import com.peterpreneur.accounts.dto.AccountResponse;
import com.peterpreneur.accounts.dto.CreateAccountRequest;
import com.peterpreneur.accounts.dto.UpdateAccountRequest;
import com.peterpreneur.accounts.service.AccountService;

public class AccountServiceImpl implements AccountService{

    

    @Override
    public AccountResponse createAccount(CreateAccountRequest request) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public AccountResponse updateAccount(UUID id, UpdateAccountRequest request) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public AccountResponse getAccountById(UUID id) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

}
