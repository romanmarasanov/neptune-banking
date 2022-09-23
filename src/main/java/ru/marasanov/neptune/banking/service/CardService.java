package ru.marasanov.neptune.banking.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.marasanov.neptune.banking.constant.CardStatusConstants;
import ru.marasanov.neptune.banking.constant.TransactionStatusConstants;
import ru.marasanov.neptune.banking.exception.CardBlockedException;
import ru.marasanov.neptune.banking.exception.CardNotFoundException;
import ru.marasanov.neptune.banking.exception.NotEnoughAmountException;
import ru.marasanov.neptune.banking.model.Card;
import ru.marasanov.neptune.banking.model.Transaction;
import ru.marasanov.neptune.banking.repository.CardRepository;
import ru.marasanov.neptune.banking.repository.TransactionRepository;

import java.sql.Timestamp;
import java.util.Optional;

@Service
public class CardService {
    private final CardRepository cardRepository;
    private final TransactionRepository transactionRepository;

    @Autowired
    public CardService(CardRepository cardRepository, TransactionRepository transactionRepository) {
        this.cardRepository = cardRepository;
        this.transactionRepository = transactionRepository;
    }

    public Card getByNumber(String number) throws CardNotFoundException {
        Optional<Card> cardOptional = cardRepository.findByNumber(number);
        if (cardOptional.isEmpty()) {
            throw new CardNotFoundException("can not find card with specified number");
        }
        return cardOptional.get();
    }

    public void block(Card card) throws CardNotFoundException {
        Optional<Card> cardOptional = cardRepository.findByNumber(card.getNumber());
        if (cardOptional.isEmpty()) {
            throw new CardNotFoundException("can not find card with specified number");
        }
        card.setStatus(CardStatusConstants.BLOCKED);
        cardRepository.save(card);
    }

    public void transfer(Card initiatorCard, Card recipientCard, int amount)
            throws CardBlockedException, NotEnoughAmountException {
        Transaction transaction = Transaction.builder()
                .setInitiatorCard(initiatorCard)
                .setRecipientCard(recipientCard)
                .setAmount(amount)
                .setInitiatorAccount(initiatorCard.getOwnerAccount())
                .setTimestamp(new Timestamp(System.currentTimeMillis()))
                .setStatus(TransactionStatusConstants.CREATED)
                .build();
        transactionRepository.save(transaction);

        if (initiatorCard.getStatus().equals(CardStatusConstants.BLOCKED)) {
            transaction.setStatus(TransactionStatusConstants.CANCELED);
            transactionRepository.save(transaction);
            throw new CardBlockedException("can not transfer: source card is blocked");
        }
        if (recipientCard.getStatus().equals(CardStatusConstants.BLOCKED)) {
            transaction.setStatus(TransactionStatusConstants.CANCELED);
            transactionRepository.save(transaction);
            throw new CardBlockedException("can not transfer: destination card is blocked");
        }
        if (initiatorCard.getAmount() < amount) {
            transaction.setStatus(TransactionStatusConstants.CANCELED);
            transactionRepository.save(transaction);
            throw new NotEnoughAmountException("can not transfer: source card has not enough amount");
        }

        initiatorCard.setAmount(initiatorCard.getAmount() - amount);
        recipientCard.setAmount(recipientCard.getAmount() + amount);
        cardRepository.save(initiatorCard);
        cardRepository.save(recipientCard);
        transaction.setStatus(TransactionStatusConstants.DONE);
        transactionRepository.save(transaction);
    }
}
