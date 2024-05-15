package com.raulb.transactionsbackend.controller;

import com.raulb.transactionsbackend.domain.Transaction;
import com.raulb.transactionsbackend.kafka.producer.TransactionCreateKafkaProducer;
import com.raulb.transactionsbackend.kafka.producer.TransactionKafkaProducer;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(("/transactions"))
@CrossOrigin()
@AllArgsConstructor
public class TransactionsController {
    private final TransactionKafkaProducer transactionKafkaProducer;
    private final TransactionCreateKafkaProducer transactionCreateKafkaProducer;

    @PostMapping
    public void saveTransaction(@RequestBody Transaction transaction) {
        transactionKafkaProducer.writeToKafka(transaction);
    }

    @PutMapping
    public void editTransaction(@RequestBody Transaction transaction) {
        transactionKafkaProducer.writeToKafka(transaction);
    }

    @PutMapping("/approve")
    public void approveTransaction(@RequestBody Transaction transaction) {
        transactionKafkaProducer.writeToKafka(transaction);
    }

    @PostMapping("/test")
    public void generate() {
        KafkaTransactionThread thread1 = new KafkaTransactionThread(transactionKafkaProducer);
        KafkaTransactionDeleteThread thread2 = new KafkaTransactionDeleteThread(transactionCreateKafkaProducer);

        thread1.start();
        thread2.start();
    }
}

