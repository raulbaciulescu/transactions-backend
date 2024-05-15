package com.raulb.transactionsbackend.kafka.producer;

import com.raulb.transactionsbackend.domain.Transaction;
import com.raulb.transactionsbackend.domain.TransactionAction;
import com.raulb.transactionsbackend.dto.TransactionEvent;
import com.raulb.transactionsbackend.kafka.consumer.TransactionKafkaConsumer;
import lombok.RequiredArgsConstructor;
import org.apache.kafka.clients.admin.NewTopic;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.core.annotation.Order;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class TransactionKafkaProducer {
    private static final Logger logger = LoggerFactory.getLogger(TransactionKafkaProducer.class);
    private final KafkaTemplate<String, TransactionEvent> kafkaTemplate;

    @Value("${spring.kafka.topic.name}")
    private String topic;

    @Value("${spring.kafka.replication.factor:1}")
    private int replicationFactor;

    @Value("${spring.kafka.partition.number:1}")
    private int partitionNumber;

    @Bean
    @Order(-1)
    public NewTopic createNewTopic() {
        return new NewTopic(topic, partitionNumber, (short) replicationFactor);
    }

    public void writeToKafka(Transaction transaction) {
        logger.info("write to kafka transaction: {}", transaction);
        TransactionEvent transactionEvent = new TransactionEvent(transaction, TransactionAction.EDIT);
        kafkaTemplate.send(topic, transactionEvent);
    }
}
