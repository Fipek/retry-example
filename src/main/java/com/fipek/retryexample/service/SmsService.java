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
public class SmsService {

    @Retryable(retryFor = SQLException.class)
    public void sendSms(String orderDetails) throws SQLException {
        if (StringUtils.isEmpty(orderDetails)) {
            log.info("Throwing SQLException in method sendSms()");
            throw new SQLException();
        }
    }

    @Recover
    private void recoverSendSms(SQLException e, String orderDetails) {
        // Send event
        log.info("In recoverSendSms method");
    }

    // WithBackoff

    @Retryable(retryFor = SQLException.class, recover = "recoverSendSmsWithBackoff",
            noRetryFor = RuntimeException.class, maxAttempts = 5,
            backoff = @Backoff(delay = 2000, multiplier = 2, maxDelay = 30000))
    public void sendSmsWithBackoff(String orderDetails) throws SQLException {
        if (StringUtils.isEmpty(orderDetails)) {
            log.info("Throwing SQLException in method sendSmsWithBackoff()");
            throw new SQLException();
        }
    }

    @Recover
    private void recoverSendSmsWithBackoff(SQLException e, String orderDetails) {
        // Send event
        log.info("In recoverSendSmsWithBackoff method");
    }
}
