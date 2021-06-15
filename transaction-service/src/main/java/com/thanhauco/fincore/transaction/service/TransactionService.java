package com.thanhauco.fincore.transaction.service;

import com.thanhauco.fincore.transaction.domain.Transaction;
import com.thanhauco.fincore.transaction.domain.TransactionStatus;
import com.thanhauco.fincore.transaction.repository.TransactionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;

@Service
@RequiredArgsConstructor
public class TransactionService {
    private final TransactionRepository transactionRepository;
    private final KafkaTemplate<String, Object> kafkaTemplate;

    @Transactional
    public Transaction initiateTransfer(String from, String to, BigDecimal amount, String ref) {
        if (amount.compareTo(BigDecimal.ZERO) <= 0) {
            throw new IllegalArgumentException("Amount must be positive");
        }
        
        Transaction tx = Transaction.builder()
                .sourceAccountId(from)
                .targetAccountId(to)
                .amount(amount)
                .reference(ref)
                .status(TransactionStatus.PENDING)
                .build();
        
        tx = transactionRepository.save(tx);
        
        // Publish event for async processing (Fraud check, Notification)
        kafkaTemplate.send("transaction-events", tx);
        
        return tx;
    }
}
