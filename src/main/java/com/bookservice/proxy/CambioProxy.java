package com.bookservice.proxy;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.bookservice.response.Cambio;

@FeignClient(name = "cambio-service")
public interface CambioProxy {
    
    @GetMapping("/cambio-service/{amount}/{from}/{to}")
    public Cambio getCambio(
            @RequestParam(value = "amount", defaultValue = "1") Double amount,
            @RequestParam(value = "from", defaultValue = "USD") String from,
            @RequestParam(value = "to", defaultValue = "BRL") String to);
}
