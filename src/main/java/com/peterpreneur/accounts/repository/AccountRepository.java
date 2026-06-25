package com.peterpreneur.accounts.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.peterpreneur.accounts.entity.Account;

public interface AccountRepository extends JpaRepository<Account, UUID> {

}
