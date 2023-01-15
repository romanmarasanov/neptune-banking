package ru.marasanov.neptune.banking.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.marasanov.neptune.banking.model.entity.Transaction;
import ru.marasanov.neptune.banking.repository.custom.CustomTransactionRepository;

import java.util.List;

@Repository
public interface TransactionRepository
        extends JpaRepository<Transaction, Integer>, CustomTransactionRepository {

    List<Transaction> findByRecipientCardIdOrInitiatorCardId(int cardId);
}
