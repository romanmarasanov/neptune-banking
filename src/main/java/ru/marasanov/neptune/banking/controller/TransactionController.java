package ru.marasanov.neptune.banking.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ru.marasanov.neptune.banking.model.ConverterDTO;
import ru.marasanov.neptune.banking.model.dto.TransactionDTO;
import ru.marasanov.neptune.banking.model.entity.Transaction;
import ru.marasanov.neptune.banking.service.TransactionService;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("api/v1/transaction")
public class TransactionController {

    private final TransactionService transactionService;

    @Autowired
    public TransactionController(TransactionService transactionService) {
        this.transactionService = transactionService;
    }

    @GetMapping
    public List<TransactionDTO> getTransactions(@RequestParam(name = "find_by") String findBy,
                                                @RequestParam String value) {
        List<Transaction> transactions;
        switch (findBy) {
            case "source_card_id":
                transactions = transactionService.getByInitiatorCardId(Integer.parseInt(value));
                break;
            case "destination_card_id":
                transactions = transactionService.getByRecipientCardId(Integer.parseInt(value));
                break;
            case "corresponding_card_id":
                transactions = transactionService.getAllByCardId(Integer.parseInt(value));
                break;
            default:
                return null; //TODO: process no args case
        }
        return transactions.stream().map(ConverterDTO::toTransactionDTO).collect(Collectors.toList());
    }
}
