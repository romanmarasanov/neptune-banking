package ru.marasanov.neptune.banking.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ru.marasanov.neptune.banking.exception.TransactionNotFoundException;
import ru.marasanov.neptune.banking.model.ConverterDTO;
import ru.marasanov.neptune.banking.model.dto.TransactionDTO;
import ru.marasanov.neptune.banking.model.entity.Transaction;
import ru.marasanov.neptune.banking.service.TransactionService;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("api/v1/transaction")
public class TransactionController {

    private final TransactionService transactionService;

    @Autowired
    public TransactionController(TransactionService transactionService) {
        this.transactionService = transactionService;
    }

    @GetMapping
    public TransactionDTO getById(@RequestParam Integer id) {
        try {
            return ConverterDTO.toTransactionDTO(transactionService.getById(id));
        } catch (TransactionNotFoundException e) {
            e.printStackTrace();
            return null; //TODO: fix
        }
    }

    @GetMapping("/card")
    public List<TransactionDTO> getByCardNumber(@RequestParam(name = "source_card_number", required = false) String sourceNumber,
                                                @RequestParam(name = "destination_card_number", required = false) String destinationNumber) {
        List<Transaction> transactions;
        if (sourceNumber != null) {
            transactions = transactionService.getBySourceCardNumber(sourceNumber);
        } else if (destinationNumber != null) {
            transactions = transactionService.getByDestinationCardNumber(destinationNumber);
        } else {
            return null; //TODO: process no args case
        }
        List<TransactionDTO> transactionDTOs = new ArrayList<>();
        for (Transaction transaction : transactions) {
            transactionDTOs.add(ConverterDTO.toTransactionDTO(transaction));
        }
        return transactionDTOs;
    }
}
