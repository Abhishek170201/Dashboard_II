package com.finance.platform.transactions.api;

import java.math.BigDecimal;

import jakarta.validation.constraints.NotNull;

public class CreateTransactionRequest {

    @NotNull
    private Long userId;

    @NotNull
    private BigDecimal amount;

    private String category;

    private String description;


    public String getCategory(){
        return category;
    }

    public String getDescription(){
        return description;
    }

    public Long getUserId() {
        return userId;
    }

    public BigDecimal getAmount(){
        return amount;
    }

}
