package com.accountservice.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * POJO class for account service.
 * the account data will be saved with account table in the database.
 */
@Document(collection = "account")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Account {
    private String id;
    private String username;
    private String email;
    private String phno;
}
