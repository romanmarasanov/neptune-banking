package ru.marasanov.neptune.banking.repository.custom;

import ru.marasanov.neptune.banking.model.entity.Transaction;

import java.util.List;

public interface CustomTransactionRepository {
    List<Transaction> findBySourceCardId(int id);
    List<Transaction> findByDestinationCardId(int id);
}
