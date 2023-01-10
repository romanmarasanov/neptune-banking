package ru.marasanov.neptune.banking.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ru.marasanov.neptune.banking.model.ConverterDTO;
import ru.marasanov.neptune.banking.model.dto.AccountDTO;
import ru.marasanov.neptune.banking.service.AccountService;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v1/account")
public class AccountController {

    private final AccountService accountService;

    @Autowired
    public AccountController(AccountService accountService) {
        this.accountService = accountService;
    }

    @GetMapping
    public AccountDTO getAccount(@RequestParam(name = "find_by") String findBy,
                                       @RequestParam String value) {
        switch (findBy) {
            case "id":
                return ConverterDTO.toAccountDTO(accountService.getById(Integer.parseInt(value)));
            case "email":
                return ConverterDTO.toAccountDTO(accountService.getByEmail(value));
            case "phone_number":
                return ConverterDTO.toAccountDTO(accountService.getByPhoneNumber(value));
            default: return null; //TODO: process no args case
        }
    }

    @GetMapping
    public List<AccountDTO> getAllAccounts() {
        return accountService.getAll().stream()
                .map(ConverterDTO::toAccountDTO)
                .collect(Collectors.toList());
    }

}
