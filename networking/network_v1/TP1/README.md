# Rapport pour le TP1 : Étude des sockets

Ce dépôt appartient à Hichem **KARFA** et Benedictus Kent **Rachmat**, Groupe 7 du S5 Licence 3 Informatique.

## UDP

### Question 1

On exécute la commande :

```bash
$ ip address show dev eth0
```

L’adresse IPv4 associée à l’interface ethernet de mon ordinateur est **10.0.2.15**

---

### Question 2

![Image Q2](TP1/images/udp/Q2.png "Image Q2")

---

### Question 3

![Image Q3](TP1/images/udp/Q3.png "Image Q3")

---

### Question 4

On exécute la commande suivante pour associer notre socket avec l’adresse IPv4 de mon ordinateur et
avec le port 3000 :

```bash
socklab> bind 3 10.0.2.15 3000
```

![Image Q4](TP1/images/udp/Q4.png "Image Q4")

---

### Question 5

![Image Q5](TP1/images/udp/Q5.png "Image Q5")

---

### Question 6

Pour pouvoir lui envoyer un message, mon voisin doit me fournir son adresse IP (10.0.2.4) et son numero de port (3000, comme le mien).

---

### Question 7

Capture d'écran sur l'ordinateur de mon voisin à qui j'ai envoyé mon message :

![Image Q7](TP1/images/udp/Q7.png "Image Q7")

On voit qu'il a bien reçu mon message "hello".

---

### Question 8

Selon moi, il est préférable de laisser le système choisir le port uniquement sur la machine qui envoie le message initial car l'émetteur a besoin de connaitre à l'avance le port du destinataire. Si un port est choisi aléatoirement à chaque redémarrage par le système de la machine qui reçoit le message initial, il aura du mal à être contacté.<br/>
Dans le cas où le destinataire souhaiterait répondre à l'émetteur, l'adresse de l’émetteur est comprise dans l’entête du datagramme UDP, il n'aura donc aucun mal à lui répondre. On peut donc laisser le système choisir le port sur la machine qui envoie le message initial.

---

### Question 9

On exécute la commande suivante pour fermer la socket créée précédemment :

```bash
socklab> close 3
```

---

### Question 10

![Image Q10](TP1/images/udp/Q10.png "Image Q10")

On voit bien que les sockets sont associées à toutes les adresses IP configurées sur mon ordinateur, et qu'elles ont été assignées automatiquement par le système d'exploitation.

---

### Question 11

On exécute la commande suivante pour capturer la communication sur l’interface de loopback avec Wireshark :

```bash
$ sudo wireshark -i lo -f udp
```

---

### Question 12

On exécute la commande :

```bash
$ ip address
```

L'adresse IPv4 associée à l'interface de loopback est 127.0.0.1

---

### Question 13

![Image Q13](TP1/images/udp/Q13.png "Image Q13")

---

### Question 14

![Image Q14](TP1/images/udp/Q14.png "Image Q14")

---

### Question 15 A

Sur la capture d'écran ci-dessous, on voit bien dans la 2ème fenêtre que l'adresse IP source est bien 127.0.0.1, et que le port source est bien le port 33866, ce qui correspond bien à ma socket 3.<br/>
On voit aussi que l'adresse IP de destination est bien 127.0.0.1, et que le port de destination est bien le port 57072, ce qui correspond bien à ma socket 4.

![Image Q15A1](TP1/images/udp/Q15A1.png "Image Q15A1")

Sur la capture d'écran ci-dessous, on voit bien dans la 2ème fenêtre que l'adresse IP source est bien 127.0.0.1, et que le port source est bien le port 57072, ce qui correspond bien à ma socket 4.<br/>
On voit aussi que l'adresse IP de destination est bien 127.0.0.1, et que le port de destination est bien le port 33866, ce qui correspond bien à ma socket 3.

![Image Q15A2](TP1/images/udp/Q15A2.png "Image Q15A2")

---

### Question 15 B

![Image Q15B](TP1/images/udp/Q15B.JPG "Image Q15B")

---

### Question 15 C

2 segments UDP ont été transmis, un pour le message "Comment allez-vous ?", et un pour le message "Tres bien. Merci !".<br/>
Il n'y a pas d'accusé de réception avec le protocole UDP.

---

### Question 15 D

L'efficacité du protocole est le rapport entre la taille des messages en octets et la taille totale des données transmises pour véhiculer ces messages:<br/><br/>
(20+18) / (62+60) = 0.31147541

---

### Question 16

On exécute les commandes suivantes pour fermer les sockets créées précédemment :

```bash
socklab> close 3
socklab> close 4
```

## TCP

### Question 1

![Image Q1](TP1/images/tcp/Q1.png "Image Q1")

