import Graphics.Gloss
    ( white, animate, Display(InWindow), Path, Picture(Line), Point )

main :: IO()
main = animate (InWindow "DragonAlternative" (500, 500) (0, 0)) white (dragonAnime (50,250) (450,250))

dragonAnime :: Point -> Point -> Float -> Picture
dragonAnime a b t = Line (dragonOrdre a b (round t `mod` 20))


pointAintercaler :: Point -> Point -> Point 
pointAintercaler (xa,ya) (xb,yb) = ((xa+ xb)/2 + (yb - ya)/2, (ya + yb)/2 + (xa - xb)/2)


--La courbe du dragon altérnative.  
--En combinant les deux sous-dragons d’ordre n, il faut que la liste des points démarre en A, passe par C et finisse en B. 
dragonOrdre :: Point -> Point -> Int -> Path
dragonOrdre a b 0 = [a,b]
dragonOrdre a b n = dragonOrdre a (pointAintercaler a b) (n-1) ++ reverse(dragonOrdre b (pointAintercaler a b) (n-1))