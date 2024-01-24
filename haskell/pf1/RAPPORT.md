# Membres du groupe

Ce dépôt appartient à Hichem **KARFA** et Benedictus Kent **RACHMAT**, Groupe 7 du S5 Licence 3 Informatique

Ce dépôt correspond aux deux premières séances de TP de PF, il y a un dossier par séances(partie1 et partie2)

## Premiers contacts

### Section 0 : Structure du dossier

Le TP "Premiers contacts" se trouve dans le dossier "partie1" de la racine. On y trouve le fichier `partie1.hs` où figure l'ensemble de nos fonctions. Pour certains exercices nous avons pensé à les faire de différentes façons donc il peut y avoir plusieurs variantes

### Section 1 : Questions traitées

Tout notre code est fonctionnel.

Cependant quelques questions nous ont posé problème :

**_Q6)_** Reprogrammer la fonction concat et ++ a été particulièrement difficile, car je n'avais pas l'habitude du filtrage de motifs. Je devais donc trouver le comportement récursif.

**_Q9)_** Pour la fonction récursive, j'avais la solution, mais un simple problème de priorité a empêché notre programme de fonctionner normalement.

### Section 2 : Questions non-traitées

L'ensemble des questions ont été traitées.

### Section 3 : Notions

- Familiarisation avec Haskell et l'interprète GHCI
- Manipulation des listes
- L'utilisation du filtrage par motif

## Deuxième contact

### Section 0 : Structure du dossier et lancement du programme

Le TP "Deuxième contact" se trouve dans le dossier "partie2" de la racine. Il y a quatre fichiers Haskell.il y a un fichier par partie du TP :

- echauffement.hs : Ce fichier contient les fonctions "alterne" et "combine"
- triangle_pascal.hs : Ce fichier contient les fonctions "pasPascal" et "pascal"
- dragon.hs : Ce fichier contient les fonctions permettant de dessiner la courbe dragon
- dragonAlternative.hs : Ce fichier contient les fonctions permettant de dessiner la courbe dragon alternative

il contient aussi un makefile permettant d'exécuter une commande et les fichiers de configuration pour Cabal et Stack(Setup.hs, premiers-contacts.cabal, stack.yaml)

#### Lancement du programme

Pour pouvoir lancer le programme (dragon ou dragonAlternative) placez-vous dans le dossier "partie2" et exécuter la commande :

```bash
$ make
```

Pour afficher les images des différentes courbes du dragon exécuter la commande :

```bash
$ ./dragon
```

ou

```bash
$ ./dragonAlternative
```

Si vous voulez supprimer les fichiers générés avec la commande make, vous pouvez utiliser la commande :

```bash
$ make clean
```

### Section 1 : Questions traitées

Voici les fonctions qui nous ont posé problème :

pasPascal: c'est l'algorithme permettant de passer d'un triangle à un autre qui à poser problème.

pasDragon : nous n'avions pas pensé à tous les cas de bases : par exemple celui où il n'y a que le segment A/B. D'ailleurs l'utilisation de la notation `(a:b:xs@(c:_))` a rendu notre code beaucoup plus simple car on n'a plus a utiiser des fonctions comme Head.

dragonOrdre : nous avions un problème d'ordre des points, pour dessiner la courbe dans le bon ordre il ne faut pas oublier d'inverser l'ordre les points du deuxième appel récursif(Entre B et C)

### Section 2 : Questions non-traitées

L'ensemble des questions ont été traitées.

### Section 3 : Notions

Dans cette partie nous avons beaucoup plus travaillé sur la récursivité(et le filtrage par motif) avec des exercices plus compliqués que la partie 1. Nous avons appris à utiliser le module graphics gloss qui permet de dessiner des graphiques vectoriels avec des fonctions d'affichage.
