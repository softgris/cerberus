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
DROP TABLE IF EXISTS cerberus.customer;
CREATE TABLE cerberus.customer (
    customer_id bigint NOT NULL,
    role_id bigint NOT NULL,
    cart_id bigint NOT NULL,
    order_history_id bigint NOT NULL,
    email varchar(200) NOT NULL,
    password varchar(100) NOT NULL,
    enabled boolean NOT NULL DEFAULT true
);
ALTER TABLE cerberus.customer OWNER TO gris;

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
    role_authority_id bigint NOT NULL,
    role_id bigint NOT NULL,
    authority_id bigint NOT NULL
);
ALTER TABLE cerberus.role_authority OWNER TO gris;

DROP TABLE IF EXISTS cerberus.address;
CREATE TABLE cerberus.address (
    address_id bigint NOT NULL,
    customer_id bigint NOT NULL,
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

DROP TABLE IF EXISTS cerberus.cart;
CREATE TABLE cerberus.cart (
    cart_id bigint NOT NULL
);
ALTER TABLE cerberus.item OWNER TO gris;

DROP TABLE IF EXISTS cerberus.cart_item;
CREATE TABLE cerberus.cart_item (
    cart_item_id bigint NOT NULL,
    cart_id bigint NOT NULL,
    item_id bigint NOT NULL,
    quantity bigint NOT NULL
);
ALTER TABLE cerberus.cart_item OWNER TO gris;

DROP TABLE IF EXISTS cerberus.order_history;
CREATE TABLE cerberus.order_history (
    order_history_id bigint NOT NULL,
    order_item_id bigint NOT NULL,
    order_status_id bigint NOT NULL,
    date_ordered date NOT NULL
);
ALTER TABLE cerberus.order_history OWNER TO gris;

DROP TABLE IF EXISTS cerberus.order_status;
CREATE TABLE cerberus.order_status (
    order_status_id bigint NOT NULL,
    status varchar(50) NOT NULL
);
ALTER TABLE cerberus.order_status OWNER TO gris;

DROP TABLE IF EXISTS cerberus.order_item;
CREATE TABLE cerberus.order_item (
    order_item_id bigint NOT NULL,
    item_id bigint NOT NULL,
    price_minor bigint NOT NULL,
    quantity bigint NOT NULL
);
ALTER TABLE cerberus.order_item OWNER TO gris;

-- Primary keys

ALTER TABLE ONLY cerberus.customer
    ADD CONSTRAINT pkey_customer
        PRIMARY KEY (customer_id),
    ADD CONSTRAINT cons_customer_positive_id
        CHECK ((customer_id >= 0));

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

ALTER TABLE ONLY cerberus.role_authority
    ADD CONSTRAINT pkey_role_authority
        PRIMARY KEY (role_authority_id),
    ADD CONSTRAINT cons_role_authority_positive_id
        CHECK ((role_authority_id >= 0));

ALTER TABLE ONLY cerberus.address
    ADD CONSTRAINT pkey_address
        PRIMARY KEY (address_id),
    ADD CONSTRAINT cons_address_positive_id
        CHECK ((address_id >= 0));

ALTER TABLE ONLY cerberus.item
    ADD CONSTRAINT pkey_item
        PRIMARY KEY (item_id),
    ADD CONSTRAINT cons_item_positive_id
        CHECK ((item_id >= 0));

ALTER TABLE ONLY cerberus.cart
    ADD CONSTRAINT pkey_cart
        PRIMARY KEY (cart_id),
    ADD CONSTRAINT cons_cart_positive_id
        CHECK ((cart_id >= 0));

ALTER TABLE ONLY cerberus.cart_item
    ADD CONSTRAINT pkey_cart_item
        PRIMARY KEY (cart_item_id),
    ADD CONSTRAINT cons_cart_item_positive_id
        CHECK ((cart_item_id >= 0));

ALTER TABLE ONLY cerberus.order_history
    ADD CONSTRAINT pkey_order_history
        PRIMARY KEY (order_history_id),
    ADD CONSTRAINT cons_order_history_positive_id
        CHECK ((order_history_id >= 0));

ALTER TABLE ONLY cerberus.order_status
    ADD CONSTRAINT pkey_order_status
        PRIMARY KEY (order_status_id),
    ADD CONSTRAINT cons_order_status_positive_id
        CHECK ((order_status_id >= 0));

ALTER TABLE ONLY cerberus.order_item
    ADD CONSTRAINT pkey_order_item
        PRIMARY KEY (order_item_id),
    ADD CONSTRAINT cons_order_item_positive_id
        CHECK ((order_item_id >= 0));

-- Foreign keys

ALTER TABLE ONLY cerberus.customer
    ADD CONSTRAINT fkey_customer_role
        FOREIGN KEY (role_id)
            REFERENCES cerberus.role(role_id)
                ON UPDATE CASCADE
                ON DELETE CASCADE,
    ADD CONSTRAINT fkey_customer_cart
        FOREIGN KEY (cart_id)
            REFERENCES cerberus.cart(cart_id)
                ON UPDATE CASCADE
                ON DELETE CASCADE,
    ADD CONSTRAINT fkey_customer_order_history
        FOREIGN KEY (order_history_id)
            REFERENCES cerberus.order_history(order_history_id);

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
    ADD CONSTRAINT fkey_address_customer
        FOREIGN KEY (customer_id)
            REFERENCES cerberus.customer(customer_id)
                ON UPDATE CASCADE
                ON DELETE CASCADE;

ALTER TABLE ONLY cerberus.cart_item
    ADD CONSTRAINT fkey_cart_item_cart
        FOREIGN KEY (cart_id)
            REFERENCES cerberus.cart(cart_id)
                ON UPDATE CASCADE
                ON DELETE CASCADE,
    ADD CONSTRAINT fkey_cart_item_item
        FOREIGN KEY (item_id)
            REFERENCES cerberus.item(item_id)
                ON UPDATE CASCADE
                ON DELETE CASCADE;

ALTER TABLE ONLY cerberus.order_history
    ADD CONSTRAINT fkey_order_item
        FOREIGN KEY (order_item_id)
            REFERENCES cerberus.order_item(order_item_id)
                ON UPDATE CASCADE
                ON DELETE CASCADE,
    ADD CONSTRAINT fkey_order_status
        FOREIGN KEY (order_status_id)
            REFERENCES cerberus.order_status(order_status_id)
                ON UPDATE CASCADE
                ON DELETE CASCADE;

ALTER TABLE ONLY cerberus.order_item
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
    (246427860, 'READ_CUSTOMERS', 'Read customers'),
    (759406271, 'WRITE_CUSTOMERS', 'Write customers'),
    (172585288, 'PLACE_ORDER', 'Place an order');

INSERT INTO cerberus.role_authority(role_authority_id, role_id, authority_id) VALUES
    (1, 262294688, 949726876), -- ADMIN READ_ITEMS
    (2, 262294688, 181221016), -- ADMIN WRITE_ITEMS
    (3, 262294688, 246427860), -- ADMIN READ_CUSTOMERS
    (4, 262294688, 759406271), -- ADMIN WRITE_CUSTOMERS
    (5, 262294688, 172585288), -- ADMIN PLACE_ORDER
    (6, 103205808, 949726876), -- CUSTOMER READ_ITEMS
    (7, 103205808, 172585288); -- CUSTOMER PLACE_ORDER

INSERT INTO cerberus.cart(cart_id) VALUES
    (229993943),
    (632523521),
    (656435222),
    (823420535);

INSERT INTO cerberus.item(item_id, name, description, type, price_minor, stock, enabled) VALUES
    (198738965, 'Apple iPhone 12', 'Phone made by Apple', 'Smartphone', 70000, 25, true),
    (177669917, 'Samsung Galaxy S21', 'Phone made by Samsung', 'Smartphone', 65000, 20, true),
    (340132899, 'Lenovo Thinkpad T495', 'Laptop made by Lenovo', 'Laptop', 120000, 10, true);

INSERT INTO cerberus.cart_item(cart_item_id, cart_id, item_id, quantity) VALUES
    (523242353, 229993943, 177669917, 3),
    (643535325, 229993943, 198738965, 1),
    (293493043, 632523521, 340132899, 1),
    (995436425, 656435222, 340132899, 1),
    (224743443, 823420535, 198738965, 2);

INSERT INTO cerberus.order_status(order_status_id, status) VALUES
    (1, 'PENDING'),
    (2, 'IN_PROGRESS'),
    (3, 'DELIVERED'),
    (4, 'CANCELLED'),
    (5, 'REFUSED');

INSERT INTO cerberus.order_item(order_item_id, item_id, price_minor, quantity) VALUES
    (592640939, 198738965, 69000, 1),
    (705277170, 340132899, 140000, 2),
    (951120582, 198738965, 65000, 1),
    (299903834, 340132899, 115000, 1);

INSERT INTO cerberus.order_history(order_history_id, order_item_id, order_status_id, date_ordered) VALUES
    (345345352, 592640939, 1, '2021-09-25'),
    (188942825, 705277170, 2, '2021-08-12'),
    (928352731, 951120582, 3, '2021-10-01'),
    (683245264, 299903834, 5, '2021-09-27');

INSERT INTO cerberus.customer(customer_id, role_id, cart_id, order_history_id, email, password, enabled) VALUES
    (566760000, 262294688, 229993943, 345345352, 'andrei@gris.com', 'password', true),
    (856146094, 262294688, 632523521, 188942825, 'marius@gris.com', 'password', true),
    (995436425, 103205808, 656435222, 928352731, 'mihai@gris.com', 'password', true),
    (224743443, 103205808, 823420535, 683245264, 'ana@gris.com', 'password', true);

INSERT INTO cerberus.address(address_id, customer_id, country, county, city, postal_code, line_1, line_2) VALUES
    (415213524, 566760000, 'Romania', 'Ilfov', 'Bucuresti', '123456', 'Strada Lulelelor 22', 'et. 1, ap. 9'),
    (134613346, 856146094, 'United Kingdom', 'Greater London', 'Harrow', 'AA2 2AA', '32 New Road', NULL),
    (643163451, 995436425, 'Romania', 'Iasi', 'Iasi', '123456', 'Strada Surcelelor 62', 'et. 2, ap. 12'),
    (345135135, 224743443, 'United Kingdom', 'Greater London', 'Hounslow', 'BB2 2BB', '19 Old Avenue', null);


