package com.finance.platform.transactions.api;

import java.time.Instant;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.finance.platform.transactions.domain.Transaction;
import com.finance.platform.transactions.repository.TransactionRepository;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/transactions")
public class TransactionController {
    private final TransactionRepository repository;

    public TransactionController(TransactionRepository repository) {
        this.repository = repository;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void createTransaction(@RequestBody @Valid CreateTransactionRequest request) {
        
        Transaction transaction = new Transaction(
            request.getUserId(),
            request.getAmount(),
            request.getCategory(),
            Instant.now(),
            request.getDescription()
        );

        repository.save(transaction);
    }

    @GetMapping
    public Page<TransactionResponse> getTransactions(
        @RequestParam Long userId,
         @RequestParam(defaultValue = "0") int page,
        @RequestParam(defaultValue = "10") int size
    ) {
        PageRequest pageable = PageRequest.of(page, size);
        return repository.findByUserId(userId,pageable)
               .map(tx -> new TransactionResponse(
                    tx.getId(),
                    tx.getUserId(),
                    tx.getAmount(),
                    tx.getCategory(),
                    tx.getCreateTs(),
                    tx.getDescription()
               ));
    }

}
