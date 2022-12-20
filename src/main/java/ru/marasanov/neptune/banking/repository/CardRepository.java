package ru.marasanov.neptune.banking.repository;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.marasanov.neptune.banking.model.entity.Card;
import ru.marasanov.neptune.banking.repository.custom.CustomCardRepository;

import java.util.Optional;

@Repository
public interface CardRepository
        extends JpaRepository<Card, Integer>, CustomCardRepository {

}
