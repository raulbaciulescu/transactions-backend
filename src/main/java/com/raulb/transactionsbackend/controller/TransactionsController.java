package com.raulb.transactionsbackend.controller;

import com.raulb.transactionsbackend.domain.Transaction;
import com.raulb.transactionsbackend.kafka.producer.TransactionApproveKafkaProducer;
import com.raulb.transactionsbackend.kafka.producer.TransactionCreateKafkaProducer;
import com.raulb.transactionsbackend.kafka.producer.TransactionCancelKafkaProducer;
import com.raulb.transactionsbackend.service.TransactionService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(("/transactions"))
@CrossOrigin()
@AllArgsConstructor
public class TransactionsController {
    private final TransactionCancelKafkaProducer transactionCancelKafkaProducer;
    private final TransactionCreateKafkaProducer transactionCreateKafkaProducer;
    private final TransactionApproveKafkaProducer transactionApproveKafkaProducer;
    private final TransactionService transactionService;

    @PostMapping
    public void saveTransaction(@RequestBody Transaction transaction) {
        transactionCreateKafkaProducer.writeToKafka(transaction);
    }

    @DeleteMapping
    public void cancelTransaction(@RequestBody Transaction transaction) {
        transactionCancelKafkaProducer.writeToKafka(transaction);
    }

    @PutMapping
    public void approveTransaction(@RequestBody Transaction transaction) {
        transactionApproveKafkaProducer.writeToKafka(transaction);
    }

    @GetMapping
    public List<Transaction> getTransactions() {
        return transactionService.getTransactions();
    }

    @GetMapping
    public List<Transaction> getApprovedTransactions() {
        return transactionService.getApprovedTransactions();
    }

    @GetMapping
    public List<Transaction> getOpenedTransactions() {
        return transactionService.getOpenedTransactions();
    }

    @GetMapping
    public List<Transaction> computeTotalAmount(String user) {
        return transactionService.getTransactions();
    }

    @PostMapping("/test")
    public void generate() {
        KafkaTransactionThread thread1 = new KafkaTransactionThread(transactionCancelKafkaProducer);
        KafkaTransactionCreateThread thread2 = new KafkaTransactionCreateThread(transactionCreateKafkaProducer);

        thread1.start();
        thread2.start();
    }
}

