package com.example.circuitbreaker;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class CircuitBreakerController {
    private final CircuitBreakerService circuitBreakerService;

    @GetMapping("/circuit-breaker")
    public String helloCircuitBreaker() {
        return circuitBreakerService.getCircuitBreaker();
    }

    @GetMapping("/retry")
    public String helloRetry() {
        return circuitBreakerService.getRetry();
    }
}
