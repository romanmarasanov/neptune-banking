package ru.marasanov.neptune.banking.model.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import ru.marasanov.neptune.banking.model.enums.TransactionStatus;

import java.sql.Timestamp;

@Getter
@Setter
@AllArgsConstructor
public class TransactionDTO {
    private int amount;
    private String initiatorCardNumber;
    private String recipientCardNumber;
    private String initiatorFirstName;
    private String receiverFirstName;
    private TransactionStatus status;
    private Timestamp timestamp;
}
