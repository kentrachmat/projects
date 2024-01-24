
Intero BDD2
41924651
Benedictus Kent RACHMAT


Cet exercice je  modéliser une base de données sur des Festivals et jeux de société.
Ici j'ai modelisé la classe personne herité les rôles comme participant, employé, et author (et chaque personne avoir leurs id, nom, prenom, email et pays).
Pour les participants, ils doivent avoir des billet pour y accéder. 
après les participant avoir leur authorization il peut accéder à la festival.
Dans le festival il ya 0 ou plusieur session qui peut lancer 1 ou plusieurs jeux (un jeu de société est décrit par son titre et la catégorie) et qui été créé par les autheurs. L'organisation d'un festival est sous la responsabilité d’un employé aussi.

(Composition) Si le festival et detruit (en BDD) le session aussi, même avec session - jeux, auther- jeux

Revisions:
Autheur 1 - 1..* Jeux


Je suis désolé pour le diagramme laid car je dois l'écrire sur papier à cause de l'erreur de genmysmodel (et si j'ai ecrit mal car je suis étudiant étranger)