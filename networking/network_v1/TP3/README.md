# Rapport pour le TP3 : TCP & Serveur de Tchat

Ce dépôt appartient à Hichem **KARFA** et Benedictus Kent **RACHMAT**, Groupe 7 du S5 Licence 3 Informatique.

# Compilation

Pour compiler les fichiers sources, placez-vous dans le dossier TP3 et exécutez la commande :

```bash
$ > make
```

Pour nettoyer le dépôt, placez-vous dans le dossier TP3 et exécutez la commande ci-dessous :

```bash
$ > make clean
```

Pour générer la documentation, placez-vous dans le dossier TP3 et exécutez la commande ci-dessous :

```bash
$ > make doc
```

# Exercice 1 : Première expérience

## Server

Cette classe permet d'implémenter un serveur TCP qui accepte en permanence des connexions, et pour chacune, envoie
au client le message « Bienvenue sur mon serveur et au revoir » avant de rompre immédiatement la communication.
le choix pour le numéro de port du serveur est libre :

Pour exécuter le programme **Server**, placez-vous dans le dossier TP3 et exécutez la commande :

```bash
$ >  java exo1.Server [port]
```

- `[port]` : le numéro de port du serveur.

Vous pouvez aussi laisser le programme choisir un port par défaut, il utilisera alors l'année en cours.
Pour exécuter le programme **Server** avec un port par défaut, placez-vous dans le dossier TP3 et exécutez la commande :

```bash
$ >  java exo1.Server
```

## Client

**Bonus** <br/>
Nous avons aussi créé une classe **Client** permettant de se connecter à un serveur avec une connexion TCP. Elle peut éventuellement être utilisée pour tester le programme.

Pour exécuter le programme **Client**, placez-vous dans le dossier TP3 et exécutez la commande :

```bash
$ > java exo1.Client [nomHote] [numPort]
ou
$ > telnet [nomHote] [numPort]
```

- `[nomHote]` : le nom d'hote du serveur.
- `[numPort]` : le numéro de port du serveur.

## Question

### Question 1

Pour traiter une requête, il y a 3 grandes étapes : la connexion, l'envoi du message, et la clôture de la connexion.<br/>

Tout d'abord il faut créer une socket pour le serveur. Chaque fois que client enverra une demande de connexion, le serveur l'acceptera et créera une socket permettant de communiquer avec ce client.

Concernant la connexion, le client envoie un segment TCP au serveur pour faire une demande de connexion client -> serveur.<br/>
Le serveur accepte la connexion client -> serveur et fait une demande de connexion serveur -> client dans un autre segment.<br/>
Le client accepte la connexion serveur -> client dans un 3ème segment. C'est le Three-way handshake <br/>

Pour l'envoi du message, le serveur envoie un segment au client contenant le message de bienvenue et le client envoie un accusé de réception au serveur.<br/>

Pour finir, le serveur envoie une demande de clôture de la connexion serveur -> client.<br/>
Le client prend acte de cette clôture et renvoie un accusé de réception.

### Question 2

Pour la connexion, il y a tout d'abord les exceptions liées à la création de la socket du serveur, voici les exceptions à traiter :

- On peut avoir un problème d'entrée /sortie `IOException`.
- Un problème lié au gestionnaire de sécurité `SecurityException`.
- Un problème lié au numéro de port, celui-ci doit être valide (compris entre 0 et 65535) et disponible `IllegalArgumentException`<br/>

- il y a aussi les exceptions liées à l'acceptation de la connexion, en plus des problèmes d'entée/sorties et de sécurité, on peut se trouver avec une `IllegalBlockingModeException` cette exception se produit quand le canal est en mode non bloquant, et il n'y a pas de connexion prête à être accepté.

