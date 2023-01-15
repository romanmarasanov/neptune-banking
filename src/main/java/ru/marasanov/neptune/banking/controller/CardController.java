package ru.marasanov.neptune.banking.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ru.marasanov.neptune.banking.exception.CardBlockedException;
import ru.marasanov.neptune.banking.exception.CardNotFoundException;
import ru.marasanov.neptune.banking.model.ConverterDTO;
import ru.marasanov.neptune.banking.model.dto.CardDTO;
import ru.marasanov.neptune.banking.model.dto.TransactionDTO;
import ru.marasanov.neptune.banking.model.entity.Card;
import ru.marasanov.neptune.banking.model.entity.Transaction;
import ru.marasanov.neptune.banking.service.CardService;

import java.util.ArrayList;
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
        try {
            switch (findBy) {
                case "number":
                    return ConverterDTO.toCardDTO(cardService.getByNumber(value));
                case "id":
                    return ConverterDTO.toCardDTO(cardService.getById(Integer.parseInt(value)));
                default:
                    return null; //TODO: process no args case
            }
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @GetMapping("/one")
    public List<CardDTO> getCards(@RequestParam(name = "find_by") String findBy,
                           @RequestParam String value) {
        List<Card> cards;
        try {
            switch (findBy) {
                case "email":
                    cards = cardService.getByOwnerEmail(value);
                    break;
                case "phone_number":
                    cards = cardService.getByOwnerPhoneNumber(value);
                    break;
                default:
                    return null; //TODO: process no args case
            }
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
        return cards.stream().map(ConverterDTO::toCardDTO).collect(Collectors.toList());
    }
}
