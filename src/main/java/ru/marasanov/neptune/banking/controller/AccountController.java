package ru.marasanov.neptune.banking.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ru.marasanov.neptune.banking.model.ConverterDTO;
import ru.marasanov.neptune.banking.model.dto.AccountDTO;
import ru.marasanov.neptune.banking.service.AccountService;

@RestController
@RequestMapping("/api/v1/account")
public class AccountController {

    private final AccountService accountService;

    @Autowired
    public AccountController(AccountService accountService) {
        this.accountService = accountService;
    }

    //TODO: change method to searchBy in mapping '/search' (now name of the method doesn't note what it really does)
    @GetMapping
    public AccountDTO getById(@RequestParam(required = false, name = "number") String phoneNumber,
                              @RequestParam(required = false) String email,
                              @RequestParam(required = false) Integer id) {
        if (phoneNumber != null) {
            return ConverterDTO.toAccountDTO(accountService.getByPhoneNumber(phoneNumber));
        } else if (email != null) {
            return ConverterDTO.toAccountDTO(accountService.getByEmail(email));
        } else if (id != null) {
            return ConverterDTO.toAccountDTO(accountService.getById(id));
        } else {
            //TODO: process no args case
            return null;
        }
    }
}
