--
-- PostgreSQL database dump
--

-- Dumped from database version 13.4 (Debian 13.4-1.pgdg100+1)
-- Dumped by pg_dump version 13.3

-- Started on 2021-08-26 20:04:35

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
-- TOC entry 201 (class 1259 OID 16395)
-- Name: address; Type: TABLE; Schema: public; Owner: postgres
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
-- TOC entry 200 (class 1259 OID 16388)
-- Name: user; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public."user" (
    user_id bigint NOT NULL,
    address_id bigint NOT NULL,
    email character varying(256) NOT NULL,
    password_hash character varying(64) NOT NULL
);


ALTER TABLE public."user" OWNER TO gris;

--
-- TOC entry 2941 (class 0 OID 16395)
-- Dependencies: 201
-- Data for Name: address; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.address (address_id, country, county, postal_code, line_1, line_2) FROM stdin;
1234567890	UK	Greater London	HA2 8AR	32 Sherwood Road	\N
\.


--
-- TOC entry 2940 (class 0 OID 16388)
-- Dependencies: 200
-- Data for Name: user; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public."user" (user_id, address_id, email, password_hash) FROM stdin;
9876543210	1234567890	mariusgrygore@gmail.com	90978y232riuhnsdffjkvhpw79yr23p8r
\.


--
-- TOC entry 2808 (class 2606 OID 16399)
-- Name: address address_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.address
    ADD CONSTRAINT address_pkey PRIMARY KEY (address_id);


--
-- TOC entry 2806 (class 2606 OID 16392)
-- Name: user user_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public."user"
    ADD CONSTRAINT user_pkey PRIMARY KEY (user_id);


--
-- TOC entry 2809 (class 2606 OID 16400)
-- Name: user address_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public."user"
    ADD CONSTRAINT address_fkey FOREIGN KEY (address_id) REFERENCES public.address(address_id) ON UPDATE CASCADE ON DELETE CASCADE NOT VALID;


-- Completed on 2021-08-26 20:04:35

--
-- PostgreSQL database dump complete
--

