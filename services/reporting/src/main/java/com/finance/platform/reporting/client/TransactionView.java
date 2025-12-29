package com.finance.platform.reporting.client;


import java.math.BigDecimal;
import java.time.Instant;

public class TransactionView {

    private long transactionId;

    private long userId;

    private BigDecimal amount;

    private String category;

    private String description;

    private Instant createTs;

    public long getTransactionId() {
        return transactionId;
    }

    public long getUserId(){
        return userId;
    }

}
