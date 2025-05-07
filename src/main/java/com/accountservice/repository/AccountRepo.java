package com.accountservice.repository;

import com.accountservice.model.Account;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;


/**
 * Repository interface for Mongo Database.
 */
@Repository
public interface AccountRepo extends MongoRepository<Account, String> {
}
