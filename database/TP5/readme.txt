
-- Groupe 6 , TP 5 BDD2 

-- Équipe :
-- Aziz BOURAHMA
-- Benedictus Kent RACHMAT
-- Corentin DUVIVIER
-- Dorian ABRAHAM

-- tp5_recette_vide.sql :  base de données SQL vide
-- tp5_recette.sql :  base de données SQL avec données et vues

---------------------------------------------------------------------------------------------------------------------

-- Q1 :

-- Création du schéma

CREATE SCHEMA tp5_bdd2;

-- Création des tables

CREATE TABLE Category(
	numCategRec SERIAL PRIMARY KEY,
	nomCat VARCHAR(200) UNIQUE
);

CREATE TABLE Ustensile(
	numUst SERIAL PRIMARY KEY,
	nomUst VARCHAR(200)
);

CREATE TABLE Produit(
	numProd SERIAL PRIMARY KEY,
	nomProd VARCHAR(200)
);

CREATE TABLE Recette(
	numRecette SERIAL PRIMARY KEY,
	numCategRec INTEGER DEFAULT 0 REFERENCES Category ON DELETE SET DEFAULT,
	nomRec VARCHAR(200),
	nbProd INTEGER,
	temps INTERVAL
);

CREATE TABLE Utiliser(
	numUst INTEGER,
	numRecette INTEGER,
	CONSTRAINT pk_Ustensile_Recette PRIMARY KEY(numUst, numRecette),
	CONSTRAINT fk_Ustensile FOREIGN KEY (numUst) REFERENCES Ustensile(numUst),
	CONSTRAINT fk_Recette FOREIGN KEY (numRecette) REFERENCES Recette(numRecette) ON DELETE CASCADE
);

CREATE TABLE Etape(
	numEtape INTEGER,
	numRecette INTEGER,
	description VARCHAR(1000),
	CONSTRAINT pk_Etape_Recette PRIMARY KEY(numEtape, numRecette),
	CONSTRAINT fk_Recette FOREIGN KEY (numRecette) REFERENCES Recette(numRecette) ON DELETE CASCADE
);

CREATE TABLE Ingredient(
	numingre INTEGER,
	numEtape INTEGER,
	numRecette INTEGER, 
	numProd INTEGER CHECK(numProd > 0),
	quantite INTEGER CHECK(quantite > 0),
	unite VARCHAR(20) CHECK (unite IN ('cl','g','cuillere a soupe','cuillere a cafe')),
	CONSTRAINT pk_Ingredient_Etape_Recette PRIMARY KEY (numingre, numEtape, numRecette),
	CONSTRAINT fk_Produit FOREIGN KEY (numProd) REFERENCES Produit(numProd),
	CONSTRAINT fk_Etape_Recette FOREIGN KEY (numEtape, numRecette) REFERENCES Etape(numEtape, numRecette) ON DELETE CASCADE		
);

---------------------------------------------------------------------------------------------------------------------

-- Remplissage des tables avec des données

INSERT INTO Category VALUES(0, 'Divers'),(1,'Cocktail'),(2,'Dessert');

INSERT INTO Ustensile(nomUst) VALUES('Couteau office'),('Pilon a cocktail'),('Rouleau a patisserie'),('Torchon'),('Cuillere a pomme parisienne'),('Planche a decouper'),('Plat a gratin'),('Plaque de cuisson pour four'),('Papier cuisson');

INSERT INTO Produit(nomProd) VALUES('Eau Gazeuse'),('Glacons'),('Citron Vert'),('Sirop de Sucre de Canne'),('Rhum Blanc'),('Feuilles de Menthe'),('Poire Conference'),('Noix de Pecan'),('Vanille en Poudre'),('Miel'),('Citron Bio'),('Cannelle en Poudre');

INSERT INTO Recette(numCategRec, nomRec, nbProd, temps) VALUES(1,'Mojito',null,'00:30:00'),(2,'Poires roties au four a la cannelle et noix de pecan',null,'00:40:00');

INSERT INTO Utiliser VALUES (1,1),(2,1),(3,1),(4,1),(5,2),(6,2),(7,2),(8,2),(9,2);

