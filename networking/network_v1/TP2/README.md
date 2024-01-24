# Rapport pour le TP2 : UDP & Multicast

Ce dépôt appartient à Hichem **KARFA** et Benedictus Kent **RACHMAT**, Groupe 7 du S5 Licence 3 Informatique.

# Compilation

Pour compiler les fichiers sources, placez-vous dans le dossier TP2 et exécutez la commande :

```bash
$ make
```

Pour nettoyer le dépôt, placez-vous dans le dossier TP2 et exécutez la commande ci-dessous :

```bash
$ make clean
```

Pour générer la documentation, placez-vous dans le dossier TP2 et exécutez la commande ci-dessous :

```bash
$ make doc
```

Pour consulter la documentation, placez-vous dans le dossier docs qui vient d'être généré à la racine et ouvrez le fichier index.html dans un navigateur.

# Exercice 1 : Protocole UDP

## ReceiveUDP

ReceiveUDP crée un serveur UDP qui écoute sur un port UDP donné et d'affiche les messages reçus sous la forme de chaine de caractères.<br/>

- Pour rendre le code plus lisible nous l'avons séparé en plusieurs fonctions, chaque fonction est documentée.<br/>
- Nous avons aussi géré un maximum d'exception.
- En plus de la chaine de caractère, ReceiveUDP affiche l'IP et le port de l'expéditeur <br/>

Pour exécuter le programme **ReceiveUDP**, placez-vous dans le dossier TP2 et exécutez la commande :

```bash
$  java exo1.ReceiveUDP [port]
```

Remplacez [port] par le port UDP de votre machine que vous souhaitez écouter.

## SendUDP

SendUDP permet d'envoyer des paquets UDP en unicast à un utilisateur désigné.

Pour exécuter le programme **SendUDP**, placez-vous dans le dossier TP2 et exécutez la commande :

```bash
$  java exo1.SendUDP [nom_hôte] [port] [message]
```

- Remplacez [nom_hôte] par le nom d'hôte de la machine distante à qui vous souhaitez envoyer le message.<br/>
- Remplacez [port] par le port UDP de la machine distante à qui vous souhaitez envoyer le message.<br/>
- Remplacez [message] par le message que vous souhaitez envoyer entre guillemets.<br/>

## Tests

Nous avons commencé par tester les deux programmes indépendamment pour cela nous avons utilisé l'outil socklab.<br/>

- Pour tester **ReceiveUDP** nous avons lancé le programme en donnant le port 1500 comme paramètre, puis nous avons créé une socket via socklab et envoyé "hello" au port 1500.<br/>

![Image Q1](TP2/images/exo1TestReceiveUdp.png "Image Q1")

![Image Q1](TP2/images/exo1TestReceiveUdp2.png "Image Q1")

Sur l'image ci-dessus, on peut voir que nous avons bien reçu le message "hello".

- Pour tester **SendUDP** nous avons crée une socket affecté au port 1500, puis nous avons lancé le programme et envoyé "hello" au port 1500.

![Image Q1](TP2/images/exo1SendUdp1.png "Image Q1")

![Image Q1](TP2/images/exo1SendUdp2.png "Image Q1")

Sur l'image ci-dessus, on peut voir que nous avons bien reçu le message "hello" sur socklab.

Pour tester les deux programmes en simultané , nous avons essayé d'envoyer des messages au port 8532, et de les recevoir via ce même port en utilisant les deux programmes :

![Image Q1](TP2/images/exo1TestReceiveSend1.png "Image Q1")

On peut voir que nous avons bien reçu le message.

Nous avons ensuite testé sur deux machines différentes et avec deux clients différents: <br/>

![Image Q1](TP2/images/exo1.png "Image Q1")

Sur l'image ci-dessus, on a deux client `a0p20` et `a0p21` et le serveur démarre dans `a0p20`.
Les deux clients envoient leur message au serveur via le port `1500` et on peut voir que le serveur a bien reçu les deux messages.

Nous avons également testé les différents cas d'erreurs :

![Image Q1](TP2/images/exo1CasErreurReceive.png "Image Q1")

![Image Q1](TP2/images/exo1CasErreurSend.png "Image Q1")

# Exercice 2 : Multicast UDP

**Q1.**
Réception :

