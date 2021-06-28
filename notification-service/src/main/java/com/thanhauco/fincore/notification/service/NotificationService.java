package com.thanhauco.fincore.notification.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class NotificationService {

    @KafkaListener(topics = "transaction-events", groupId = "notification-group")
    public void handleTransactionEvent(Object event) {
        log.info("Received transaction event: {}", event);
        // Simulate sending email/SMS
        sendEmail("user@example.com", "Transaction Alert", "Your transaction is processing.");
    }

    private void sendEmail(String to, String subject, String body) {
        log.info("Sending email to {}: {}", to, subject);
    }
}
