# DM Les transistors d’Ornicar

# Equipe

- BENEDICTUS KENT **RACHMAT**
- HICHEM **KARFA**

# Organisation du fichier

Le DM se répartir de la façon suivante :

- Un dossier `annexe` contenant les fichiers `.mod` et `.dat` correspondent aux modélisations sur AMPL numérotés par question.

- Le fichier `report.pdf` est notre compte rendu de ce DM ([lien](report.pdf)).

# Ligne de Commande

Placez-vous dans le dossier annexe du dépôt avant d'exécuter les commandes suivantes.

Tout d'abord on doit lancer le programme ampl pour exécuter notre code :

```bash
$ > ampl
```

Àpres, le préfixe se transformera en `ampl :` et nous tapons notre commande d'ampl à l'intérieur.
Pour charger le modèle utilisez cette commande et mettez le nom du fichier en paramètre :

```bash
$ ampl: model {NOM DU FICHIER};
```

Pour afficher les variables utilisez cette commande et mettez le nom du variable en paramètre :

```bash
$ ampl: display {NOM DU VARIABLE};
```

Et enfin pour réinitialiser le code/pour lancer une autre modélisation il faut faire un reset :

```bash
$ ampl: reset;
```

# EXERCICES

## EXO 1

Pour exécuter le code du modepremier exercice, veuillez utiliser cette commande ci-dessous :

```bash
$ ampl : model exo1.mod;
```

Pour afficher la dispersion, on utilise la commande `display dispersion;`.

## EXO 2

Pour exécuter le code du deuxième exercice, veuillez utiliser cette commande ci-dessous :

```bash
$ ampl : model exo2.mod;
```

Voici les commandes pour :

- Afficher la dispersion `display dispersion;`.
- Afficher les transistors exclus : `display transistor_est_pris;`

## EXO 3

Il n'y a pas de code ampl à faire.

## EXO 4

Pour exécuter le code du quatrième exercice, veuillez utiliser cette commande ci-dessous :

```bash
$ ampl : model exo4.mod;
```

Voici les commandes pour :

- Afficher la dispersion `display dispersion;`.
- Afficher les groupes de transistors : `display gtransistor;`

## EXO 5

Pour exécuter le cinquième exercice, veuillez utiliser cette commande ci-dessous :

```bash
$ ampl : model annexe/exo5.mod;
```

Nous avons trop de contrainte le code pour cette question. Le code n'est donc pas exploitable, nous avons tout de même laissé le code et expliqué dans le rapport tout ce que nous avons compris pour cet exercice.
