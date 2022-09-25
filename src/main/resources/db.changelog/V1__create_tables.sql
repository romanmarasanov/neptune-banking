
CREATE TABLE account (
    account_id int PRIMARY KEY,
    password varchar(100) check ( length(password) > 5 ),
    full_name varchar(100) not null,
    phone_number varchar(20) UNIQUE not null,
    role varchar(30) not null,
    created_at timestamp
);

CREATE TABLE card (
    card_id int PRIMARY KEY,
    account_id int not null REFERENCES account(account_id) ON DELETE CASCADE,
    card_number varchar(16) UNIQUE,
    pin varchar not null,
    amount int not null check ( amount >= 0 ),
    status varchar(30) not null
);

CREATE TABLE transaction (
    transaction_id int PRIMARY KEY,
    source_card_id int not null REFERENCES card(card_id) ON DELETE SET NULL,
    destination_card_id int not null REFERENCES card(card_id) ON DELETE SET NULL,
    initiation_account_id int not null REFERENCES account(account_id) ON DELETE SET NULL,
    amount int not null check (amount>=0),
    ts timestamp not null,
    status varchar(30) not null,
    check (source_card_id != destination_card_id)
);