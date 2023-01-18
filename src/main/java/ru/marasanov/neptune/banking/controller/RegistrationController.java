package ru.marasanov.neptune.banking.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ru.marasanov.neptune.banking.model.dto.RegistrationDTO;
import ru.marasanov.neptune.banking.service.RegistrationService;

@RestController
@RequestMapping("api/v1/registration")
public class RegistrationController {

    private final RegistrationService registrationService;

    @Autowired
    public RegistrationController(RegistrationService registrationService) {
        this.registrationService = registrationService;
    }

    @PostMapping
    public RegistrationDTO register(@RequestBody RegistrationDTO registrationDTO) {
        registrationService.register(registrationDTO);
        return registrationDTO;
    }
}
