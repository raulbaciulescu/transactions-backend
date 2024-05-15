package com.raulb.transactionsbackend.repository;

import com.raulb.transactionsbackend.domain.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TransactionRepository extends JpaRepository<Transaction, String> {
}
