module Main where

import Graphics.Gloss
    ( white, animate, Display(InWindow), Path, Picture(Line), Point )

main :: IO ()
main = animate (InWindow "Dragon" (500, 500) (0, 0)) white (dragonAnime (50,250) (450,250))

dragonAnime :: RealFrac a => Point -> Point -> a -> Picture
dragonAnime a b t = Line (dragon a b !! (round t `mod` 20))

pointAintercaler :: Point -> Point -> Point 
pointAintercaler (xa,ya) (xb,yb) = ((xa+ xb)/2 + (yb - ya)/2, (ya + yb)/2 + (xa - xb)/2)


-- calcule la courbe à l’itération suivante à partir de la courbe d’une itération, elle suit la regle suivante pour une liste avec trois points(a,b,c) :
-- (a,a-b,b,c-b,c)
pasDragon :: Path -> Path
pasDragon [] = []
pasDragon [a] = [a]
pasDragon [a,b] = [a, pointAintercaler a b, b]
pasDragon (a:b:xs@(c:_)) = a : pointAintercaler a b : b : pointAintercaler c b : pasDragon xs


-- calcule la liste des différentes itérations de la courbe du dragon créée entre les deux points donnés.
dragon :: Point -> Point -> [Path]
dragon a b = iterate pasDragon [a,b]