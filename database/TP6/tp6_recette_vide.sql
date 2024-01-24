--
-- PostgreSQL database dump
--

-- Dumped from database version 11.9 (Debian 11.9-0+deb10u1)
-- Dumped by pg_dump version 11.9 (Debian 11.9-0+deb10u1)

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

--
-- Name: tp6_bdd2; Type: SCHEMA; Schema: -; Owner: rachmat
--

CREATE SCHEMA tp6_bdd2;


ALTER SCHEMA tp6_bdd2 OWNER TO rachmat;

SET default_tablespace = '';

SET default_with_oids = false;

--
-- Name: category; Type: TABLE; Schema: tp6_bdd2; Owner: rachmat
--

CREATE TABLE tp6_bdd2.category (
    numcategrec integer NOT NULL,
    nomcat character varying(200)
);


ALTER TABLE tp6_bdd2.category OWNER TO rachmat;

--
-- Name: category_numcategrec_seq; Type: SEQUENCE; Schema: tp6_bdd2; Owner: rachmat
--

CREATE SEQUENCE tp6_bdd2.category_numcategrec_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE tp6_bdd2.category_numcategrec_seq OWNER TO rachmat;

--
-- Name: category_numcategrec_seq; Type: SEQUENCE OWNED BY; Schema: tp6_bdd2; Owner: rachmat
--

ALTER SEQUENCE tp6_bdd2.category_numcategrec_seq OWNED BY tp6_bdd2.category.numcategrec;


--
-- Name: etape; Type: TABLE; Schema: tp6_bdd2; Owner: rachmat
--

CREATE TABLE tp6_bdd2.etape (
    numetape integer NOT NULL,
    numrecette integer NOT NULL,
    description character varying(1000)
);


ALTER TABLE tp6_bdd2.etape OWNER TO rachmat;

--
-- Name: ingredient; Type: TABLE; Schema: tp6_bdd2; Owner: rachmat
--

CREATE TABLE tp6_bdd2.ingredient (
    numingre integer NOT NULL,
    numetape integer NOT NULL,
    numrecette integer NOT NULL,
    numprod integer,
    quantite integer,
    unite character varying(20),
    CONSTRAINT ingredient_numprod_check CHECK ((numprod > 0)),
    CONSTRAINT ingredient_quantite_check CHECK ((quantite > 0)),
    CONSTRAINT ingredient_unite_check CHECK (((unite)::text = ANY ((ARRAY['cl'::character varying, 'g'::character varying, 'cuillere a soupe'::character varying, 'cuillere a cafe'::character varying])::text[])))
);


ALTER TABLE tp6_bdd2.ingredient OWNER TO rachmat;

--
-- Name: produit; Type: TABLE; Schema: tp6_bdd2; Owner: rachmat
--

CREATE TABLE tp6_bdd2.produit (
    numprod integer NOT NULL,
    nomprod character varying(200)
);


ALTER TABLE tp6_bdd2.produit OWNER TO rachmat;

--
-- Name: produit_numprod_seq; Type: SEQUENCE; Schema: tp6_bdd2; Owner: rachmat
--

CREATE SEQUENCE tp6_bdd2.produit_numprod_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE tp6_bdd2.produit_numprod_seq OWNER TO rachmat;

--
-- Name: produit_numprod_seq; Type: SEQUENCE OWNED BY; Schema: tp6_bdd2; Owner: rachmat
--

ALTER SEQUENCE tp6_bdd2.produit_numprod_seq OWNED BY tp6_bdd2.produit.numprod;


--
-- Name: recette; Type: TABLE; Schema: tp6_bdd2; Owner: rachmat
--

CREATE TABLE tp6_bdd2.recette (
    numrecette integer NOT NULL,
    numcategrec integer DEFAULT 0,
    nomrec character varying(200),
    nbprod integer,
    temps interval
);


ALTER TABLE tp6_bdd2.recette OWNER TO rachmat;

--
-- Name: recette_numrecette_seq; Type: SEQUENCE; Schema: tp6_bdd2; Owner: rachmat
--

CREATE SEQUENCE tp6_bdd2.recette_numrecette_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE tp6_bdd2.recette_numrecette_seq OWNER TO rachmat;

--
-- Name: recette_numrecette_seq; Type: SEQUENCE OWNED BY; Schema: tp6_bdd2; Owner: rachmat
--

ALTER SEQUENCE tp6_bdd2.recette_numrecette_seq OWNED BY tp6_bdd2.recette.numrecette;


