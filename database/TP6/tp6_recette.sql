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
    CONSTRAINT ingredient_unite_check CHECK (((unite)::text = ANY (ARRAY[('cl'::character varying)::text, ('g'::character varying)::text, ('cuillere a soupe'::character varying)::text, ('cuillere a cafe'::character varying)::text])))
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
-- Name: vue_etape; Type: VIEW; Schema: tp6_bdd2; Owner: rachmat
--

CREATE VIEW tp6_bdd2.vue_etape AS
 SELECT e.numetape,
    e.numrecette,
    e.description,
    p.nomprod,
    i.quantite,
    i.unite
   FROM ((tp6_bdd2.ingredient i
     JOIN tp6_bdd2.produit p ON ((i.numprod = p.numprod)))
     RIGHT JOIN tp6_bdd2.etape e ON (((e.numetape = i.numetape) AND (e.numrecette = i.numrecette))))
  ORDER BY e.numrecette, e.numetape;


ALTER TABLE tp6_bdd2.vue_etape OWNER TO rachmat;

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

INSERT INTO tp6_bdd2.category VALUES (0, 'Divers');
INSERT INTO tp6_bdd2.category VALUES (1, 'Cocktail');
INSERT INTO tp6_bdd2.category VALUES (2, 'Dessert');


--
-- Data for Name: etape; Type: TABLE DATA; Schema: tp6_bdd2; Owner: rachmat
--

INSERT INTO tp6_bdd2.etape VALUES (1, 1, 'Mettez vos glacons dans un torchon, refermez-le puis, pilez la glace.');
INSERT INTO tp6_bdd2.etape VALUES (2, 1, 'Vous pouvez encore avoir des morceaux. Versez dans un bol et reservez au congelateur.');
INSERT INTO tp6_bdd2.etape VALUES (3, 1, 'Depose les feuilles de menthe au fond du verre et coupez le citron en deux puis chaque demi citron en 6 morceaux.');
INSERT INTO tp6_bdd2.etape VALUES (4, 1, 'Ajoutez le sirop de sucre de canne et les citron');
INSERT INTO tp6_bdd2.etape VALUES (5, 1, 'Crasez le citron avec un pilon special cocktail et puis ajoutez la glace pile en laissant 2 cm de libre.');
INSERT INTO tp6_bdd2.etape VALUES (6, 1, 'Ajoutez le rhum.');
INSERT INTO tp6_bdd2.etape VALUES (7, 1, 'Completez avec eau gazeuse.');
INSERT INTO tp6_bdd2.etape VALUES (1, 2, 'Prechauffez le four a 200C. Disposez les noix de pecan sur une plaque avec du papier sulfurise en dessous. Enfournez et faites cuire les noix de pecan 5 a 8 minutes. Otez du four, concassez grossierement les noix de pecan, reservez.');
INSERT INTO tp6_bdd2.etape VALUES (2, 2, 'Versez le miel dans un bol, ajoutez-y la cannelle, la vanille, et quelques gouttes de jus. Melangez le tout.');
INSERT INTO tp6_bdd2.etape VALUES (3, 2, 'Lavez les poires et evidez-les delicatement. Ensuite, retirer le trognon.');
INSERT INTO tp6_bdd2.etape VALUES (4, 2, 'Deposez les poires dans un plat a gratin, ajoutez le jus du citron dans le fond du plat. Arrosez les poires avec 1/2 de la garniture, enfournez et faites-les cuire 15 minutes.');
INSERT INTO tp6_bdd2.etape VALUES (5, 2, 'Melangez les noix de pecan grossierement concassees avec la restante garniture.');
INSERT INTO tp6_bdd2.etape VALUES (6, 2, 'Otez les poires du four, remplissez-en les cavites en y repartissant la garniture aux noix de pecan. Enfournez de nouveau et poursuivez la cuisson pendant 10 minutes. Servez chaudes.');


--
-- Data for Name: ingredient; Type: TABLE DATA; Schema: tp6_bdd2; Owner: rachmat
--

INSERT INTO tp6_bdd2.ingredient VALUES (1, 1, 1, 2, 20, 'g');
INSERT INTO tp6_bdd2.ingredient VALUES (2, 2, 1, NULL, 1, NULL);
INSERT INTO tp6_bdd2.ingredient VALUES (3, 3, 1, 6, 30, 'g');
INSERT INTO tp6_bdd2.ingredient VALUES (4, 4, 1, 4, 20, 'cl');
INSERT INTO tp6_bdd2.ingredient VALUES (5, 5, 1, 3, 1, NULL);
INSERT INTO tp6_bdd2.ingredient VALUES (6, 6, 1, 5, 150, 'cl');
INSERT INTO tp6_bdd2.ingredient VALUES (7, 7, 1, 1, 50, 'cl');
INSERT INTO tp6_bdd2.ingredient VALUES (1, 1, 2, 8, 40, 'g');
INSERT INTO tp6_bdd2.ingredient VALUES (2, 2, 2, 9, 20, 'g');
INSERT INTO tp6_bdd2.ingredient VALUES (3, 3, 2, 10, 30, 'g');
INSERT INTO tp6_bdd2.ingredient VALUES (4, 4, 2, 12, 10, 'g');


