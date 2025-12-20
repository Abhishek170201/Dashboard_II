package com.finance.platform.transactions.api;

import java.math.BigDecimal;
import java.time.Instant;

public class TransactionResponse {

    private Long id;
    private Long userId;
    private BigDecimal amount;
    private String category;
    private Instant createTs;
    private String description;

    public TransactionResponse(
            Long id,
            Long userId,
            BigDecimal amount,
            String category,
            Instant createTs,
            String description
    ) {
        this.id = id;
        this.userId = userId;
        this.amount = amount;
        this.category = category;
        this.createTs = createTs;
        this.description = description;
    }

    // getters
    public Long getId() {
    return id;
    }

    public Long getUserId() {
        return userId;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public String getCategory() {
        return category;
    }

    public Instant getCreateTs() {
        return createTs;
    }

    public String getDescription() {
        return description;
    }

}
