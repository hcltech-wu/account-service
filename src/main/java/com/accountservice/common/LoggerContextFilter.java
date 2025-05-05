package com.accountservice.common;

import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import org.apache.logging.log4j.ThreadContext;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.UUID;

@Component
public class LoggerContextFilter implements Filter {
    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest httpReq = (HttpServletRequest) request;
        String correlationId = getOrGenerate(httpReq.getHeader("X_CORRELATION_ID"));
        String sessionId = getOrGenerate(httpReq.getHeader("X_SESSION_ID"));
        String cleintId = getOrGenerate(httpReq.getHeader("X_CLIENT_ID"));

        ThreadContext.put("correlationId",correlationId);
        ThreadContext.put("sessionId",sessionId);
        ThreadContext.put("cleintId",cleintId);

        try{
            chain.doFilter(request, response);
        }
        finally {
            ThreadContext.clearAll();
        }
    }

    private String getOrGenerate(String header){
        return (header != null && !header.isEmpty())? header: UUID.randomUUID().toString();
    }
}