INSERT INTO Etape VALUES 
(1,1,'Mettez vos glacons dans un torchon, refermez-le puis, pilez la glace.'),
(2,1,'Vous pouvez encore avoir des morceaux. Versez dans un bol et reservez au congelateur.'),
(3,1,'Depose les feuilles de menthe au fond du verre et coupez le citron en deux puis chaque demi citron en 6 morceaux.'),
(4,1,'Ajoutez le sirop de sucre de canne et les citron'),
(5,1,'Crasez le citron avec un pilon special cocktail et puis ajoutez la glace pile en laissant 2 cm de libre.'),
(6,1,'Ajoutez le rhum.'),
(7,1,'Completez avec eau gazeuse.'),
(1,2,'Prechauffez le four a 200C. Disposez les noix de pecan sur une plaque avec du papier sulfurise en dessous. Enfournez et faites cuire les noix de pecan 5 a 8 minutes. Otez du four, concassez grossierement les noix de pecan, reservez.'),
(2,2,'Versez le miel dans un bol, ajoutez-y la cannelle, la vanille, et quelques gouttes de jus. Melangez le tout.'),
(3,2,'Lavez les poires et evidez-les delicatement. Ensuite, retirer le trognon.'),
(4,2,'Deposez les poires dans un plat a gratin, ajoutez le jus du citron dans le fond du plat. Arrosez les poires avec 1/2 de la garniture, enfournez et faites-les cuire 15 minutes.'),
(5,2,'Melangez les noix de pecan grossierement concassees avec la restante garniture.'),
(6,2,'Otez les poires du four, remplissez-en les cavites en y repartissant la garniture aux noix de pecan. Enfournez de nouveau et poursuivez la cuisson pendant 10 minutes. Servez chaudes.');

INSERT INTO Ingredient VALUES
(1,1,1,2,20,'g'),
(2,2,1,null,1,null),
(3,3,1,6,30,'g'),
(4,4,1,4,20,'cl'),
(5,5,1,3,1,null),
(6,6,1,5,150,'cl'),
(7,7,1,1,50,'cl'),
(1,1,2,8,40,'g'),
(2,2,2,9,20,'g'),
(3,3,2,10,30,'g'),
(4,4,2,12,10,'g');

---------------------------------------------------------------------------------------------------------------------

-- Q2 :  
UPDATE Recette SET nbProd=(SELECT COUNT(DISTINCT numprod) FROM Ingredient WHERE numRecette = 1) WHERE numRecette = 1;
UPDATE Recette SET nbProd=(SELECT COUNT(DISTINCT numprod) FROM Ingredient WHERE numRecette = 2) WHERE numRecette = 2;

-- Q3 : 
SELECT nomUst AS "Ustensile"
FROM Ustensile us 
JOIN Utiliser ut ON us.numUst = ut.numUst
JOIN Recette re ON re.numRecette = ut.numRecette
WHERE nomRec='Mojito';

SELECT nomProd AS "Produit",quantite,unite 
FROM Produit pr
JOIN Ingredient ing ON ing.numProd = pr.numprod
JOIN Recette rec ON rec.numrecette = ing.numrecette 
JOIN Etape et ON (et.numEtape = ing.numEtape AND et.numrecette = ing.numrecette)
WHERE nomrec = 'Mojito';

SELECT numEtape AS "no",description
FROM Etape et
JOIN Recette re ON re.numRecette = et.numRecette
WHERE nomRec='Mojito';

-- Q4 : 
INSERT INTO Category(nomCat) VALUES('Cocktail'); -- Nous ne pouvons pas ajouter plus de cocktail car cela doublera la base de données (UNIQUE)
INSERT INTO Ustensile VALUES (1,'test'); --  Nous ne pouvons pas avoir 2 numUst avec la même valeur (PRIMARY KEY)
INSERT INTO Produit VALUES(1,'test'); -- Nous ne pouvons pas avoir 2 numProd avec la même valeur (PRIMARY KEY)
INSERT INTO Recette(numCategRec, nomRec, nbProd, temps) VALUES(12,'Tapas',6,'00:30:00'); --  numCategRec doit correspondre à la table Category (FOREIGN KEY)
INSERT INTO Utiliser VALUES(100,100) -- ils doivent correspondre à la table Ustensile et Recette (FOREIGN KEY)
INSERT INTO Etape VALUES(1,'test','test1') -- numRecette doit être INTEGER
INSERT INTO Ingredient VALUES(1,1,1,-2,-1,null) -- numProd et quantite doivent être positif (CHECK)

DELETE FROM Ustensile WHERE numUst=1 -- Référencée à partir de la table Utiliser
DELETE FROM Produit WHERE numProd=1 -- Référencée à partir de la table Ingredient

-- Q5 : 
DELETE FROM Category WHERE numCategRec=2; --Agrégation 
DELETE FROM Etape WHERE numEtape=7; --Composition
DELETE FROM Recette WHERE numRecette=2; --Composition
