package com.raulb.transactionsbackend.controller;

import com.raulb.transactionsbackend.domain.Transaction;
import com.raulb.transactionsbackend.kafka.producer.TransactionCancelKafkaProducer;

public class KafkaTransactionThread extends Thread {
    private final TransactionCancelKafkaProducer transactionCancelKafkaProducer;

    public KafkaTransactionThread(TransactionCancelKafkaProducer producer) {
        this.transactionCancelKafkaProducer = producer;
    }

    @Override
    public void run() {
        for (int i = 1; i <= 5; i++) {
            transactionCancelKafkaProducer.writeToKafka(Transaction.builder().amount((double) i).id(i).build());
        }
    }
}
