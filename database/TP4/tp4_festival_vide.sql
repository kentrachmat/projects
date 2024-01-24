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
-- Name: tp4_bdd2; Type: SCHEMA; Schema: -; Owner: rachmat
--

CREATE SCHEMA tp4_bdd2;


ALTER SCHEMA tp4_bdd2 OWNER TO rachmat;

SET default_tablespace = '';

SET default_with_oids = false;

--
-- Name: auteur; Type: TABLE; Schema: tp4_bdd2; Owner: rachmat
--

CREATE TABLE tp4_bdd2.auteur (
    num_aut integer NOT NULL,
    num_pers integer
);


ALTER TABLE tp4_bdd2.auteur OWNER TO rachmat;

--
-- Name: auteur_num_aut_seq; Type: SEQUENCE; Schema: tp4_bdd2; Owner: rachmat
--

CREATE SEQUENCE tp4_bdd2.auteur_num_aut_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE tp4_bdd2.auteur_num_aut_seq OWNER TO rachmat;

--
-- Name: auteur_num_aut_seq; Type: SEQUENCE OWNED BY; Schema: tp4_bdd2; Owner: rachmat
--

ALTER SEQUENCE tp4_bdd2.auteur_num_aut_seq OWNED BY tp4_bdd2.auteur.num_aut;


--
-- Name: billet; Type: TABLE; Schema: tp4_bdd2; Owner: rachmat
--

CREATE TABLE tp4_bdd2.billet (
    num_fest integer NOT NULL,
    num_part integer NOT NULL,
    num_emp integer,
    prix numeric,
    debut date NOT NULL,
    fin date NOT NULL,
    CONSTRAINT billet_check CHECK ((fin > debut)),
    CONSTRAINT billet_prix_check CHECK ((prix > (0)::numeric))
);


ALTER TABLE tp4_bdd2.billet OWNER TO rachmat;

--
-- Name: categorie; Type: TABLE; Schema: tp4_bdd2; Owner: rachmat
--

CREATE TABLE tp4_bdd2.categorie (
    num_categ integer NOT NULL,
    titre character varying(50) NOT NULL
);


ALTER TABLE tp4_bdd2.categorie OWNER TO rachmat;

--
-- Name: categorie_num_categ_seq; Type: SEQUENCE; Schema: tp4_bdd2; Owner: rachmat
--

CREATE SEQUENCE tp4_bdd2.categorie_num_categ_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE tp4_bdd2.categorie_num_categ_seq OWNER TO rachmat;

--
-- Name: categorie_num_categ_seq; Type: SEQUENCE OWNED BY; Schema: tp4_bdd2; Owner: rachmat
--

ALTER SEQUENCE tp4_bdd2.categorie_num_categ_seq OWNED BY tp4_bdd2.categorie.num_categ;


--
-- Name: creer; Type: TABLE; Schema: tp4_bdd2; Owner: rachmat
--

CREATE TABLE tp4_bdd2.creer (
    num_jeu integer NOT NULL,
    num_aut integer NOT NULL
);


ALTER TABLE tp4_bdd2.creer OWNER TO rachmat;

--
-- Name: employe; Type: TABLE; Schema: tp4_bdd2; Owner: rachmat
--

CREATE TABLE tp4_bdd2.employe (
    num_emp integer NOT NULL,
    num_pers integer
);


ALTER TABLE tp4_bdd2.employe OWNER TO rachmat;

--
-- Name: employe_num_emp_seq; Type: SEQUENCE; Schema: tp4_bdd2; Owner: rachmat
--

CREATE SEQUENCE tp4_bdd2.employe_num_emp_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE tp4_bdd2.employe_num_emp_seq OWNER TO rachmat;

--
-- Name: employe_num_emp_seq; Type: SEQUENCE OWNED BY; Schema: tp4_bdd2; Owner: rachmat
--

ALTER SEQUENCE tp4_bdd2.employe_num_emp_seq OWNED BY tp4_bdd2.employe.num_emp;


--
-- Name: festival; Type: TABLE; Schema: tp4_bdd2; Owner: rachmat
--

CREATE TABLE tp4_bdd2.festival (
    num_fest integer NOT NULL,
    num_ville integer,
    num_emp integer,
    titre character varying(50) NOT NULL,
    debut date NOT NULL,
    fin date NOT NULL,
    CONSTRAINT festival_check CHECK ((fin > debut))
);