- Etape 1. on a besoin d'une adresse de Multicast (224.0.0.0 à 239.255.255.255) d'un numéro de port et d'un buffer qui contiendra les futurs messages.
- Etape 2. on crée le paquet datagramme ainsi qu'une socket Multicaste avec le port voulu.
- Etape 3. on ajoute la socket au groupe de Multicast avec la méthode joinGroup
- Etape 4. on écoute en utilisant la methode receive de la socket et on ferme la socket quand on ne l'utilise plus.

Émission :
Pour l'émission il n'y a en réalité que très peu de changement par rapport à l'unicast en effet, il suffit simplement de créer une
`socket(MulticastSocket)` et d'envoyer le paquet avec la methode send à l'adresse de multicast.

**Q2.**
D'émission :

1. Vérifier les arguments du programme(l'adresse IP, le port). (UnknownHostException, SecurityException)
2. Vérifier que la MulticastSocket est créé avec succès. (IOException, SecurityException)
3. Vérifier que le message a été envoyé avec succès.(IOException, SecurityException)

Réception :

4. Vérifier qu'on peut attribuer l'adresse demandée (`224.0.0.1` par exemple).(UnknownHostException, SecurityException)
5. Vérifier que la MulticastSocket est créée avec succès et a le bon nom d'hôte.(IOException, SecurityException)
6. Vérifier que la socket peut joindre le groupe de Multicast.
7. Vérifier qu'une erreur ne survient pas lors de la réception des messages.

Pour exécuter le programme **ReceiveMulticastSocket**, placez-vous dans le dossier TP2 et exécutez la commande :

```bash
$  java exo2.ReceiveMulticastSocket
```

Pour exécuter le programme **SendMulticastSocket**, placez-vous dans le dossier TP2 et exécutez la commande :

```bash
$  java exo2.SendMulticastSocket [message]
```

Remplacez [message] par le message que vous souhaitez envoyer entre guillemets.<br/>

![Image Q2](TP2/images/exo2.png "Image Q2")

Le principe est le même avec le premier exercice mais ici on utilise le multicast. cela signifie que lorsque on envoye un message, il sera reçu par tous ceux qui écoutent le même port (par exemple ici le port `7654`). C'est parce que l'adresse `224.0.0.1` est une adresse multicast réservée.

# Exercice 3 : Programme de Tchat

**Q1.**
On doit manipuler des threads avec par exemple la classe `java.lang.Thread`. un Thread permet à un programme de fonctionner plus efficacement en effectuant plusieurs tâches en même temps(concurrence). on a donc créé une instance du thread dans `ReaderMulticast.java` et nous l'avons déclaré dans notre code principal qui est `ClientMulticast.java`.
Avec cela nous pouvons émettre et recevoir des paquets simultanément.

**Q2.**
Grâce à la fonction `.getAddress().getHostName()` on peut connaître la machine hôte, et la fonction `System.getProperty("user.name")` nous donne aussi le nom d'utilisateur connecté à l'ordinateur.

Dans cet exercice, on a créé 2 types de Tchat, un avec le Multicast(ClientMulticast) et un avec un l'unicast(Client).

Pour exécuter le programme **ClientMulticast**, placez-vous dans le dossier TP2 et exécutez la commande :

```bash
$ java exo3.ClientMulticast {PORT}
```

Remplacez {port} par le port UDP de la machine distante à qui vous souhaitez envoyer le message.<br/>

Pour exécuter le programme **Client** (Unicast) placez-vous dans le dossier TP2 et exécutez la commande :

```bash
$ java exo3.Client {DESTINATION HOSTNAME} {PORT}
```

Pour terminer une conversation, il suffit d'entrer le mot **leave**.

Voici un exemple d'utilisation pour les deux programmes :

| [Exemple avec Client.java](TP2/images/exo3_1.png "Image Q3_1") | [Exemple avec ClientMulticast.java](TP2/images/exo3_2.png "Image Q3_2") |
| :------------------------------------------------------------: | :---------------------------------------------------------------------: |
|       ![Image Q3_1](TP2/images/exo3_1.png "Image Q3_1")        |            ![Image Q3_2](TP2/images/exo3_2.png "Image Q3_2")            |

Sur l'image ci-dessus, on a deux client `a0p20` et `a0p21` qui communiquent simultanément. c'est possible avec l'aide de Thread
