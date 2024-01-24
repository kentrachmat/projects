# Afficher les métadonnées d’un i-nœud

## Membres du groupe

Ce travail est à réaliser en équipe dont les membres sont (**groupe 7 du S5 Licence 3 Informatique**) :

- BENEDICTUS KENT **RACHMAT**
- HICHEM **KARFA**

## Section 1 : Questions traitées

## Exercice 5

J'ai lancé la commande suivante :

```bash
$ > stat test
```

et j'ai obtenu une liste de champs d'information concernant le fichier.

Pour comprendre la signification de tous les champs j'ai lu la page de manuel concernant la commande stat ainsi que celle concernant les i-noeud(inode).

```bash
$ > man 2 stat
$ > man inode
```

## Exercice 6

En exécutant la commande :

```bash
$ > whatis stat
```

Nous voyons que trois sections concernent la commande stat
la section 1 (sur les commandes utilisateur)
la section 2 (pour l'appel système)
la section 3 (pour la fonction de la bibliothèque)

## Exercice 7

C'est l'appel système stat() qui permet à la commande stat d’obtenir les métadonnées qu’elle affiche.
Le type utilisé pour les représenter est une structure de type stat.
Le champ « Blocs » représente le nombre de blocs de 512 octets alloués pour le fichier.
Le champ « Blocs d’E/S » donne la taille de bloc préférée pour des entrées-sorties efficaces.

## Exercice 8

Le nombre de blocs augmente lorsque la taille de 4096 octets est dépassée, chaque bloc pèse 512 octets.
Une fois ce seuil dépassé le nombre de blocs passe de 8 à 16.
Ce nombre augmente de 8 car le système de fichiers de Linux est ext4, et ce système utilise des blocs de taille 4 KiB soit 4096 octets qui correspondent à 8 blocs de 512 octets.

## Exercice 9

Commande pour créer un lien physique :

```bash
$ > ln test lien_physique_test
```

Commande pour créer un lien symbolique :

```bash
$ > ln -s test lien_symbolique_test
```

Commande pour faire une copie du fichier test :

```bash
$ > cp test copie_test
```

## Exercice 10

On constate qu'il y a 2 liens pour le lien physique et pour le fichier d'origine, et qu'il n'y a qu'un lien pour le lien symbolique et la copie du fichier.
Le lien physique a le même numéro i-nœud que le fichier d'origine alors que la copie et le lien symbolique ont des numéros différents.

Commande pour supprimer fichier test :

```bash
$ > rm test
```

Après la suppression, on constate qu'on peut retrouver le texte que nous avions écrit dans la copie et dans le lien physique, mais pas dans le lien symbolique.

On peut donc conclure que :

- La copie est un fichier indépendant n'ayant plus de lien avec le fichier original et ayant un numéro d'inode qui lui est propre.
- Le lien physique et le fichier original partagent les mêmes données mais sont "indépendants".
- Le lien symbolique pointe directement sur le fichier orignale ils partagent donc les mêmes données. En cas de suppression du fichier original celui ci ne pointera sur aucune donnée et sera donc inexploitable

## Exercice 11 / 12 / 13

Pour créer l'exécutable placez-vous dans le dossier stat et lancez la commande suivante :

```bash
$ > make
```

Pour tester notre code, on utilise la commande sur un fichier quelconque (par exemple makefile):

```bash
$ > ./stat makefile
```

Qui nous retourne :

```
Nom du fichier : makefile
Taille : 155 octets      Blocs : 8        Blocs d'E/S : 1048576
Type du fichier : fichier ordinaire
Numéro d'inoeud  : 49022509
Nombre de liens : 1
UID : (9817/karfa)   GID :(1300/l3)  Nom utilisateur : Karfa Hichem
Droit : 0664 (octal)
Dernier accès au fichier  : Thu Sep 16 12:53:27 2021
Dernière modification du fichier: Sun Sep 12 11:54:32 2021
Dernier changement d'état : Thu Sep 16 12:53:27 2021
```

Nous avons ensuite vérifié que chaque champ est correct.

# Section 2 : Questions non-traitées

Toutes les questions ont été traitées.

# Section 3 : Bilan

Dans ce TP nous avons appris à utiliser man qui s'avère être une commande extrêmement utile pour rechercher des informations.

Nous avons appris à faire la distinction entre les commandes et les appels système.

Pour conclure nous avons étudié la structure et les métadonnées des i-noeud afin par exemple d'extraire des informations concernant un fichier.
