package com.accountservice.common;

import jakarta.servlet.*;
import org.apache.logging.log4j.ThreadContext;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;


import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

@ExtendWith(MockitoExtension.class)
class LoggerContextFilterTest {
    private LoggerContextFilter filter;
    @BeforeEach
    void setUp(){
        filter = new LoggerContextFilter();
    }
    @Test
    void testDoFilter_setsThreadContextWithHeaders() throws ServletException, IOException{
        MockHttpServletRequest request = new MockHttpServletRequest();
        request.addHeader("X_CORRELATION_ID","test-corrid");
        request.addHeader("X_SESSION_ID","test-sessionid");
        request.addHeader("X_CLIENT_ID","test-clientid");

        MockHttpServletResponse response = new MockHttpServletResponse();
        FilterChain chain = (req, res)-> {

            assertEquals("test-corrid", ThreadContext.get("correlationId"));
            assertEquals("test-sessionid", ThreadContext.get("sessionId"));
            assertEquals("test-clientid", ThreadContext.get("cleintId"));
        };
        filter.doFilter(request,response,chain);

        //verify(chain,times(1)).doFilter(request,response);

        assertNull(ThreadContext.get("correlationId"));
        assertNull(ThreadContext.get("sessionId"));
        assertNull(ThreadContext.get("cleintId"));
    }
}
