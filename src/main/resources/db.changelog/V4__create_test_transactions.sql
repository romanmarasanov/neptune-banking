insert into transaction(source_card_id, destination_card_id, initiation_account_id, amount, ts, status) values (100, 101, 100, 500, CURRENT_TIMESTAMP, 'DONE');
insert into transaction(source_card_id, destination_card_id, initiation_account_id, amount, ts, status) values (100, 101, 100, 200, CURRENT_TIMESTAMP, 'DONE');
insert into transaction(source_card_id, destination_card_id, initiation_account_id, amount, ts, status) values (100, 101, 100, 1500, CURRENT_TIMESTAMP, 'CANCELED');
insert into transaction(source_card_id, destination_card_id, initiation_account_id, amount, ts, status) values (101, 100, 101, 234, CURRENT_TIMESTAMP, 'DONE');
insert into transaction(source_card_id, destination_card_id, initiation_account_id, amount, ts, status) values (101, 100, 101, 543, CURRENT_TIMESTAMP, 'DONE');