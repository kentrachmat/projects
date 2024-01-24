# Performances des entrées/sorties

## Membres du groupe

Ce travail est à réaliser en équipe dont les membres sont (**groupe 7 du S5 Licence 3 Informatique**) :

- BENEDICTUS KENT **RACHMAT**
- HICHEM **KARFA**

## Introduction

Pour simplifier et automatiser l'utilisation de notre travail, nous avons décidé de créer un script (script.sh) permettant de lancer la commande make, d'effectuer les tests et de créer une image contenant les résultats.<br/>

## Fonctionnement détaillé du script

Notre script compile les différents fichiers c (cat et mcat-lib) en lançant la commande suivant pour créé deux fichiers :

```bash
$ > make
```

- `resultat.dat` : où se trouve pour chaque ligne la taille du tampon passer en argument à cat et le temps d'exécution de la commande cat (notre version de cat)

- `resultat2.dat` : où se trouve pour chaque ligne le temps d'exécution de la commande mcat-lib. (ici la taille du tampon permettra simplement de comparer les deux résultats mais évidement mcat-lib ne prend pas de tampon en argument)

Il exécutera ensuite le fichier `run.gnu` qui interprète les résultats et qui dessine deux courbes (une pour cat et une pour mcat-lib) avec pour ordonner la taille du tampon et en abscisse le temps d'exéduction des commandes qu'il enregistre dans une image au format .png qu'on peut retrouver dans le dossier `images`.<br/>
Le script ne redirige pas le texte retourné par cat et mcat-lib car la redirection a un impact significatif sur le temps d'exécution qui en devient quasiment nul.<br/>

Afin d'améliorer la qualité de nos courbes, nous avons utilisé une échelle logarithmique afin que ces dernières soient plus lisibles et représentatives. <br/>

Le fichier `texte.txt` est le fichier de test utilisé par les commandes cat et mcat-lib. Il est assez lourd pour donner des valeurs significatives (11Mo).

Vous pouvez cependant utiliser n'importe quel fichier en le précisant en argument du script.

## Commandes

Placez-vous à la racine du dossier performance avant de lancer les différentes commandes..

Pour exécuter notre script `script.sh`, utilisez la commande suivante (il utilisera par défaut le fichier texte.txt) :

```bash
$ > ./script.sh
```

Pour exécuter notre script `script.sh`, avec un autre fichier utilisez la commande suivante :

```bash
$ > ./script.sh <nomFichier>
```

en remplaçant `<nomFichier>` par le chemin du fichier.

Pour compiler les différents fichiers sans lancer le script, utilisez la commande suivante :

```bash
$ > make
```

Pour utiliser notre programme `cat.c` sans utiliser le script, utilisez la commande suivante :

```bash
$ > ./cat <tampon> <fichier>
```

Où `<tampon>` correspond à la taille du tampon voulu et `<fichier>` correspond au fichier qui doit être lu.

Pour utiliser notre programme `mcat-lib.c` sans utiliser le script , utilisez la commande suivante :

```bash
$ ./mcat-lib <fichier>
```

Où `<fichier>` correspond au fichier qui doit être lu.

Vous pouvez supprimer tous les fichiers binaires, les fichiers "resultat.dat" resultat2.dat" ainsi que les images se trouvant dans le dossier images en utilisant la commande :

```bash
$ make clean
```

## Résultats

![Résultats](performances/images/performance.png "Résultats")

## Conclusion

Pour la courbe cat-taille-tampon (cat), le temps d'exécution commence à environ 29 secondes. Ce temps diminue rapidement en fonction de la taille du tampon puis stagne à environ 2.5 secondes quand la taille du tampon atteint 128 octets.<br/>
Pour la courbe mcat-lib, qui ne dépend pas de la taille du tampon, le temps d'exécution stagne à environ 2.9 secondes.<br/>

En conclusion pour avoir un temps d'exécution le plus petit possible il faut utiliser une taille de tampon suffisamment grande cela est dû au fait que les appels systems prennent beaucoup de temps il faut donc en faire le moins possible.

Nous pouvons aussi constater que la fonction mcat-lib est relativement performante et a le même temps d'exécution que notre commande cat même dans les meilleurs cas.
