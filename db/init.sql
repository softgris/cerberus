-- Cerberus Application db schema

-- Settings

SET statement_timeout = 0;
SET lock_timeout = 0;
SET idle_in_transaction_session_timeout = 0;
SET client_encoding = 'UTF8';
SET standard_conforming_strings = on;
SELECT pg_catalog.set_config('search_path', '', false);
SET check_function_bodies = false;
SET xmloption = content;
SET client_min_messages = warning;
SET row_security = off;

SET default_tablespace = '';

SET default_table_access_method = heap;

-- Schema

CREATE SCHEMA cerberus AUTHORIZATION gris;

-- Tables
DROP TABLE IF EXISTS cerberus.user_t;
CREATE TABLE cerberus.user_t (
    user_id bigint NOT NULL,
    email varchar(200) NOT NULL,
    password varchar(100) NOT NULL,
    role_id bigint NOT NULL,
    enabled boolean NOT NULL DEFAULT true
);
ALTER TABLE cerberus.user_t OWNER TO gris;

DROP TABLE IF EXISTS cerberus.authority;
CREATE TABLE cerberus.authority (
    authority_id bigint NOT NULL,
    authority varchar(50) NOT NULL,
    description varchar(200) NOT NULL
);
ALTER TABLE cerberus.authority OWNER TO gris;

DROP TABLE IF EXISTS cerberus.role;
CREATE TABLE cerberus.role (
    role_id bigint NOT NULL,
    role varchar(50) NOT NULL,
    description varchar(200) NOT NULL
);
ALTER TABLE cerberus.role OWNER TO gris;

DROP TABLE IF EXISTS cerberus.role_authority;
CREATE TABLE cerberus.role_authority (
    role_id bigint NOT NULL,
    authority_id bigint NOT NULL
);
ALTER TABLE cerberus.role_authority OWNER TO gris;

DROP TABLE IF EXISTS cerberus.address;
CREATE TABLE cerberus.address (
    user_id bigint NOT NULL,
    country varchar(50) NOT NULL,
    county varchar(50),
    city varchar(50) NOT NULL,
    postal_code varchar(10) NOT NULL,
    line_1 varchar(50) NOT NULL,
    line_2 varchar(50)
);
ALTER TABLE cerberus.address OWNER TO gris;

DROP TABLE IF EXISTS cerberus.item;
CREATE TABLE cerberus.item (
    item_id bigint NOT NULL,
    name varchar(100) NOT NULL,
    description varchar(500) NOT NULL,
    type varchar(50) NOT NULL,
    price_minor bigint NOT NULL,
    stock bigint NOT NULL,
    enabled boolean NOT NULL DEFAULT true
);
ALTER TABLE cerberus.item OWNER TO gris;

DROP TABLE IF EXISTS cerberus.cart_item;
CREATE TABLE cerberus.cart_item (
    user_id bigint NOT NULL,
    item_id bigint NOT NULL,
    quantity bigint NOT NULL
);
ALTER TABLE cerberus.cart_item OWNER TO gris;

DROP TABLE IF EXISTS cerberus.order_t;
CREATE TABLE cerberus.order_t (
    order_id bigint NOT NULL,
    user_id bigint NOT NULL,
    date_ordered date NOT NULL,
    cancelled boolean NOT NULL DEFAULT false
);
ALTER TABLE cerberus.order_t OWNER TO gris;

DROP TABLE IF EXISTS cerberus.order_item;
CREATE TABLE cerberus.order_item (
    order_id bigint NOT NULL,
    item_id bigint NOT NULL,
    price_minor bigint NOT NULL,
    quantity bigint NOT NULL
);
ALTER TABLE cerberus.order_item OWNER TO gris;

-- Primary keys

ALTER TABLE ONLY cerberus.user_t
    ADD CONSTRAINT pkey_user
        PRIMARY KEY (user_id),
    ADD CONSTRAINT cons_user_positive_id
        CHECK ((user_id >= 0));

ALTER TABLE ONLY cerberus.authority
    ADD CONSTRAINT pkey_authority
        PRIMARY KEY (authority_id),
    ADD CONSTRAINT cons_authority_positive_id
        CHECK ((authority_id >= 0));

ALTER TABLE ONLY cerberus.role
    ADD CONSTRAINT pkey_role
        PRIMARY KEY (role_id),
    ADD CONSTRAINT cons_role_positive_id
        CHECK ((role_id >= 0));

ALTER TABLE ONLY cerberus.item
    ADD CONSTRAINT pkey_item
        PRIMARY KEY (item_id),
    ADD CONSTRAINT cons_item_positive_id
        CHECK ((item_id >= 0));

ALTER TABLE ONLY cerberus.order_t
    ADD CONSTRAINT pkey_order
        PRIMARY KEY (order_id),
    ADD CONSTRAINT cons_order_positive_id
        CHECK ((order_id >= 0));

-- Foreign keys

ALTER TABLE ONLY cerberus.user_t
    ADD CONSTRAINT fkey_user_role
        FOREIGN KEY (role_id)
            REFERENCES cerberus.role(role_id)
                ON UPDATE CASCADE
                ON DELETE CASCADE;

ALTER TABLE ONLY cerberus.role_authority
    ADD CONSTRAINT fkey_role_authority_role
        FOREIGN KEY (role_id)
            REFERENCES cerberus.role(role_id)
                ON UPDATE CASCADE
                ON DELETE CASCADE,
    ADD CONSTRAINT fkey_role_authority_authority
        FOREIGN KEY (authority_id)
            REFERENCES cerberus.authority(authority_id)
                ON UPDATE CASCADE
                ON DELETE CASCADE;

