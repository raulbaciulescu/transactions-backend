package com.raulb.transactionsbackend.controller;

import com.raulb.transactionsbackend.domain.Transaction;
import com.raulb.transactionsbackend.kafka.producer.TransactionCreateKafkaProducer;

public class KafkaTransactionCreateThread extends Thread {
    private final TransactionCreateKafkaProducer transactionCreateKafkaProducer;

    public KafkaTransactionCreateThread(TransactionCreateKafkaProducer producer) {
        this.transactionCreateKafkaProducer = producer;
    }

    @Override
    public void run() {
        for (int i = 1; i <= 5; i++) {
            transactionCreateKafkaProducer.writeToKafka(
                    Transaction.builder()
                            .id(i)
                            .approved(false)
                            .sender("user1")
                            .receiver("user2")
                            .amount((double) i)
                            .build());
        }
    }
}
