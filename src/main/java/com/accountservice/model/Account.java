package com.accountservice.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import org.springframework.data.mongodb.core.mapping.Document;

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
