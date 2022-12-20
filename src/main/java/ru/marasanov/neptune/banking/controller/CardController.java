package ru.marasanov.neptune.banking.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ru.marasanov.neptune.banking.exception.CardNotFoundException;
import ru.marasanov.neptune.banking.model.ConverterDTO;
import ru.marasanov.neptune.banking.model.dto.CardDTO;
import ru.marasanov.neptune.banking.model.entity.Card;
import ru.marasanov.neptune.banking.service.CardService;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("api/v1/card")
public class CardController {
    private final CardService cardService;

    @Autowired
    public CardController(CardService cardService) {
        this.cardService = cardService;
    }

    @GetMapping
    public CardDTO getCard(@RequestParam(required = false, name = "card_number") String cardNumber,
                           @RequestParam(required = false) Integer id) {
        if (cardNumber != null) {
            try {
                return ConverterDTO.toCardDTO(cardService.getByNumber(cardNumber));
            } catch (CardNotFoundException e) {
                e.printStackTrace();
            }
        } else if(id != null) {
            try {
                return ConverterDTO.toCardDTO(cardService.getById(id));
            } catch (CardNotFoundException e) {
                e.printStackTrace();
            }
        }
        //TODO: process no args case
        return null;
    }

    @GetMapping("/owner")
    public List<CardDTO> getCards(@RequestParam(required = false, name = "email") String ownerEmail,
                                  @RequestParam(required = false, name = "phone_number") String ownerPhoneNumber) {
        if (ownerEmail != null) {
            List<Card> cards = cardService.getByOwnerEmail(ownerEmail);
            List<CardDTO> cardDTOs = new ArrayList<>();
            for (Card card : cards) {
                cardDTOs.add(ConverterDTO.toCardDTO(card));
            }
            return cardDTOs;
        } else if (ownerPhoneNumber != null) {
            List<Card> cards = cardService.getByOwnerPhoneNumber(ownerPhoneNumber);
            List<CardDTO> cardDTOs = new ArrayList<>();
            for (Card card : cards) {
                cardDTOs.add(ConverterDTO.toCardDTO(card));
            }
            return cardDTOs;
        }
        //TODO: process no args case
        return null;
    }
}
