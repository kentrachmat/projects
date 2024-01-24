#   Des arbres et des couleurs

Ce dépôt correspond au TP de PF « Des arbres et des couleurs ».


##  Instructions pour rendre votre travail avec gitlab

Pour permettre à votre chargé de TD de suivre votre travail sur ce projet :

-   *forkez* ce dépôt (bouton _Fork_),
-   dans le dépôt *forké*, ajoutez votre chargé de TD aux membres du
    projet avec l’accès _Developer_,
-   créez un fichier `RAPPORT.md` à la racine du dépôt.


##  Contenu de votre `RAPPORT.md`

Votre rapport devra être au format [markdown] et être à la racine du
dépôt.

[markdown]: https://gitlab-etu.fil.univ-lille1.fr/help/user/markdown.md

Il contiendra :

-   vos noms et prénoms,
-   pour chaque sujet de TP, vous ferez une section contenant les
    3 sous-sections suivantes :

    1.  questions traitées : quelles questions vous avez traitées en
        précisant si elles vous ont posé une difficulté et laquelle,
    2.  questions non-traitées : quelles questions vous n’avez pas
        traitées et pourquoi,
    3.  notions : quelles sont les idées importantes à retenir de ce
        TP (notamment ce que vous avez appris en le faisant).
        En particulier : pourquoi ces structures de données (arbres
        binaires de recherche, arbres rouges et noirs) sont
        utiles ?


##  Contenu du dépôt

Ce dépôt ne contient initialement qu’un fichier presque vide et la
règle d’intégration continue pour vérifier que ce source compile
correctement.
N’hésitez pas à modifier le fichier `.gitlab-ci.yml` pour effectuer
plus de tests, ou au contraire le supprimer si l’intégration continue
ne vous sert pas.

Pour traiter ce TP, vous aurez besoin de QuickCheck en plus des
bibliothèques de base.
N’hésitez pas à vous inspirer des fichiers du dépôt du 1^er dépôt de
TP, en particulier `premiers-contacts.cabal`, etc. si vous voulez
utiliser les outils comme `stack` et/ou `cabal` pour travailler sur
votre machine personnelle : il vous suffira de remplacer la ligne
indiquant la dépendance `gloss` par une mentionnant `QuickCheck` pour
avoir un fichier `.cabal` exploitable.