- Pour l'envoi du message il peut y avoir une erreur d'E/S (`IOException`) lors de la création du flux de sortie (par exemple si la socket n'est pas connectée).

- Pour finir la fermeture de la connexion peut produire une exception s'il y a un problème d'E/S `IOException`.

Nous avons traité chaque exception et si une exception se produit celle-ci sera affichée et l'administrateur en sera averti.

### Question 3 et 4

Pour tester le programme, nous utiliserons le port 2021 :

```bash
$ > java exo1.Server 2021
```

le nom d'hôte pour la machine du serveur est `karfa-VirtualBox`.

Pour le choix du client nous pouvons soit utiliser le programme **client** avec la commande :

```bash
$ > java exo1.Client karfa-VirtualBox 2021
```

ou utiliser telnet, avec la commande :

```bash
$ > telnet karfa-VirtualBox 2021
```

![Image exo1Serveur](TP3/images/exo1Serveur.PNG "exo1Serveur")

![Image exo1ClientTelnet](TP3/images/exo1ClientTelnet.PNG "exo1ClientTelnet")

![Image exo1ClientJava](TP3/images/exo1ClientJava.PNG "exo1ClientJava")

L'image ci-dessus nous montre que la connexion TCP s'est correctement établie.
il y a 1 serveur dans `karfa-VirtualBox` et 2 clients qui se connectent au serveur. Une fois qu'ils sont connectés, le serveur envoie le message.

Nous pouvons aussi voir la connexion d'un client sur Wireshark :

![Image wireshark](TP3/images/wireshark.PNG "wireshark")

Pour s'assurer que le programme fonctionne en boucle nous avons pensé à deux choix, le 1er étant de créer un script permettant de générer un grand nombre de connexion et de tester que le programme fonctionne avec plusieurs clients. le deuxième est de mettre en pause le programme pendant quelques secondes quand il s'occupe d'une requête d'un client, et de tenter de se connecter au serveur depuis d'autres clients.

![Image ClientSimultane1](TP3/images/exo1ClientSimultane1.PNG "ClientSimultane 1")

On peut voir ci-dessus que le client1 (situé à gauche) est connecté pendant que le client2 attend que le traitement soit fini.<br/>
Une fois que le serveur finit le traitement, il arrête la connexion avec le client1 et commence une nouvelle avec le client2.

![Image ClientSimultane2](TP3/images/exo1ClientSimultane2.PNG "ClientSimultane 2")

Nous avons aussi testé différents cas d'erreurs :<br/>

![Image CasErreur1](TP3/images/exo1CasErreur1.PNG "CasErreur1")
![Image CasErreur2](TP3/images/exo1CasErreur2.PNG "CasErreur2")
![Image CasErreur3](TP3/images/exo1CasErreur3.PNG "CasErreur3")
![Image CasErreur4](TP3/images/exo1CasErreur4.PNG "CasErreur4")

Pour garder la trace de toutes les connexions ayant eu lieu, nous affichons à l'administrateur chaque connexion avec le nom d'hôte du client ainsi que l'heure de connexion comme vous pouvez le voir ci-dessus. Nous enregistrons aussi les logs dans un fichier `connection.txt` que l'administrateur peut consulter à tout moment.

![Image FichierLogs](TP3/images/exo1FichierLogs.PNG "FichierLogs")

# Exercice 2 : Serveur de Dialogue

## Server

Server est une classe qui implémente un serveur TCP qui accepte simultanément plusieurs connexions.
Chaque client qui se connecte peut envoyer des messages, qui seront alors reçus de tous les clients actuellement connectés.

Pour exécuter le programme **Server**, placez-vous dans le dossier TP3 et exécutez la commande :

```bash
$ > java exo2.Server [port]
```

- `[port]` : le numéro de port du serveur.

Vous pouvez aussi laisser le programme choisir un port par défaut, il utilisera alors l'année en cours.
Pour exécuter le programme **Server** avec un port par défaut, placez-vous dans le dossier TP3 et exécutez la commande :

```bash
$ > java exo2.Server
```

**Q1.**

Après avoir créé une instance de `ServerSocket` avec le port comme argument, on doit créer une boucle infinie. à l'intérieur, nous accepterons tous les sockets entrants demandant à se connecter, c'est à ce moment-là qu'on créera un thread avec le socket, la liste des utilisateurs connectés, et l'identifiant unique comme argument (comme dans `ServerThread` par exemple).
On aura donc un thread pour chaque client se connectant sur le serveur.

**Q2.**

Tout d'abord pour obtenir le flux d'entrée pour cette socket, on doit utiliser la méthode `getInputStream`. Et puis nous mettons cette instance comme argument de `InputStreamReader` pour créer des flux d'octets en flux de caractères. Et finalement la mettons sur l'argument de `BufferedReader` pour lire le texte à partir d'un flux d'entrée de caractères.

**Q3.**

Pour envoyer un message à tous les utilisateurs connectés, nous devons utiliser la fonction `synchronized`. cela nous permet de synchroniser la liste des utilisateurs connectés, afin que le message puisse être envoyé à tous. C'est pourquoi dans le constructeur `ServerThread` on passe l'argument de la liste des utilisateurs connectés.

**Q4.**

Pour garder la trace toutes les connections ayant eu lieu on peut utiliser `new FileOutputStream`. Donc une fois le message a été envoyé, on peut enregistrer les données dans un fichier, par exemple, `exo2/connection.txt` sera créé lorsqu'il y aura une nouvelle connexion ou qu'un utilisateur se déconnecte. et le fichier `exo2/chat.txt` sera créé lorsque l'utilisateur envoie un message.

**Q5.**

En déclarant une `NullPointerException`, on peut intercepter l'erreur et en faire quelque chose. dans notre cas, après que l'utilisateur appuie sur le `CTRL-D`, l'utilisateur sera déconnecté du serveur.

## Test

Tout d'abord il faut se placer dans le dossier TP3 puis compiler les sources :

```bash
$ > make
```

on exécute ensuite le programme **server** pour tester le programme, nous utiliserons le port 2021 :

```bash
$ > java exo2.Server 2021
```

le nom d'hôte pour la machine du serveur est `karfa-VirtualBox`.

Pour le choix du client nous utilisons deux clients telnet :

```bash
$ > telnet karfa-VirtualBox 2021
```

Voici l'affichage qu'on obtient côté serveur :

![Image exo2Serveur](TP3/images/exo2Serveur.PNG "exo2Serveur")

et côté client :

![Image exo2Client](TP3/images/exo2Client.PNG "exo2Client")

![Image exo2Client2](TP3/images/exo2Client2.PNG "exo2Client2")

le serveur nous affiche alors les logs dans la sortie standard, tous les logs de connexion et les chat sont aussi enregistrée dans un fichier texte (`exo2/connection.txt` et `exo2/chat.txt`).

```
Bienvenue sur le serveur [NOM D'HOTE ET IP]
-----------------------------
| Bonjour [NOM D'HOTE] |
17/11/2021 09:00:20
-----------------------------
MENU  Voici votre nom d'utilisateur : [VOTRE ID]
-----------------------------
1. Changer nom d'utilisateur
2. Afficher les utilisateurs connectés
3. Entrer dans le tchat
4. tchat avec une personne
5. Exit
-----------------------------
Input :
```

**Instructions**

1. Changer nom d'utilisateur : Changer l'ID à un nom.
2. Afficher les utilisateurs connectés : Les utilisateurs connectés de ce serveur.
3. Entrer dans le tchat : Entrer dans une Tchat de groupe où tout le monde peut recevoir et envoyer un message (tapez `exit` pour sortir).
4. tchat avec une personne : Ce menu nous permet de discuter avec certaines personnes en privé, il suffit de passer le nom de l'utilisateur en argument.
5. Exit : Quitter le programme.

**Bonus**

Nous pouvons avant de démarrer une discussion changée de nom, par défaut le nom d'un client et un numéro d'identifiant qui est incrémentée d'un pour chaque nouveau client.

![Image changementNom](TP3/images/changementNom.PNG "changementNom")

nous appellerons `Hichem` notre premier client et `Kent_Rachmat` pour le second.

On a aussi créer un 3e client qu'on appelle `Alex` pour la suite de l'exercice.
Entrons maintenant dans un chat pour communiquer avec toutes les personnes connectées :

![Image rejoindreChat1](TP3/images/rejoindreChat1.PNG "rejoindreChat1")

![Image rejoindreChat2](TP3/images/rejoindreChat2.PNG "rejoindreChat2")

![Image message1](TP3/images/message1.PNG "message1")

on peut voir que le message est reçu par tout le monde :

![Image message2](TP3/images/message2.PNG "message2")

![Image message3](TP3/images/message3.PNG "message3")

Nous pouvons alors quitter le chat :

![Image exit1](TP3/images/exit1.PNG "exit1")

les clients du chat voient alors que nous avons quitté le chat :

![Image exit2](TP3/images/exit2.PNG "exit2")

Les départs sont aussi affichés côté serveur :

![Image logsServeur.PNG](TP3/images/logsServeur.PNG "logsServeur.PNG")
