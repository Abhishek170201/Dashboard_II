package com.finance.platform.transactions.domain;

import java.math.BigDecimal;
import java.time.Instant;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name="transactions")
public class Transaction {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private long userId;

    @Column(nullable = false, precision = 19, scale = 2)
    private BigDecimal amount;

    @Column(nullable = false)
    private Instant createTs;

    @Column(nullable = true)
    private String category;

    @Column(nullable = true)
    private String description;

    protected Transaction() {

    }

    public Transaction(
        Long userId,
        BigDecimal amount,
        String category,
        Instant createTs,
        String description
    ) {
        this.userId = userId;
        this.amount = amount;
        this.category = category;
        this.createTs = createTs;
        this.description = description;
    }

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
    