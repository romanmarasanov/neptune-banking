package ru.marasanov.neptune.banking.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import ru.marasanov.neptune.banking.exception.NotValidJSONValueException;
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

    @GetMapping("/{id}")
    public TransactionDTO getById(@PathVariable int id) {
        return ConverterDTO.toTransactionDTO(transactionService.getById(id));
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
                throw new NotValidJSONValueException(HttpStatus.BAD_REQUEST,
                        "request param 'find_by' is not valid");
        }
        return transactions.stream().map(ConverterDTO::toTransactionDTO).collect(Collectors.toList());
    }
}
