package ru.marasanov.neptune.banking.model.entity;

import lombok.Data;
import ru.marasanov.neptune.banking.model.enums.Role;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.List;

@Data
@Entity
@Table(name = "account")
public class Account {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "account_id")
    private int id;

    @Column(name = "password")
    private String password;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @Column(name = "phone_number")
    private String phoneNumber;

    @Column(name = "email")
    private String email;

    @Column(name = "role")
    @Enumerated(EnumType.STRING)
    private Role role;

    @Column(name = "created_at")
    private Timestamp createdAt;

    @OneToMany(mappedBy = "ownerAccount")
    private List<Card> cards;

    public Account() {
    }

    public static Builder builder() {
        return new Builder();
    }

    public Account(Builder builder) {
        this.id = builder.id;
        this.firstName = builder.firstName;
        this.lastName = builder.lastName;
        this.phoneNumber = builder.phoneNumber;
        this.email = builder.email;
        this.role = builder.role;
        this.createdAt = builder.createdAt;
        this.cards = builder.cards;
    }

    public static final class Builder {
        private int id;
        private String firstName;
        private String lastName;
        private String email;
        private String phoneNumber;
        private Role role;
        private Timestamp createdAt;
        private List<Card> cards;

        private Builder() {}

        public Builder setId(int id) {
            this.id = id;
            return this;
        }

        public Builder setFirstName(String firstName) {
            this.firstName = firstName;
            return this;
        }

        public Builder setLastName(String lastName) {
            this.lastName = lastName;
            return this;
        }

        public Builder setEmail(String email) {
            this.email = email;
            return this;
        }

        public Builder setPhoneNumber(String phoneNumber) {
            this.phoneNumber = phoneNumber;
            return this;
        }

        public Builder setRole(Role role) {
            this.role = role;
            return this;
        }

        public Builder setCreatedAt(Timestamp createdAt) {
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
