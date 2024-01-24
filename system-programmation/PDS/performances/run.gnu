set title "Performance"
set term png
set output "images/performance.png"
set logscale x 2
set xlabel "taille du tampon en octets"
set logscale y
set ylabel "temps d’exécution de la commande en seconde"
set style data linespoints
plot "resultat.dat" using 1:2 title "cat-taille-tampon", \
     "resultat2.dat" using 1:2 title "mcat-lib"
pause -1  "Hit return to continue"
