package com.finance.platform.transactions.service;

import java.time.Instant;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.finance.platform.transactions.api.CreateTransactionRequest;
import com.finance.platform.transactions.api.TransactionResponse;
import com.finance.platform.transactions.domain.Transaction;
import com.finance.platform.transactions.repository.TransactionRepository;

@Service
public class TransactionService {
    private TransactionRepository repository;

    public TransactionService(TransactionRepository repository){
        this.repository = repository;
    }

    public void createTransaction(CreateTransactionRequest request){
        Transaction transaction = new Transaction(
            request.getUserId(),
            request.getAmount(), 
            request.getCategory(), 
            Instant.now(), 
            request.getDescription()
        );

        repository.save(transaction);
    }

    public Page<TransactionResponse> getTransactionsCategory(
        String category,
        Instant start,
        Instant end,
        Pageable pageable) {

            Page<Transaction> page;

            if(start != null && end != null)
            page = repository.findByCategoryAndBetweenCreateTs(category, start, end, pageable);
        else                    
            page =  repository.findByCategory(category, pageable);

        return  page.map(tx -> new TransactionResponse(
                    tx.getId(),
                    tx.getUserId(),
                    tx.getAmount(), 
                    tx.getCategory(), 
                    tx.getCreateTs(), 
                    tx.getDescription()
                ));
        }

    public Page<TransactionResponse> getTransactions(
        Long userId, 
        Instant start,
        Instant end,
        Pageable pageable){

        Page<Transaction> page;
        
        
        if(start != null && end != null)
            page = repository.findByUserIdAndCreateTsBetween(userId, start, end, pageable);
        else                    
            page =  repository.findByUserId(userId, pageable);

        return  page.map(tx -> new TransactionResponse(
                    tx.getId(),
                    tx.getUserId(),
                    tx.getAmount(), 
                    tx.getCategory(), 
                    tx.getCreateTs(), 
                    tx.getDescription()
                ));
    }
}
