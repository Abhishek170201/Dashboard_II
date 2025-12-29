package com.finance.platform.transactions.repository;

import java.time.Instant;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import com.finance.platform.transactions.domain.Transaction;

public interface TransactionRepository extends JpaRepository<Transaction, Long> {

    Page<Transaction> findByUserId(Long userId, Pageable pageable);

    Page<Transaction> findByUserIdAndCreateTsBetween(
        Long userId,
        Instant start,
        Instant end,
        Pageable pageable
    );

    Page<Transaction> findByCategory(String category, Pageable pageable);
    
    Page<Transaction> findByCategoryAndBetweenCreateTs(
        String category, 
        Instant start,
        Instant end,
        Pageable pageable);
}
    

