# Membres du groupe

Ce dépôt appartient à Hichem **KARFA** et Benedictus Kent **RACHMAT**, Groupe 7 du S5 Licence 3 Informatique

Ce dépôt correspond au TP numéro trois (Le L-système et la Tortue) de PF

# Le L-système et la Tortue

## Section 0.1 : Structure du dossier

afin de rendre le code plus agréable à lire, nous avons séparé le code en plusieurs fichiers :

- `floconDeVonKoch.hs` qui permet d'afficher le dessin du Flocon de von Koch au tout début
- `lsystemeAnime.hs` qui permet de visualiser au fur et à mesure les itérations d'un L-système(vonKockAnime, hilbertAnime..)
- `tortueVolante.hs` qui permet de visualiser la tortue volante (brindille, broussaille)

- (bonus) `récursivité.hs` qui correspond à l'implémentation des fonctions de LYAH sur la récursivité.



Pour dessiner les courbes, ces fichiers importent deux modules se trouvant dans le dossier LsystemTortue :

- `Tortue.hs` qui permet de construire une tortue
- `Lystème.hs` qui permet de construire un L-système

## Section 0.2 : Compilation et Exécution

Pour compiler les fichiers, placez-vous à la racine du dépôt et exécuter la commande :

```bash
$ make
```
Puis pour visualiser les différentes courbes exécutez une des commandes suivantes en fonction du besoin : 

Pour afficher le dessin du Flocon de von Koch au tout début :
```bash
$ ./floconDeVonKoch   
```

Pour visualiser au fur et à mesure les itérations d'un L-système :
```bash
$ ./lsystemeAnime
```

Pour visualiser la tortue volante :
```bash
$ ./tortueVolante
```

Pour nettoyer le dépôt placez-vous à la racine et exécuter la commande :
```bash
$ make clean
```

## Section 1.1 : Questions traitées

le sujet comporte une seul question : 

**Q9)** Dans la fonction interpreteSymbole, avez-vous ajouté en tête ou en queue le nouveau point dans le chemin ? Cela a-t-il une conséquence d’un point de vue complexité (quand la taille du chemin est importante) ?

Réponse: L'ajout en tête est en O(1) (on ne fait qu'ajouter l'élément sans traverser la liste), alors que l'ajout en queue est en O(n) où n est la taille de la liste, on doit d'abord traverser toutes la liste avant d'ajouter l'élément en fin de liste. le choix d'ajout en tete ou en queue est donc important et nous avons tout naturellement choisi l'ajout tete pour une meilleure complixité.

Nous avons coder toutes les fonctions liées à l'ensemble des questions, Nous avons eu certaines difficultés qui nous détaillions dans la section suivante.

## Section 1.2 : Difficulté rencontrée 

- La 1re difficulté avant même de commencer à coder était de comprendre le sujet, les notions abordées ainsi que les fonctions que nous devions coder. Par exemple qu'est-ce qu'un  L-systeme, une tortue ? 

- Les problèmes de typage on était la cause la plus fréquente de nos erreurs il a donc fallu bien en comprendre la signification de tous les types et revenir à la définition des différents synonymes si besoin.

Voici les fonctions qui nous ont causé le plus de problèmes :

- **Q1)** MotSuivant <br/>
`motSuivant :: Regles -> Mot -> Mot` <br/>
La version avec la liste en compréhension de "motSuivant" posé un problème de type, notre fonction retournée une liste de mots plutôt qu'un mot.
exemple **["f--","f++"]** au lieu de **["f--f++"]** on a résolu le probleme en utilisant la fonction concat, qui transforme une liste de liste a une liste.

- **Q10)** interpreteMot <br/>
`interpreteMot :: Config -> Mot -> Picture` <br/>
Nous étions perdus car nous ne savions pas par où commencer et surtout ce qu'il fallait faire nous avons donc retourné le problème :
au lieu de commencer par les arguments et trouver une solution pour retourner une Pictures nous avons fait l'inverse, Nous avons regardé quelle fonction pouvait retourner une image et nous avons déconstruit pas à pas la fonction. Nous avons laissé en commentaire de la fonction pour les différentes questions que nous nous sommes posée avant de coder la fonction. cela peut être très utile pour comprendre la fonction si nous revenons dessus dans quelques semaine ou mois.

- **Q11)** lsystemeAnime <br/>
`lsystemeAnime :: LSysteme -> Config -> Float -> Picture` <br/>
Même chose que pour interpreteMot, on n'avait pas très bien compris l'énoncé on a donc directement codé la fonction en regardant la signature de la méthode. 
ici la fonction retourne une picture, sachant qu'on venait de coder une fonction qui retourne le même type (une picture) il y avait de force chance que on devrait l'utiliser. Concernant la deuxième partie de la question avec le facteur d'échelle on ne comprenait pas le problème on a donc lancé la fonction et constater qu'il y avait en effet un problème d'échelle.

- **Q12)** interpreteSymbole <br/> 
`interpreteSymbole :: Config -> EtatDessin -> Symbole -> EtatDessin` <br/>
Nous dessinions la tortue d'une mauvaise façon, en effet lors de l'envol de la tortue nous n'avions pas créé de nouveau chemin ce qui avait pour conséquence d'ajouter trop de traits. Il a fallu remodifier notre code afin de créer un nouveau chemin mais cela n'a pas été simple à faire.

## Section 2 : Questions non-traitées

L'ensemble des questions ont été traitées.

## Section 3 : Notion

le typage est une des notion très importante du sujet, beaucoup de problèmes ont été résolu grace à la compréhension des différents types : <br/>
Quand nous étions bloqués sur des fonctions la lecture des types la signature était un élément-clé afin de comprendre ce qu'il fallait faire. Parfois un mot peut être beaucoup plus clair qu'un énoncé.

Il y a bien évidemment la bibliothèque Gloss qui nous a permis de dessiner les différentes courbes.

D'autres notions indépendantes d'Haskell on était vus comme les l-systeme et la tortue.

La récursivité était encore une notion importante de ce sujet.
