package ru.marasanov.neptune.banking.exception;

public class NotEnoughAmountException extends Exception {
    public NotEnoughAmountException(String message) {
        super(message);
    }
}