--
-- Name: ustensile; Type: TABLE; Schema: tp6_bdd2; Owner: rachmat
--

CREATE TABLE tp6_bdd2.ustensile (
    numust integer NOT NULL,
    nomust character varying(200)
);


ALTER TABLE tp6_bdd2.ustensile OWNER TO rachmat;

--
-- Name: ustensile_numust_seq; Type: SEQUENCE; Schema: tp6_bdd2; Owner: rachmat
--

CREATE SEQUENCE tp6_bdd2.ustensile_numust_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE tp6_bdd2.ustensile_numust_seq OWNER TO rachmat;

--
-- Name: ustensile_numust_seq; Type: SEQUENCE OWNED BY; Schema: tp6_bdd2; Owner: rachmat
--

ALTER SEQUENCE tp6_bdd2.ustensile_numust_seq OWNED BY tp6_bdd2.ustensile.numust;


--
-- Name: utiliser; Type: TABLE; Schema: tp6_bdd2; Owner: rachmat
--

CREATE TABLE tp6_bdd2.utiliser (
    numust integer NOT NULL,
    numrecette integer NOT NULL
);


ALTER TABLE tp6_bdd2.utiliser OWNER TO rachmat;

--
-- Name: category numcategrec; Type: DEFAULT; Schema: tp6_bdd2; Owner: rachmat
--

ALTER TABLE ONLY tp6_bdd2.category ALTER COLUMN numcategrec SET DEFAULT nextval('tp6_bdd2.category_numcategrec_seq'::regclass);


--
-- Name: produit numprod; Type: DEFAULT; Schema: tp6_bdd2; Owner: rachmat
--

ALTER TABLE ONLY tp6_bdd2.produit ALTER COLUMN numprod SET DEFAULT nextval('tp6_bdd2.produit_numprod_seq'::regclass);


--
-- Name: recette numrecette; Type: DEFAULT; Schema: tp6_bdd2; Owner: rachmat
--

ALTER TABLE ONLY tp6_bdd2.recette ALTER COLUMN numrecette SET DEFAULT nextval('tp6_bdd2.recette_numrecette_seq'::regclass);


--
-- Name: ustensile numust; Type: DEFAULT; Schema: tp6_bdd2; Owner: rachmat
--

ALTER TABLE ONLY tp6_bdd2.ustensile ALTER COLUMN numust SET DEFAULT nextval('tp6_bdd2.ustensile_numust_seq'::regclass);


--
-- Data for Name: category; Type: TABLE DATA; Schema: tp6_bdd2; Owner: rachmat
--



--
-- Data for Name: etape; Type: TABLE DATA; Schema: tp6_bdd2; Owner: rachmat
--



--
-- Data for Name: ingredient; Type: TABLE DATA; Schema: tp6_bdd2; Owner: rachmat
--



--
-- Data for Name: produit; Type: TABLE DATA; Schema: tp6_bdd2; Owner: rachmat
--



--
-- Data for Name: recette; Type: TABLE DATA; Schema: tp6_bdd2; Owner: rachmat
--



--
-- Data for Name: ustensile; Type: TABLE DATA; Schema: tp6_bdd2; Owner: rachmat
--



--
-- Data for Name: utiliser; Type: TABLE DATA; Schema: tp6_bdd2; Owner: rachmat
--



--
-- Name: category_numcategrec_seq; Type: SEQUENCE SET; Schema: tp6_bdd2; Owner: rachmat
--

SELECT pg_catalog.setval('tp6_bdd2.category_numcategrec_seq', 1, false);


--
-- Name: produit_numprod_seq; Type: SEQUENCE SET; Schema: tp6_bdd2; Owner: rachmat
--

SELECT pg_catalog.setval('tp6_bdd2.produit_numprod_seq', 1, false);


--
-- Name: recette_numrecette_seq; Type: SEQUENCE SET; Schema: tp6_bdd2; Owner: rachmat
--

SELECT pg_catalog.setval('tp6_bdd2.recette_numrecette_seq', 1, false);


--
-- Name: ustensile_numust_seq; Type: SEQUENCE SET; Schema: tp6_bdd2; Owner: rachmat
--

SELECT pg_catalog.setval('tp6_bdd2.ustensile_numust_seq', 1, false);


--
-- Name: category category_nomcat_key; Type: CONSTRAINT; Schema: tp6_bdd2; Owner: rachmat
--

