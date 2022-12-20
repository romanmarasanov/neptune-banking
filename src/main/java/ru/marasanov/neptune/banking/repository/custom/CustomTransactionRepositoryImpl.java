package ru.marasanov.neptune.banking.repository.custom;

import ru.marasanov.neptune.banking.model.entity.Card;
import ru.marasanov.neptune.banking.model.entity.Transaction;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

public class CustomTransactionRepositoryImpl implements CustomTransactionRepository {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<Transaction> findBySourceCardNumber(String number) {
        return entityManager.createQuery("select c " +
                "from Card c " +
                "left join fetch c.outputTransactions " +
                "where c.number = :number", Card.class)
                .setParameter("number", number)
                .getSingleResult()
                .getOutputTransactions();
    }

    @Override
    public List<Transaction> findByDestinationCardNumber(String number) {
        return entityManager.createQuery("select c " +
                "from Card c " +
                "left join fetch c.inputTransactions " +
                "where c.number = :number", Card.class)
                .setParameter("number", number)
                .getSingleResult()
                .getInputTransactions();
    }
}
