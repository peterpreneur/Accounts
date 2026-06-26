package com.peterpreneur.accounts.controller;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.UUID;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.webmvc.test.autoconfigure.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import com.peterpreneur.accounts.dto.AccountResponse;
import com.peterpreneur.accounts.dto.CreateAccountRequest;
import com.peterpreneur.accounts.dto.UpdateAccountRequest;
import com.peterpreneur.accounts.service.AccountService;

@WebMvcTest(AccountController.class)
public class AccountControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private AccountService accountService;

    @Test
    void shouldCreateAccount() throws Exception {

        AccountResponse response = AccountResponse.builder()
                .id(UUID.randomUUID())
                .accountNumber("ACC-1001")
                .statusName("OPEN")
                .statusReasonName("New account")
                .build();

        given(accountService.createAccount(any(CreateAccountRequest.class)))
                .willReturn(response);

        mockMvc.perform(post("/accounts")
                .contentType(MediaType.APPLICATION_JSON)
                .content("""
                        {
                          "accountNumber": "ACC-1001",
                          "statusName": "OPEN",
                          "statusReasonName": "New account",
                          "accountOpenDate": "2026-06-25"
                        }
                        """))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.accountNumber").value("ACC-1001"))
                .andExpect(jsonPath("$.statusName").value("OPEN"));
    }

    @Test
    void shouldRejectMissingAccountNumber() throws Exception {
        mockMvc.perform(post("/accounts")
                .contentType(MediaType.APPLICATION_JSON)
                .content("""
                            {
                              "statusName": "OPEN",
                              "accountOpenDate": "2026-06-25"
                            }
                            """))
                .andExpect(status().isBadRequest());
    }

    @Test
    void shouldUpdateAccount() throws Exception {
        UUID id = UUID.randomUUID();

        AccountResponse response = AccountResponse.builder()
                .id(id)
                .accountNumber("ACC-1001")
                .statusName("PENDING")
                .statusReasonName("Updated account")
                .build();

        given(accountService.updateAccount(eq(id), any(UpdateAccountRequest.class))).willReturn(response);

        mockMvc.perform(put("/accounts/{id}", id)
                .contentType(MediaType.APPLICATION_JSON)
                .content("""
                            {
                              "accountNumber": "ACC-1001",
                              "statusName": "PENDING",
                              "statusReasonName": "Updated account",
                              "accountOpenDate": "2026-06-25"
                            }
                            """))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.accountNumber").value("ACC-1001"))
                .andExpect(jsonPath("$.statusReasonName").value("Updated account"));
    }

}
