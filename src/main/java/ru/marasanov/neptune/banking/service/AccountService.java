package ru.marasanov.neptune.banking.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.marasanov.neptune.banking.exception.UserNotFoundException;
import ru.marasanov.neptune.banking.model.Account;
import ru.marasanov.neptune.banking.repository.AccountRepository;

import javax.transaction.Transactional;
import java.sql.Timestamp;
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

    public List<Account> getAll() {
        return accountRepository.findAll();
    }

    public Account getById(int id) {
        Optional<Account> accountOptional = accountRepository.findById(id);
        if (accountOptional.isEmpty()) {
            throw new UserNotFoundException("can not find account with specified id");
        }
        return accountOptional.get();
    }

    public Account getByPhoneNumber(String number) {
        Optional<Account> accountOptional = accountRepository.findByPhoneNumber(number);
        if (accountOptional.isEmpty()) {
            throw new UserNotFoundException("can not find account with specified phone number");
        }
        return accountOptional.get();
    }

    public List<Account> getSearchByFullNameResults(String searchStr) {
        return accountRepository.findByFullNameContainingIgnoreCase(searchStr);
    }

    public List<Account> getAllCreatedAfter(Timestamp ts) {
        return accountRepository.findByCreatedAtGreaterThan(ts);
    }

    public void save(Account account) {
        accountRepository.save(account);
    }

    public void delete(Account account) {
        accountRepository.delete(account);
    }
}
