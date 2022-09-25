package ru.marasanov.neptune.banking.model.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.sql.Timestamp;

@Getter
@Setter
@AllArgsConstructor
public class TransactionDTO {
    private int amount;
    private String initiatorCardNumber;
    private String recipientCardNumber;
    private String initiatorFullName;
    private String status;
    private Timestamp timestamp;
}