---

### Question 2

On ouvre Wireshark avec la commande suivante :

```bash
$ sudo wireshark -i lo -f 'tcp port 3000'
```

---

### Question 3

![Image Q3](TP1/images/tcp/Q3.png "Image Q3")

---

### Question 4

On constate que la connexion est refusée.<br/>
Le premier segment contient un SYN qui est une demande de synchronisation ou établissement de connexion.<br/>
Le deuxième segment contient un ACK, qui signifie que c'est un accusé de réception, et il contient aussi un RST, qui signifie une rupture anormale de la connexion, Cela est dû au fait que nous avons tenté d'établir une connexion avec un port qui n'est pas en écoute.

![Image Q4A](TP1/images/tcp/Q4A.png "Image Q4A")

![Image Q4B](TP1/images/tcp/Q4B.png "Image Q4B")

---

### Question 5

On exécute la commande :

```bash
socklab> listen 4 1
```

On peut voir que notre port 4000 est en écoute

## ![Image Q5](TP1/images/udp/Q5.png "Image Q5_1")

### Question 6

La socket S2(port 4000) est mise en écoute et attend de recevoir des segments.<br/>
La socket S2 est donc la socket serveur et la socket S1 est la socket cliente.

---

### Question 7

On constate que la connexion est bien établie.<br/>
Le premier segment est de la part de S1. Il contient un SYN qui est une demande de synchronisation(de s1 vers s2).<br/>
Le deuxième segment est de la part de S2. Il contient un ACK, qui signifie que c'est un accusé de réception(il acquitte la demande), et il contient aussi un SYN, qui signifie un établissement de connexion(une demande de connexion dans l'autre sens de s2 vers s1).<br/>
Il y a cette fois-ci un troisième segment de la part de S1. Il contient un ACK, qui signifie que c'est un accusé de réception(il accepte la demande de connexion de s2 vers s1).

![Image Q7A](TP1/images/tcp/Q7A.png "Image Q7A")

![Image Q7B](TP1/images/tcp/Q7B.png "Image Q7B")

Ce fonctionnement en 3 étapes(le « 3-Way Handshake ») est obligatoire en TCP pour établir une connexion.

---

### Question 8

![Image Q8](TP1/images/tcp/Q8.png "Image Q8")

---

### Question 9

On constate qu'une troisième socket a été créee.<br/>
Cette socket possède une adresse locale correspondant à l'adresse locale de notre socket S2 et a une adresse distante correspondant à l'adresse locale de notre socket S1.<br/>
On remarque aussi que notre socket S1 a une adresse distante correspondant à l'adresse locale de notre socket S2.

![Image Q9](TP1/images/tcp/Q9.png "Image Q9")

---

### Question 10

![Image Q10](TP1/images/tcp/Q10.png "Image Q10")

---

### Question 11

On constate que deux segments ont été échangés.<br/>
Le premier segment provient de la socket S1 et envoie notre message à la socket S3. Il contient un PSH qui signifie que les données doivent être envoyées immédiatement, ainsi qu'un ACK.<br/>
Le deuxième segment provient de la socket S3. Il contient également un ACK.

![Image Q11](TP1/images/tcp/Q11.png "Image Q11")

---

### Question 11 A

Si le flag PSH n’avait pas été activé par l’émetteur, la couche de transport aurait attendu que la couche d'application envoie suffisamment de données égales à la taille de segment maximale afin que le nombre de paquets transmis sur le réseau soit minimisé.<br/>

Le flag PSH signifie donc que les données doivent être envoyées immédiatement.

Il indique aussi au récepteur de ne pas attendre que le buffer soit rempli que les données doivent être reçues immédiatement

---

### Question 11 B

Le numéro de séquence du segment envoyé représente le premier octet de données du segment actuel.<br/>
le numéro de séquence à envoyer correspond aussi au dernier numéro d'acquittement reçu <br/>
lors de l'envoie d'un SYN il est initialisé avec une valeur "aléatoire".

---

### Question 11 C

le numéro d'acquittement correspond au numéro (d'ordre) du prochain segment attendu, ce numéro dépend des données envoyées par le destinataire et la règle est la suivante :
- le numéro D'acquittement = numéro séquence du message reçu + longueur du message reçu.
- Si le message reçu contient un flag SYN ou FIN le numéro d'acquittement est égal au numéro de séquence du message reçu + 1

dans notre cas le numéro vaut 21(20 + 1) car le "serveur" indique au "client" qu'il a reçu les 20 octets de données (avec un numéro de séquence de 1) il attend donc le premier octet des données qui suivent.


### Question 11 D

