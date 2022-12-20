package ru.marasanov.neptune.banking.repository.custom;

import ru.marasanov.neptune.banking.model.entity.Transaction;

import java.util.List;

public interface CustomTransactionRepository {
    List<Transaction> findBySourceCardNumber(String number);
    List<Transaction> findByDestinationCardNumber(String number);
}
