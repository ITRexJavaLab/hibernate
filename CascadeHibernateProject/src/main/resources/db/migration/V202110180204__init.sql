-- One-to-One
CREATE TABLE IF NOT EXISTS address (
    id int NOT NULL AUTO_INCREMENT PRIMARY KEY,
    name varchar(32));

CREATE TABLE IF NOT EXISTS person (
    id int NOT NULL AUTO_INCREMENT PRIMARY KEY,
    name varchar(32),
    address_id int,
    FOREIGN KEY (address_id) REFERENCES address(id));

INSERT INTO address(name) VALUES ('Minsk');
INSERT INTO address(name) VALUES ('Kiev');
INSERT INTO address(name) VALUES ('Moscow');
INSERT INTO address(name) VALUES ('London');

INSERT INTO person(name, address_id) VALUES ('Sergey',
    (SELECT id FROM address WHERE name = 'Minsk'));
INSERT INTO person(name, address_id) VALUES ('Alexey',
    (SELECT id FROM address WHERE name = 'Kiev'));
INSERT INTO person(name, address_id) VALUES ('Irina',
    (SELECT id FROM address WHERE name = 'Moscow'));
INSERT INTO person(name, address_id) VALUES ('Olga',
    (SELECT id FROM address WHERE name = 'London'));

-- Many-to-One / One-to-Many
CREATE TABLE IF NOT EXISTS book_category (
    id int NOT NULL AUTO_INCREMENT PRIMARY KEY,
    name varchar(32));

CREATE TABLE IF NOT EXISTS book (
    id int NOT NULL AUTO_INCREMENT PRIMARY KEY,
    name varchar(32),
    book_category_id int,
    FOREIGN KEY (book_category_id) REFERENCES book_category(id));

INSERT INTO book_category(name) VALUES ('Detective');
INSERT INTO book_category(name) VALUES ('Science');

INSERT INTO book(name, book_category_id) VALUES ('Black book',
    (SELECT id FROM book_category WHERE name = 'Detective'));
INSERT INTO book(name, book_category_id) VALUES ('Red book',
    (SELECT id FROM book_category WHERE name = 'Science'));
INSERT INTO book(name, book_category_id) VALUES ('Green book',
    (SELECT id FROM book_category WHERE name = 'Detective'));
INSERT INTO book(name, book_category_id) VALUES ('Blue book',
    (SELECT id FROM book_category WHERE name = 'Detective'));

COMMIT;