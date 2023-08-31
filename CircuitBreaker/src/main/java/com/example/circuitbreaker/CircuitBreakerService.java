package com.example.circuitbreaker;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.retry.annotation.Retry;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class CircuitBreakerService {
    @CircuitBreaker(name = Resilience4jCode.CIRCUIT_TEST_70000, fallbackMethod = "getCircuitBreakerFallback")
    public String getCircuitBreaker() {
        runtimeException();
        return "hello world!";
    }

    @Retry(name = Resilience4jCode.RETRY_TEST_3000, fallbackMethod = "getRetryFallback")
    public String getRetry() {
        log.info("=== getRetry request !!");
        runtimeException();
        return "hello world!";
    }

    private void runtimeException() {
        throw new RuntimeException("failed");
    }

    private String getCircuitBreakerFallback(Throwable t) {
        return "getCircuitBreakerFallback! exception type: " + t.getClass() + "exception, message: " + t.getMessage();
    }

    private String getRetryFallback(Throwable t) {
        return "getRetryFallback! exception type: " + t.getClass() + "exception, message: " + t.getMessage();
    }
}
