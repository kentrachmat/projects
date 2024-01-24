set title "Performances"
set term png
set output "images/performances.png"
set logscale x
set logscale y
set dgrid3d 30,30
set pm3d
splot "res.dat" using 1:2:3 with lines
