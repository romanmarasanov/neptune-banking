package ru.marasanov.neptune.banking.model.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import ru.marasanov.neptune.banking.model.enums.CardStatus;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
public class CardDTO {
    private String number;
    private long amount;
    private CardStatus status;
    private String ownerFirstName;
    private List<TransactionDTO> outputTransactionDTOs;
    private List<TransactionDTO> inputTransactionDTOs;

}
