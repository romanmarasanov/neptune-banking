package ru.marasanov.neptune.banking.model;

import ru.marasanov.neptune.banking.model.dto.AccountDTO;
import ru.marasanov.neptune.banking.model.dto.CardDTO;
import ru.marasanov.neptune.banking.model.dto.TransactionDTO;
import ru.marasanov.neptune.banking.model.entity.Account;
import ru.marasanov.neptune.banking.model.entity.Card;
import ru.marasanov.neptune.banking.model.entity.Transaction;

import java.util.ArrayList;
import java.util.List;

public class ConverterDTO {
    public static AccountDTO toAccountDTO(Account account) {
        List<CardDTO> cardDTOs = new ArrayList<>();
        for (Card card : account.getCards()) {
            cardDTOs.add(toCardDTO(card));
        }
        return new AccountDTO(
                account.getFirstName(),
                account.getLastName(),
                account.getEmail(),
                account.getPhoneNumber(),
                account.getRole(),
                cardDTOs);
    }

    public static CardDTO toCardDTO(Card card) {
        List<TransactionDTO> inputTransactionDTOs = new ArrayList<>();
        List<TransactionDTO> outputTransactionDTOs = new ArrayList<>();
        for (Transaction transaction : card.getInputTransactions()) {
            inputTransactionDTOs.add(toTransactionDTO(transaction));
        }
        for (Transaction transaction : card.getOutputTransactions()) {
            outputTransactionDTOs.add(toTransactionDTO(transaction));
        }

        return new CardDTO(
                card.getNumber(),
                card.getAmount(),
                card.getStatus(),
                card.getOwnerAccount().getFirstName(),
                inputTransactionDTOs, outputTransactionDTOs
        );
    }

    public static TransactionDTO toTransactionDTO(Transaction transaction) {
        return new TransactionDTO(
                transaction.getAmount(),
                transaction.getInitiatorCard().getNumber(),
                transaction.getRecipientCard().getNumber(),
                transaction.getInitiatorAccount().getFirstName(),
                transaction.getStatus(),
                transaction.getTimestamp()
        );
    }
}