--
-- Data for Name: produit; Type: TABLE DATA; Schema: tp6_bdd2; Owner: rachmat
--

INSERT INTO tp6_bdd2.produit VALUES (1, 'Eau Gazeuse');
INSERT INTO tp6_bdd2.produit VALUES (2, 'Glacons');
INSERT INTO tp6_bdd2.produit VALUES (3, 'Citron Vert');
INSERT INTO tp6_bdd2.produit VALUES (4, 'Sirop de Sucre de Canne');
INSERT INTO tp6_bdd2.produit VALUES (5, 'Rhum Blanc');
INSERT INTO tp6_bdd2.produit VALUES (6, 'Feuilles de Menthe');
INSERT INTO tp6_bdd2.produit VALUES (7, 'Poire Conference');
INSERT INTO tp6_bdd2.produit VALUES (8, 'Noix de Pecan');
INSERT INTO tp6_bdd2.produit VALUES (9, 'Vanille en Poudre');
INSERT INTO tp6_bdd2.produit VALUES (10, 'Miel');
INSERT INTO tp6_bdd2.produit VALUES (11, 'Citron Bio');
INSERT INTO tp6_bdd2.produit VALUES (12, 'Cannelle en Poudre');


--
-- Data for Name: recette; Type: TABLE DATA; Schema: tp6_bdd2; Owner: rachmat
--

INSERT INTO tp6_bdd2.recette VALUES (1, 1, 'Mojito', 6, '00:30:00');
INSERT INTO tp6_bdd2.recette VALUES (2, 2, 'Poires roties au four a la cannelle et noix de pecan', 4, '00:40:00');


--
-- Data for Name: ustensile; Type: TABLE DATA; Schema: tp6_bdd2; Owner: rachmat
--

INSERT INTO tp6_bdd2.ustensile VALUES (1, 'Couteau office');
INSERT INTO tp6_bdd2.ustensile VALUES (2, 'Pilon a cocktail');
INSERT INTO tp6_bdd2.ustensile VALUES (3, 'Rouleau a patisserie');
INSERT INTO tp6_bdd2.ustensile VALUES (4, 'Torchon');
INSERT INTO tp6_bdd2.ustensile VALUES (5, 'Cuillere a pomme parisienne');
INSERT INTO tp6_bdd2.ustensile VALUES (6, 'Planche a decouper');
INSERT INTO tp6_bdd2.ustensile VALUES (7, 'Plat a gratin');
INSERT INTO tp6_bdd2.ustensile VALUES (8, 'Plaque de cuisson pour four');
INSERT INTO tp6_bdd2.ustensile VALUES (9, 'Papier cuisson');


--
-- Data for Name: utiliser; Type: TABLE DATA; Schema: tp6_bdd2; Owner: rachmat
--

INSERT INTO tp6_bdd2.utiliser VALUES (1, 1);
INSERT INTO tp6_bdd2.utiliser VALUES (2, 1);
INSERT INTO tp6_bdd2.utiliser VALUES (3, 1);
INSERT INTO tp6_bdd2.utiliser VALUES (4, 1);
INSERT INTO tp6_bdd2.utiliser VALUES (5, 2);
INSERT INTO tp6_bdd2.utiliser VALUES (6, 2);
INSERT INTO tp6_bdd2.utiliser VALUES (7, 2);
INSERT INTO tp6_bdd2.utiliser VALUES (8, 2);
INSERT INTO tp6_bdd2.utiliser VALUES (9, 2);


--
-- Name: category_numcategrec_seq; Type: SEQUENCE SET; Schema: tp6_bdd2; Owner: rachmat
--

SELECT pg_catalog.setval('tp6_bdd2.category_numcategrec_seq', 1, false);


--
-- Name: produit_numprod_seq; Type: SEQUENCE SET; Schema: tp6_bdd2; Owner: rachmat
--

SELECT pg_catalog.setval('tp6_bdd2.produit_numprod_seq', 12, true);


--
-- Name: recette_numrecette_seq; Type: SEQUENCE SET; Schema: tp6_bdd2; Owner: rachmat
--

SELECT pg_catalog.setval('tp6_bdd2.recette_numrecette_seq', 2, true);


--
-- Name: ustensile_numust_seq; Type: SEQUENCE SET; Schema: tp6_bdd2; Owner: rachmat
--

SELECT pg_catalog.setval('tp6_bdd2.ustensile_numust_seq', 9, true);


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

