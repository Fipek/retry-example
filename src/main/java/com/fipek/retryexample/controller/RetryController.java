package com.fipek.retryexample.controller;

import com.fipek.retryexample.service.RetryService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class RetryController {
    private final RetryService retryService;

    @GetMapping("/retryProcess")
    public void retry() {
        retryService.retryProcess("");
    }

    @GetMapping("/retryProcessWithBackoff")
    public void retryProcessWithBackoff() {
        retryService.retryProcessWithBackoff("");
    }

    @GetMapping("/retryFeignClient/{currencyType}")
    public void retryFeignClient(@PathVariable String currencyType) {
        retryService.retryFeignClient(currencyType);
    }
}