La différence entre le numéro de séquence du paquet envoyé et le numéro d’acquittement du paquet reçu est :<br/><br/>
1 - 21= -20 <br/>

Ce résultat correspond au nombre d'octets(20 octets) envoyés au "serveur" dans le premier segment.

Cela montre aussi que le numéro D'acquittement = (numéro séquence du message reçu + longueur du message reçu)

---

### Question 12

Le champ Recv-Q vaut 20 pour la socket S3 et vaut 0 pour la socket S1.<br/>
En effet, le "serveur" a bien reçu les 20 octets envoyés par le "client".

![Image Q12](TP1/images/tcp/Q12.png "Image Q12")

---

### Question 13

id_socket est l'identifiant renvoyé par la commande accept, soit la socket S3.

![Image Q13](TP1/images/tcp/Q13.png "Image Q13")

---

### Question 14

La valeur du champ Recv-Q vaut maintenant 0 pour les sockets S1 et S3.

![Image Q14](TP1/images/tcp/Q14.png "Image Q14")

---

### Question 15

On exécute la commande :

```bash
socklab> shutdown 3 out
```

On constate que deux segments ont été échangés.<br/>
Le premier segment provient de la socket S1. Il contient un FIN qui demande la fin de la connexion, ainsi qu'un ACK.<br/>
Le deuxième segment provient de la socket S3. Il contient également un ACK.<br/>
Lorsqu'on essaie de renvoyer un message de la socket S1 à la socket S3 il y a une erreur cette fois-ci.<br/>
Pour résumer, la socket S1 demande une fin de connexion à la socket S3, qui en prend connaissance et qui lui renvoie un accusé de réception.

![Image Q15A](TP1/images/tcp/Q15A.png "Image Q15A")

![Image Q15B](TP1/images/tcp/Q15B.png "Image Q15B")

---

### Question 16

![Image Q16](TP1/images/tcp/Q16.png "Image Q16")

---

### Question 17

On exécute la commande :

```bash
socklab> shutdown 5 out
```

On constate que deux segments ont été échangés.<br/>
Le premier segment provient de la socket S3. Il contient un FIN qui demande la fin de la connexion, ainsi qu'un ACK.<br/>
Le deuxième segment provient de la socket S1. Il contient également un ACK.<br/>
Pour résumer, la socket S3 demande une fin de connexion à la socket S1, qui en prend connaissance et qui lui renvoie un accusé de réception.

La commande shutdown out permet donc de fermer la connexion en sortie uniquement, ce qui veut dire que la socket peut toujours recevoir des données, pour pouvoir fermer la connexion dans les deux sens il faut soit shudown out dans les deux sockets (comme ce que l'on vient de faire) ou alors de faire un shutdown both qui ferme la connexion en entrée et en sortie.

![Image Q17](TP1/images/tcp/Q17.png "Image Q17")

---

### Question 18

Voici une capture d'écran de Wireshark comprenant l'ensemble de la session de communication.

![Image Q18](TP1/images/tcp/Q18.png "Image Q18")

---

### Question 18 A
Précision importante concernant le schéma : <br/>

- Nous avons représenté l'ensemble de la session de communication il y a donc 13 segments qui ont été transmis.
Pour faciliter la lecture du schéma j'ai coloré les flèches avec différentes couleurs : <br/>
Le bleu correspond à une demande d'établissements de connexions, ainsi que l'accusé de réception (SYN / SYN ACK / ACK).<br/>
Le rouge correspond à un refus d'une demande de connexion (dans notre cas cela représente la tentative de connecter s1 à s2 quand s2 n'était pas encore en écoute) .<br/>
le vert correspond à l'émission d'un message ainsi que l'accusé de réception du message (acquittement) (Nous avons envoyé deux messages il y a donc quatre segments verts(deux messages + deux accusés)).<br/>
l'orange correspond à une demande de fermeture de connexion(FIN) ainsi que l'accusé de réception pour cette demande de fermeture.<br/>

- La longueur du premier message est de 20 octets et celui du 2eme est de 18 octets

- J'ai choisi arbitrairement d'initialiser les numéros de séquence à 0 bien évidemment ce numéro est normalement aléatoire.

![Image Q18A](TP1/images/tcp/Q18A.png "Image Q18A")

---

### Question 18 B

Si on retire les deux premiers segments qui correspondent à l'échec de la connexion, 11 segments ont été transmis.<br/>
3 pour établir la connexion, 2 pour envoyer le premier message, 2 pour fermer la connexion "client - serveur", 2 pour envoyer le second message, et 2 pour fermer la connexion "serveur - client".<br/>
C'est 9 segments de plus qu'avec le protocole UDP.

---

### Question 18 C

