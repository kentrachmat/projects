ls_thème 6_ — Allocations
=======================

> [Université de Lille](https://www.univ-lille.fr/)  
> [Licence d'informatique, 2e année](https://portail.fil.univ-lille1.fr/ls4)  
> [Module de MPC — Maîtrise de la Programmation en C](https://portail.fil.univ-lille1.fr/ls4/mpc)  
> Équipe pédagogique de MPC, avec des emprunts à des documents CC-BY de Philippe Marquet, 2004-2018.  
> avril 2021  
> [CC BY](https://creativecommons.org/licenses/by/4.0/)

Une version PDF pour impression est accessible sur [nextcloud.univ-lille.fr](https://nextcloud.univ-lille.fr/index.php/s/X38zSGRywjZgEyS). 

Ce document est le support d'exercices de TD et TP.

Le thème 6 traite des classes d'allocations, et de l'allocation dynamique de
mémoire. 

## Membres du binôme

Le rendu du TP *theme5* des étudiants Aziz **_BOURAHMA_** et Benedictus Kent **_RACHMAT_**, groupe 6 du S4 Licence 2 Informatique.

**_Veuillez consulter la commande bash de chaque question de TP_**


→ Classe d'allocation, portée
-----------------------------

### Classe d'allocations des variables ? — _exercice de TD_ ###

Soit le programme :

```c
int var1;
static int var2;
 
void
foo(int var5)
{
    int var6;
    static int var7;
}

int
main()
{
    int var3;
    static int var4;

    foo(42);
   
    return 0;
 }
```

1. Pour chacune des variables, indiquez quelle est sa classe
   d'allocation : statique, ou automatique. 

### Quelques portées et allocations — _exercice de TD_ ###

1. Quelle affichage produit l'exécution du programme suivant :

    ```c
    #include <stdio.h>
    
    int var = 42;
    
    void
    f(int var)
    {   
        printf("f > var %d\n", var);
        var = 271;
    }
    
    void
    g(int arg)
    {    
        printf("g > arg %d\n", arg);
        printf("g > var %d\n", var);
        var = 141;
    }
    
    int
    main()
    {
        int var;
    
        var = 314;
    
        f(var);
        g(var);
        f(var);
        g(var);
    
        printf("main > var %d\n", var);
    }
    ```

1. De même pour ce programme :

    ```c
    #include <stdio.h>
    
    static int g = 42;
    
    void
    f(int a)
    {
        static int s = 314; 
        int l = 141;
    
        printf("f > a %d\n", a);
    
        g++ ; s++ ; l++ ;    
        printf("f > g, s, l %d, %d, %d\n", g, s, l);
        
        if (a > 10)
            f(a/10);
    
        printf("f > a %d\n", a);    
    }
    
    int
    main()
    {
        static int s = 314; 
        int l = 141;
    
        f(l); 
    
        g++ ; s++ ; l++ ;    
        printf("main > g, s, l %d, %d, %d\n", g, s, l);
    
    }
    ```

### Compteur — _exercice de TP_ ###
``` bash
../theme6-g6-y21> make compteur
../theme6-g6-y21> ./module/compteur
```
L'objectif est de proposer une une fonction de prototype `int
compteur()` qui renvoie 0 la première fois qu'elle est appelée, puis
ensuite 1, ensuite 2, et ainsi de suite. 

1. Donnez une définition de la fonction `compteur()` 

1. Réalisez une fonction `main()` qui teste la fonction `compteur()`,
   par exemple en l'appelant 10 fois dans une boucle, et en affichant
   la valeur renvoyée.

→ Allocation dynamique
----------------------

### Construire un tableau — _exercice de TD_ ###

Soit le programme _incorrect_ suivant :
```c
/* Renvoie un tableau à valeurs: {0, 1, 2, ..., 9} */
int *
creer_sequence_10()
{
    int t[10];
    int i;
    for (i=0; i<10; i++)
        t[i] = i;

    return t;
}

int
main(void)
{
    int *tab;
    int i;
    tab = creer_sequence_10();
    for (i=0; i<10; i++)
        printf("%d\n", tab[i]);
}
```

1. Pourquoi ce programme est-il incorrect ?

1. Proposez une correction de ce programme utilisant `malloc()` et `free()`.

### Dupliquer une chaîne — _exercice de TP_ ###
``` bash
../theme6-g6-y21> make dupliquer
../theme6-g6-y21> ./module/dupliquer {VOTRE OPTIONS}
```

Soit la fonction de prototype:

```c
char *mstrdup(const char *str);
```

qui renvoie une copie allouée dynamiquement de la chaîne de caractères
`str`. 

1. Donnez une définition de cette fonction `mstrdup()`. Que renvoie cette
   fonction en cas de problème ? 

1. Réalisez un `main()` pour tester votre fonction `mstrdup()`. Par
   exemple copiez une à une les chaînes de caractères de `argv` et
   affichez-les. Pensez à libérer avec `free()` la mémoire allouée.

### Noms temporaires — _exercice de TP_ ###

``` bash
../theme6-g6-y21> make nom_temp
../theme6-g6-y21> ./module/nom_temp
```

On souhaite définir une fonction `nom_temp()` qui retourne à chaque
   invocation une chaîne de caractères différente, par exemple en vue de 
   l'utiliser comme un nom de fichier temporaire. On pourra par exemple
   utiliser le format suivant : `fileN`, avec `N` un numéro associéé au
   fichier (ex : `file0`, `file1`, `file42`, etc.).

1. Écrire le prototype d'une telle fonction.
2. Sur quels types d'allocation mémoire peut-on s'appuyer pour allouer la mémoire de cette chaîne de caractères ?
3. Pour chaque type d'allocation, donner la définition de la fonction.
4. Tester chaque version de la fonction avec le code suivant :
```c
#include <stdio.h>

void test_nom_temp() {
    char *file1, *file2, *file3;

    file1 = nom_temp();
    file2 = nom_temp();
    file3 = nom_temp();

    printf("Fichier 1 : %s\n", file1);
    printf("Fichier 2 : %s\n", file2);
    printf("Fichier 3 : %s\n", file3);
}
```
Quel est l'impact du type d'allocation employé sur le résultat affiché par cette fonction de test ? Expliquer le phénomène observé.

5. Modifier (quand nécessaire) la fonction de test pour tenir compte des bonnes pratiques.

### Allocation, libération de structures — _exercice de TD_ ###

Soit les déclarations suivantes :

```c
struct point_s {
    int p_x;
    int p_y;
};
struct point_s *new_point(int x, int y);
void free_point(struct point_s *p);
```

La fonction `new_point()` alloue et renvoie l'adresse d'une nouvelle
structure de type `struct point_s`, dont les champs sont initialisés
avec les valeurs des paramètres `x` et `y`.  
La fonction `free_point()` se charge de libérer la mémoire allouée
correspondante à `p`. 

1. Donnez une définition de la fonction `new_point()`.

2. Donnez une définition de la fonction `free_point()`.

Soient les déclarations suivantes :

```c
struct etudiant_s {
    char *etu_nom;
    char *etu_prenom;
    int   etu_no_etudiant;
}; 
struct etudiant_s *new_etudiant(const char *nom, const char *prenom, int no_etudiant);
void free_etudiant(struct etudiant_s *etu);
```

La fonction `new_etudiant()` alloue et renvoie l'adresse d'une
nouvelle structure de type `struct etudiant_s`.   
Les champs `etu_nom` et `etu_prenom` sont initialisés à l'aide d'une
*copie* des valeurs des paramètres `nom` et `prenom`.

La fonction `free_etudiant()` se charge de libérer la mémoire allouée
correspondante à `e`. 

3. Donnez une définition de la fonction `new_etudiant()`.

4. Donnez une définition de la fonction `free_etudiant()`. 

5. Proposez une modification du type `struct etudiant_s` et des
   fonctions d'allocation et désallocation associées pour associer
   à un étudiant un certain nombre de notes (valeurs de type `int` par
   exemple).

### Tableaux grandissants — _exercice de TP_ ###

``` bash
../theme6-g6-y21/tableaux_grandissants> make test
```

Les tableaux en C sont de taille fixe. L'objet de cet exercice est de
développer une structure de données _tableaux grandissants_ qui
autorise :

* la mémorisation d'une séquence d'éléments (ici de type `int`), 
* des accès indexés à partir de zéro à ces éléments, à la fois en
  lecture – obtenir la valeur de l'élément –, et en écriture
  – modifier la valeur de l'élément –, 
* l'allocation automatique de la mémoire nécessaire, au fur et
  à mesure que des écritures d'éléments sont réalisés.

L'interface de cette structure de données est la suivante :

```c
int ga_set(struct ga_s *ga, unsigned int index, int val);
int ga_get(struct ga_s *ga, unsigned int index, int *val);
int ga_new(struct ga_s *ga); 
int ga_del(struct ga_s *ga); 
```

* les fonctions `ga_new()` et `ga_del()` permettent de créer,
  respectivement désallouer, un tableau grandissant,
* les fonctions `ga_set()` et `ga_get()` permettent d'accéder en
  écriture, respectivement en lecture, à un élément du tableau. 

Ces fonctions retournent une valeur non nulle en cas d'erreur.

L'implantation d'une telle structure de données peut reposer sur un
tableau de valeurs entières dont on conserve la taille. La mémoire
associée au tableau est allouée dynamiquement, réallouée si nécessaire
en fonction des accès en écriture au delà de la taille actuelle.

Dans un premier temps, on pourra utiliser une structure comme

```c
struct ga_s {
    unsigned int ga_size;             /* nombre d'éléments alloués */
    int *ga_elements;                 /* les éléments */
}; 
```

1. Proposez une définition des quatre fonctions.

    Vous travaillerez dans les ficheirs `libga.h` et `libga.c` du
    répertoire `tableaux_grandissants/` de votre dépôt git. 

1. Testez votre proposition à l'aide du programme de test se trouvant
   dans le dossier `tableaux_grandissants/` de votre dépôt git,
   à l'aide de la cible `test` du Makefile fourni.

<!-- 
Un mot sur une progression géométrique, par exemple de raison 2, de la
taille de la zone mémoire allouée ? Et aussi de distinguer taille du
ga (max des index écrits) et taille de la zone mémoire ? 
-->

<!-- eof --> 