ALTER TABLE tp4_bdd2.festival OWNER TO rachmat;

--
-- Name: festival_num_fest_seq; Type: SEQUENCE; Schema: tp4_bdd2; Owner: rachmat
--

CREATE SEQUENCE tp4_bdd2.festival_num_fest_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE tp4_bdd2.festival_num_fest_seq OWNER TO rachmat;

--
-- Name: festival_num_fest_seq; Type: SEQUENCE OWNED BY; Schema: tp4_bdd2; Owner: rachmat
--

ALTER SEQUENCE tp4_bdd2.festival_num_fest_seq OWNED BY tp4_bdd2.festival.num_fest;


--
-- Name: jeux; Type: TABLE; Schema: tp4_bdd2; Owner: rachmat
--

CREATE TABLE tp4_bdd2.jeux (
    num_jeu integer NOT NULL,
    num_categ integer,
    nom character varying(50) NOT NULL
);


ALTER TABLE tp4_bdd2.jeux OWNER TO rachmat;

--
-- Name: jeux_num_jeu_seq; Type: SEQUENCE; Schema: tp4_bdd2; Owner: rachmat
--

CREATE SEQUENCE tp4_bdd2.jeux_num_jeu_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE tp4_bdd2.jeux_num_jeu_seq OWNER TO rachmat;

--
-- Name: jeux_num_jeu_seq; Type: SEQUENCE OWNED BY; Schema: tp4_bdd2; Owner: rachmat
--

ALTER SEQUENCE tp4_bdd2.jeux_num_jeu_seq OWNED BY tp4_bdd2.jeux.num_jeu;


--
-- Name: participant; Type: TABLE; Schema: tp4_bdd2; Owner: rachmat
--

CREATE TABLE tp4_bdd2.participant (
    num_part integer NOT NULL,
    num_pers integer
);


ALTER TABLE tp4_bdd2.participant OWNER TO rachmat;

--
-- Name: participant_num_part_seq; Type: SEQUENCE; Schema: tp4_bdd2; Owner: rachmat
--

CREATE SEQUENCE tp4_bdd2.participant_num_part_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE tp4_bdd2.participant_num_part_seq OWNER TO rachmat;

--
-- Name: participant_num_part_seq; Type: SEQUENCE OWNED BY; Schema: tp4_bdd2; Owner: rachmat
--

ALTER SEQUENCE tp4_bdd2.participant_num_part_seq OWNED BY tp4_bdd2.participant.num_part;


--
-- Name: pays; Type: TABLE; Schema: tp4_bdd2; Owner: rachmat
--

CREATE TABLE tp4_bdd2.pays (
    nom_pays character varying(50) NOT NULL
);


ALTER TABLE tp4_bdd2.pays OWNER TO rachmat;

--
-- Name: personne; Type: TABLE; Schema: tp4_bdd2; Owner: rachmat
--

CREATE TABLE tp4_bdd2.personne (
    num_pers integer NOT NULL,
    nom_pays character varying(50),
    nom character varying(50) NOT NULL,
    prenom character varying(50) NOT NULL,
    mail character varying(50) NOT NULL
);


ALTER TABLE tp4_bdd2.personne OWNER TO rachmat;

--
-- Name: personne_num_pers_seq; Type: SEQUENCE; Schema: tp4_bdd2; Owner: rachmat
--

CREATE SEQUENCE tp4_bdd2.personne_num_pers_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE tp4_bdd2.personne_num_pers_seq OWNER TO rachmat;

--
-- Name: personne_num_pers_seq; Type: SEQUENCE OWNED BY; Schema: tp4_bdd2; Owner: rachmat
--

ALTER SEQUENCE tp4_bdd2.personne_num_pers_seq OWNED BY tp4_bdd2.personne.num_pers;


--
-- Name: session; Type: TABLE; Schema: tp4_bdd2; Owner: rachmat
--

