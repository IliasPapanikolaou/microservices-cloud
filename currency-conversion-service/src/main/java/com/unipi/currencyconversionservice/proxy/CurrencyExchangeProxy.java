package com.unipi.currencyconversionservice.proxy;

import com.unipi.currencyconversionservice.model.CurrencyConversion;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

//@FeignClient(name="currency-exchange", url="localhost:8000")
// Use of Eureka Discovery Service
@FeignClient(name="currency-exchange")
public interface CurrencyExchangeProxy {

    @GetMapping("/currency-exchange/from/{from}/to/{to}")
    CurrencyConversion retrieveCurrencyValue(@PathVariable String from, @PathVariable String to);
}