ALTER TABLE ONLY cerberus.address
    ADD CONSTRAINT fkey_address_user
        FOREIGN KEY (user_id)
            REFERENCES cerberus.user_t(user_id)
                ON UPDATE CASCADE
                ON DELETE CASCADE;

ALTER TABLE ONLY cerberus.cart_item
    ADD CONSTRAINT fkey_cart_item_user
        FOREIGN KEY (user_id)
            REFERENCES cerberus.user_t(user_id)
                ON UPDATE CASCADE
                ON DELETE CASCADE,
    ADD CONSTRAINT fkey_cart_item_item
        FOREIGN KEY (item_id)
            REFERENCES cerberus.item(item_id)
                ON UPDATE CASCADE
                ON DELETE CASCADE;

ALTER TABLE ONLY cerberus.order_t
    ADD CONSTRAINT fkey_order_user
        FOREIGN KEY (user_id)
            REFERENCES cerberus.user_t(user_id)
                ON UPDATE CASCADE
                ON DELETE CASCADE;

ALTER TABLE ONLY cerberus.order_item
    ADD CONSTRAINT fkey_order_item_order
        FOREIGN KEY (order_id)
            REFERENCES cerberus.order_t(order_id)
                ON UPDATE CASCADE
                ON DELETE CASCADE,
    ADD CONSTRAINT fkey_order_item_item
        FOREIGN KEY (item_id)
            REFERENCES cerberus.item(item_id)
                ON UPDATE CASCADE
                ON DELETE CASCADE;

-- Other constraints

ALTER TABLE ONLY cerberus.item
    ADD CONSTRAINT cons_item_positive_price
        CHECK ((price_minor >= 0));

ALTER TABLE ONLY cerberus.item
    ADD CONSTRAINT cons_item_positive_stock
        CHECK ((stock >= 0));

ALTER TABLE ONLY cerberus.cart_item
    ADD CONSTRAINT cons_cart_item_positive_quantity
        CHECK ((quantity >= 0));

ALTER TABLE ONLY cerberus.order_item
    ADD CONSTRAINT cons_order_item_positive_quantity
        CHECK ((quantity >= 0));

-- Dummy data


INSERT INTO cerberus.role(role_id, role, description) VALUES
    (262294688, 'ADMIN', 'Administrator'),
    (103205808, 'CUSTOMER', 'Customer');

INSERT INTO cerberus.authority(authority_id, authority, description) VALUES
    (949726876, 'READ_ITEMS', 'Read items'),
    (181221016, 'WRITE_ITEMS', 'Write items'),
    (246427860, 'READ_USERS', 'Read users'),
    (759406271, 'WRITE_USERS', 'Write users'),
    (172585288, 'PLACE_ORDER', 'Place an order');

INSERT INTO cerberus.role_authority(role_id, authority_id) VALUES
    (262294688, 949726876), -- ADMIN READ_ITEMS
    (262294688, 181221016), -- ADMIN WRITE_ITEMS
    (262294688, 246427860), -- ADMIN READ_USERS
    (262294688, 759406271), -- ADMIN WRITE_USERS
    (262294688, 172585288), -- ADMIN PLACE_ORDER
    (103205808, 949726876), -- CUSTOMER READ_ITEMS
    (103205808, 172585288); -- CUSTOMER PLACE_ORDER

INSERT INTO cerberus.user_t(user_id, email, password, role_id, enabled) VALUES
    (566760000, 'andrei@gris.com', 'password', 262294688, true),
    (856146094, 'marius@gris.com', 'password', 262294688, true),
    (995436425, 'mihai@gris.com', 'password', 103205808, true),
    (224743443, 'ana@gris.com', 'password', 103205808, true);

INSERT INTO cerberus.address(user_id, country, county, city, postal_code, line_1, line_2) VALUES
    (566760000, 'Romania', 'Ilfov', 'Bucuresti', '123456', 'Strada Lulelelor 22', 'et. 1, ap. 9'),
    (856146094, 'United Kingdom', 'Greater London', 'Harrow', 'AA2 2AA', '32 New Road', NULL),
    (995436425, 'Romania', 'Iasi', 'Iasi', '123456', 'Strada Surcelelor 62', 'et. 2, ap. 12'),
    (224743443, 'United Kingdom', 'Greater London', 'Hounslow', 'BB2 2BB', '19 Old Avenue', null);

INSERT INTO cerberus.item(item_id, name, description, type, price_minor, stock, enabled) VALUES
    (198738965, 'Apple iPhone 12', 'Phone made by Apple', 'Smartphone', 70000, 25, true),
    (177669917, 'Samsung Galaxy S21', 'Phone made by Samsung', 'Smartphone', 65000, 20, true),
    (340132899, 'Lenovo Thinkpad T495', 'Laptop made by Lenovo', 'Laptop', 120000, 10, true);

INSERT INTO cerberus.cart_item(user_id, item_id, quantity) VALUES
    (566760000, 177669917, 3),
    (566760000, 198738965, 1),
    (856146094, 340132899, 1),
    (995436425, 198738965, 1),
    (224743443, 340132899, 2);

INSERT INTO cerberus.order_t(order_id, user_id, date_ordered) VALUES
    (592640939, 566760000, '2021-09-25'),
    (705277170, 566760000, '2021-08-12'),
    (951120582, 995436425, '2021-10-01'),
    (299903834, 224743443, '2021-09-27');

INSERT INTO cerberus.order_item(order_id, item_id, price_minor, quantity) VALUES
    (592640939, 198738965, 69000, 1),
    (592640939, 177669917, 60000, 2),
    (705277170, 340132899, 140000, 2),
    (951120582, 198738965, 65000, 1),
    (951120582, 177669917, 60000, 1),
    (951120582, 340132899, 100000, 1),
    (299903834, 340132899, 115000, 1);


