package ru.marasanov.neptune.banking.model;

import ru.marasanov.neptune.banking.model.dto.AccountDTO;
import ru.marasanov.neptune.banking.model.dto.CardDTO;
import ru.marasanov.neptune.banking.model.dto.RegistrationDTO;
import ru.marasanov.neptune.banking.model.dto.TransactionDTO;
import ru.marasanov.neptune.banking.model.entity.Account;
import ru.marasanov.neptune.banking.model.entity.Card;
import ru.marasanov.neptune.banking.model.entity.Transaction;
import ru.marasanov.neptune.banking.model.enums.Role;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

public class ConverterDTO {
    public static AccountDTO toAccountDTO(Account account) {
        List<Integer> cardIds = new ArrayList<>();
        for (Card card : account.getCards()) {
            cardIds.add(card.getId());
        }
        return new AccountDTO(
                account.getFirstName(),
                account.getLastName(),
                account.getEmail(),
                account.getPhoneNumber(),
                account.getRole(),
                cardIds);
    }

    public static CardDTO toCardDTO(Card card) {
        List<Integer> inputTransactionIds = new ArrayList<>();
        List<Integer> outputTransactionIds = new ArrayList<>();
        for (Transaction transaction : card.getInputTransactions()) {
            inputTransactionIds.add(transaction.getId());
        }
        for (Transaction transaction : card.getOutputTransactions()) {
            outputTransactionIds.add(transaction.getId());
        }

        return new CardDTO(
                card.getNumber(),
                card.getAmount(),
                card.getStatus(),
                card.getOwnerAccount().getFirstName(),
                inputTransactionIds, outputTransactionIds
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

    public static Account toAccount(RegistrationDTO registrationDTO) {
        return Account.builder()
                .setFirstName(registrationDTO.getFirstName())
                .setLastName(registrationDTO.getLastName())
                .setPassword(registrationDTO.getPassword())
                .setEmail(registrationDTO.getEmail())
                .setPhoneNumber(registrationDTO.getPhoneNumber())
                .setRole(Role.CLIENT)
                .setCreatedAt(new Timestamp(System.currentTimeMillis()))
                .build();
    }
}
