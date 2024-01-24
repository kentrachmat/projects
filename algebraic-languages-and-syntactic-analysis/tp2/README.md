# TP2 LAAS S6 - Analyse récursive descendante - suite

## Membres du groupe

Ce travail est à réaliser en équipe dont les membres sont (**S6 Licence 3 Informatique**) :

- BENEDICTUS KENT **RACHMAT**
- HICHEM **KARFA**

## Arborescence du projet

```
.
├── Exobool_ard.py
├── Exobool_eval.py
├── Exobool_postfix.py
├── README.md
├── ard.py
└── lexer.py

1 directory, 6 files
```

## Questions

### 1 La grammaire et sa transformation

les règles ont été mises en œuvre correctement.

### 2 L’analyseur lexical

le code est bien implémenté, par défaut la valeur est `a && b || c !d (true) (false)` mais si vous souhaitez saisir manuellement, veuillez commenter la ligne **22** et décommenter la ligne **21**. Pour lancer le code veuillez utiliser cette commande :

```python
$ python3 lexer.py
```

exemple de résultat :

```bash
Mot à analyser :  a && b || c !d (true) (false)
type='IDENT', value='a'
type='AND', value='&&'
type='IDENT', value='b'
type='OR', value='||'
type='IDENT', value='c'
type='NOT', value='!'
type='IDENT', value='d'
type='OPEN_BRACKET', value='('
type='CONSTANT', value='true'
type='CLOSE_BRACKET', value=')'
type='OPEN_BRACKET', value='('
type='CONSTANT', value='false'
type='CLOSE_BRACKET', value=')'
```

### 3 L’analyseur syntaxique LL(1)

le code est bien implémenté, par défaut la valeur est `true && false || !true` mais si vous souhaitez saisir manuellement, veuillez commenter la ligne **63** et décommenter la ligne **62**. Pour lancer le code veuillez utiliser cette commande :

```python
$ python3 Exobool_ard.py
```

exemple de résultat :

```bash
Mot à analyser : true && false || !true
Résultat : None
```

### 4 Action sémantique 1 : évaluation d’expression

le code est bien implémenté, par défaut la valeur est `(true || (!false || true)) && false` mais si vous souhaitez saisir manuellement, veuillez commenter la ligne **67** et décommenter la ligne **66**. Pour lancer le code veuillez utiliser cette commande :

```python
$ python3 Exobool_eval.py
```

exemple de résultat :

```bash
Mot à analyser : (true || (!false || true)) && false
Résultat : False
```

### 5 Action sémantique 2 : traduction d’expression

le code est bien implémenté, par défaut la valeur est `(a||b)&&(c||d)` mais si vous souhaitez saisir manuellement, veuillez commenter la ligne **69** et décommenter la ligne **68**. Pour lancer le code veuillez utiliser cette commande :

```python
$ python3 Exobool_postfix.py
```

exemple de résultat :

```bash
Mot à analyser : (a||b)&&(c||d)
Résultat : a b or c d or and
```
