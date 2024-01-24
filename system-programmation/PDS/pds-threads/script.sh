#!/bin/bash
make
mkdir -p images

> res.dat
for((i = 2; i < 10; i++)); do
  let "a = 10 ** $i"
  ./aleazard $a > genome
  for((j = 0; j < 13; j++)); do
    let "b = 2 ** $j"
    echo  -n -e "$a $b " >> res.dat
    /usr/bin/time -o  res.dat -a -f '%e' ./compteur-gc-multithreaded genome $b
    done
  done
gnuplot run.gnu
rm genome
echo "Votre image se trouve dans le dossier images"