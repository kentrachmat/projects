# TP Starship

Ce dépôt contient le code du premier projet sur le TP vaisseau/starship ([lien vers le sujet](https://www.fil.univ-lille1.fr/~routier/enseignement/licence/js-s4/tdtp/starship.html))

## Installation
Pour exécuter le code, tout d'abord vous devez installer les modules en exécutant la commande :

```bash
$ ../rendu-tp-js/tp-balles> npm install
```

## Lancez le jeu
et puis vous devez créer le dossier dist/ et construire un premier bundle en exécutant la commande :

```bash
$ ../rendu-tp-js/tp-balles> npm run build ou npm run watch
```

Vous pouvez ouvrir le fichier dist/index.html, pour vérifier que tout s'est bien déroulé en consultant la console (Ctrl + Shift + K) dans laquelle vous devez lire le message **_le bundle a été généré_**


Pour construire le bundle et visualiser les résultats en démarrant le serveur de développement :

```bash
$ ../rendu-tp-js/tp-balles> npm run dev-server
```

Et finalement, n'oubliez pas d'exécuter la commande **_npm run build_** après l'arrêt du serveur de développement pour mettre à jour le dossier dist/

Appuyez sur les boutons fléchés haut et bas pour déplacer le vaisseau et ajouter les ennemies autant que vous le souhaitez.
