package ru.marasanov.neptune.banking.model.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class RegistrationDTO {
    private final String firstName;
    private final String lastName;
    private final String phoneNumber;
    private final String email;
    private final String password;
}
