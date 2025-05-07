package com.accountservice.common;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class AppConstantsTest {
    @Test
    void testAppConstants(){
        assertEquals("X_CORRELATION_ID", AppConstants.X_CORRELATION_ID);
        assertEquals("X_SESSION_ID", AppConstants.X_SESSION_ID);
        assertEquals("X_CLIENT_ID", AppConstants.X_CLIENT_ID);
    }
}
