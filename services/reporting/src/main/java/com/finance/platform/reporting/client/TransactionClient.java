package com.finance.platform.reporting.client;

import java.time.Instant;

import org.springframework.core.ParameterizedTypeReference;
import org.springframework.data.domain.Page;
import org.springframework.web.reactive.function.client.WebClient;

public class TransactionClient {

    private WebClient webClient;

    public TransactionClient(WebClient.Builder builder) {
        this.webClient = builder
                        .baseUrl("http://localhost:8080")
                        .build();
    }

    public Page<TransactionView> getTransactions(
        long userId,
        Instant from, 
        Instant to
    ) {
        return webClient.get()
                .uri(uriBuilder -> uriBuilder
                        .path("/transactions")
                        .queryParam("userId", userId)
                        .queryParam("from", from)
                        .queryParam("to", to)
                        .build())
                .retrieve()
                .bodyToMono(new ParameterizedTypeReference<Page<TransactionView>>() {})
                .block();
    }


}
