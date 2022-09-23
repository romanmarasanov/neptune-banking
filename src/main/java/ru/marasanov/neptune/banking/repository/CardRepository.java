package ru.marasanov.neptune.banking.repository;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.marasanov.neptune.banking.model.Card;

import java.util.Optional;

@Repository
public interface CardRepository extends JpaRepository<Card, Integer> {

    @EntityGraph(
            type = EntityGraph.EntityGraphType.FETCH,
            attributePaths = {
                "transactions",
                "transactions.initiatorCard",
                "transactions.recipientCard",
                "transactions.initiatorAccount"
            }
    )
    Optional<Card> findByNumber(String number);
}
