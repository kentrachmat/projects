# TDM4 - LAAS

## Membres du groupe

Ce travail est à réaliser en équipe dont les membres sont (**S6 Licence 3 Informatique**) :

- BENEDICTUS KENT **RACHMAT**
- HICHEM **KARFA**

## Arborescence du projet

```
.
├── README.md
├── jade1_trad.py
├── jade_to_py.py
├── jadelexer.py
├── jademachine.py
├── jadeparser.py
└── test
    ├── jade1.txt
    └── jade2.txt

1 directory, 8 files
```

## Questions

### Exercice 1 :

le code est bien implémenté dans `jadeparser.py`, une fois que vous avez lancé le code une traduction en code python apparaîtra dans la console. En utilisant les exemples donnés dans le dossier `test` on peut voir que la fonction fonctionne parfaitement :

- `python3 jade_to_py.py test/jade1.txt show` : pour exécuter le code python avec la fonctionnalité de tortue

```bash
$ python3 jade_to_py.py test/jade1.txt
import jademachine
jm = jademachine.JadeMachine()
s = jm.myturtle.getscreen()
jm.exec0("pendown")
jm.exec1("setstep", 20)
while ( jm.posx < 200 ) :
        jm.exec0("east")
        if ( jm.posx < 100 ) :
                jm.exec0("north")
        else :
                jm.exec0("south")

jm.myturtle.hideturtle()
s.exitonclick()
```

- `python3 jade_to_py.py test/jade2.txt show` : pour exécuter le code python avec la fonctionnalité de tortue

```bash
$ python3 jade_to_py.py test/jade2.txt

import jademachine
jm = jademachine.JadeMachine()
s = jm.myturtle.getscreen()
jm.exec0("east")
jm.exec0("north")
jm.exec0("west")
jm.exec0("south")

jm.myturtle.hideturtle()
s.exitonclick()
```

- Exemple Supplémentaire

```bash
$ python3 jadeparser.py
jade > pendown setstep 10 while (posx < 150){ east if (posx < 150) north if (posx < 50) south}

import jademachine
jm = jademachine.JadeMachine()
s = jm.myturtle.getscreen()
jm.exec0("pendown")
jm.exec1("setstep", 10)
while ( jm.posx < 150 ) :
        jm.exec0("east")
        if ( jm.posx < 150 ) :
                jm.exec0("north")

        if ( jm.posx < 50 ) :
                jm.exec0("south")

jm.myturtle.hideturtle()
s.exitonclick()
```
