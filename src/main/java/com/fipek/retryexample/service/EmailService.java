package com.fipek.retryexample.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.retry.annotation.Backoff;
import org.springframework.retry.annotation.Recover;
import org.springframework.retry.annotation.Retryable;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.sql.SQLException;

@Slf4j
@Service
@RequiredArgsConstructor
public class EmailService {

    @Retryable(retryFor = SQLException.class)
    public void sendEmail(String orderDetails) throws SQLException {
        if (StringUtils.isEmpty(orderDetails)) {
            log.info("Throwing SQLException in method sendEmail()");
            throw new SQLException();
        }
    }

    @Recover
    private void recoverSendEmail(SQLException e, String orderDetails) {
        // Send event
        log.info("In recoverSendEmail method");
    }

    // WithBackoff

    @Retryable(retryFor = SQLException.class, recover = "recoverSendEmailWithBackoff",
            noRetryFor = RuntimeException.class, maxAttempts = 5,
            backoff = @Backoff(delay = 2000, multiplier = 2, maxDelay = 30000))
    public void sendEmailWithBackoff(String orderDetails) throws SQLException {
        if (StringUtils.isEmpty(orderDetails)) {
            log.info("Throwing SQLException in method sendEmailWithBackoff()");
            throw new SQLException();
        }
    }

    @Recover
    private void recoverSendEmailWithBackoff(SQLException e, String orderDetails) {
        // Send event
        log.info("In recoverSendEmailWithBackoff method");
    }
}
