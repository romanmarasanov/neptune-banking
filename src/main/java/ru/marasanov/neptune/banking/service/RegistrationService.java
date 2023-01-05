package ru.marasanov.neptune.banking.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.marasanov.neptune.banking.exception.NotValidFormDataException;
import ru.marasanov.neptune.banking.model.ConverterDTO;
import ru.marasanov.neptune.banking.model.dto.RegistrationDTO;
import ru.marasanov.neptune.banking.model.entity.Account;
import ru.marasanov.neptune.banking.repository.AccountRepository;
import ru.marasanov.neptune.banking.validation.EmailValidator;
import ru.marasanov.neptune.banking.validation.PhoneNumberValidator;

@Service
public class RegistrationService {
    private final EmailValidator emailValidator;
    private final PhoneNumberValidator phoneNumberValidator;
    private final AccountRepository accountRepository;

    @Autowired
    public RegistrationService(EmailValidator emailValidator, PhoneNumberValidator phoneNumberValidator, AccountRepository accountRepository) {
        this.emailValidator = emailValidator;
        this.phoneNumberValidator = phoneNumberValidator;
        this.accountRepository = accountRepository;
    }

    public void register(RegistrationDTO registrationDTO) throws NotValidFormDataException {
        if (!emailValidator.test(registrationDTO.getEmail())) {
            throw new NotValidFormDataException("email is not valid");
        }
        if (!phoneNumberValidator.test(registrationDTO.getPhoneNumber())) {
            throw new NotValidFormDataException("phone number is not valid");
        }
        Account newAccount = ConverterDTO.toAccount(registrationDTO);
        accountRepository.save(newAccount);
    }
}
