package ru.marasanov.neptune.banking.model;

import lombok.Data;

import java.util.List;

@Data
public class Card {
    private int id;
    private String number;
    private String pin;
    private long amount;
    private String status;
    private Account ownerAccount;
    private List<Transaction> transactions;

    public Card() {
    }

    public static Builder builder() {
        return new Builder();
    }

    public Card(Builder builder) {
        this.id = builder.id;
        this.number = builder.number;
        this.pin = builder.pin;
        this.amount = builder.amount;
        this.status = builder.status;
        this.ownerAccount = builder.ownerAccount;
        this.transactions = builder.transactions;
    }

    public static final class Builder {
        private int id;
        private String number;
        private String pin;
        private long amount;
        private String status;
        private Account ownerAccount;
        private List<Transaction> transactions;

        private Builder() {}

        public Builder setId(int id) {
            this.id = id;
            return this;
        }

        public Builder setNumber(String number) {
            this.number = number;
            return this;
        }

        public Builder setPin(String pin) {
            this.pin = pin;
            return this;
        }

        public Builder setAmount(long amount) {
            this.amount = amount;
            return this;
        }

        public Builder setStatus(String status) {
            this.status = status;
            return this;
        }

        public Builder setOwnerAccount(Account ownerAccount) {
            this.ownerAccount = ownerAccount;
            return this;
        }

        public Builder setTra
                (List<Transaction> transactions) {
            this.transactions = transactions;
            return this;
        }

        public Card build() {
            return new Card(this);
        }
    }
}
