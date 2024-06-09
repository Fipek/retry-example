package com.fipek.retryexample;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.retry.annotation.EnableRetry;
import org.springframework.scheduling.annotation.EnableAsync;

@EnableAsync
@EnableRetry
@EnableFeignClients
@SpringBootApplication
public class RetryExampleApplication {

	public static void main(String[] args) {
		SpringApplication.run(RetryExampleApplication.class, args);
	}

}
