package com.raulb.transactionsbackend.service;

import com.raulb.transactionsbackend.domain.Transaction;

import java.util.List;

public interface TransactionService {
    void createTransaction(Transaction transaction);

    void approveTransaction(Transaction transaction);

    void cancelTransaction(Transaction transaction);

    List<Transaction> getTransactions();

    List<Transaction> getApprovedTransactions();

    List<Transaction> getOpenedTransactions();
}
