package com.fipek.retryexample.client;

import feign.FeignException;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.retry.annotation.Retryable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "exchange-rate-api")
@Retryable(maxAttempts = 3, value = {FeignException.class})
public interface RetryApiClient {
    @GetMapping("{currencyType}")
    ExchangeRateApiResponse getLatestExchangeRate(@PathVariable String currencyType);
}