CREATE TABLE tp4_bdd2.session (
    num_session integer NOT NULL,
    num_fest integer,
    num_jeu integer,
    num_aut integer,
    date date NOT NULL,
    heure_debut integer NOT NULL,
    heure_fin integer NOT NULL,
    numero integer NOT NULL,
    CONSTRAINT session_check CHECK ((heure_fin >= heure_debut)),
    CONSTRAINT session_heure_debut_check CHECK ((heure_debut >= 0))
);


ALTER TABLE tp4_bdd2.session OWNER TO rachmat;

--
-- Name: session_num_session_seq; Type: SEQUENCE; Schema: tp4_bdd2; Owner: rachmat
--

CREATE SEQUENCE tp4_bdd2.session_num_session_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE tp4_bdd2.session_num_session_seq OWNER TO rachmat;

--
-- Name: session_num_session_seq; Type: SEQUENCE OWNED BY; Schema: tp4_bdd2; Owner: rachmat
--

ALTER SEQUENCE tp4_bdd2.session_num_session_seq OWNED BY tp4_bdd2.session.num_session;


--
-- Name: ville; Type: TABLE; Schema: tp4_bdd2; Owner: rachmat
--

CREATE TABLE tp4_bdd2.ville (
    num_ville integer NOT NULL,
    nom character varying(50)
);


ALTER TABLE tp4_bdd2.ville OWNER TO rachmat;

--
-- Name: ville_num_ville_seq; Type: SEQUENCE; Schema: tp4_bdd2; Owner: rachmat
--

CREATE SEQUENCE tp4_bdd2.ville_num_ville_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE tp4_bdd2.ville_num_ville_seq OWNER TO rachmat;

--
-- Name: ville_num_ville_seq; Type: SEQUENCE OWNED BY; Schema: tp4_bdd2; Owner: rachmat
--

ALTER SEQUENCE tp4_bdd2.ville_num_ville_seq OWNED BY tp4_bdd2.ville.num_ville;


--
-- Name: auteur num_aut; Type: DEFAULT; Schema: tp4_bdd2; Owner: rachmat
--

ALTER TABLE ONLY tp4_bdd2.auteur ALTER COLUMN num_aut SET DEFAULT nextval('tp4_bdd2.auteur_num_aut_seq'::regclass);


--
-- Name: categorie num_categ; Type: DEFAULT; Schema: tp4_bdd2; Owner: rachmat
--

ALTER TABLE ONLY tp4_bdd2.categorie ALTER COLUMN num_categ SET DEFAULT nextval('tp4_bdd2.categorie_num_categ_seq'::regclass);


--
-- Name: employe num_emp; Type: DEFAULT; Schema: tp4_bdd2; Owner: rachmat
--

ALTER TABLE ONLY tp4_bdd2.employe ALTER COLUMN num_emp SET DEFAULT nextval('tp4_bdd2.employe_num_emp_seq'::regclass);


--
-- Name: festival num_fest; Type: DEFAULT; Schema: tp4_bdd2; Owner: rachmat
--

ALTER TABLE ONLY tp4_bdd2.festival ALTER COLUMN num_fest SET DEFAULT nextval('tp4_bdd2.festival_num_fest_seq'::regclass);


--
-- Name: jeux num_jeu; Type: DEFAULT; Schema: tp4_bdd2; Owner: rachmat
--

ALTER TABLE ONLY tp4_bdd2.jeux ALTER COLUMN num_jeu SET DEFAULT nextval('tp4_bdd2.jeux_num_jeu_seq'::regclass);


--
-- Name: participant num_part; Type: DEFAULT; Schema: tp4_bdd2; Owner: rachmat
--

ALTER TABLE ONLY tp4_bdd2.participant ALTER COLUMN num_part SET DEFAULT nextval('tp4_bdd2.participant_num_part_seq'::regclass);


--
-- Name: personne num_pers; Type: DEFAULT; Schema: tp4_bdd2; Owner: rachmat
--

ALTER TABLE ONLY tp4_bdd2.personne ALTER COLUMN num_pers SET DEFAULT nextval('tp4_bdd2.personne_num_pers_seq'::regclass);


--
-- Name: session num_session; Type: DEFAULT; Schema: tp4_bdd2; Owner: rachmat
--

ALTER TABLE ONLY tp4_bdd2.session ALTER COLUMN num_session SET DEFAULT nextval('tp4_bdd2.session_num_session_seq'::regclass);


