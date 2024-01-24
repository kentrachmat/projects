# TP3 - Pong

## Installation

Pour exécuter le code veuillez vous utilisez cette commande :

- pour le côté client

```bash
$ npm run watch
ou
$ npm run build
```

- pour le côté client

```bash
$ nodemon
ou
$ npm run start
```

et n'oubliez pas d'utiliser `npm install` pour installer toutes les dépendances utilisées sur ce TP3

## Arborescence du projet

```bash
.
├── README.md
├── client
│   ├── package-lock.json
│   ├── package.json
│   ├── src
│   │   ├── images
│   │   │   ├── balle24.png
│   │   │   └── paddle.png
│   │   ├── index.html
│   │   ├── scripts
│   │   │   ├── Ball.js
│   │   │   ├── Game.js
│   │   │   ├── Mobile.js
│   │   │   ├── Paddle.js
│   │   │   ├── messageConstants.js
│   │   │   └── pong.js
│   │   └── style
│   │       └── style.css
│   └── webpack.config.js
└── server
    ├── controllers
    │   ├── ioController.js
    │   └── requestController.js
    ├── main.js
    ├── messageConstants.js
    ├── package-lock.json
    ├── package.json
    ├── public
    │   ├── images
    │   │   ├── balle24.png
    │   │   └── paddle.png
    │   ├── index.html
    │   ├── scripts
    │   │   ├── bundle.js
    │   │   └── bundle.js.LICENSE.txt
    │   └── style
    │       └── style.css
    └── scripts
        └── contentTypeUtil.js.
├── README.md
├── client
│   ├── package-lock.json
│   ├── package.json
│   ├── src
│   │   ├── images
│   │   │   ├── balle24.png
│   │   │   └── paddle.png
│   │   ├── index.html
│   │   ├── scripts
│   │   │   ├── Ball.js
│   │   │   ├── Game.js
│   │   │   ├── Mobile.js
│   │   │   ├── Paddle.js
│   │   │   ├── messageConstants.js
│   │   │   └── pong.js
│   │   └── style
│   │       └── style.css
│   └── webpack.config.js
└── server
    ├── controllers
    │   ├── ioController.js
    │   └── requestController.js
    ├── main.js
    ├── messageConstants.js
    ├── package-lock.json
    ├── package.json
    ├── public
    │   ├── images
    │   │   ├── balle24.png
    │   │   └── paddle.png
    │   ├── index.html
    │   ├── scripts
    │   │   ├── bundle.js
    │   │   └── bundle.js.LICENSE.txt
    │   └── style
    │       └── style.css
    └── scripts
        └── contentTypeUtil.js
```

## Questions

tout le code a été implémenté correctement.

pour le premier joueur, utilisez `z` ou `w` pour monter et `s` pour descendre, et pour le deuxième joueur, utilisez la flèche vers le haut pour monter et la flèche vers le bas pour descendre. pour démarrer le jeu s'il vous plait appuyez sur le bouton `JOUER`.

la balle rebondira si elle touche le haut ou le bas, mais si elle touche le côté gauche, le joueur 2 gagne, si elle touche le côté droit, le joueur 1 gagne.
