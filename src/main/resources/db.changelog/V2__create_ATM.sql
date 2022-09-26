INSERT INTO account VALUES (100, '91b4d142823f7d20c5f08df69122de43f35f057a988d9619f6d3138485c9a203', 'ATM', 'ATM', '-', 'ATM@banking.com', 'ROLE_ATM', CURRENT_TIMESTAMP);
INSERT INTO card VALUES (100, 100, '0000000000000000', '9af15b336e6a9619928537df30b2e6a2376569fcf9d7e773eccede65606529a0', 2000000000, 'ACTIVE');
--ATM card base settings: card number is 16 zeros, pin is hashed (sha256) '0000', amount is two billion
--ATM account base settings: password is hashed (sha256) '000000'