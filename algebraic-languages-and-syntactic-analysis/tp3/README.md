# TP3 LAAS S6 - Un analyseur ascendant en Python : sly.Parser

## Membres du groupe

Ce travail est à réaliser en équipe dont les membres sont (**S6 Licence 3 Informatique**) :

- BENEDICTUS KENT **RACHMAT**
- HICHEM **KARFA**

## Arborescence du projet

```
.
├── README.md
├── anbn.py
├── exo1.py
├── exo2.py
├── exo2_1.py
├── exo2_2.py
├── exo3.py
├── exo4.py
├── exo5.py
├── fns.py
└── lexer_td2.py

0 directories, 11 files
```

## Questions

### 1.1 Exemple très simple, sans action sémantique

Dans l'exemple de code nous pouvons voir que le résultat affiche la valeur de `None` si la lettre respecte les règles et déclenche une exception en cas contraire.

```python
$ python3 anbn.py
```

exemple de résultat :

```bash
test a^n b^n > aabb
None
test a^n b^n > ab
None
test a^n b^n > aaab
sly: Parse error in input. EOF
None
test a^n b^n > abb
sly: Syntax error at line 1, token=B
None
```

### 2 Calculs d’attributs

Après quelques modifications sur le fichier nous pouvons maintenant voir le nombre d'occurrences de laa régle `s` qui a été appelé.

```python
$ python3 anbn.py
```

exemple de résultat :

```
test a^n b^n >
0
test a^n b^n > aabb
2
test a^n b^n > aaaabbbb
4
test a^n b^n > abb
sly: Syntax error at line 1, token=B
0
```

### 3 Exercices

#### Exercise 1

Q1) le code est bien implémenté dans `exo1.py`, la réponse pour toutes les expressions sera None car le but de cet exercice est de montrer que l'expression donnée est valide ou non. Pour exécuter le fichier veuillez utiliser cette commande :

```python
$ python3 exo1.py
```

exemple de résultat :

```
test exo1 > 12
None
test exo1 > g(12)
None
test exo1 > f(12,13)
None
test exo1 > f(14,g(12))
None
test exo1 > g(g(1))
None
test exo1 > g(f(1,2))
None
test exo1 > f(g(f(1,2)),f(3,4))
None
test exo1 > f
sly: Parse error in input. EOF
None
```

Q2) le code est bien implémenté dans `exo2.py`, le résultat sera le nombre de constantes figurant dans l’expression. pour exécuter le fichier veuillez utiliser cette commande :

```python
$ python3 exo2.py
```

exemple de résultat :

```
test exo2 > 12
1
test exo2 > g(12)
1
test exo2 > f(12,13)
2
test exo2 > f(14,g(12))
2
test exo2 > g(g(1))
1
test exo2 > g(f(1,2))
2
test exo2 > f(g(f(1,2)),f(3,4))
4
test exo2 > f
sly: Parse error in input. EOF
None
```

Q3) le code est bien implémenté dans `exo3.py`, le résultat sera le résultat du calcul de l'expression, la fonction f est la somme de 2 paramètres et la fonction g est le paramètre multiplié par 3. pour exécuter le fichier veuillez utiliser cette commande :

```python
$ python3 exo3.py
```

exemple de résultat :

```
test exo3 > 12
12
test exo3 > g(12)
36
test exo3 > f(12,13)
25
test exo3 > f(14,g(12))
50
test exo3 > g(g(1))
9
test exo3 > g(f(1,2))
9
test exo3 > f(g(f(1,2)),f(3,4))
16
test exo3 > f
sly: Parse error in input. EOF
None
```

Q4) le code est bien implémenté dans `exo4.py`, le résultat sera le nombre maximal de niveaux d’imbrication des appels de fonctions. Pour exécuter le fichier veuillez utiliser cette commande :

```python
$ python3 exo4.py
```

exemple de résultat :

```
test exo4 > 12
0
test exo4 > g(12)
1
test exo4 > f(13,14)
1
test exo4 > f(g(14),g(38))
2
test exo4 > g(g(3))
2
test exo4 > f(2,g(3))
2
test exo4 > f(g(f(1,2)),f(3,4))
3
test exo4 > f
sly: Parse error in input. EOF
None
```

Q5) le code est bien implémenté dans `exo5.py`, le résultat sera une liste de longueur 3 :

- le premier élément est le nombre de constantes figurant dans l’expression.
- le deuxième élément est le résultat de l’expression.
- le troisième le nombre maximal de niveaux d’imbrication des appels de fonctions.

Pour exécuter le fichier veuillez utiliser cette commande :

```python
$ python3 exo5.py
```

exemple de résultat :

```
test exo5 > 12
[1, 12, 0]
test exo5 > g(12)
[1, 36, 1]
test exo5 > f(12,13)
[2, 25, 1]
test exo5 > f(14,g(12))
[2, 50, 2]
test exo5 > g(g(1))
[1, 9, 2]
test exo5 > g(f(1,2))
[2, 9, 2]
test exo5 > f(g(f(1,2)),f(3,4))
[4, 16, 3]
test exo5 > f
sly: Parse error in input. EOF
None
```

#### Exercise 2

Q1) le code est bien implémenté dans `exo2_1.py`, la réponse pour toutes les expressions sera None car le but de cet exercice est de montrer que l'expression donnée est valide ou non (avec lexer du TDM2). Pour exécuter le fichier veuillez utiliser cette commande :

```python
$ python3 exo2_1.py
```

```
test exo2.1 > (!true || !false) && true
None
test exo2.1 > true && false && !true || false
None
test exo2.1 > false && !(false && true)
None
test exo2.1 > a && b
None
test exo2.1 > a && b && !c || !d
None
test exo2.1 > a || &&
sly: Syntax error at line 1, token=AND
None
```

Q2) le code est bien implémenté dans `exo2_2.py`, la réponse sera booléenne en fonction de l'expression, par défaut les valeurs des lettres sont False. Pour exécuter le fichier veuillez utiliser cette commande :

```python
$ python3 exo2_2.py
```

```
test exo2.2 > (!true || !false) && true
True
test exo2.2 > true && false && !true || false
False
test exo2.2 > false && !(false && true)
False
test exo2.2 > a && b
False
test exo2.2 > a && b && !c || !d
True
test exo2.2 > a || &&
sly: Syntax error at line 1, token=AND
None
```
