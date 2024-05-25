package com.raulb.transactionsbackend.service;


import com.raulb.transactionsbackend.domain.Transaction;
import com.raulb.transactionsbackend.repository.TransactionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TransactionServiceImpl implements TransactionService {
    private final TransactionRepository transactionRepository;

    @Override
    public void createTransaction(Transaction transaction) {
        transactionRepository.save(transaction);
    }

    @Override
    public void approveTransaction(Transaction transaction) {
        transaction.setApproved(true);
        transactionRepository.save(transaction);
    }

    @Override
    public void cancelTransaction(Transaction transaction) {
        transactionRepository.delete(transaction);
    }

    @Override
    public List<Transaction> getTransactions() {
        return transactionRepository.findAll();
    }

    @Override
    public List<Transaction> getApprovedTransactions() {
        return null;
    }

    @Override
    public List<Transaction> getOpenedTransactions() {
        return null;
    }
}
