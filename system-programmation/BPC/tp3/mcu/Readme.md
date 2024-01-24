# Partie Mes Commandes Unix

Ce dépôt correspond au première partie de TP3
  
## Membres du binôme 

Le rendu du TP *theme3* des étudiants Aziz **_BOURAHMA_** et Benedictus Kent **_RACHMAT_**, groupe 6 du S4 Licence 2 Informatique. 

## Les réponses

Toutes les commandes sont lancées dans le terminal.

* Exercise 1,2,3
```bash
../mcu> dans le fichier tableau.c
```

* Exercise 4
```bash
../mcu> make
../mcu> ./build/mcu_test < src/mcu_test.c ; echo "le code de retour est $?"
```

* Exercise 5
```bash
../mcu> make mcu_wc
../mcu> echo 'Hello Unix World!' | ./build/mcu_wc

../mcu> make mcu_wl
../mcu> echo 'Hello Unix World!' | ./build/mcu_wl
```

* Exercise 6
```bash
../mcu> make mcu_rev
../mcu> echo 'Hello World,' > essai ; echo 'Hello Unix World!' >> essai ; 
../mcu> ./build/mcu_rev < essai
```

* Exercise 7
```bash
../mcu> make mcu_uniq
../mcu> ./build/mcu_uniq
```

## Contenu de ce dépôt

 * `src/` les code pour l'exercise 1-4
 * `build/` les fichiers exécutables
 * `Makefile` le code de Makefile
 * `essai` le texte pour l'exercise 3