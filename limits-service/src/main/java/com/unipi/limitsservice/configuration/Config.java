package com.unipi.limitsservice.configuration;


import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@Data
@ConfigurationProperties(prefix = "limits-service")
public class Config {
    private int minimum;
    private int maximum;
}
