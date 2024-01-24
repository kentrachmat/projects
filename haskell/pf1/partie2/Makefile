all : dragon dragonAlternative

dragon: dragon.hs
	ghc -dynamic -Wall dragon.hs 

dragonAlternative: dragonAlternative.hs
	ghc -dynamic -Wall dragonAlternative.hs

clean :
	-rm *.o *.hi dragon dragonAlternative
 
.PHONY: all