--
-- Name: ville num_ville; Type: DEFAULT; Schema: tp4_bdd2; Owner: rachmat
--

ALTER TABLE ONLY tp4_bdd2.ville ALTER COLUMN num_ville SET DEFAULT nextval('tp4_bdd2.ville_num_ville_seq'::regclass);


--
-- Data for Name: auteur; Type: TABLE DATA; Schema: tp4_bdd2; Owner: rachmat
--



--
-- Data for Name: billet; Type: TABLE DATA; Schema: tp4_bdd2; Owner: rachmat
--



--
-- Data for Name: categorie; Type: TABLE DATA; Schema: tp4_bdd2; Owner: rachmat
--



--
-- Data for Name: creer; Type: TABLE DATA; Schema: tp4_bdd2; Owner: rachmat
--



--
-- Data for Name: employe; Type: TABLE DATA; Schema: tp4_bdd2; Owner: rachmat
--



--
-- Data for Name: festival; Type: TABLE DATA; Schema: tp4_bdd2; Owner: rachmat
--



--
-- Data for Name: jeux; Type: TABLE DATA; Schema: tp4_bdd2; Owner: rachmat
--



--
-- Data for Name: participant; Type: TABLE DATA; Schema: tp4_bdd2; Owner: rachmat
--



--
-- Data for Name: pays; Type: TABLE DATA; Schema: tp4_bdd2; Owner: rachmat
--



--
-- Data for Name: personne; Type: TABLE DATA; Schema: tp4_bdd2; Owner: rachmat
--



--
-- Data for Name: session; Type: TABLE DATA; Schema: tp4_bdd2; Owner: rachmat
--



--
-- Data for Name: ville; Type: TABLE DATA; Schema: tp4_bdd2; Owner: rachmat
--



--
-- Name: auteur_num_aut_seq; Type: SEQUENCE SET; Schema: tp4_bdd2; Owner: rachmat
--

SELECT pg_catalog.setval('tp4_bdd2.auteur_num_aut_seq', 1, false);


--
-- Name: categorie_num_categ_seq; Type: SEQUENCE SET; Schema: tp4_bdd2; Owner: rachmat
--

SELECT pg_catalog.setval('tp4_bdd2.categorie_num_categ_seq', 1, false);


--
-- Name: employe_num_emp_seq; Type: SEQUENCE SET; Schema: tp4_bdd2; Owner: rachmat
--

SELECT pg_catalog.setval('tp4_bdd2.employe_num_emp_seq', 1, false);


--
-- Name: festival_num_fest_seq; Type: SEQUENCE SET; Schema: tp4_bdd2; Owner: rachmat
--

SELECT pg_catalog.setval('tp4_bdd2.festival_num_fest_seq', 1, false);


--
-- Name: jeux_num_jeu_seq; Type: SEQUENCE SET; Schema: tp4_bdd2; Owner: rachmat
--

SELECT pg_catalog.setval('tp4_bdd2.jeux_num_jeu_seq', 1, false);


--
-- Name: participant_num_part_seq; Type: SEQUENCE SET; Schema: tp4_bdd2; Owner: rachmat
--

SELECT pg_catalog.setval('tp4_bdd2.participant_num_part_seq', 1, false);


--
-- Name: personne_num_pers_seq; Type: SEQUENCE SET; Schema: tp4_bdd2; Owner: rachmat
--

SELECT pg_catalog.setval('tp4_bdd2.personne_num_pers_seq', 1, false);


--
-- Name: session_num_session_seq; Type: SEQUENCE SET; Schema: tp4_bdd2; Owner: rachmat
--

SELECT pg_catalog.setval('tp4_bdd2.session_num_session_seq', 1, false);


--
-- Name: ville_num_ville_seq; Type: SEQUENCE SET; Schema: tp4_bdd2; Owner: rachmat
--

SELECT pg_catalog.setval('tp4_bdd2.ville_num_ville_seq', 1, false);


--
-- Name: auteur auteur_pkey; Type: CONSTRAINT; Schema: tp4_bdd2; Owner: rachmat
--

ALTER TABLE ONLY tp4_bdd2.auteur
    ADD CONSTRAINT auteur_pkey PRIMARY KEY (num_aut);