ALTER TABLE ONLY tp6_bdd2.category
    ADD CONSTRAINT category_nomcat_key UNIQUE (nomcat);


--
-- Name: category category_pkey; Type: CONSTRAINT; Schema: tp6_bdd2; Owner: rachmat
--

ALTER TABLE ONLY tp6_bdd2.category
    ADD CONSTRAINT category_pkey PRIMARY KEY (numcategrec);


--
-- Name: etape pk_etape_recette; Type: CONSTRAINT; Schema: tp6_bdd2; Owner: rachmat
--

ALTER TABLE ONLY tp6_bdd2.etape
    ADD CONSTRAINT pk_etape_recette PRIMARY KEY (numetape, numrecette);


--
-- Name: ingredient pk_ingredient_etape_recette; Type: CONSTRAINT; Schema: tp6_bdd2; Owner: rachmat
--

ALTER TABLE ONLY tp6_bdd2.ingredient
    ADD CONSTRAINT pk_ingredient_etape_recette PRIMARY KEY (numingre, numetape, numrecette);


--
-- Name: utiliser pk_ustensile_recette; Type: CONSTRAINT; Schema: tp6_bdd2; Owner: rachmat
--

ALTER TABLE ONLY tp6_bdd2.utiliser
    ADD CONSTRAINT pk_ustensile_recette PRIMARY KEY (numust, numrecette);


--
-- Name: produit produit_pkey; Type: CONSTRAINT; Schema: tp6_bdd2; Owner: rachmat
--

ALTER TABLE ONLY tp6_bdd2.produit
    ADD CONSTRAINT produit_pkey PRIMARY KEY (numprod);


--
-- Name: recette recette_pkey; Type: CONSTRAINT; Schema: tp6_bdd2; Owner: rachmat
--

ALTER TABLE ONLY tp6_bdd2.recette
    ADD CONSTRAINT recette_pkey PRIMARY KEY (numrecette);


--
-- Name: ustensile ustensile_pkey; Type: CONSTRAINT; Schema: tp6_bdd2; Owner: rachmat
--

ALTER TABLE ONLY tp6_bdd2.ustensile
    ADD CONSTRAINT ustensile_pkey PRIMARY KEY (numust);


--
-- Name: ingredient fk_etape_recette; Type: FK CONSTRAINT; Schema: tp6_bdd2; Owner: rachmat
--

ALTER TABLE ONLY tp6_bdd2.ingredient
    ADD CONSTRAINT fk_etape_recette FOREIGN KEY (numetape, numrecette) REFERENCES tp6_bdd2.etape(numetape, numrecette) ON DELETE CASCADE;


--
-- Name: ingredient fk_produit; Type: FK CONSTRAINT; Schema: tp6_bdd2; Owner: rachmat
--

ALTER TABLE ONLY tp6_bdd2.ingredient
    ADD CONSTRAINT fk_produit FOREIGN KEY (numprod) REFERENCES tp6_bdd2.produit(numprod);


--
-- Name: utiliser fk_recette; Type: FK CONSTRAINT; Schema: tp6_bdd2; Owner: rachmat
--

ALTER TABLE ONLY tp6_bdd2.utiliser
    ADD CONSTRAINT fk_recette FOREIGN KEY (numrecette) REFERENCES tp6_bdd2.recette(numrecette) ON DELETE CASCADE;


--
-- Name: etape fk_recette; Type: FK CONSTRAINT; Schema: tp6_bdd2; Owner: rachmat
--

ALTER TABLE ONLY tp6_bdd2.etape
    ADD CONSTRAINT fk_recette FOREIGN KEY (numrecette) REFERENCES tp6_bdd2.recette(numrecette) ON DELETE CASCADE;


--
-- Name: utiliser fk_ustensile; Type: FK CONSTRAINT; Schema: tp6_bdd2; Owner: rachmat
--

ALTER TABLE ONLY tp6_bdd2.utiliser
    ADD CONSTRAINT fk_ustensile FOREIGN KEY (numust) REFERENCES tp6_bdd2.ustensile(numust);


--
-- Name: recette recette_numcategrec_fkey; Type: FK CONSTRAINT; Schema: tp6_bdd2; Owner: rachmat
--

ALTER TABLE ONLY tp6_bdd2.recette
    ADD CONSTRAINT recette_numcategrec_fkey FOREIGN KEY (numcategrec) REFERENCES tp6_bdd2.category(numcategrec) ON DELETE SET DEFAULT;


--
-- PostgreSQL database dump complete
--

