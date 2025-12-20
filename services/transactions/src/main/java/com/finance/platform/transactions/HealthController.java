package com.finance.platform.transactions; 

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HealthController {

    @GetMapping("/health")
    public String Health() {
        return "Transaction service is up and running.";
    }
}