package ru.marasanov.neptune.banking.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.marasanov.neptune.banking.exception.AccountNotFoundException;
import ru.marasanov.neptune.banking.model.entity.Account;
import ru.marasanov.neptune.banking.repository.AccountRepository;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class AccountService {

    private final AccountRepository accountRepository;

    @Autowired
    public AccountService(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    public Account getById(int id) throws AccountNotFoundException {
        Optional<Account> accountOptional = accountRepository.findById(id);
        if (accountOptional.isEmpty()) {
            throw new AccountNotFoundException("can not find account with specified id");
        }
        return accountOptional.get();
    }

    public Account getByPhoneNumber(String number) throws AccountNotFoundException {
        Optional<Account> accountOptional = accountRepository.findByPhoneNumber(number);
        if (accountOptional.isEmpty()) {
            throw new AccountNotFoundException("can not find account with specified phone number");
        }
        return accountOptional.get();
    }

    public Account getByEmail(String email) throws AccountNotFoundException {
        Optional<Account> accountOptional = accountRepository.findByEmail(email);
        if (accountOptional.isEmpty()) {
            throw new AccountNotFoundException("can not find account with specified email");
        }
        return accountOptional.get();
    }


    public List<Account> getAll() {
        return accountRepository.findAll();
    }
}
