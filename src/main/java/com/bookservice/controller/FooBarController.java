package com.bookservice.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import io.github.resilience4j.bulkhead.annotation.Bulkhead;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.ratelimiter.annotation.RateLimiter;
import io.github.resilience4j.retry.annotation.Retry;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;


@Tag(name = "Foo Bar Endpoint")
@RestController
@RequestMapping("foo-service")
public class FooBarController {

    private Logger logger = LoggerFactory.getLogger(FooBarController.class);
    
    @Operation(summary = "foo bar")
    @GetMapping("/foo-bar")
    // @Retry(name = "foo-bar", fallbackMethod = "fooBarFallback")
    // @CircuitBreaker(name = "foo-bar", fallbackMethod = "fooBarFallback")
    // @RateLimiter(name = "default")
    @Bulkhead(name = "default")
    public String fooBar(){
        logger.info("Request received");
        // var response = new RestTemplate().getForEntity("http://localhost:8080/foo-bar", String.class);
        // return response.getBody();
        return "Foo Bar";
    }

    public String fooBarFallback(Exception e){
        logger.info("Fallback method called");
        return "Fallback Response";
    }
}
