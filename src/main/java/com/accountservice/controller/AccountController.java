package com.accountservice.controller;

import com.accountservice.model.Account;
import com.accountservice.repository.AccountRepo;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.accountservice.common.AppConstants.*;

@RestController
@RequestMapping("/accounts")
public class AccountController {
    private static final Logger logger = LogManager.getLogger(AccountController.class);
    @Autowired
    private AccountRepo accRepo;

    @GetMapping(value = "/getAllAccounts",
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Account>> getAllAccounts(
            @RequestHeader(value = X_CORRELATION_ID, required = false) String correlationId,
            @RequestHeader(value = X_SESSION_ID, required = false) String sessionId,
            @RequestHeader(value = X_CLIENT_ID, required = false) String clientId
    ){
        logger.info("Received request to fetch all accounts");
        logger.info("SessionId {} and ClientId {}", sessionId, clientId);
        try{
            List<Account> accounts = accRepo.findAll();
            if(accounts.isEmpty()){
                logger.warn("No accounts found in Database");
                return ResponseEntity.noContent().build();
            }
            logger.info("Successfully retrieved {} accounts", accounts.size());
            return ResponseEntity.ok(accounts);
        }
        catch(Exception e){
            logger.error("Failed to fetch accounts: {}", e.getMessage(), e);
            return ResponseEntity.internalServerError().build();
        }
    }

    @PostMapping(value = "/addAccount",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> addAccount(@RequestBody Account acc,
        @RequestHeader(value = X_CORRELATION_ID, required = false) String correlationId,
        @RequestHeader(value = X_SESSION_ID, required = false) String sessionId,
        @RequestHeader(value = X_CLIENT_ID, required = false) String clientId
    ){
        logger.info("Received request to create account for: {}", acc.getUsername());
        logger.info("SessionId {} and ClientId {}", sessionId, clientId);
        if(acc.getUsername() == null || acc.getUsername().isBlank()){
            logger.warn("Account creation failed: user name is empty");
            return ResponseEntity.badRequest().body("username can't be empty");
        }
        try {
            accRepo.save(acc);
            logger.info("Account saved successfully with account id: {}", acc.getId());
            return ResponseEntity.ok("Account created");
        }
        catch (Exception e){
            logger.error("Error while creating account: {}", e.getMessage(), e);
            return ResponseEntity.internalServerError().body("Account creation failed");
        }
    }

    @GetMapping(value = "/getAccount",
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Account> getAccWithId(
            @RequestParam String id,
            @RequestHeader(value = X_CORRELATION_ID, required = false) String correlationId,
            @RequestHeader(value = X_SESSION_ID, required = false) String sessionId,
            @RequestHeader(value = X_CLIENT_ID, required = false) String clientId
    ){
        logger.info("Fetching account with Id: {}", id);
        logger.info("SessionId {} and ClientId {}", sessionId, clientId);
        return accRepo.findById(id)
                .map(account -> {logger.info("Account Found for Id: {}", id);
                        return ResponseEntity.ok(account);
                }).orElseGet(()-> {logger.warn("No account found with Id: {}", id);
                        return ResponseEntity.notFound().build();
                });

    }
}
