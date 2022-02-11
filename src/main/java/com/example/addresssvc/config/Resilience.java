package com.example.addresssvc.config;

import io.github.resilience4j.retry.Retry;
import io.github.resilience4j.retry.RetryConfig;
import io.github.resilience4j.retry.RetryRegistry;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.io.IOException;
import java.time.Duration;
import java.util.concurrent.TimeoutException;

@Configuration
public class Resilience {

   /* @Bean
    public RetryConfig retryConfig() {
        return RetryConfig.custom()
                .maxAttempts(2)
                .waitDuration(Duration.ofMillis(1000))
                //.retryOnResult(response -> response.getStatus() == 500)
                //.retryOnException(e -> e instanceof WebServiceException)
                .retryExceptions(IOException.class, TimeoutException.class, RuntimeException.class)
                //.ignoreExceptions(BusinessException.class, OtherBusinessException.class)
                .failAfterMaxAttempts(true)
                .build();
    }

    @Bean
    public RetryRegistry retryRegistry ()  {
        // Create a RetryRegistry with a custom global configuration
        RetryRegistry registry = RetryRegistry.of(retryConfig());
        return registry;
    }

    @Bean
    public Retry retry() {
        // Get or create a Retry from the registry -
        // Retry will be backed by the default config
        Retry retryWithDefaultConfig = retryRegistry ().retry("name1");
        return retryWithDefaultConfig;
    }*/

}
