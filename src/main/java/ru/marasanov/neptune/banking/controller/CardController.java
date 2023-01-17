package ru.marasanov.neptune.banking.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import ru.marasanov.neptune.banking.exception.NotValidJSONValueException;
import ru.marasanov.neptune.banking.model.ConverterDTO;
import ru.marasanov.neptune.banking.model.dto.CardDTO;
import ru.marasanov.neptune.banking.model.entity.Card;
import ru.marasanov.neptune.banking.service.CardService;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("api/v1/card")
public class CardController {
    private final CardService cardService;

    @Autowired
    public CardController(CardService cardService) {
        this.cardService = cardService;
    }

    @GetMapping
    public CardDTO getCard(@RequestParam(name = "find_by") String findBy,
                           @RequestParam String value) {
        switch (findBy) {
            case "number":
                return ConverterDTO.toCardDTO(cardService.getByNumber(value));
            case "id":
                return ConverterDTO.toCardDTO(cardService.getById(Integer.parseInt(value)));
            default:
                throw new NotValidJSONValueException(HttpStatus.BAD_REQUEST,
                        "request param 'find_by' is not valid");
        }
    }

    @GetMapping("/by_account")
    public List<CardDTO> getCards(@RequestParam(name = "find_by") String findBy,
                           @RequestParam String value) {
        List<Card> cards;
        switch (findBy) {
            case "email":
                cards = cardService.getByOwnerEmail(value);
                break;
            case "phone_number":
                cards = cardService.getByOwnerPhoneNumber(value);
                break;
            case "account_id":
                cards = cardService.getByOwnerId(Integer.parseInt(value));
                break;
            default:
                throw new NotValidJSONValueException(HttpStatus.BAD_REQUEST,
                        "request param 'find_by' is not valid");
        }
        return cards.stream().map(ConverterDTO::toCardDTO).collect(Collectors.toList());
    }
}
