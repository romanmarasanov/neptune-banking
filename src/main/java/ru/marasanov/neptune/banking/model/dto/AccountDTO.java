package ru.marasanov.neptune.banking.model.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import ru.marasanov.neptune.banking.model.enums.Role;

import java.util.List;


@Getter
@Setter
@AllArgsConstructor
public class AccountDTO {
    private String firstName;
    private String lastName;
    private String email;
    private String phoneNumber;
    private Role role;
    private List<Integer> cardsIds;
}
