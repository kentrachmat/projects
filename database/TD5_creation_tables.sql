-- UE Base de données 2
-- Licence 2 informatique, Université de Lille
-- TD5, requêtes de création de tables
-- Mars 2021

------------------------------------------------
--  Base de données : Musiques
------------------------------------------------

------------------------------------------------
-- Création du schéma

CREATE SCHEMA Musiques;

------------------------------------------------
-- Création des tables

-- Tables sans clé étrangère

CREATE TABLE Album (
    numAlbum SERIAL PRIMARY KEY,
    titre VARCHAR(200) NOT NULL,
    date_sortie DATE
);

CREATE TABLE Artiste (
    numArtiste SERIAL PRIMARY KEY,
    nom VARCHAR(200),
    prenom VARCHAR(200),
    pseudo VARCHAR(200)  -- Pseudo de l'artiste ou nom du groupe
);

-- Pour s'assurer que 2 artistes/groupes n'aient pas le même pseudo, 
-- quelque-soit la casse, il est possible de passer par la création d'un index

CREATE UNIQUE INDEX Artiste_pseudo ON Artiste (lower(pseudo));


CREATE TABLE Chanson (
    numChanson SERIAL PRIMARY KEY,
    titre VARCHAR(500) NOT NULL,
    duree INTERVAL NOT NULL
);

-- Tables avec clés étrangères

-- Remarque : les contraintes sur les clés étrangères sont nommées
--            pour pouvoir les modifier 
-- Remarque : deux contraintes de tables différentes peuvent avoir le même nom 

CREATE TABLE Se_composer_de (
    numAlbum INTEGER,
    numChanson INTEGER,
    PRIMARY KEY (numAlbum, numChanson),
    CONSTRAINT fk_Album
        FOREIGN KEY (numAlbum) REFERENCES Album,
    CONSTRAINT fk_Chanson
        FOREIGN KEY (numChanson) REFERENCES Chanson
);

CREATE TABLE Etre_membre_de (
    numArtiste INTEGER,
    numGroupe INTEGER,
    PRIMARY KEY (numArtiste, numGroupe),
    CONSTRAINT fk_Artiste
        FOREIGN KEY (numArtiste) REFERENCES Artiste(numArtiste),
    CONSTRAINT fk_Groupe
        FOREIGN KEY (numGroupe) REFERENCES Artiste(numArtiste)
);

CREATE TABLE Contribuer_a (
    numArtiste INTEGER,
    numChanson INTEGER,
    role VARCHAR(100),
    PRIMARY KEY (numArtiste, numChanson),
    CONSTRAINT fk_Artiste
        FOREIGN KEY (numArtiste) REFERENCES Artiste,
    CONSTRAINT fk_Chanson
        FOREIGN KEY (numChanson) REFERENCES Chanson
);

