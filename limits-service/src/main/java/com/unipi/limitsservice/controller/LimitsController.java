package com.unipi.limitsservice.controller;

import com.unipi.limitsservice.bean.Limits;
import com.unipi.limitsservice.configuration.Config;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/limits")
public class LimitsController {

    private final Config config;

    @Autowired
    public LimitsController(Config config) {
        this.config = config;
    }

    @GetMapping
    public Limits retrieveLimits() {
        return new Limits(config.getMinimum(), config.getMaximum());
    }
}
