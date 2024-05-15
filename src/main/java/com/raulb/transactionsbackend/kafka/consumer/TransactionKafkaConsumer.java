package com.raulb.transactionsbackend.kafka.consumer;

import com.raulb.transactionsbackend.domain.Transaction;
import com.raulb.transactionsbackend.domain.TransactionAction;
import com.raulb.transactionsbackend.dto.TransactionEvent;
import com.raulb.transactionsbackend.service.TransactionService;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class TransactionKafkaConsumer {
    private static final Logger logger = LoggerFactory.getLogger(TransactionKafkaConsumer.class);

    private final TransactionService transactionService;

    @KafkaListener(topics = "${spring.kafka.topic.name}",
            concurrency = "${spring.kafka.consumer.level.concurrency:3}")
    public void receive(@Payload TransactionEvent transactionEvent,
                                 @Header(KafkaHeaders.RECEIVED_TOPIC) String topic,
                                 @Header(KafkaHeaders.RECEIVED_PARTITION) Integer partition,
                                 @Header(KafkaHeaders.OFFSET) Long offset) {
        logger.info("Received a message contains a user information from topic, " +
                "{} partition {}, and {} offset, name: {}", topic, partition, offset, transactionEvent);
        if (transactionEvent.getAction() == TransactionAction.CREATE) {
            // do something
            logger.info("CREATE");
        } else if (transactionEvent.getAction() == TransactionAction.APPROVE) {
            // do something
            logger.info("APPROVE");
        } else if (transactionEvent.getAction() == TransactionAction.EDIT) {
            // do something
            logger.info("EDIT");
        }
    }
}
