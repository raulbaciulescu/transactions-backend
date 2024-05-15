package com.raulb.transactionsbackend.dto;

import com.raulb.transactionsbackend.domain.Transaction;
import com.raulb.transactionsbackend.domain.TransactionAction;
import lombok.*;

@Builder
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class TransactionEvent {
    private Transaction transaction;
    private TransactionAction action;
}
