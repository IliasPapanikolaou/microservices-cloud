package com.unipi.currencyconversionservice.controller;

import com.unipi.currencyconversionservice.model.CurrencyConversion;
import com.unipi.currencyconversionservice.proxy.CurrencyExchangeProxy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;

@RestController
@RequestMapping("/currency-conversion-feign")
public class CurrencyConversionControllerFeign {

    private final CurrencyExchangeProxy proxy;

    @Autowired
    public CurrencyConversionControllerFeign(CurrencyExchangeProxy proxy) {
        this.proxy = proxy;
    }

    @GetMapping("/from/{from}/to/{to}/quantity/{quantity}")
    public CurrencyConversion calculateCurrencyConversion(@PathVariable String from,
                                                          @PathVariable String to,
                                                          @PathVariable BigDecimal quantity) {

        // Use of CurrencyExchange Proxy
        CurrencyConversion currencyConversion = proxy.retrieveCurrencyValue(from, to);

        return new CurrencyConversion(currencyConversion.getId(), from, to, quantity,
                currencyConversion.getConversionMultiple(),
                quantity.multiply(currencyConversion.getConversionMultiple()),
                currencyConversion.getEnvironment() + " feign");
    }
}
