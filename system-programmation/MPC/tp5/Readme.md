_thème 5_ — Arithmétique des pointeurs
======================================

> [Université de Lille](https://www.univ-lille.fr/)  
> [Licence d'informatique, 2e année](https://portail.fil.univ-lille1.fr/ls4)  
> [Module de MPC — Maîtrise de la Programmation en C](https://portail.fil.univ-lille1.fr/ls4/mpc)  
> Équipe pédagogique de MPC, d'après un document CC BY-SA de Gilles
    Grimaud Philippe Marquet, 2018-2020.  
> mars 2021  
> [CC BY-SA](https://creativecommons.org/licenses/by-sa/4.0/)

Une version PDF pour impression est accessible sur [nextcloud.univ-lille.fr](https://nextcloud.univ-lille.fr/index.php/s/zrWxCeBefzewJWH)

Ce document est le support d'exercices de TD et TP.

Le thème 5 traite de l'arithmétique des pointeurs, de l'équivalence
tableaux/pointeurs, de la représentation des tableaux à plusieurs
dimensions, de la possible généricité des pointeurs `void *`, et enfin
des pointeurs de fonctions.

## Membres du binôme

Le rendu du TP *theme5* des étudiants Aziz **_BOURAHMA_** et Benedictus Kent **_RACHMAT_**, groupe 6 du S4 Licence 2 Informatique.

**_Veuillez consulter la commande bash de chaque question de TP_**

→ Adresses et arithmétique de pointeurs
---------------------------------------

### Adresses mémoires des types construits — _exercice de TD_ ###

Soit le programme `tab_duo_trio.c` suivant

```c
 #include <stdlib.h>
 #include <stdio.h>

 struct duo_s {
     int x;
     int y;
 };

 union trio_u {
     int n;
     char c;
     float x;
 };

 int
 main()
 {
     int tab[10];
     struct duo_s duo;
     union trio_u trio;

     printf("sizeof(int) : %lu\n", sizeof(int));
     printf("tab :\t%p\n", tab);
     printf("&duo :\t%p\n", &duo);
     printf("&trio :\t%p\n", &trio);

     exit(EXIT_SUCCESS);
 }
```

dont une exécution produit :

```console
% ./tab_duo_trio
sizeof(int) : 4
tab :   0x7ffeebe6a6d0
&duo :  0x7ffeebe6a6c8
&trio : 0x7ffeebe6a6c0
```

1. Quelle est l'adresse du champ `duo.x` ? `duo.y` ?

1. Quelle est l'adresse du champ `trio.n` ? `trio.c` ? `trio.x` ?

1. Quelle est l'adresse de l'élément `tab[5]` du tableau ?   
   De manière générale, quelle est l'adresse de l'élément `tab[i]` ?

1. Soit la déclaration et initialisation :

    ```c
    int *ptr;
    ptr = tab;
    ```

    En utilisant uniquement la variable `ptr`, et l'arithmétique des
    pointeurs, proposez une boucle qui va écrire la valeur `0` dans
    toutes les cases du tableau.

### Pointeurs et chaînes de caractères — _exercice de TD_ ###

1. Écrire une fonction `char *strend(char *str)` qui renvoie
   l'adresse du zéro terminal de la chaîne `str`.

1. (Pourquoi le prototype de cette fonction ne peut-il être
   `char *strend(const char *str)` ?)

1. En utilisant la fonction `strend()`, proposez une fonction qui
   renvoie le nombre de caractères d'une chaîne donnée - le caractère
   `'\0'` final non compris :

    ```c
    int mstrlen(const char *str);
    ```

    _Pour information_. Cette fonction `mstrlen()` est similaire à la
    fonction `strlen()` fournie par la bibliothèque standard
    `string.h`.

1. La bibliothèque `string.h` fournie également les fonctions suivantes :

    ```c
    /* recopie le contenu de src dans dest
       renvoie dest */
    char *strcpy(char *dest, const char *src);

    /* recopie src à la fin de dest (concat)
       renvoie dest */
    char *strcat(char *dest, const char *src);
    ```

    Proposez des fonctions `mstrcpy()` et `mstrcat()`, réécritures de
    ces fonctions.

### Autour des variables d'environnement – _exercice de TP_ ###

``` bash
../theme5-g6-y21> make mprintenv
../theme5-g6-y21> ./module/mprintenv {VOTRE OPTION}
```

La variable globale

```c
extern char **environ;
```

est un pointeur sur le premier élément d'un tableau.  
Chacun des éléments de ce tableau est une chaîne de caractères, donc
un pointeur `char *`.  
Une valeur `(char *) 0` indique la fin du tableau.

Chacune des chaînes est de la forme `"VAR=valeur"`, `VAR` correspondant
à une des variables d'environnement. Cette variable `environ` est par
exemple exploitée par la commande Unix `printenv` qui affiche
l'ensemble des variables d'environnement et leur valeur :

```console
% printenv | head
SHELL=/bin/bash
LANGUAGE=en_US:
PWD=/home/l2/duchmol
LOGNAME=duchmol
HOME=/home/l2/duchmol
LANG=en_US.UTF-8
TERM=xterm-256color
USER=duchmol
PATH=/usr/local/sbin:/usr/local/bin:/usr/sbin:/usr/bin:/sbin:/bin
SSH_TTY=/dev/pts/0
```

1. Proposez une fonction `nvar()` qui exploite cette variable
   `environ` et renvoie le nombre de variables d'environnement
   définies.

    Proposez un programme qui fait appel à cette fonction et affiche
    ce nombre de variables.

    On pourra comparer le résultat avec celui fourni par la commande

    ```console
    % printenv | wc -l
    ```

1. Proposez une commande `mprintenv` qui reproduit le comportement de
   `printenv`.

1. La commande `printenv` admet des paramètres qu'elle considère comme
   des noms de variables d'environnement donc elle affiche la valeur :

    ```console
    % printenv USER SHELL
    duchmol
    /bin/bash
    ```

    Proposez une nouvelle version de votre commande `mprintenv` pour
    inclure cette fonctionnalité.

### Nouvelle recherche dichotomique — _exercice de TP_ ###

``` bash
../theme5-g6-y21> make dichotomique
../theme5-g6-y21> ./module/dichotomique
```

1. Proposez une fonction récursive qui recherche par dichotomie une
   valeur `v` dans un tableau trié `tab` :

    ```c
    float * search_interval(float v, const float *tab, const float *end);
    ```

    L'argument `end` pointe sur le dernier élément du tableau.

1. On pourra reprendre le programme principal utilisé pour la fonction
   `search_dicho()` du TP précédent, _thème 4_ pour valider cette
   nouvelle fonction de recherche.


→ Pointeurs génériques `void *`
-------------------------------

### Mise à zéro — _exercice de TD_ ###

1. Proposez une fonction "générique" qui prend l'adresse d'un entier de
   type non spécifié, ainsi que sa taille, et met cet entier à zéro :

   ```c
   void memzero(void *addr, unsigned int size);
   ```

   Exemples d'utilisations de la fonction :

   ```c
   int a;
   short int b;
   memzero(&a, sizeof(int));            /* équivalent à a = 0 */
   memzero(&b, sizeof(short int));      /* équivalent à b = 0 */
   ```

1. Cette fonction peut-elle être utilisée pour mettre à zéro
   l'ensemble des valeurs d'un tableau, par exemple :

   ```c
   #define MAX    128
   int t[MAX];
   ```

_Pour information._ La bibliothèque `string.h` propose une fonction
`memset()` qui est généralement utilisée pour mettre à zéro une zone
mémoire donnée.

### Comparaison générique — _exercice de TD_ ###

L'objet de l'exercice est de proposer une fonction `mmemcmp()` qui
compare deux zones mémoire octet par octet.

1. Donnez un prototype possible pour cette fonction.

1. Donnez une définition de cette fonction.  
   La fonction renverra une valeur nulle si et seulement si les deux
   zones sont égales.

_Pour information._ La bibliothèque `string.h` propose une fonction
`memcmp()` qui réalise une telle comparaison.

### Copie générique — _exercice de TP_ ###

``` bash
../theme5-g6-y21> make generique
../theme5-g6-y21> ./module/generique
```

1. Proposez une fonction qui copie un objet d'adresse `from`, de type
   non spécifié, et de taille `size`, à l'adresse `to` :

   ```c
   void mmemcpy(void *to, const void *from, unsigned int size);
   ```

2. Testez votre proposition, par exemple avec le code suivant

   ```c
   #include <stdlib.h>             /* pour random() */
   #include <string.h>             /* pour memcmp() */
   #include <assert.h>             /* pour assert() */

   #define SIZE    1021

   void
   test_mmemcpy()
   {
       char     tc_orig[SIZE], tc_dest[SIZE];
       long int ti_orig[SIZE], ti_dest[SIZE];
       int i;

       /* initialisation */
       for(i=0 ; i<SIZE ; i++) {
           tc_orig[i] = random() % 256;
           tc_dest[i] = random() % 256;
           ti_orig[i] = random();
           ti_dest[i] = random();
       }

       /* copie */
       mmemcpy(tc_dest, tc_orig, SIZE);
       mmemcpy(ti_dest, ti_orig, SIZE * sizeof(long int));

       /* vérification */
       assert(memcmp(tc_orig, tc_dest, SIZE) == 0);
       assert(memcmp(ti_orig, ti_dest, SIZE * sizeof(long int)) == 0);
   }
   ```

_Pour information._ La bibliothèque `string.h` propose une fonction
`memcpy()` qui réalise une telle copie.

### Échange générique — _exercice de TP_ ###

``` bash
../theme5-g6-y21> make generique
../theme5-g6-y21> ./module/generique
```

1. Proposez une fonction `memswap()` qui réalise un échange générique
   entre deux variables de même taille.  
   Dans un premier temps, on pourra supposer que les deux objets ne se
   recouvrent pas en mémoire.

2. Testez votre proposition avec une fonction semblable à celle
   proposée pour l'exercice précédent.

→ Tableaux à plusieurs dimensions
---------------------------------

### Arrangement mémoire des éléments d'un tableau à plusieurs dimensions — _exercice de TD_ ###

Soit la déclaration d'un tableau

```c
int b[3][5];
```

En considérant que l'allocation du tableau se fait linéairement en
mémoire (les 3 "tranches" de `b` sont allouées à des adresses contiguës),
donnez l'état du tableau `b` après
l'exécution du code C suivant :

```c
int b[3][5];
int *a = *b, i;

for (i=0 ; i<15 ; *a++ = i++)
    ;
**b = 15;           **(b+1) = 16;        *(b[0]+1) = 17;
*(*b+8) = 18;       *(b[1]+2) = 19;      *(*(b+1)+5) = 20;
*(b[2]+3) = 21;     *(*(b+2)+2) = 22;
```

→ Pointeurs de fonction
-----------------------

### Appliquer une fonction à un tableau d'entiers — _exercice de TD_ ###

Soit `func_t` le type des fonctions à un paramètre entier qui
renvoient une valeur entière :

```c
typedef int (func_t)(int);
```

L'objet de la fonction `map_int()` que nous allons écrire est
d'appliquer une fonction de type `func_t` à chacun des éléments d'un
tableau d'entiers.  
Le résultat sera produit dans autre tableau d'entiers.

1. Étant données cette fonction `map_int()` qui produit dans le tableau
   `res` l'application de la fonction `f` à chacun des `size` éléments
   du tableau `arg` :

   ```c
   void map_int(const int arg[], int res[], unsigned int size, func_t *f);
   ```

   et les définitions suivantes :

   ```c
   #define MAX      128

   int t[MAX], t2[MAX];

   int
   carre(int n)
   {
       return n*n;
   }
   ```

   En supposant le tableau `t` initialisé, donnez le code d'un appel
   à `map_int()` pour produire dans  `t2` les carrés des valeurs de
   `t`.

1. Proposez une définition de `map_int()`.

### Filtrer les éléments d'un tableau d'entiers — _exercice de TP_ ###

``` bash
../theme5-g6-y21> make filter
../theme5-g6-y21> ./module/filter
```

Étant donnée une fonction `f` à un paramètre entier qui renvoie une
valeur entière, la fonction `filter_int()` recopie certains éléments
d'un tableau d'entiers source dans un tableau d'entiers destination.  
Les éléments `e` recopiés sont ceux pour lesquels `f(e)` est non nul.

1. Proposez un prototype de la fonction `filter_int()`.

2. Proposez une définition de la fonction `filter_int()`.

3. Proposez une programme de test qui utilise `filter_int()`pour
   recopier uniquement les entiers pairs d'un tableau tableau source
   vers un tableau destination.

### Map générique — _exercice de TD_ ###

La fonction `map_gen()` est une version générique de la fonction
`map_int()`, capable de traiter des tableaux de type quelconque.

Cette fonction `map_gen()` utilise une fonction de type `funcgen_t`
pour appliquer une opération sur chaque élément d'un tableau :

```c
typedef void (funcgen_t)(const void*, void*);
```

Le principe d'une telle fonction est de considérer son premier
paramètre comme une référence vers un argument, et renvoyer son
résultat via le second paramètre.

Le prototype de `map_gen()` est le suivant :

```c
void map_gen(const void *arg, void *res, unsigned int nbelem, unsigned int elemsize, funcgen_t *f);
```

Les paramètres `arg` et `res` sont des tableaux de `nbelem` élements,
chaque éléments occupe `elemsize` octets.  
La fonction produit la valeur `f(arg[i])` dans l'élément `i` du
tableau `res`.

1. Soient les définitions suivantes :

   ```c
   #define MAX      128

   int t[MAX], t2[MAX];

   void
   carre_int_v1(const void *src, void *dst)
   {
       int* src_int = (int*) src;
       int* dst_int = (int*) dst;
       *dst_int = *src_int * *src_int;
   }
   ```

   En supposant le tableau `t` initialisé, donnez le code d'un appel
   à `map_gen()` pour produire dans  `t2` les carrés des valeurs de
   `t`.

1. Faites de même en utilisant maintenant la fonction suivante :

   ```c
   void
   carre_int_v2(const int *src, int *dst)
   {
       *dst = *src * *src;
   }
   ```

1. Donnez la définition de fonctions et le code permettant de produire
   un tableaux des carrés de valeur de type `float`.

1. Proposez une définition de la fonction `map_gen()`.


...
---

_suite à venir..._

<!-- eof -->
