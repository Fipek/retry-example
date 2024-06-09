package com.fipek.retryexample.client;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ExchangeRateApiResponse implements Serializable {
    private String result;
    @JsonProperty("base_code")
    private String baseCurrency;
}
