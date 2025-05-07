package com.accountservice;

import org.junit.jupiter.api.Test;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;


@SpringBootTest
class AccountApplicationTest {
	@Test
	void contextLoads(){ }

	@Test
	void mainMethodRunsWithoutExceptions(){
		assertDoesNotThrow(()-> AccountApplication.main(new String[] {}));
	}
}
