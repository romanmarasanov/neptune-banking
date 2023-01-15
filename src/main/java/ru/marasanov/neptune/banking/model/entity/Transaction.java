package ru.marasanov.neptune.banking.model.entity;
import lombok.Data;
import ru.marasanov.neptune.banking.model.enums.TransactionStatus;

import javax.persistence.*;
import java.sql.Timestamp;

@Data
@Entity
@Table(name = "transaction")
public class Transaction {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "transaction_id")
    private int id;

    @Column(name = "amount")
    private int amount;

    @ManyToOne
    @JoinColumn(name = "source_card_id", referencedColumnName = "card_id")
    private Card initiatorCard;

    @ManyToOne
    @JoinColumn(name = "destination_card_id", referencedColumnName = "card_id")
    private Card recipientCard;

    @ManyToOne
    @JoinColumn(name = "initiation_account_id", referencedColumnName = "account_id")
    private Account initiatorAccount;

    @ManyToOne
    @JoinColumn(name = "receiver_account_id", referencedColumnName = "account_id")
    private Account receiverAccount;

    @Column(name = "status")
    @Enumerated(EnumType.STRING)
    private TransactionStatus status;

    @Column(name = "ts")
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
        this.receiverAccount = builder.receiverAccount;
        this.timestamp = builder.timestamp;
        this.status = builder.status;
    }

    public static final class Builder {
        private int id;
        private int amount;
        private Card initiatorCard;
        private Card recipientCard;
        private Account initiatorAccount;
        private Account receiverAccount;
        private TransactionStatus status;
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

        public void setReceiverAccount(Account receiverAccount) {
            this.receiverAccount = receiverAccount;
        }

        public Builder setTimestamp(Timestamp timestamp) {
            this.timestamp = timestamp;
            return this;
        }

        public Builder setStatus(TransactionStatus status) {
            this.status = status;
            return this;
        }

        public Transaction build() {
            return new Transaction(this);
        }
    }
}
