
Groupe 6 , TP 2 BDD2 

�quipe :
Aziz BOURAHMA
Benedictus Kent RACHMAT
Corentin DUVIVIER
Dorian ABRAHAM

Exercice 1: 

Cet exercice nous propose de mod�liser en MCD les diff�rentes maison d'une rue, qui ont des pi�ces de plusieurs types et des �l�ment d�coratifs et utilitaires � l'int�rieur.
Pour cela, nous cr�ons une entit� Maison qui contient les num�ros de celles-ci , associ� � une entit� rue. Pour la cardinalit�, on peut avoir 0 ou plusieurs maison dans une rue.
Les pi�ces sont elles mod�lis�es par une entit� pi�ce, qui poss�de une superficie et un type, qui vient de l'association avec l'entit� Type qui contient les diff�rents types de pi�ces possible, il peut y avoir plusieurs pi�ces avec un m�me type mais seulement un type par pi�ce.
Pour mod�liser les �l�ment de d�cors utilitaires, nous proposons de cr�er une entit� �l�ment de d�cor associ� � Pi�ces contenant les �l�ment de d�cors, avec une cardinalit� de 0 ou plusieurs �l�ment de d�cors pour 1 ou plusieurs pi�ces.
Les �l�ment utilisaires, apr�s lecture de la question f sont mod�lis� par l'entit� Utilities associ� � l'entit� Type de cardinalit� 0 ou plusieurs Utilities pour 1 type.



Exercice 2:

Ce deuxi�me exercice nous propose de mod�liser la distribution de produit de producteurs locaux par une entreprise.
Nous commen�ons tout d'abord par cr�er une entit� Producteur contenant nom et ville. Nous cr�ons ensuite l'entit� Point de vente, associ� avec Producteurs (1 ou plusieurs points de vente pour 0 ou plusieurs producteurs).
Les producteurs peuvent proposer leur gammes de produit, on cr�e donc une entit� Gamme de Produit (Nom produit, prix unitaire, disponibilit�) composant de producteur (donc cardinalit� de producteur 1 et 0 ou plusieurs Gammes de produit par producteurs).
Cette Gamme de produit est associe � l'entit� Cat�gorie, de cardinal 1 ou plus Cat�gorie pour 0 ou plusieurs Gammes de Produit.

Ensuite nous cr�ons l'entit� commande, qui contient la liste des produits, en relation porteuse de donn�es avec Gammes qui contient les quantit�s pour chaque produits.
Une autre relation porteuse de donn�es avec Point de vente qui repr�sente le bon de commande et contient le prix par produit, les quantit�s souhait�es et livr�es et le montant total. Pour la cardinalit�, elles sont de 0 ou plusieurs pour les commandes et 1 pour le c�t� oppos�.
