package com.unipi.currencyexchangeservice.controller;

import com.unipi.currencyexchangeservice.model.CurrencyExchange;
import com.unipi.currencyexchangeservice.repository.CurrencyExchangeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;

@RestController
@RequestMapping("/currency-exchange")
public class CurrencyExchangeController {

    private final Environment environment;
    private final CurrencyExchangeRepository repository;

    @Autowired
    public CurrencyExchangeController(Environment environment, CurrencyExchangeRepository repository) {
        this.environment = environment;
        this.repository = repository;
    }

    @GetMapping("/from/{from}/to/{to}")
    public CurrencyExchange retrieveCurrencyValue(@PathVariable String from, @PathVariable String to) {
//        CurrencyExchange currencyExchange = new CurrencyExchange(1000L, from, to, BigDecimal.valueOf(50));
        CurrencyExchange currencyExchange = repository.findByFromAndTo(from, to);
        String port = environment.getProperty("local.server.port");
        if (currencyExchange == null) {
            throw new RuntimeException("Unable to Find Data from " + from + " to " + to);
        }
        currencyExchange.setEnvironment(port);
        repository.findAll();
        return currencyExchange;
    }
}
