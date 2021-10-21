CREATE TABLE IF NOT EXISTS client (
    id int NOT NULL AUTO_INCREMENT PRIMARY KEY,
    name varchar(32),
    age int);

CREATE TABLE IF NOT EXISTS account (
    id int NOT NULL AUTO_INCREMENT PRIMARY KEY,
    amount int,
    currency varchar(3),
    client_id int,
    FOREIGN KEY (client_id) REFERENCES client(id));

INSERT INTO client(name, age) VALUES ('Sergey', 36);
INSERT INTO client(name, age) VALUES ('Alexey', 40);
INSERT INTO client(name, age) VALUES ('Irina', 27);
INSERT INTO client(name, age) VALUES ('Daria', 32);
INSERT INTO client(name, age) VALUES ('Olga', 33);
INSERT INTO client(name, age) VALUES ('Vadim', 54);
INSERT INTO client(name, age) VALUES ('Nikolay', 41);

INSERT INTO account(amount, currency, client_id) VALUES (100, 'USD',
    (SELECT id FROM client WHERE name = 'Sergey'));
INSERT INTO account(amount, currency, client_id) VALUES (200, 'EUR',
    (SELECT id FROM client WHERE name = 'Alexey'));
INSERT INTO account(amount, currency, client_id) VALUES (300, 'USD',
    (SELECT id FROM client WHERE name = 'Irina'));
INSERT INTO account(amount, currency, client_id) VALUES (400, 'EUR',
    (SELECT id FROM client WHERE name = 'Alexey'));

COMMIT;