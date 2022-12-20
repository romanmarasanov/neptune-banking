package ru.marasanov.neptune.banking.repository;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.marasanov.neptune.banking.model.entity.Account;

import java.util.Optional;

@Repository
public interface AccountRepository extends JpaRepository<Account, Integer> {

    @EntityGraph(
        type = EntityGraph.EntityGraphType.FETCH,
        attributePaths = {"cards"}
    )
    Optional<Account> findById(int id);

    @EntityGraph(
            type = EntityGraph.EntityGraphType.FETCH,
            attributePaths = {"cards"}
    )
    Optional<Account> findByPhoneNumber(String number);

    @EntityGraph(
            type = EntityGraph.EntityGraphType.FETCH,
            attributePaths = {"cards"}
    )
    Optional<Account> findByEmail(String email);

}
