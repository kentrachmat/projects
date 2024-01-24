_thème 7_ — Structures autoréférentes
=====================================

> [Université de Lille](https://www.univ-lille.fr/)  
> [Licence d'informatique, 2e année](https://portail.fil.univ-lille1.fr/ls4)  
> [Module de MPC — Maîtrise de la Programmation en C](https://portail.fil.univ-lille1.fr/ls4/mpc)  
> Équipe pédagogique de MPC, avec des emprunts à des documents CC-BY de Philippe Marquet, 2004-2018.  
> mai 2021  
> [CC BY](https://creativecommons.org/licenses/by/4.0/)

Une version PDF pour impression est accessible sur [nextcloud.univ-lille.fr](https://nextcloud.univ-lille.fr/index.php/s/KPpFdnfCPTaRGcj).

Ce document est le support d'exercices de TD et TP.

Le thème 7 traite des structures autoréférentes via la manipulation de
+listes chaînées, piles, files.

→ Implémentation de piles d'entiers — _exercices de TD_
-------------------------------------------------------

On veut réaliser une structure de donnée de type pile, stockant des entiers
`int`, et implémentée sous forme d'une liste chainée.

On se propose de donner les définitions des fonctions de prototypes :

```c
int is_empty(pile_t);     /* vraie ssi la pile est vide */
int init(pile_t *);       /* initialise la pile */
int push(pile_t *, int);  /* empile une valeur */
int pop(pile_t *, int *); /* dépile une valeur */
int destroy(pile_t *);    /* désalloue toute la pile */
```

Les fonctions renvoient une valeur nulle pour indiquer un succès, une
valeur non nulle sinon.

1. Donnez la définition du type `pile_t`

1. Comment est représentée la pile vide ?

1. Expliquez les paramètres des prototypes ci-dessus.

1. Donnez une définition de la fonction `init()`

1. Donnez une définition du prédicat `is_empty()`

1. Donnez une définition de la fonction `push()`

1. Donnez une définition de la fonction `pop()`

1. Donnez une définition de la fonction `destroy()`

On souhaite maintenant étendre les fonctionnalités de notre structure
de données pour permettre l'affichage de tous ses éléments,
l'insertion et la suppression d'éléments à une position passée en
paramètre (contrairement aux fonctions `push()` et `pop()` qui opèrent
sur le sommet de la pile).

Les fonctions `insert()` et `remove()` renvoient 0 en cas de
succès, -1 en cas d'échec.

```c
void display(pile_t); /* parcourt la pile et affiche les éléments */
int insert(pile_t *p, int val, int pos); /* insère un nouvel élément à la position demandée */
int remove(pile_t *p, int *val, int pos); /* supprime l'élément à la position demandée */
```

1. Donnez une définition de la fonction `display()`.

1. Donnez une définition de la fonction `insert()`.  
   La position d'insertion dans la pile est telle que
   `insert (p, v, 0)` est équivalent à `push(p, v)`.

valeur du paramètre `pos` doit être com
1. Donnez une définition de la fonction `remove()`.
   La position de suppression dans la pile est telle que
   `remove (p, v, 0)` est équivalent à `pop(p, v)`.

→ Files d'entiers — _exercices de TP_
-------------------------------------

On se propose d'implementer des files d'entiers, structures de données
de type FIFO dans laquelle les premiers éléments ajoutés sont les premiers
éléments à être enlevés.

Notre implémentation se base sur une liste chaînée de nœuds.  
Chaque nœud contient une valeur entière. De plus :

* le premier nœud de la file pointe sur le second,
* le second pointe sur le 3e
* etc.

Une file est elle-même une structure qui mémorise principalement une
référence vers le 1er nœud.  
Afin de permettre d'enfiler une nouvelle valeur en fin de file sans
avoir à parcourir la liste chainée, la structure mémorise également
une référence vers le dernier nœud.

Nous définirons :

* un type `struct ififo_node_s` pour représenter un nœud.
* un type `struct ififo_s` pour représenter une file.

1. Donnez la définition du type `struct ififo_node_s`

1. Donnez la définition du type `struct ififo_s`

1. Comment est représentée la file vide ?

1. Donnez la définition d'une fonction `struct ififo_s *ififo_new()`
   qui alloue et initialise une nouvelle structure `struct ififo_s`.

    Cette fonction renvoie `NULL` en cas d'échec.

1. Donnez la définition d'un prédicat
   `int ififo_is_empty(struct ififo_s *f)` qui teste la vacuité d'une
   file.

1. Donnez la définition d'une fonction
   `int ififo_enqueue(struct ififo_s *, int)` qui rajoute un nouvel
   élement en fin de file.

    La fonction retourne 0 en cas de succès, une valeur non nulle sinon.

1. Donnez la définition d'une fonction
   `int ififo_dequeue(struct ififo_s *, int *)` qui enlève l'élement en
   tête de file.

    La valeur de cet élément sera copié à l'adresse `int *` fournie en
    paramètre.

    La fonction retourne 0 en cas de succès, une valeur non nulle sinon.

1. Donnez la définition d'une fonction
   `int ififo_head(const struct ififo_s *)` qui renvoie la valeur de
   l'élément de tête, sans modifier la file.

1. Donnez la définition d'une fonction
   `int ififo_apply(struct ififo_s *f, func_t *fn)` qui applique la
   fonction `fn` sur la valeur int de chaque nœud, dans l'ordre, du
   premier au dernier élément de la file.

    Le type `func_t` est défini ainsi : `typedef void (func_t)(int)`.

1. Donnez la définition d'une fonction
   `void ififo_del(struct ififo_s *f)` qui libère la mémoire associée
   à la file et à chacun de ses noeuds.

On testera l'ensemble de ces fonctions sur un code tel

```c
void
print_int(int i)
{
    printf("%d←", i);
}

void
test_fifo_int()
{
    struct ififo_s *fifo;
    int i;

    fifo = ififo_new();

    ififo_enqueue(fifo, 12);   /* → 12 → */
    ififo_enqueue(fifo, 13);   /* → 13 → 12 → */

    ififo_apply(fifo, print_int); putchar('\n');

    ififo_enqueue(fifo, 14);   /* → 14 → 13 → 12 → */
    ififo_dequeue(fifo, &i);   /* 12 & → 14 → 13 → */

    printf("%d \n", i);
    ififo_apply(fifo, print_int); putchar('\n');

    ififo_dequeue(fifo, &i);   /* 13 & → 14 → */
    ififo_dequeue(fifo, &i);   /* 14 & → → */
    ififo_apply(fifo, print_int); putchar('\n');

    ififo_del(fifo);
}
```

→ Files génériques — _exercices de TP_
--------------------------------------

Les exercices précédents ont permis de définir une bibliothèque de
manipulation de files d'entiers. Nous désirons maintenant, sur le même
modèle, définir une bibliothèque de manipulation de files
_génériques_.

Les éléments de ces files sont des valeurs de type `void *`.

L'interface de cette bibliothèque est la suivante (`gfifo` pour
_generic FIFO_) :

```c
struct gfifo_node_s {           /* une cellule de la liste chaînée */
    ...
};
struct gfifo_s {                /* une file */
    ...
};

struct gfifo_s *gfifo_new();
int gfifo_del(struct gfifo_s *f);

int gfifo_size(struct gfifo_s *f);

int gfifo_enqueue(struct gfifo_s *f, void *p);
int gfifo_dequeue(struct gfifo_s *f, void **p);

typedef void (gfunc_t)(void *);
int gfifo_apply(struct gfifo_s *f, gfunc_t *fn);
```

Inspirez-vous du travail réalisé pour la bibliothèque de files
d'entiers pour

1. Proposer des définitions des types et fonctions de cette
   bibliothèque.

1. Proposer une fonction de test minimal de cette bibliothèque.

→ Trier avec des files — _exercices de TP_
------------------------------------------

L'objet de ce dernier exercice est d'utiliser les deux bibliothèques
de manipulation de files d'entiers et de files génériques pour trier
des entiers.

Le principe est le suivant :

* nous manipulons une file (générique) de file d'entiers
* dans chaque file d'entiers, les valeurs sont triées : la plus petite
  en tête de file, la plus grande en fin de file.

Pour trier une collection d'entiers nous procédons ainsi :

* initialement
    - on créée une file d'un entier pour chacune des valeurs à trier
    - ces files d'entiers sont enfilées dans la file générique
* tant que la file générique contient deux files d'entiers ou plus
    - on défile deux files d'entiers
    - on fusionne ces deux files d'entiers triées en une nouvelle file
      d'entiers, elle aussi triée
    - on enfile cette nouvelle file dans la file générique
* il ne reste plus qu'une file d'entiers dans la file générique

Pour mettre en œuvre cet algorithme, vous pouvez écrire une fonction
auxiliaire de fusion de files d'entiers triées :

```c
/* fusion de deux files d'entiers triées.
   condition d'utilisation : f1 et f2 triées
   effet de bord : f1 et f2 vides
   résultat : une nouvelle file d'entiers triée */
struct ififo_s * ififo_merge(struct ififo_s *f1, struct ififo_s *f2)
```

Enfin, afin de tester votre implémentation du tri, vous pouvez lire
une suite d'entiers depuis l'entrée standard et créer une file
générique de files d'entiers à un élément que vous trierez avant
d'afficher l'unique file d'entiers triée résultat.


<!-- pas d'abres pour le moment
→ Arbres (à faire en TP)
----------------------

On se propose de créer des fonctions de manipulation d'un arbre binaire,
dans lequel chaque noeud permet de stocker un int.

Dans un premier temps, on s'intéresse aux fonctions de prototype suivants:

```c
tree_t *construct(tree_t gauche, tree_t droite); /* Fabrique un arbre à partir de deux sous-arbres */
void display(tree_t) /* Parcours l'arbre et affiche la valeur de tous les noeuds */
void destroy(tree_t*) /* Destruction de l'arbre */

```
### Manipulation basique d'arbre — _exercice de TP_ ###

1. Donnez la définition de la fonction `construct()`. Cette fonction prends
en paramètre deux noeuds (gauche et droite) qui représentent chacun un
sous-arbre. Puis elle créé un nouveau noeud qui est parent des noeuds gauche
et droite, et enfin renvoie l'adresse du noeud parent.


2. Donnez la définition de la fonction `display()` qui parcours l'arbre dont
le noeud racine est donné en argument, et parcours la totalité de l'arbre
en affichant les entiers stockés sur chaque noeud. Vous pouvez choisir
l'ordre de parcours que vous voulez.

3. Donnez la définition de la fonction `destroy()` qui libère la mémoire
associée à tout l'arbre (chaque noeud), et mets à NULL le pointeur dont
l'adresse est passée en paramètre.



Un arbre binaire de recherche est un arbre pour lequel la valeur de chaque
noeud est supérieure à la valeur de tous ses sous-noeuds de gauche, et
inférieure à la valeur de tous les sous-noeuds de droite. Par exemple:

```
                10
               /  \
              4    12
             / \   / \
            3   6 11  13

```

On considère qu'il ne peut pas y avoir deux fois la même valeur.


On considère maintenant les fonctions suivantes:
```c
int verify(t_tree);
tree_t search(t_tree);
tree_t insert(t_tree, int val);

```

### Manipulation d'arbres binaire de recherche — _exercice de TP_ ###


1. Réalisez un `main()` qui construit un arbre binaire de recherche (vous
   pouvez utiliser l'exemple donné ci-dessus) à l'aide de la fonction
   `construct()`

2. Donnez le code de la fonction `verify()` qui vérifie que l'arbre passé
   en paramètre respecte les contraintes énoncées ci-dessus correspondantes à
   un arbre binaire de recherche (renvoie 1 si l'argument passé est bien un
   arbre binaire de recherche, 0 dans le cas contraire). Testez ensuite
   votre fonction avec l'arbre construit dans la question précédente.

3. Donnez le code de la fonction `search()` qui permet de rechercher un
   noeud (via son numéro) dans l'arbre binaire de recherche, et qui renvoie
   l'adresse de cet élement, ou bien NULL si aucun élement ne porte le
   numéro demandé.

4. Donnez le code de la fonction `insert()` qui rajoute un nouvel élement
   dans l'arbre binaire de recherche. L'ajout de cet élement doit préserver
   les propriétés de l'arbre binaire de recherche.

5. Dans le `main()`, réalisez un petit menu interactif qui permet à
   l'utilisateur d'ajouter des noeuds dans l'arbre, et de rechercher des
   noeuds. Par exemple:

```
Menu:
1 - Ajouter un noeud
2 - Rechercher un noeud
Choix?

[L'utilisateur tape 1]

Quel est le numéro du noeud à ajouter?

[L'utilisateur tape 42]

Le noeud 42 est ajouté à l'arbre!

Menu:
1 - Ajouter un noeud
2 - Rechercher un noeud
Choix?

[... etc ...]

```

... pas d'arbres -->

<!-- eof -->

<!--  LocalWords:  autoréférentes
 -->
