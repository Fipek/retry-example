package com.fipek.retryexample.service;

import com.fipek.retryexample.client.ExchangeRateApiResponse;
import com.fipek.retryexample.client.RetryApiClient;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class RetryService {

    private final SmsService smsService;
    private final EmailService emailService;
    private final RetryApiClient retryApiClient;

    @Async
    public void retryProcess(String orderDetails){
        try {
            log.info("retryProcess started");
            emailService.sendEmail(orderDetails);
            smsService.sendSms(orderDetails);
            log.info("Success");
        } catch (Exception e) {
            log.info("Failed");
        }
    }

    @Async
    public void retryProcessWithBackoff(String orderDetails) {
        try {
            log.info("retryProcess started");
            emailService.sendEmailWithBackoff(orderDetails);
            smsService.sendSmsWithBackoff(orderDetails);
            log.info("Success");
        } catch (Exception e) {
            log.info("Failed");
        }
    }

    @Async
    public void retryFeignClient(final String currencyType){
        try{
            log.info("retryFeignClient started");
            ExchangeRateApiResponse response = retryApiClient.getLatestExchangeRate(currencyType);
            log.info(String.valueOf(response));
            log.info("retryFeignClient finished");
        } catch (Exception e) {
            log.info("Failed");
        }
    }
}
