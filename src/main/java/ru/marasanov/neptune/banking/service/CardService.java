package ru.marasanov.neptune.banking.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import ru.marasanov.neptune.banking.exception.CardBlockedException;
import ru.marasanov.neptune.banking.exception.CardNotFoundException;
import ru.marasanov.neptune.banking.exception.NotEnoughAmountException;
import ru.marasanov.neptune.banking.model.entity.Card;
import ru.marasanov.neptune.banking.model.entity.Transaction;
import ru.marasanov.neptune.banking.model.enums.CardStatus;
import ru.marasanov.neptune.banking.model.enums.TransactionStatus;
import ru.marasanov.neptune.banking.repository.CardRepository;
import ru.marasanov.neptune.banking.repository.TransactionRepository;

import javax.transaction.Transactional;
import java.sql.Timestamp;
import java.util.List;

@Service
@Transactional
public class CardService {
    private final CardRepository cardRepository;
    private final TransactionRepository transactionRepository;

    @Autowired
    public CardService(CardRepository cardRepository, TransactionRepository transactionRepository) {
        this.cardRepository = cardRepository;
        this.transactionRepository = transactionRepository;
    }

    public Card getByNumber(String number) throws CardNotFoundException {
        return cardRepository
                .findCustomByNumber(number)
                .orElseThrow(() -> new CardNotFoundException(
                        HttpStatus.NOT_FOUND,
                        "can not find card with specified id")
                );
    }

    public Card getById(Integer id) throws CardNotFoundException {
        return cardRepository
                .findCustomById(id)
                .orElseThrow(() -> new CardNotFoundException(
                        HttpStatus.NOT_FOUND,
                        "can not find card with specified id")
                );
    }


    /**
     * gets two cards and amount and do transfer. This method does not check
     * if cards exist in DB, so initiatorCard and recipientCard should be
     * received from DB beforehand with cardService
     */
    public void transfer(Card initiatorCard, Card recipientCard, int amount)
            throws CardBlockedException, NotEnoughAmountException {
        Transaction transaction = Transaction.builder()
                .setInitiatorCard(initiatorCard)
                .setRecipientCard(recipientCard)
                .setAmount(amount)
                .setInitiatorAccount(initiatorCard.getOwnerAccount())
                .setTimestamp(new Timestamp(System.currentTimeMillis()))
                .setStatus(TransactionStatus.CREATED)
                .build();
        transactionRepository.save(transaction);

        if (initiatorCard.getStatus().equals(CardStatus.BLOCKED)) {
            transaction.setStatus(TransactionStatus.CANCELED);
            transactionRepository.save(transaction);
            throw new CardBlockedException(HttpStatus.BAD_REQUEST,
                    "can not transfer: source card is blocked");
        }
        if (recipientCard.getStatus().equals(CardStatus.BLOCKED)) {
            transaction.setStatus(TransactionStatus.CANCELED);
            transactionRepository.save(transaction);
            throw new CardBlockedException(HttpStatus.BAD_REQUEST,
                    "can not transfer: destination card is blocked");
        }
        if (initiatorCard.getAmount() < amount) {
            transaction.setStatus(TransactionStatus.CANCELED);
            transactionRepository.save(transaction);
            throw new NotEnoughAmountException(HttpStatus.BAD_REQUEST,
                    "can not transfer: source card has not enough amount");
        }

        initiatorCard.setAmount(initiatorCard.getAmount() - amount);
        recipientCard.setAmount(recipientCard.getAmount() + amount);
        cardRepository.save(initiatorCard);
        cardRepository.save(recipientCard);
        transaction.setStatus(TransactionStatus.SUCCESSFUL);
    }

    public List<Card> getByOwnerEmail(String email) {
        return cardRepository.findByOwnerEmail(email);
    }

    public List<Card> getByOwnerPhoneNumber(String phoneNumber) {
        return cardRepository.findByOwnerPhoneNumber(phoneNumber);
    }

    public List<Card> getByOwnerId(int ownerId) {
        return cardRepository.findByOwnerId(ownerId);
    }
}
