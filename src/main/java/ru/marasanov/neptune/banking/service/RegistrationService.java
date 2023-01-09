package ru.marasanov.neptune.banking.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
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
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public RegistrationService(EmailValidator emailValidator, PhoneNumberValidator phoneNumberValidator, AccountRepository accountRepository, PasswordEncoder passwordEncoder) {
        this.emailValidator = emailValidator;
        this.phoneNumberValidator = phoneNumberValidator;
        this.accountRepository = accountRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public void register(RegistrationDTO registrationDTO) throws NotValidFormDataException {
        if (!emailValidator.test(registrationDTO.getEmail())) {
            throw new NotValidFormDataException("email is not valid");
        }
        if (!phoneNumberValidator.test(registrationDTO.getPhoneNumber())) {
            throw new NotValidFormDataException("phone number is not valid");
        }
        if (accountRepository.findByEmail(registrationDTO.getEmail()).isPresent()) {
            throw new NotValidFormDataException("the account with specified email already exists");
        }

        RegistrationDTO registrationDTOEncrypted = new RegistrationDTO(
                registrationDTO.getFirstName(),
                registrationDTO.getLastName(),
                registrationDTO.getPhoneNumber(),
                registrationDTO.getEmail(),
                passwordEncoder.encode(registrationDTO.getPassword())
        );

        Account newAccount = ConverterDTO.toAccount(registrationDTOEncrypted);
        accountRepository.save(newAccount);
    }
}
