package ru.marasanov.neptune.banking.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import ru.marasanov.neptune.banking.exception.TransactionNotFoundException;
import ru.marasanov.neptune.banking.model.entity.Transaction;
import ru.marasanov.neptune.banking.repository.TransactionRepository;

import java.util.List;

@Service
public class TransactionService {

    private final TransactionRepository transactionRepository;

    @Autowired
    public TransactionService(TransactionRepository transactionRepository) {
        this.transactionRepository = transactionRepository;
    }

    public Transaction getById(Integer id) throws TransactionNotFoundException {
        return transactionRepository
                .findById(id)
                .orElseThrow(() -> new TransactionNotFoundException(
                        HttpStatus.NOT_FOUND,
                        "can not find transaction with specified id")
                );
    }

    public List<Transaction> getByRecipientCardId(int cardId) {
        return transactionRepository.findByDestinationCardId(cardId);
    }

    public List<Transaction> getByInitiatorCardId(int cardId) {
        return transactionRepository.findBySourceCardId(cardId);
    }

    public List<Transaction> getAllByCardId(int cardId) {
        return transactionRepository.findByRecipientCardIdOrInitiatorCardId(cardId, cardId);
    }
}
