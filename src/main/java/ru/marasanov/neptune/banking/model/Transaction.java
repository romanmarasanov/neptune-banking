package ru.marasanov.neptune.banking.model;

import lombok.Data;

import java.sql.Timestamp;

@Data
public class Transaction {
    private int id;
    private int amount;
    private Card initiatorCard;
    private Card recipientCard;
    private Account initiatorAccount;
    private String status;
    private Timestamp timestamp;

    public Transaction() {
    }

    public static Builder builder() {
        return new Builder();
    }

    public Transaction(Builder builder) {
        this.id = builder.id;
        this.amount = builder.amount;
        this.initiatorCard = builder.initiatorCard;
        this.recipientCard = builder.recipientCard;
        this.initiatorAccount = builder.initiatorAccount;
        this.timestamp = builder.timestamp;
        this.status = builder.status;
    }

    public static final class Builder {
        private int id;
        private int amount;
        private Card initiatorCard;
        private Card recipientCard;
        private Account initiatorAccount;
        private String status;
        private Timestamp timestamp;

        private Builder() {}

        public Builder setId(int id) {
            this.id = id;
            return this;
        }

        public Builder setAmount(int amount) {
            this.amount = amount;
            return this;
        }


        public Builder setInitiatorCard(Card initiatorCard) {
            this.initiatorCard = initiatorCard;
            return this;
        }

        public Builder setRecipientCard(Card recipientCard) {
            this.recipientCard = recipientCard;
            return this;
        }

        public Builder setInitiatorAccount(Account initiatorAccount) {
            this.initiatorAccount = initiatorAccount;
            return this;
        }

        public Builder setTimestamp(Timestamp timestamp) {
            this.timestamp = timestamp;
            return this;
        }

        public Builder setStatus(String status) {
            this.status = status;
            return this;
        }

        public Transaction build() {
            return new Transaction(this);
        }
    }
}
