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

    /**
     * this one and the next method gets one particular card from DB
     * with fetched output and input transactions. First we get card
     * and join transaction table to get input transactions, then we
     * use fetchOutputTransactionsToCard method to add output transactions
     * Thus, we create two transactions, but don't create more than one
     * join in the query
     * <p>
     * possible improvement: using batch-query to make two queries
     * by one transaction
     *
     * @param number – number of card to find
     * @return founded card or empty optional
     */
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
        return Optional.ofNullable(fetchOutputTransactionsToCard(card));
    }

    @Override
    public Optional<Card> findCustomById(Integer id) {
        Card card = entityManager
                .createQuery(
                        "select distinct c " +
                                "from Card c " +
                                "left join fetch c.inputTransactions " +
                                "where c.id=:id ", Card.class)
                .setParameter("id", id)
                .setHint(QueryHints.HINT_PASS_DISTINCT_THROUGH, false)
                .getSingleResult();
        return Optional.ofNullable(fetchOutputTransactionsToCard(card));
    }

    /**
     * the next three methods get the list card from DB
     * with fetched output and input transactions. First we get cards
     * and join transaction table to get input transactions, then we
     * use fetchOutputTransactionsToCard method to add output transactions
     * Thus, we create two transactions, but don't create more than one
     * join in the query
     *
     * possible improvement: using batch-query to make two queries
     * by one transaction or use jdbc or jOOQ
     *
     * @param email – owner account's email
     * @return a list of founded cards (maybe empty)
     */
    @Override
    public List<Card> findByOwnerEmail(String email) {
        List<Card> cards = entityManager
                .createQuery(
                        "select distinct c " +
                                "from Card c " +
                                "left join fetch c.inputTransactions " +
                                "where c.ownerAccount.email = :email", Card.class)
                .setParameter("email", email)
                .setHint(QueryHints.HINT_PASS_DISTINCT_THROUGH, false)
                .getResultList();
        return fetchOutputTransactionsToCards(cards);
    }

    @Override
    public List<Card> findByOwnerPhoneNumber(String number) {
        List<Card> cards = entityManager
                .createQuery("select c " +
                        "from Card c " +
                        "left join fetch c.inputTransactions " +
                        "where c.ownerAccount.phoneNumber = :number", Account.class)
                .setParameter("number", number)
                .getSingleResult()
                .getCards();
        return fetchOutputTransactionsToCards(cards);
    }

    @Override
    public List<Card> findByOwnerId(int id) {
        List<Card> cards = entityManager
                .createQuery("select distinct c " +
                        "from Card c " +
                        "left join fetch c.inputTransactions " +
                        "where c.ownerAccount.id = :id", Card.class)
                .setParameter("id", id)
                .setHint(QueryHints.HINT_PASS_DISTINCT_THROUGH, false)
                .getResultList();
        return fetchOutputTransactionsToCards(cards);
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

    private List<Card> fetchOutputTransactionsToCards(List<Card> cards) {
        return entityManager
                .createQuery("select distinct c " +
                        "from Card c " +
                        "left join fetch c.outputTransactions " +
                        "where c in :cards", Card.class)
                .setParameter("cards", cards)
                .setHint(QueryHints.HINT_PASS_DISTINCT_THROUGH, false)
                .getResultList();
    }
}