--
-- Name: billet billet_pkey; Type: CONSTRAINT; Schema: tp4_bdd2; Owner: rachmat
--

ALTER TABLE ONLY tp4_bdd2.billet
    ADD CONSTRAINT billet_pkey PRIMARY KEY (num_fest, num_part);


--
-- Name: categorie categorie_pkey; Type: CONSTRAINT; Schema: tp4_bdd2; Owner: rachmat
--

ALTER TABLE ONLY tp4_bdd2.categorie
    ADD CONSTRAINT categorie_pkey PRIMARY KEY (num_categ);


--
-- Name: creer creer_pkey; Type: CONSTRAINT; Schema: tp4_bdd2; Owner: rachmat
--

ALTER TABLE ONLY tp4_bdd2.creer
    ADD CONSTRAINT creer_pkey PRIMARY KEY (num_jeu, num_aut);


--
-- Name: employe employe_pkey; Type: CONSTRAINT; Schema: tp4_bdd2; Owner: rachmat
--

ALTER TABLE ONLY tp4_bdd2.employe
    ADD CONSTRAINT employe_pkey PRIMARY KEY (num_emp);


--
-- Name: festival festival_pkey; Type: CONSTRAINT; Schema: tp4_bdd2; Owner: rachmat
--

ALTER TABLE ONLY tp4_bdd2.festival
    ADD CONSTRAINT festival_pkey PRIMARY KEY (num_fest);


--
-- Name: jeux jeux_pkey; Type: CONSTRAINT; Schema: tp4_bdd2; Owner: rachmat
--

ALTER TABLE ONLY tp4_bdd2.jeux
    ADD CONSTRAINT jeux_pkey PRIMARY KEY (num_jeu);


--
-- Name: participant participant_pkey; Type: CONSTRAINT; Schema: tp4_bdd2; Owner: rachmat
--

ALTER TABLE ONLY tp4_bdd2.participant
    ADD CONSTRAINT participant_pkey PRIMARY KEY (num_part);


--
-- Name: pays pays_pkey; Type: CONSTRAINT; Schema: tp4_bdd2; Owner: rachmat
--

ALTER TABLE ONLY tp4_bdd2.pays
    ADD CONSTRAINT pays_pkey PRIMARY KEY (nom_pays);


--
-- Name: personne personne_pkey; Type: CONSTRAINT; Schema: tp4_bdd2; Owner: rachmat
--

ALTER TABLE ONLY tp4_bdd2.personne
    ADD CONSTRAINT personne_pkey PRIMARY KEY (num_pers);


--
-- Name: session session_pkey; Type: CONSTRAINT; Schema: tp4_bdd2; Owner: rachmat
--

ALTER TABLE ONLY tp4_bdd2.session
    ADD CONSTRAINT session_pkey PRIMARY KEY (num_session);


--
-- Name: ville ville_nom_key; Type: CONSTRAINT; Schema: tp4_bdd2; Owner: rachmat
--

ALTER TABLE ONLY tp4_bdd2.ville
    ADD CONSTRAINT ville_nom_key UNIQUE (nom);


--
-- Name: ville ville_pkey; Type: CONSTRAINT; Schema: tp4_bdd2; Owner: rachmat
--

ALTER TABLE ONLY tp4_bdd2.ville
    ADD CONSTRAINT ville_pkey PRIMARY KEY (num_ville);


--
-- Name: auteur auteur_num_pers_fkey; Type: FK CONSTRAINT; Schema: tp4_bdd2; Owner: rachmat
--

ALTER TABLE ONLY tp4_bdd2.auteur
    ADD CONSTRAINT auteur_num_pers_fkey FOREIGN KEY (num_pers) REFERENCES tp4_bdd2.personne(num_pers);


--
-- Name: billet billet_num_fest_fkey; Type: FK CONSTRAINT; Schema: tp4_bdd2; Owner: rachmat
--

ALTER TABLE ONLY tp4_bdd2.billet
    ADD CONSTRAINT billet_num_fest_fkey FOREIGN KEY (num_fest) REFERENCES tp4_bdd2.festival(num_fest);


--
-- Name: billet billet_num_part_fkey; Type: FK CONSTRAINT; Schema: tp4_bdd2; Owner: rachmat
--

