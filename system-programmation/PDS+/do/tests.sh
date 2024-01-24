#!/bin/bash
# This script will test the return of do
# Usage: ./tests.sh 
 
# Main script starts here 

## Test the return
make
echo -e "\n --------[Partie 1/7] test one commande -------"

### Cas une commande
./do "./sleepAndDisplay true 0"
echo  -e "\n Valeur de retour : $? Commande terminé \n "
./do "./sleepAndDisplay false 0" 
echo  -e "\n Valeur de retour : $? Commande terminé \n "

### Cas -o
echo -e "\n --------[Partie 2/7] test -o -------"
./do -o "./sleepAndDisplay true 0"  "./sleepAndDisplay true 0"   || echo "Erreur: or true true a renvoyé false"
echo  -e "\n Valeur de retour : $? Commande terminé \n "
./do -o "./sleepAndDisplay true 0"  "./sleepAndDisplay false 0"  || echo "Erreur: or true false a renvoyé false"
echo  -e "\n Valeur de retour : $? Commande terminé \n "
./do -o "./sleepAndDisplay false 0" "./sleepAndDisplay true 0"   || echo "Erreur: or false true a renvoyé false"
echo  -e "\n Valeur de retour : $? Commande terminé \n "
./do -o "./sleepAndDisplay false 0" "./sleepAndDisplay false 0"   && echo "Erreur: or false false a renvoyé true"
echo  -e "\n Valeur de retour : $? Commande terminé \n "

### Cas -a
echo -e "\n --------[Partie 3/7] test -a -------"
./do -a "./sleepAndDisplay true 0"  "./sleepAndDisplay true 0"   || echo "Erreur: or true true a renvoyé false"
echo  -e "\n Valeur de retour : $? Commande terminé \n "
./do -a "./sleepAndDisplay true 0"  "./sleepAndDisplay false 0"  && echo "Erreur: or true false a renvoyé true"
echo  -e "\n Valeur de retour : $? Commande terminé \n "
./do -a "./sleepAndDisplay false 0" "./sleepAndDisplay true 0"   && echo "Erreur: or false true a renvoyé true"
echo  -e "\n Valeur de retour : $? Commande terminé \n "
./do -a "./sleepAndDisplay false 0" "./sleepAndDisplay false 0"  && echo "Erreur: or false false a renvoyé true"
echo  -e "\n Valeur de retour : $? Commande terminé \n "


### Cas -a -c 
echo -e "\n --------[Partie 4/7] test -a -c -------"
./do -a -c  "./sleepAndDisplay true 0"  "./sleepAndDisplay true 1"   || echo "Erreur: or true true a renvoyé false"
echo  -e "\n Valeur de retour : $? Commande terminé \n "

./do -a -c  "./sleepAndDisplay true 0"  "./sleepAndDisplay false 1"   && echo "Erreur: or true false a renvoyé true"
echo  -e "\n Valeur de retour : $? Commande terminé \n "

./do -a  -c "./sleepAndDisplay false 0"  "./sleepAndDisplay true 1"   && echo "Erreur: or false true a renvoyé true"
echo  -e "\n Valeur de retour : $? Commande terminé"
sleep 2
echo  -e "\n"

./do -a  -c "./sleepAndDisplay false 0"  "./sleepAndDisplay false 1"  && echo "Erreur: or false false a renvoyé true"
echo  -e "\n Valeur de retour : $? Commande terminé"
sleep 2
echo  -e "\n" 


### Cas -o -c
echo -e "\n --------[Partie 5/7] test -o -c -------"
./do -o  -c "./sleepAndDisplay false 0"  "./sleepAndDisplay false 1"  && echo "Erreur: or false false a renvoyé true"
echo  -e "\n Valeur de retour : $? Commande terminé \n "

./do -o -c "./sleepAndDisplay false 0"  "./sleepAndDisplay true 1"   || echo "Erreur: or false true a renvoyé true"
echo  -e "\n Valeur de retour : $? Commande terminé \n"


./do -o -c  "./sleepAndDisplay true 0"  "./sleepAndDisplay false 1"   || echo "Erreur: or true false a renvoyé true"
echo  -e "\n Valeur de retour : $? Commande terminé"
sleep 2
echo  -e "\n"


./do -o -c  "./sleepAndDisplay true 0"  "./sleepAndDisplay true 1"   || echo "Erreur: or true true a renvoyé false"
echo  -e "\n Valeur de retour : $? Commande terminé"
sleep 2
echo  -e "\n"

### Cas -a -k
echo -e "\n --------[Partie 6/7] test -a -k -------"
./do -a -k  "./sleepAndDisplay true 0"  "./sleepAndDisplay true 1"   || echo "Erreur: or true true a renvoyé false"
echo  -e "\n Valeur de retour : $?  Commande terminé \n "

./do -a  -k "./sleepAndDisplay false 0"  "./sleepAndDisplay true 1"   && echo "Erreur: or false true a renvoyé true"
echo  -e "\n Valeur de retour : $? Commande terminé"
sleep 2
echo  -e " "

./do -a -k  "./sleepAndDisplay true 0"  "./sleepAndDisplay false 1"   && echo "Erreur: or true false a renvoyé true"
echo  -e "\n Valeur de retour : $? Commande terminé\n"

./do -a  -k "./sleepAndDisplay false 0"  "./sleepAndDisplay false 1"  && echo "Erreur: or false false a renvoyé true"
echo  -e "\n Valeur de retour : $? Commande terminé"
sleep 2

### Cas -o -c
echo -e "\n --------[Partie 7/7] test -o -c -------"
./do -o  -k "./sleepAndDisplay false 0"  "./sleepAndDisplay false 1"  && echo "Erreur: or false false a renvoyé true"
echo  -e "\n Valeur de retour : $? Commande terminé \n "

./do -o -k "./sleepAndDisplay false 0"  "./sleepAndDisplay true 1"   || echo "Erreur: or false true a renvoyé true"
echo  -e "\n Valeur de retour : $? Commande terminé \n "


./do -o -k  "./sleepAndDisplay true 0"  "./sleepAndDisplay false 1"   || echo "Erreur: or true false a renvoyé true"
echo  -e "\n Valeur de retour : $? Commande terminé"
sleep 2
echo  -e " "


./do -o -k  "./sleepAndDisplay true 0"  "./sleepAndDisplay true 1"   || echo "Erreur: or true true a renvoyé false"
echo  -e "\n Valeur de retour : $? Commande terminé"
sleep 2
echo  -e "\n" 