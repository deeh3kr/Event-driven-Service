package com.microservice.demo.common.config;

import com.microservice.demo.config.RetryConfigData;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.retry.backoff.ExponentialBackOffPolicy;
import org.springframework.retry.policy.SimpleRetryPolicy;
import org.springframework.retry.support.RetryTemplate;

@Configuration
public class RetryConfig {
    private final RetryConfigData retryConfigData;

    public RetryConfig(RetryConfigData configData) {
        this.retryConfigData = configData;
    }

    @Bean
    public RetryTemplate retryTemplate(){
        RetryTemplate template = new RetryTemplate();
        ExponentialBackOffPolicy exponentialBackOffPolicy = new ExponentialBackOffPolicy();
        exponentialBackOffPolicy.setInitialInterval(retryConfigData.getInitialIntervalMs());
        exponentialBackOffPolicy.setMaxInterval(retryConfigData.getMaxIntervalMs());
        exponentialBackOffPolicy.setMultiplier(retryConfigData.getMultiplier());

        template.setBackOffPolicy(exponentialBackOffPolicy);
        SimpleRetryPolicy simpleRetryPolicy = new SimpleRetryPolicy();
        simpleRetryPolicy.setMaxAttempts(retryConfigData.getMaxAttempts());
        template.setRetryPolicy(simpleRetryPolicy);
        return template;
    }
}
