package ru.marasanov.neptune.banking.repository.custom;

import org.hibernate.jpa.QueryHints;
import org.springframework.stereotype.Component;
import ru.marasanov.neptune.banking.model.entity.Account;
import ru.marasanov.neptune.banking.model.entity.Card;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Component
@Transactional
public class CustomCardRepositoryImpl implements CustomCardRepository {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public Optional<Card> findCustomByNumber(String number) {
        Card card = entityManager.createQuery(
                        "select distinct c " +
                        "from Card c " +
                        "left join fetch c.inputTransactions " +
                        "where c.number=:number ", Card.class)
                .setParameter("number", number)
                .setHint(QueryHints.HINT_PASS_DISTINCT_THROUGH, false)
                .getSingleResult();

        card = fetchOutputTransactionsToCard(card);

        return Optional.ofNullable(card);
    }

    @Override
    public Optional<Card> findCustomById(Integer id) {
        Card card = entityManager.createQuery(
                        "select distinct c " +
                                "from Card c " +
                                "left join fetch c.inputTransactions " +
                                "where c.id=:id ", Card.class)
                .setParameter("id", id)
                .setHint(QueryHints.HINT_PASS_DISTINCT_THROUGH, false)
                .getSingleResult();

        card = fetchOutputTransactionsToCard(card);

        return Optional.ofNullable(card);
    }

    @Override
    public List<Card> findByOwnerEmail(String email) {
        List<Card> cards = entityManager
                .createQuery("select a " +
                        "from Account a " +
                        "left join fetch a.cards c " +
                        "where a.email = :email", Account.class)
                .setParameter("email", email)
                .getSingleResult()
                .getCards();

        return fetchTransactionsToList(cards);
    }

    @Override
    public List<Card> findByOwnerPhoneNumber(String number) {
        List<Card> cards = entityManager
                .createQuery("select a " +
                        "from Account a " +
                        "left join fetch a.cards c " +
                        "where a.phoneNumber = :number", Account.class)
                .setParameter("number", number)
                .getSingleResult()
                .getCards();

        return fetchTransactionsToList(cards);
    }

    private Card fetchOutputTransactionsToCard(Card card) {
        return entityManager.createQuery("select distinct c " +
                        "from Card c " +
                        "left join fetch c.outputTransactions " +
                        "where c in :card ", Card.class)
                .setParameter("card", card)
                .setHint(QueryHints.HINT_PASS_DISTINCT_THROUGH, false)
                .getSingleResult();
    }

    private List<Card> fetchTransactionsToList(List<Card> cards) {
        cards = entityManager
                .createQuery("select c " +
                        "from Card c " +
                        "left join fetch c.inputTransactions " +
                        "where c in :cards", Card.class)
                .setParameter("cards", cards)
                .getResultList();
        cards = entityManager
                .createQuery("select c " +
                        "from Card c " +
                        "left join fetch c.outputTransactions " +
                        "where c in :cards", Card.class)
                .setParameter("cards", cards)
                .getResultList();
        return cards;
    }
}
