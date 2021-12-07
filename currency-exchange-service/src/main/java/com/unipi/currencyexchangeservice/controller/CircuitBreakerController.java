package com.unipi.currencyexchangeservice.controller;

import io.github.resilience4j.bulkhead.annotation.Bulkhead;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.ratelimiter.annotation.RateLimiter;
import io.github.resilience4j.retry.annotation.Retry;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@Log4j2
@RestController
public class CircuitBreakerController {

    /*
    * Linux-Unix Utility to make a lot of requests:
    * $ watch -n 0.1 curl http://localhost:8000/sample-api
    *
    * Windows PowerShell script for 100 requests:
    * > for ($i=0; $i -lt 100; $i++) {  curl http://localhost:8000/sample-api }
    * */

    @GetMapping("/sample-api")
    // Configure in application.properties
    @Retry(name = "sample-api", fallbackMethod = "hardcodedResponse") // "default" tries 3 times
    @CircuitBreaker(name = "default", fallbackMethod = "hardcodedResponse")
    @RateLimiter(name = "default") // example: In 10s allow only 10000 calls, then reset
    @Bulkhead(name = "default") // how many concurrent calls are allowed
    public String ampleApi() {
        log.info("Sample Api call received");
        // This is intended to not work, it is a dummy api to test resilience4j
        ResponseEntity<String> entity = new RestTemplate()
                .getForEntity("http://localhost:8080/some-dummy-url", String.class);

        return entity.getBody();
    }

    // Fallback method
    public String hardcodedResponse(Exception ex) {
        return "fallback-response: " + ex.getMessage();
    }
 }
