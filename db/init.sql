--
-- PostgreSQL database dump
--

-- Dumped from database version 13.4 (Debian 13.4-1.pgdg100+1)
-- Dumped by pg_dump version 13.3

-- Started on 2021-09-16 23:20:52

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

--
-- TOC entry 200 (class 1259 OID 16385)
-- Name: address; Type: TABLE; Schema: public; Owner: gris
--

CREATE TABLE public.address (
    address_id bigint NOT NULL,
    country character varying(64) NOT NULL,
    county character varying(64),
    postal_code character varying(16) NOT NULL,
    line_1 character varying(64) NOT NULL,
    line_2 character varying(64)
);


ALTER TABLE public.address OWNER TO gris;

--
-- TOC entry 201 (class 1259 OID 16388)
-- Name: customer; Type: TABLE; Schema: public; Owner: gris
--

CREATE TABLE public.customer (
    customer_id bigint NOT NULL,
    address_id bigint NOT NULL,
    email character varying(256) NOT NULL,
    password_hash character varying(64) NOT NULL
);


ALTER TABLE public.customer OWNER TO gris;

--
-- TOC entry 203 (class 1259 OID 16411)
-- Name: customer_item; Type: TABLE; Schema: public; Owner: gris
--

CREATE TABLE public.customer_item (
    customer_item_id bigint NOT NULL,
    customer_id bigint NOT NULL,
    item_id bigint NOT NULL
);


ALTER TABLE public.customer_item OWNER TO gris;

--
-- TOC entry 202 (class 1259 OID 16400)
-- Name: item; Type: TABLE; Schema: public; Owner: gris
--

CREATE TABLE public.item (
    description character varying(512),
    item_id bigint NOT NULL,
    type character varying(256) NOT NULL,
    stock integer NOT NULL,
    CONSTRAINT positive_stock CHECK ((stock >= 0))
);


ALTER TABLE public.item OWNER TO gris;

--
-- TOC entry 2816 (class 2606 OID 16392)
-- Name: address address_pkey; Type: CONSTRAINT; Schema: public; Owner: gris
--

ALTER TABLE ONLY public.address
    ADD CONSTRAINT address_pkey PRIMARY KEY (address_id);


--
-- TOC entry 2824 (class 2606 OID 16415)
-- Name: customer_item customer_item_pkey; Type: CONSTRAINT; Schema: public; Owner: gris
--

ALTER TABLE ONLY public.customer_item
    ADD CONSTRAINT customer_item_pkey PRIMARY KEY (customer_item_id);


--
-- TOC entry 2818 (class 2606 OID 16394)
-- Name: customer customer_pkey; Type: CONSTRAINT; Schema: public; Owner: gris
--

ALTER TABLE ONLY public.customer
    ADD CONSTRAINT customer_pkey PRIMARY KEY (customer_id);


--
-- TOC entry 2820 (class 2606 OID 16408)
-- Name: item item_pkey; Type: CONSTRAINT; Schema: public; Owner: gris
--

ALTER TABLE ONLY public.item
    ADD CONSTRAINT item_pkey PRIMARY KEY (item_id);


--
-- TOC entry 2822 (class 2606 OID 16410)
-- Name: item unique_types; Type: CONSTRAINT; Schema: public; Owner: gris
--

ALTER TABLE ONLY public.item
    ADD CONSTRAINT unique_types UNIQUE (type);


--
-- TOC entry 2825 (class 2606 OID 16395)
-- Name: customer address_fkey; Type: FK CONSTRAINT; Schema: public; Owner: gris
--

ALTER TABLE ONLY public.customer
    ADD CONSTRAINT address_fkey FOREIGN KEY (address_id) REFERENCES public.address(address_id) ON UPDATE CASCADE ON DELETE CASCADE NOT VALID;


--
-- TOC entry 2826 (class 2606 OID 16416)
-- Name: customer_item customer_fkey; Type: FK CONSTRAINT; Schema: public; Owner: gris
--

ALTER TABLE ONLY public.customer_item
    ADD CONSTRAINT customer_fkey FOREIGN KEY (customer_id) REFERENCES public.customer(customer_id) ON UPDATE CASCADE ON DELETE CASCADE;


--
-- TOC entry 2827 (class 2606 OID 16421)
-- Name: customer_item item_fkey; Type: FK CONSTRAINT; Schema: public; Owner: gris
--

ALTER TABLE ONLY public.customer_item
    ADD CONSTRAINT item_fkey FOREIGN KEY (item_id) REFERENCES public.item(item_id) ON UPDATE CASCADE ON DELETE CASCADE;


-- Completed on 2021-09-16 23:20:52

--
-- PostgreSQL database dump complete
--