L'efficacité du protocole est le rapport entre la taille des messages en octets et la taille totale des données transmises pour véhiculer ces messages :<br/><br/>
(20+18) / (74+74+66+86+66+66+66+84+66+66+66) = 0.04871795<br/><br/>
Le protocole TCP est donc beaucoup moins efficace que le protocole UDP qui avait une efficacité de 0.31147541.

---

### Question 19

On ferme toutes les sockets avec les commandes :

```bash
socklab> close 3
socklab> close 4
socklab> close 5
```

## Retransmissions et contrôle de flux

### Question 1

On ouvre une connexion entre S1 et S2

![Image Q1](TP1/images/Retransmissions_controle_flux/Q1.png "Image Q1")

### Question 2 et 3

On peut constater que le message a bien était envoyé.

![Image Q2_1](TP1/images/Retransmissions_controle_flux/Q2_1.png "Image Q2_1")

On peut d'ailleurs le lire depuis S2

![Image Q2_2](TP1/images/Retransmissions_controle_flux/Q2_2.png "Image Q2_2")

On peut alors ce dire que tout est normal, mais en analysant la trame sur Wireshark nous voyons que S1 essaye de retransmettre le message qu'il a envoyé.

![Image Q2_3](TP1/images/Retransmissions_controle_flux/Q2_3.png "Image Q2_3")

Ce qui se passe c'est que la commande

```bash
$ -A INPUT -p tcp --dport 3000 --tcp-flags ACK ACK -j DROP
```

Jette tous les paquets en entrée du port 3000 contenant un ACK. Ce qui signifie qu'après un certain temps après avoir envoyé le message, S1 ne reçoit toujours pas d'ACK et pense donc que le message n'a pas été reçu par le destinataire il retransmet donc le message. Cette fois-ci le destinataire rejette le paquet car il comprend qu'il l'a déjà reçu et envoie encore un ACK, la même chose se passe donc en boucle. à partir d'un certain moment S1 arrête de retransmettre le message.

### Question 4

On constate que S1 reçoit l'ACK et arrête donc de retransmettre le message, la commande :

```bash
$ sudo iptables -F
```

Permets de supprimer les règles.

### Question 5

On exécute la commande :

```bash
socklab> send <id_S1> hello -loop
```

![Image Q5](TP1/images/Retransmissions_controle_flux/Q5.png "Image Q5")

le message s'envoie en boucle.

### Question 6

On peut voir que les buffers d'émission et réception sont pleins

![Image Q6](TP1/images/Retransmissions_controle_flux/Q6.png "Image Q6")

cela est dû au fait on envoie des messages en boucle, le récepteur n'a pas le temps des traitres et donc les places dans le buffer de réception mais une fois que son buffer est plein il l'indique qu'il ne peut pas recevoir de message. L'émetteur doit donc attendre que le buffer de réception se vide mais en attendant il continue d'essayer de remplir des messages et le place dans la pile d'émission.

### Question 7 & 8

on peut constater que la socket 1 envoie les messages qui étaient en attente dans son buffer d'émission.

![Image Q7](TP1/images/Retransmissions_controle_flux/Q7.png "Image Q7")

### Question 9 a)

le destinataire envoie un segment ACK indiquant que la taille de sa fenêtre (window size) est de 0. Quand le destinataire reçoit ce segment il comprend alors qu'il ne peut plus émettre de nouveau segment.

![Image Q9_A](TP1/images/Retransmissions_controle_flux/Q9_A.png "Image Q9_A")

Ici nous pouvons voir que le champ window size value est de 0.

### Question 9 b)

en attendant de pouvoir émettre à nouveau S1 envoi des segments TCP "keep alive" permettant de maintenir la connexion.

![Image Q9_B](TP1/images/Retransmissions_controle_flux/Q9_B.png "Image Q9_B")

### Question 9 c)

Une fois le buffer de réception vidé, le récepteur va envoyer un ACK avec cette fois-ci une taille de fenêtre différente de 0. Permettant à l'émetteur de continuer à envoyer des segments.

### Question 9 d)

Non on peut voir sur le screen qu'il y a beaucoup plus d'envoi de messages que d'accusé de réception.

![Image Q9_d](TP1/images/Retransmissions_controle_flux/Q9_d.png "Image Q9_d")

en réalité en TCP pour minimiser le nombre d'acquittements échangés le protocole permet d'acquitter plusieurs segments à la fois. Le destinataire va acquitter le dernier segment pour indiquer que tous les segments du rang inférieur sont bien reçus.

### Question 9 e)

La taille de la fenêtre de réception du destinataire varie en fonction de la quantité d'espace libre dans son buffer de réception

### Question 10

```bash
socklab> close 3
socklab> close 4
socklab> close 5
```
