package ru.marasanov.neptune.banking.model.entity;

import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Data
@Entity
@Table(name = "card")
public class Card {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "card_id")
    private int id;

    @Column(name = "card_number")
    private String number;

    @Column(name = "pin")
    private String pin;

    @Column(name = "amount")
    private long amount;

    @Column(name = "status")
    private String status;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "account_id")
    private Account ownerAccount;

    @OneToMany(mappedBy = "initiatorCard", fetch = FetchType.LAZY)
    private List<Transaction> outputTransactions;
    @OneToMany(mappedBy = "recipientCard", fetch = FetchType.LAZY)
    private List<Transaction> inputTransactions;

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
        this.outputTransactions = builder.outputTransactions;
        this.inputTransactions = builder.inputTransactions;
    }

    public static final class Builder {
        private int id;
        private String number;
        private String pin;
        private long amount;
        private String status;
        private Account ownerAccount;
        private List<Transaction> outputTransactions;
        private List<Transaction> inputTransactions;

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

        public Builder setOutputTransactions(List<Transaction> outputTransactions) {
            this.outputTransactions = outputTransactions;
            return this;
        }

        public Builder setInputTransactions(List<Transaction> inputTransactions) {
            this.inputTransactions = inputTransactions;
            return this;
        }

        public Card build() {
            return new Card(this);
        }
    }
}
