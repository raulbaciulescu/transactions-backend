package com.raulb.transactionsbackend.kafka.producer;

import com.raulb.transactionsbackend.domain.Transaction;
import com.raulb.transactionsbackend.domain.TransactionAction;
import com.raulb.transactionsbackend.dto.TransactionEvent;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class TransactionApproveKafkaProducer {
    private static final Logger logger = LoggerFactory.getLogger(TransactionApproveKafkaProducer.class);
    private final KafkaTemplate<String, TransactionEvent> kafkaTemplate;

    @Value("${spring.kafka.topic.name}")
    private String topic;

    public void writeToKafka(Transaction transaction) {
        logger.info("TransactionApproveKafkaProducer: {}", transaction);
        TransactionEvent transactionEvent = new TransactionEvent(transaction, TransactionAction.APPROVE);
        kafkaTemplate.send(topic, transactionEvent);
    }
}
