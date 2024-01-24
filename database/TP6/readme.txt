
-- Groupe 6 , TP 6 BDD2 

-- Équipe :
-- Benedictus Kent RACHMAT

-- tp6_recette_vide.sql :  base de données SQL vide
-- tp6_recette.sql :  base de données SQL avec données et vues

---------------------------------------------------------------------------------------------------------------------

-- Q1
-- ils sont les mêmes

-- Q2
-- non car cette requête recherche le même numEtape et numRecette dans le tableau des ingrédients et Etape

-- Q3
SELECT E.numEtape, E.numRecette, description
FROM Ingredient I
RIGHT JOIN Etape E ON (E.numEtape = I.numEtape AND E.numrecette = I.numrecette) 
ORDER BY E.numRecette,E.numEtape;

-- Q4
CREATE VIEW vue_etape AS
SELECT E.numEtape, E.numRecette, description, nomProd, quantite, unite 
FROM Ingredient I
JOIN Produit P ON I.numProd = P.numprod   
RIGHT JOIN Etape E ON (E.numEtape = I.numEtape AND E.numrecette = I.numrecette) 
ORDER BY E.numRecette,E.numEtape;

-- Q5
SELECT nomrec, nbprod, temps, numetape, description, nomprod, quantite, unite
FROM vue_etape
NATURAL JOIN Recette
NATURAL JOIN Category
WHERE numCategRec = 1;