# TP1 LAAS S6 - Analyse récursive descendante

## Membres du groupe

Ce travail est à réaliser en équipe dont les membres sont (**S6 Licence 3 Informatique**) :

- BENEDICTUS KENT **RACHMAT**
- HICHEM **KARFA**

## Arborescence du projet

```
.
├── README.md
├── analyseur.py
├── analyseurV2.py
├── ard.py
├── ard_elementaire.py
└── lexer.py

1 directory, 6 files
```

## Questions

### 2.1 Préparer un analyseur lexical

le code est bien implémenté, par défaut la valeur est `(a(bc)2)3(ba)2` mais si vous souhaitez saisir manuellement, veuillez commenter la ligne **26** et décommenter la ligne **25**. Pour lancer le code veuillez utiliser cette commande :

```python
$ python3 lexer.py
```

exemple de résultat :

```bash
Mot à analyser :  (a(bc)2)3(ba)2
token -> type: OUVRANTE, valeur: ( (<class 'str'>), ligne : 1
token -> type: LETTRE, valeur: a (<class 'str'>), ligne : 1
token -> type: OUVRANTE, valeur: ( (<class 'str'>), ligne : 1
token -> type: LETTRE, valeur: b (<class 'str'>), ligne : 1
token -> type: LETTRE, valeur: c (<class 'str'>), ligne : 1
token -> type: FERMANTE, valeur: ) (<class 'str'>), ligne : 1
token -> type: ENTIER, valeur: 2 (<class 'int'>), ligne : 1
token -> type: FERMANTE, valeur: ) (<class 'str'>), ligne : 1
token -> type: ENTIER, valeur: 3 (<class 'int'>), ligne : 1
token -> type: OUVRANTE, valeur: ( (<class 'str'>), ligne : 1
token -> type: LETTRE, valeur: b (<class 'str'>), ligne : 1
token -> type: LETTRE, valeur: a (<class 'str'>), ligne : 1
token -> type: FERMANTE, valeur: ) (<class 'str'>), ligne : 1
token -> type: ENTIER, valeur: 2 (<class 'int'>), ligne : 1
```

### 2.2 La grammaire

les règles ont été mises en œuvre correctement

### 2.3 Construire un analyseur simple

le code est bien implémenté, par défaut la valeur est `(a)b` mais si vous souhaitez saisir manuellement, veuillez commenter la ligne **62** et décommenter la ligne **61**. Pour lancer le code veuillez utiliser cette commande :

```python
$ python3 analyseur.py
```

exemple de résultat :

```bash
Mot à analyser :  (a)b
S->ERS Token(type='OUVRANTE', value='(', lineno=1, index=0) 0
E->(S) Token(type='OUVRANTE', value='(', lineno=1, index=0) 0
S->ERS Token(type='LETTRE', value='a', lineno=1, index=1) 1
E->LETTRE Token(type='LETTRE', value='a', lineno=1, index=1) 1
R->epsilon Token(type='FERMANTE', value=')', lineno=1, index=2) 2
S->epsilon Token(type='FERMANTE', value=')', lineno=1, index=2) 2
R->epsilon Token(type='LETTRE', value='b', lineno=1, index=3) 3
S->ERS Token(type='LETTRE', value='b', lineno=1, index=3) 3
E->LETTRE Token(type='LETTRE', value='b', lineno=1, index=3) 3
R->epsilon <EOD> None
S->epsilon <EOD> None
```

### 2.4 Ajouter des actions sémantiques

le code est bien implémenté, par défaut la valeur est `(a(bc)2)3(ba)2` mais si vous souhaitez saisir manuellement, veuillez commenter la ligne **58** et décommenter la ligne **57**. Pour lancer le code veuillez utiliser cette commande :

```python
$ python3 analyseurV2.py
```

exemple de résultat :

```bash
Mot à analyser :  (a(bc)2)3(ba)2
abcbcabcbcabcbcbaba
```
