package ru.marasanov.neptune.banking.exception;

public class CardNotFoundException extends Exception {
    public CardNotFoundException(String message) {
        super(message);
    }
}
