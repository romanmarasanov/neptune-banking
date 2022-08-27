CREATE SEQUENCE account_id_seq
    AS int
    START WITH 1000
    INCREMENT BY 1;
ALTER TABLE account
    ALTER COLUMN account_id SET DEFAULT nextval('account_id_seq');

CREATE SEQUENCE card_id_seq
    AS int
    START WITH 1000
    INCREMENT BY 1;
ALTER TABLE card
    ALTER COLUMN card_id SET DEFAULT nextval('card_id_seq');

CREATE SEQUENCE transaction_id_seq
    AS int
    START WITH 1000
    INCREMENT BY 1;
ALTER TABLE transaction
    ALTER COLUMN transaction_id SET DEFAULT nextval('transaction_id_seq');