ALTER TABLE ONLY tp4_bdd2.billet
    ADD CONSTRAINT billet_num_part_fkey FOREIGN KEY (num_part) REFERENCES tp4_bdd2.participant(num_part);


--
-- Name: creer creer_num_aut_fkey; Type: FK CONSTRAINT; Schema: tp4_bdd2; Owner: rachmat
--

ALTER TABLE ONLY tp4_bdd2.creer
    ADD CONSTRAINT creer_num_aut_fkey FOREIGN KEY (num_aut) REFERENCES tp4_bdd2.auteur(num_aut);


--
-- Name: creer creer_num_jeu_fkey; Type: FK CONSTRAINT; Schema: tp4_bdd2; Owner: rachmat
--

ALTER TABLE ONLY tp4_bdd2.creer
    ADD CONSTRAINT creer_num_jeu_fkey FOREIGN KEY (num_jeu) REFERENCES tp4_bdd2.jeux(num_jeu);


--
-- Name: employe employe_num_pers_fkey; Type: FK CONSTRAINT; Schema: tp4_bdd2; Owner: rachmat
--

ALTER TABLE ONLY tp4_bdd2.employe
    ADD CONSTRAINT employe_num_pers_fkey FOREIGN KEY (num_pers) REFERENCES tp4_bdd2.personne(num_pers);


--
-- Name: festival festival_num_emp_fkey; Type: FK CONSTRAINT; Schema: tp4_bdd2; Owner: rachmat
--

ALTER TABLE ONLY tp4_bdd2.festival
    ADD CONSTRAINT festival_num_emp_fkey FOREIGN KEY (num_emp) REFERENCES tp4_bdd2.employe(num_emp);


--
-- Name: festival festival_num_ville_fkey; Type: FK CONSTRAINT; Schema: tp4_bdd2; Owner: rachmat
--

ALTER TABLE ONLY tp4_bdd2.festival
    ADD CONSTRAINT festival_num_ville_fkey FOREIGN KEY (num_ville) REFERENCES tp4_bdd2.ville(num_ville);


--
-- Name: session fk_numfest; Type: FK CONSTRAINT; Schema: tp4_bdd2; Owner: rachmat
--

ALTER TABLE ONLY tp4_bdd2.session
    ADD CONSTRAINT fk_numfest FOREIGN KEY (num_fest) REFERENCES tp4_bdd2.festival(num_fest) ON DELETE CASCADE;


--
-- Name: jeux jeux_num_categ_fkey; Type: FK CONSTRAINT; Schema: tp4_bdd2; Owner: rachmat
--

ALTER TABLE ONLY tp4_bdd2.jeux
    ADD CONSTRAINT jeux_num_categ_fkey FOREIGN KEY (num_categ) REFERENCES tp4_bdd2.categorie(num_categ);


--
-- Name: participant participant_num_pers_fkey; Type: FK CONSTRAINT; Schema: tp4_bdd2; Owner: rachmat
--

ALTER TABLE ONLY tp4_bdd2.participant
    ADD CONSTRAINT participant_num_pers_fkey FOREIGN KEY (num_pers) REFERENCES tp4_bdd2.personne(num_pers);


--
-- Name: personne personne_nom_pays_fkey; Type: FK CONSTRAINT; Schema: tp4_bdd2; Owner: rachmat
--

ALTER TABLE ONLY tp4_bdd2.personne
    ADD CONSTRAINT personne_nom_pays_fkey FOREIGN KEY (nom_pays) REFERENCES tp4_bdd2.pays(nom_pays);


--
-- Name: session session_num_aut_fkey; Type: FK CONSTRAINT; Schema: tp4_bdd2; Owner: rachmat
--

ALTER TABLE ONLY tp4_bdd2.session
    ADD CONSTRAINT session_num_aut_fkey FOREIGN KEY (num_aut) REFERENCES tp4_bdd2.auteur(num_aut);


--
-- Name: session session_num_jeu_fkey; Type: FK CONSTRAINT; Schema: tp4_bdd2; Owner: rachmat
--

ALTER TABLE ONLY tp4_bdd2.session
    ADD CONSTRAINT session_num_jeu_fkey FOREIGN KEY (num_jeu) REFERENCES tp4_bdd2.jeux(num_jeu);


--
-- PostgreSQL database dump complete
--

