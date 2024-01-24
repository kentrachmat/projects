
-- Groupe 6 , TP 4 BDD2 

-- Équipe :
-- Aziz BOURAHMA
-- Benedictus Kent RACHMAT
-- Corentin DUVIVIER
-- Dorian ABRAHAM

-- tp4_festival_vide.sql :  base de données SQL vide
-- tp4_festival.sql :  base de données SQL avec données

---------------------------------------------------------------------------------------------------------------------

-- Exercice 1: 

-- Toutes les explications sont dans l'image

-- Exercice 2:

CREATE SCHEMA tp4_bdd2;

CREATE TABLE Ville(
	num_Ville SERIAL PRIMARY KEY,
	nom VARCHAR(50) UNIQUE
);

CREATE TABLE Pays(
	nom_Pays VARCHAR(50) PRIMARY KEY
);

CREATE TABLE Personne(
	num_Pers SERIAL PRIMARY KEY,
	nom_Pays VARCHAR(50) REFERENCES Pays,
	nom VARCHAR(50) NOT NULL,
	prenom VARCHAR(50) NOT NULL,
	mail VARCHAR(50) NOT NULL
);

CREATE TABLE Employe(
	num_Emp SERIAL PRIMARY KEY,
	num_Pers INTEGER REFERENCES Personne(num_Pers)
);

CREATE TABLE Auteur(
	num_Aut SERIAL PRIMARY KEY,
	num_Pers INTEGER REFERENCES Personne(num_Pers)
);

CREATE TABLE Participant(
	num_Part SERIAL PRIMARY KEY,
	num_Pers INTEGER REFERENCES Personne(num_Pers)
);

CREATE TABLE Festival(
	num_Fest SERIAL PRIMARY KEY,
	num_Ville INTEGER,
	num_Emp INTEGER,
	titre VARCHAR(50) NOT NULL,
	debut DATE NOT NULL,
	fin DATE NOT NULL CHECK(fin>debut),
	FOREIGN KEY (num_Ville) REFERENCES Ville(num_Ville),
	FOREIGN KEY (num_Emp) REFERENCES Employe(num_Emp)
);

CREATE TABLE Billet(
	num_Fest INTEGER,
	num_Part INTEGER,
	num_Emp INTEGER,
	prix NUMERIC CHECK(prix>0),
	debut DATE NOT NULL,
	fin DATE NOT NULL CHECK(fin>debut),
	FOREIGN KEY (num_Fest) REFERENCES Festival(num_Fest),
	FOREIGN KEY (num_Part) REFERENCES Participant(num_Part),
	PRIMARY KEY(num_Fest, num_Part)
);

CREATE TABLE Categorie(
	num_Categ SERIAL PRIMARY KEY,
	titre VARCHAR(50) NOT NULL
);

CREATE TABLE Jeux(
	num_Jeu SERIAL PRIMARY KEY,
	num_Categ INTEGER,
	nom VARCHAR(50) NOT NULL,
	FOREIGN KEY (num_Categ) REFERENCES Categorie(num_Categ)
);

CREATE TABLE Creer(
	num_Jeu INTEGER,
	num_Aut INTEGER,
	FOREIGN KEY (num_Jeu) REFERENCES Jeux(num_Jeu),
	FOREIGN KEY (num_Aut) REFERENCES Auteur(num_Aut),
	PRIMARY KEY(num_Jeu, num_Aut)
);

CREATE TABLE Session(
	num_Session SERIAL PRIMARY KEY,
	num_Fest INTEGER,
	num_Jeu INTEGER,
	num_Aut INTEGER,
	date DATE NOT NULL,
	heure_Debut INTEGER NOT NULL CHECK (heure_Debut>=0),
	heure_Fin INTEGER NOT NULL CHECK (heure_Fin>=heure_Debut),
	numero INTEGER NOT NULL,
	FOREIGN KEY (num_Jeu) REFERENCES Jeux(num_Jeu),
	FOREIGN KEY (num_Aut) REFERENCES Auteur(num_Aut),
	CONSTRAINT fk_numFest FOREIGN KEY (num_Fest) REFERENCES Festival(num_Fest) ON DELETE CASCADE
);

---------------------------------------------------------------------------------------------------------------------

-- Exercice 3:  

INSERT INTO pays VALUES ('France'),('Indonesie'),('Belgique');
INSERT INTO categorie (titre) VALUES ('Famille'),('Jeu de soci�t�');
INSERT INTO jeux (num_Categ,nom) VALUES (1,'Jengga'),(2,'Monopoly');
INSERT INTO ville (nom) VALUES ('Lille'),('Mons-en-Baroeul');
INSERT INTO personne (nom_Pays,nom,prenom,mail) VALUES 
			   ('Indonesie','Rachmat','Benedictus Kent','benedictuskent.rachmat.etu@univ-lille.fr'),
			   ('France','Bourahma','Aziz','aziz.bourahma.etu@univ-lille.fr'),
			   ('France','Duvivier','Corentin','corentin.duvivier.etu@univ-lille.fr'),
			   ('France','Abraham','Dorian','dorian.abraham.etu@univ-lille.fr'),
			   ('Belgique','Thomas','Louis','louis.thomas.etu@univ-lille.fr');
INSERT INTO employe (num_Pers) VALUES (1);
INSERT INTO auteur (num_Pers) VALUES (2),(4);
INSERT INTO participant (num_Pers) VALUES (3),(5);
INSERT INTO festival (num_Ville,num_Emp,titre,debut,fin) VALUES (1,1,'Lille Fest','2021-03-01','2021-03-06'),(2,1,'Mons-en-Baroeul Fest','2021-05-12','2021-05-20');
INSERT INTO creer VALUES (1,1),(2,2);
INSERT INTO session (num_Fest,num_Jeu,num_Aut,date,heure_Debut,heure_Fin,numero) VALUES (1,1,1,'2021-03-02',6.30,12.30,1),(1,2,2,'2021-03-05',7.30,8.30,2),(2,2,2,'2021-03-14',6.30,12.30,1),(2,1,1,'2021-03-19',8,9,4)


-- NOTE
-- pour nommer le constraint
-- CONSTRAINT fk_numFest FOREIGN KEY (numFest) REFERENCES Festival(numFest) ON DELETE CASCADE