package ru.marasanov.neptune.banking.model;

import lombok.Getter;
import lombok.Setter;

import java.sql.Timestamp;

@Getter
@Setter
public class Transaction {
    private int id;
    private Card initiatorCard;
    private Card recipientCard;
    private Timestamp timestamp;

    public Transaction(Transaction.Builder builder) {
        this.id = builder.id;
        this.initiatorCard = builder.initiatorCard;
        this.recipientCard = builder.recipientCard;
        this.timestamp = builder.timestamp;
    }

    public static final class Builder {
        private int id;
        private Card initiatorCard;
        private Card recipientCard;
        private Timestamp timestamp;

        private Builder() {}

        public Builder setId(int id) {
            this.id = id;
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

        public Builder setTimestamp(Timestamp timestamp) {
            this.timestamp = timestamp;
            return this;
        }

        public Transaction build() {
            return new Transaction(this);
        }
    }
}
