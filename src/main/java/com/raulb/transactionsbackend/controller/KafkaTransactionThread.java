package com.raulb.transactionsbackend.controller;

import com.raulb.transactionsbackend.domain.Transaction;
import com.raulb.transactionsbackend.dto.TransactionEvent;
import com.raulb.transactionsbackend.kafka.producer.TransactionKafkaProducer;

public class KafkaTransactionThread extends Thread {
    private final TransactionKafkaProducer transactionKafkaProducer;

    public KafkaTransactionThread(TransactionKafkaProducer producer) {
        this.transactionKafkaProducer = producer;
    }

    @Override
    public void run() {
        for (int i = 1; i <= 5; i++) {
            transactionKafkaProducer.writeToKafka(Transaction.builder().amount((double) i).id(i).build());
        }
    }
}
