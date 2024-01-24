# Partie Lecture et écriture de données binaires sur l’entrée sortie standard

Ce dépôt correspond au deuxième partie de TP
  
## Membres du binôme 

Le rendu du TP *theme3* des étudiants Aziz **_BOURAHMA_** et Benedictus Kent **_RACHMAT_**, groupe 6 du S4 Licence 2 Informatique. 

## Les réponses

Toutes les commandes sont lancées dans le terminal.

* Exercise 8

Premièrement la taille de int est 4 bytes donc la valeur 0x41424344 est divisée par 4 valeur qui est 44 43 42 41. Donc code ascii de ce hexadécimal est DCBA
```bash
../representation> make exo5

00000000  44 43 42 41           |DCBA|
00000004
```

* Exercise 9
```bash
../representation> make output
../representation> ./module/output > module/output.bin
../representation> hd module/output.bin
```

* Exercise 10
```bash
../representation> make input
../representation> ./module/input < module/output.bin
```

* Exercise 11
```bash
../representation> make test
```

* Exercise 12
```bash
../representation> make test
```

* Exercise 13
```bash
../representation> make test
```
## Contenu de ce dépôt

* `*.c` les code pour les questions
* `*.h` les code pour les questions
* `module/` le fichier objet et le fichier d'exécution (fichiers avec .o et .bin)
* `Makefile` les code pour les questions