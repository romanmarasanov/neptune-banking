package ru.marasanov.neptune.banking.repository.custom;

import ru.marasanov.neptune.banking.model.entity.Card;

import java.util.List;
import java.util.Optional;

public interface CustomCardRepository {
    Optional<Card> findCustomByNumber(String number);
    Optional<Card> findCustomById(Integer id);
    List<Card> findByOwnerEmail(String email);
    List<Card> findByOwnerPhoneNumber(String number);
}
