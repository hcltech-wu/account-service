package com.accountservice.controller;

import com.accountservice.model.Account;
import com.accountservice.repository.AccountRepo;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;


class AccountControllerTest {
    @Mock
    private AccountRepo accountRepo;

    @InjectMocks
    private AccountController accountController;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testGetAccountById_success() {

        Account mockAcc = new Account();
        String id = "1";
        Account mockAccount = new Account(id, "John Doe", "John@mail.com", "8798656789");
        when(accountRepo.findById(id)).thenReturn(Optional.of(mockAccount));

        ResponseEntity<Account> response = accountController.getAccWithId(id,"testcorrid","testsessionid","testclientid");

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(mockAccount, response.getBody());
    }

    @Test
    public void testGetAllAccounts() {
        Account account1 = new Account("1", "Checking", "ghfhg@mail.com", "464765");
        Account account2 = new Account("2", "Savings", "ghfhg@mail.com", "464765");
        List<Account> accounts = Arrays.asList(account1, account2);

        when(accountRepo.findAll()).thenReturn(accounts);

        ResponseEntity<List<Account>> response = accountController.getAllAccounts("testcorrid","testsessionid","testclientid");

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(accounts, response.getBody());
    }

    @Test
    public void testCreateAccount_success() {

        Account savedAccount = new Account("1", "Jane Doe","jkshf@mail.com", "8768676");
        when(accountRepo.save(savedAccount)).thenReturn(savedAccount);

        ResponseEntity<String> response = accountController.addAccount(savedAccount,"testcorrid","testsessionid","testclientid");

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("Account created", response.getBody());
    }
}
