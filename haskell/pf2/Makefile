all : floconDeVonKoch lsystemeAnime tortueVolante

floconDeVonKoch : floconDeVonKoch.hs
	ghc --make -dynamic floconDeVonKoch.hs -o floconDeVonKoch

lsystemeAnime : lsystemeAnime.hs
	ghc --make -dynamic lsystemeAnime.hs -o lsystemeAnime
	
tortueVolante : tortueVolante.hs
	ghc --make -dynamic tortueVolante.hs -o tortueVolante

clean :
	-rm -f *.o *.hi floconDeVonKoch lsystemeAnime tortueVolante ./LsystemTortue/*.o ./LsystemTortue/*.hi 

.PHONY: all 
