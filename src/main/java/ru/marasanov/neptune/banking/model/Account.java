package ru.marasanov.neptune.banking.model;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
public class Account {
    private int id;
    private String fullName;
    private String phoneNumber;
    private String role;
    private LocalDateTime createdAt;
    private List<Card> cards;

    public Builder builder() {
        return new Builder();
    }

    public Account(Builder builder) {
        this.id = builder.id;
        this.fullName = builder.fullName;
        this.phoneNumber = builder.phoneNumber;
        this.role = builder.role;
        this.createdAt = builder.createdAt;
        this.cards = builder.cards;
    }

    public static final class Builder {
        private int id;
        private String fullName;
        private String phoneNumber;
        private String role;
        private LocalDateTime createdAt;
        private List<Card> cards;

        private Builder() {}

        public Builder setId(int id) {
            this.id = id;
            return this;
        }

        public Builder setFullName(String fullName) {
            this.fullName = fullName;
            return this;
        }

        public Builder setPhoneNumber(String phoneNumber) {
            this.phoneNumber = phoneNumber;
            return this;
        }

        public Builder setRole(String role) {
            this.role = role;
            return this;
        }

        public Builder setCreatedAt(LocalDateTime createdAt) {
            this.createdAt = createdAt;
            return this;
        }

        public Builder setCards(List<Card> cards) {
            this.cards = cards;
            return this;
        }

        public Account build() {
            return new Account(this);
        }
    }
}
