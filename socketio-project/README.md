# TP2 - petit exemple de mise en place de socket.io

Ce dépôt contient le premiere TP du JSFS ([lien vers le sujet](https://www.fil.univ-lille1.fr/~routier/enseignement/licence/jsfs/tdtp/chartio.html))

## Installation

Tout d'abord vous devez installer le socket.io en utilisant cette commande :

```bash
$ npm run install
```

Pour exécuter le code veuillez vous utilisez cette commande :

```bash
$ nodemon
ou
$ npm run start
```

Le programme se lancera au port `8080`, et pour voir le contenu veuillez aller sur ce lien - [http://localhost:8080/public/chartio.html](http://localhost:8080/public/chartio.html)

## Arborescence du projet

```bash
.
├── README.md
├── controllers
│   ├── ioController.js
│   └── requestController.js
├── main.js
├── package-lock.json
├── package.json
├── public
│   ├── chartio.html
│   ├── scripts
│   │   ├── chart.min.js
│   │   ├── messageConstants.js
│   │   └── myChart.js
│   └── style
│       └── style.css
└── scripts
    └── contentTypeUtil.js

5 directories, 12 files
```

## Questions

Toute les questions a été implémentée sur le code source.

Q5)
Lorsque plusieurs clients sont connectés :

- pour avoir le même numéro pour chaque client, avant de lancer l'application veuillez décommenter la ligne `18` et commenter la ligne `15` dans le fichier `ioController.js`. on a utilisé `this.#io.emit` pour cibler tous les clients connectés.

- pour avoir différent numéro pour chaque client, avant de lancer l'application veuillez commenter la ligne `18` et décommenter la ligne `15` dans le fichier `ioController.js`. ici on a fait une boucle pour chaque utilisateur connecté et on a utilisé `socket.emit` pour cibler le client specifique connecté.
