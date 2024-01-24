# Sujet

[Le sujet de TP1 2022](https://moodle.univ-lille.fr/pluginfile.php/1317424/mod_resource/content/1/sujetTDD.pdf)


# Equipe

Ce travail est à réaliser en équipe dont les membres sont (**groupe 3 du S6 Licence 3 Informatique**) :

- BENEDICTUS KENT **RACHMAT**
- HICHEM **KARFA**


# Organisation du projet et Arborescence du projet

Le projet se répartir de la façon suivante :

- Une archive _<strong>junit5.jar </strong>_  permettant d'exécuter les tests sans utiliser Maven.
- Un fichier _<strong>Makefile</strong>_ contenant des instructions pour créer les classes, doc, etc.
- Un fichier _<strong>pom.xml </strong>_ contentant les dépendances du projet.
- Un dossie _<strong>src</strong>_ contenant un dossier _<strong>main</strong>_  et un dossier _<strong>test</strong>_ contenant respectivement les fichiers sources du projets et les fichiers sources des tests du projet .
- Un dossier _<strong>target</strong>_  ce dossier est généré à l'aide de la commande mvn, il contient les différents fichiers générés par mvn (.class, jar).

```bash  
.
├── junit5.jar
├── Makefile
├── pom.xml
├── README.md
├── src
│   ├── main
│   │   └── java
│   └── test
│       └── java
└── target
```


## 1) Récupération du projet

Récupérer le projet avec HTTPS :

```bash
$ > git clone https://gitlab-etu.fil.univ-lille1.fr/rachmat/gl_g3_2022.git
```

Récupérer le projet avec SSH :

```bash
$ > git clone git@gitlab-etu.fil.univ-lille1.fr:rachmat/gl_g3_2022.git
```

## 2) Génération de la documentation

Pour générer la documentation, placez-vous dans la racine du projet (`gl_g3_2022`) et exécutez la commande :

```bash
$ > make doc
```

Pour consulter la documentation, placez-vous dans le dossier docs qui vient d'être généré à la racine et ouvrez le fichier index.html dans un navigateur.

## 3.1) Compilation du projet (Avec Maven)

Pour compiler le projet, placez-vous dans la racine du projet et exécutez la commande :

```bash
$ > mvn compile
```

## 3.2) Compilation du projet (Sans Maven)

Pour compiler le projet, placez-vous dans la racine du projet et exécutez la commande :

```bash
$ > make cls
```

## 4.1) Exécution des tests (Avec Maven)

```bash
$ > mvn test
```

## 4.2) Exécution des tests (Sans Maven)

```bash
$ > javac -d classes -cp classes:junit5.jar src/test/java/banque/*.java
$ > java -jar junit5.jar --class-path classes --scan-class-path
```

## 5) Nettoyage de projet

Pour nettoyer le répertoire du projet placez-vous dans la racine du projet, exécutez la commande ci-dessous :

```bash
$ > make clean
```

## 6) Génération de l'archive compresser du projet (zip)

Placez-vous dans la racine du projet, exécutez la commande ci-dessous :

```bash
$ > make archive
```
