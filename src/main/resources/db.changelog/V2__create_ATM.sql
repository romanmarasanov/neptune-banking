INSERT INTO account VALUES (100, '$2a$12$T6LFLZA/WR3Z9W/kkID3uOPNzkZON.8gYFNTWPRqtQ6q5Z3RonxB.', 'ATM1', 'ATM1', '-', 'ATM1@banking.com', 'ROLE_ATM', CURRENT_TIMESTAMP);
INSERT INTO card VALUES (100, 100, '0000000000000000', '$2a$12$RIkk1mWP7CzD.ynNUYOiGurXk38qlF8v4DdFb.d6wlnDO3KRLvPwm', 2000000000, 'ACTIVE');
INSERT INTO account VALUES (101, '$2a$12$T6LFLZA/WR3Z9W/kkID3uOPNzkZON.8gYFNTWPRqtQ6q5Z3RonxB.', 'ATM2', 'ATM2', '--', 'ATM2@banking.com', 'ROLE_ATM', CURRENT_TIMESTAMP);
INSERT INTO card VALUES (101, 101, '9999999999999999', '$2a$12$RIkk1mWP7CzD.ynNUYOiGurXk38qlF8v4DdFb.d6wlnDO3KRLvPwm', 2000000000, 'ACTIVE');
--ATM1 card base settings: card number is 16 zeros, pin is hashed (bcrypt) '0000', amount is two billion
--ATM1 account base settings: password is hashed (bcrypt) '000000'
--ATM2 the same but card number is 16 nines