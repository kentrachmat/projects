# Membres du groupe

Ce dépôt appartient à Hichem **KARFA** et Benedictus Kent **RACHMAT**, Groupe 7 du S5 Licence 3 Informatique

Ce dépôt correspond au TP numéro trois (Des arbres et des couleurs) de PF

# Des arbres et des couleurs

## Section 0 : Compilation et Exécution

Affichage de l'arbre :

```bash
$ > dot -Txlib arbre.dot
```

Exécution de l'animation :

```bash
$ > runghc arbres.hs
```

Nous avons aussi créé un script permettant de créer un fichier arbre.dot et d'afficher le contenu avec la commande dot,
pour exécuter le fichier placez-vous dans la racine du dépôt et lancez la commande suivante :

```bash
$ > ./createArbre.sh
```

## Section 1.1 : Questions traitées

Tout notre code est fonctionnel.

L'ensemble des questions traitées nous semblent correctes.

Voici les réponses aux Questions pour cette exercices :

- **Q6)** Que vérifie **prop_hauteurPeigne xs = length xs == hauteur (peigneGauche xs)** ?
  Elle vérifie que la hauteur d'un arbre créé avec **peigneGauche** est bien égale au nombre de couples (c, a) de la liste passée en paramètre.<br/>
  cela permet donc de tester une des propriétés de **peigneGauche**

- **Q10)** Quels sont les peignes à gauche complets ?
  Les peignes à gauche complets sont les arbres vides et les arbres a un élément. On peut tester cela avec QuickCheck.

- **Q11)** Combien de nœuds et de feuilles a l’arbre complet de hauteur n
  taille = (2<sup>n </sup>-1)
  Feuille = 2<sup>n </sup>

Pour un arbre d'une hauteur de 20 on se trouve avec 1 million de noeuds ! Il n'est pas donc pas pertinent d'aller au-delà de ce nombres.

Nous avons eu certaines difficultés qui nous détaillions dans la section suivante.

## Section 1.2 : Difficulté rencontrée et remarque

Difficulté rencontrée et surmontée :

- **Q10)** Nous ne savions pas comment trouver des cas de peignes gauches avec **QuickCheck**, mais en réfléchissant à une autre manière d'utiliser cette fonction nous avions trouvé une solution. Il suffisait de lui dire qu'il n'existe aucun peigne gauche complet, en faisant cela QuickCheck nous trouve les cas ou cette expression est fausse.

- **Q11)** Nous n'avions pas compris comment les couples (couleur, valeur) étaient positionnées dans la liste, et comment les extraire afin de créer un arbre complet. finalement nous avions compris que la liste positionnée le premier couple de l'arbre au milieu. Une fois cela l'extraction n'a pas été simple mais faisable en séparant à chaque fois la liste en deux et en récupérant le couple situé au milieu de cette liste.

- **Q17)** Pour cette question il fallait bien réfléchir aux différents cas possibles, grâce au filtrage de motif et l'utilisation de @ pour pouvoir récupérer le fils d'un noeud ainsi qu'un élément de ce fils m'a été d'une grande utilité.

- **Q19)** Pour cette fonction nous avons dû décomposer le problème car la fonction est assez complexe à écrire.

- **22)** | **24)** | **25)** Le code a été simple à écrire mais je ne comprenais pas ce que je faisais au début, j'ai donc dû comprendre ce qu'était un arbre R/N ainsi que l'utilité de celui-ci.

- **23)** Très compliqué à écrire sachant que je n'avais pas compris comment et pourquoi je devais équilibrer un arbre, je devais aussi mettre la racine en noir à la fin du traitement, je voulais de base regarder si le résultat était rouge ou noir avant de changer la couleur de la racine mais cela revenait à compliquer le code. Pour réussir la fonction j'ai dû écrire une fonction annexe pour insérer un élément puis mettre à la fin la racine en noir.

- **26)** Pour **arbresDot** je pense qu'il y d'autre moyen pour écrire la fonction, par exemple en utilisant un **fold**. Pour cette fonction je suis plus à l'aise avec l'utilisation de **map** couplé à une fonction annexe qui génère la liste d'arbres.

## Section 2 : Questions non-traitées

La question 9 n'a pas été traitée. Nous avons compris la question, mais nous n'arrivons pas à trouver l'algorithme permettant de rendre **estComplet** assez générique. La question 8 a été donnée, même avec du recul je ne pense pas que j'aurais pu réussir à écrire la fonction car elle était relativement complexe.

## Section 3 : Notion

Ce TP a été plus complexe que les précédents, néanmoins cela nous a permis d'étudier différentes notions comme :

- l'utilisation du système de type pour pouvoir créer des structures de données assez complexes comme les arbres

- l'utilisation de QuickCheck pour tester les différentes fonctions ou trouver des cas un peu spécifiques dans un test de fonction

- Ce TP m'as aussi permis de comprendre et de voir ce qu'était un arbre binaire de recherche et un arbre Rouge/Noir.

- Pour des arbres équilibrés nous avons constaté que cela peut être particulièrement efficace pour la recherche et l'insertion d'éléments, en plus d'avoir une notion d'ordre des éléments dans cette structure. Par exemple si je veux trouver un minimum ou un maximum cela peut être très efficace.
