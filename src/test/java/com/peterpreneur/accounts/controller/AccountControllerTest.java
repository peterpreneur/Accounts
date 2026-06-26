package com.peterpreneur.accounts.controller;

import java.util.UUID;

import org.junit.jupiter.api.Test;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.webmvc.test.autoconfigure.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.peterpreneur.accounts.dto.AccountResponse;
import com.peterpreneur.accounts.dto.CreateAccountRequest;
import com.peterpreneur.accounts.service.AccountService;

@WebMvcTest(AccountController.class)
public class AccountControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private AccountService accountService;

    @Test
    void testCreateAccount() throws Exception {

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
}
