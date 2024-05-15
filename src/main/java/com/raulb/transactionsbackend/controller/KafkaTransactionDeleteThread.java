package com.raulb.transactionsbackend.controller;

import com.raulb.transactionsbackend.domain.Transaction;
import com.raulb.transactionsbackend.kafka.producer.TransactionCreateKafkaProducer;

public class KafkaTransactionDeleteThread extends Thread {
    private final TransactionCreateKafkaProducer transactionCreateKafkaProducer;

    public KafkaTransactionDeleteThread(TransactionCreateKafkaProducer producer) {
        this.transactionCreateKafkaProducer = producer;
    }

    @Override
    public void run() {
        for (int i = 1; i <= 5; i++) {
            transactionCreateKafkaProducer.writeToKafka(Transaction.builder().amount((double) i).id(i).build());
        }
    }
}
