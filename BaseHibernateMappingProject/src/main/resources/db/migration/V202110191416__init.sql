CREATE TABLE IF NOT EXISTS employee (
    id bigint NOT NULL AUTO_INCREMENT PRIMARY KEY,
    name varchar(128),
    gender varchar(64)
    );

INSERT INTO employee(name) VALUES ('Ivan');

COMMIT;

create sequence employee_id_sequence;

CREATE TABLE IF NOT EXISTS employee_sequence
(
    id bigint NOT NULL default employee_id_sequence.nextval PRIMARY KEY,
    name varchar(128) not null
);

CREATE TABLE IF NOT EXISTS table_sequence
(
    table_name varchar(128) not null,
    pk_value   bigint  not null default 1
);

CREATE TABLE IF NOT EXISTS employee_table
(
    id bigint NOT NULL PRIMARY KEY,
    name varchar(128) not null
);

CREATE TABLE IF NOT EXISTS employee3 (
    id bigint NOT NULL AUTO_INCREMENT PRIMARY KEY,
    name varchar(128),
    gender varchar(64),
    home_city varchar(128),
    home_street varchar(128),
    work_city varchar(128),
    work_street varchar(128)
);