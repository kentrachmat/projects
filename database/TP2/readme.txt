
Groupe 6 , TP 2 BDD2 

Équipe :
Aziz BOURAHMA
Benedictus Kent RACHMAT
Corentin DUVIVIER
Dorian ABRAHAM

Exercice 1: 

Cet exercice nous propose de modéliser en MCD les différentes maison d'une rue, qui ont des pièces de plusieurs types et des élément décoratifs et utilitaires ‡ l'intérieur.
Pour cela, nous créons une entité Maison qui contient les numéros de celles-ci , associé ‡ une entité rue. Pour la cardinalité, on peut avoir 0 ou plusieurs maison dans une rue.
Les pièces sont elles modélisées par une entité pièce, qui possÈde une superficie et un type, qui vient de l'association avec l'entité Type qui contient les différents types de pièces possible, il peut y avoir plusieurs pièces avec un même type mais seulement un type par pièce.
Pour modéliser les élément de décors utilitaires, nous proposons de créer une entité élément de décor associé ‡ Pièces contenant les élément de décors, avec une cardinalité de 0 ou plusieurs élément de décors pour 1 ou plusieurs pièces.
Les élément utilisaires, après lecture de la question f sont modélisé par l'entité Utilities associé ‡ l'entité Type de cardinalité 0 ou plusieurs Utilities pour 1 type.



Exercice 2:

Ce deuxième exercice nous propose de modéliser la distribution de produit de producteurs locaux par une entreprise.
Nous commençons tout d'abord par créer une entité Producteur contenant nom et ville. Nous créons ensuite l'entité Point de vente, associé avec Producteurs (1 ou plusieurs points de vente pour 0 ou plusieurs producteurs).
Les producteurs peuvent proposer leur gammes de produit, on crée donc une entité Gamme de Produit (Nom produit, prix unitaire, disponibilité) composant de producteur (donc cardinalité de producteur 1 et 0 ou plusieurs Gammes de produit par producteurs).
Cette Gamme de produit est associe ‡ l'entité Catégorie, de cardinal 1 ou plus Catégorie pour 0 ou plusieurs Gammes de Produit.

Ensuite nous créons l'entité commande, qui contient la liste des produits, en relation porteuse de données avec Gammes qui contient les quantités pour chaque produits.
Une autre relation porteuse de données avec Point de vente qui représente le bon de commande et contient le prix par produit, les quantités souhaitées et livrées et le montant total. Pour la cardinalité, elles sont de 0 ou plusieurs pour les commandes et 1 pour le côté opposé.
