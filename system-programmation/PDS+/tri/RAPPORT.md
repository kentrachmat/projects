# `Tri rapide multithreadé`

## Membres du groupe

Ce travail est à réaliser en équipe dont les membres sont :

- BENEDICTUS KENT **RACHMAT**
- HICHEM **KARFA**

## Compte-rendu

-- La section 1 contient les questions traitées ainsi que le rapport pour chaque question <br/>
-- La section 2 contient les questions non traitées <br/>
-- La section 3 contient les notions apprises<br/>
-- La section 4 contient les commandes à effectuer pour lancer les programmes ainsi que les tests

## Section 1

Q1)

- la pile et les n threads sont créés avec succès grâce à `init_pile`, `pthread_create` et `pthread_join` pour attendre qu'un thread se termine.
- à l'intérieur de la fonction `rapide_thread` il y a une boucle qui peut vérifier si le tri est terminé ou non en vérifiant si la pile est vide ou non, le mutex est aussi utile pour garder ces données jusqu'à ce qu'il termine son opération.
- à l'intérieur de la fonction `depile_thread` il y a une fonction `pthread_cond_wait`, donc elle s'arrêtera quand la pile est vide, mais quand un nouveau bloc sera disponible, elle enverra un signal et ouvrira cette condition d'attente.
- après le découpe, j'ai appelé la méthode `empile_thread` pour empiler la pile actuelle sur un bloc et envoyer un signal pour ouvrir le `pthread_cond_wait`.

le problème pour lequel l'algorithme de tri ne fonctionne pas est que lorsque le bloc est petit, je ne peux pas dire à mes threads de se synchroniser dans les données partagées (je pense que le problème principal est le `empile_thread`).

Si j'ai encore de temps, mon plan consiste d'abord à créer une boucle infinie et à vérifier s'il n'y a plus de thread en cours d'exécution, je diffuserai mon signal et sortirai la boucle (cela signifie que le bloc est peut être suffisamment petit pour que le thread puisse synchroniser leurs données). sinon, s'il y a encore un fil qui est en train d'éxecuter, j'attends que le signal soit reçu pour passer à le depiler. je vais aussi vérifier si `bloc.fin - bloc.debut` est inférieur au seuil j'utiliserai la méthode `rapide_seq(seq)` pour implémenter la modification en question 2. sinon, nous utilisons les fonctions `rapide_decoupebloc` et `empile`. Bien sûr, le verrouillage et le déverrouillage du mutex sont importants pour synchroniser la variable partagée

## Section 2

Q2)

car la première n'est pas bien traitée, cette question est la conséquence car elles sont liées.

## Section 3

- comment combiner thread, mutex et traitement du signal
- verrouiller et déverrouiller lorsqu'une opération sur une variable est nécessaire
- comment envoyer le signal et la condition d'attente

## Section 4

Avant de commencer notre tri rapide, vous pouvez créer un fichier /tmp/alea contenant 128Mo de données aléatoires à l'aide de cette commande :

```bash
$ dd if=/dev/urandom of=/tmp/alea bs=1048576 count=128
ou
$ ./test/alea
```

ou créer un nombre avec ordre décroissant (pire cas) avec python :

```bash
$ python3 test/generate.py
```

Pour compiler les sources, placez-vous dans le dossier `tri` et utilisez la commande :

```bash
$ make
```

Après avoir les données à trier vous pouvez lancer cette commande pour vérifier si l'algorithme de tri fonctionne :

```bash
$ ./tri-rapide -vc test/simple
verbeux                         = 1
format texte                    = 0
contrôle final                  = 1
affichage final                 = 0
nombre d’entiers à lire         = 2
nombre de threads               = 0
taille minimale des blocs longs = 1
Vérification du tableau trié
Vérification du tableau effectuée avec succès
```

mettre le `time` devant pour vérifier le temps écoulé. la commande `-vc` sert à afficher les détails nécessaires comme ci-dessus, vous pouvez également mettre `-n` suivi avec le nombre de thread pour déclarer combien de threads vous voulez, si vous voulez voir le numéro trié, veuillez utiliser `-l` suivi de la longueur que vous voulez être trié. et enn tapant `./tri-rapide` vous pouvez voir les autres commande.
