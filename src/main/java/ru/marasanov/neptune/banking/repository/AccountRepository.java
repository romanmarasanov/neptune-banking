package ru.marasanov.neptune.banking.repository;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.marasanov.neptune.banking.model.Account;

import java.sql.Timestamp;
import java.util.List;
import java.util.Optional;

@Repository
public interface AccountRepository extends JpaRepository<Account, Integer> {

    @EntityGraph(
        type = EntityGraph.EntityGraphType.FETCH,
        attributePaths = {"cards"}
    )
    List<Account> findByFullNameContainingIgnoreCase(String searchStr);

    @EntityGraph(
            type = EntityGraph.EntityGraphType.FETCH,
            attributePaths = {"cards"}
    )
    Optional<Account> findByPhoneNumber(String number);

    @EntityGraph(
            type = EntityGraph.EntityGraphType.FETCH,
            attributePaths = {"cards"}
    )
    List<Account> findByCreatedAtGreaterThan(Timestamp ts);

}
