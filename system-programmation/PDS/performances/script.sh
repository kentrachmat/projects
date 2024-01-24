#!/bin/bash
make
mkdir -p images

if [ "$#" -ne 1 ]; then
for((i = 0; i < 23; i++)); do
    let "a = 2 ** $i"
    echo -n $a  " " >> resultat.dat
    echo -n $a  " " >> resultat2.dat
    /usr/bin/time -o  resultat.dat -a -f '%e' ./cat $a texte.txt
    /usr/bin/time -o  resultat2.dat -a -f '%e' ./mcat-lib texte.txt
  done
else
for((i = 0; i < 23; i++)); do
    let "a = 2 ** $i"
    echo -n $a  " " >> resultat.dat
    echo -n $a  " " >> resultat2.dat
    /usr/bin/time -o  resultat.dat -a -f '%e' ./cat $a $1
    /usr/bin/time -o  resultat2.dat -a -f '%e' ./mcat-lib $1
  done
fi

gnuplot run.gnu
echo -e "\nVotre image se trouve dans le dossier images"



