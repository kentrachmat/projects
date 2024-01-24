module LsystemTortue.Tortue
( interpreteMot
, Config
, Mot
, Symbole
, EtatTortue
) where

import Graphics.Gloss (Path,Point,Picture(Line),)

--Synonyme
type Symbole  = Char
type Mot      = [Symbole]
type EtatTortue = (Point, Float)
type EtatDessin = (EtatTortue, Path)
type Config = (EtatTortue -- État initial de la tortue
              ,Float      -- Longueur initiale d’un pas
              ,Float      -- Facteur d’échelle
              ,Float      -- Angle pour les rotations de la tortue
              ,[Symbole]) -- Liste des symboles compris par la tortue


-- Information de la tortue 
etatInitial :: Config -> EtatTortue
etatInitial (etat,_,_,_,_) = etat

longueurPas :: Config -> Float
longueurPas (_,longueur,_,_,_) = longueur

facteurEchelle :: Config -> Float
facteurEchelle (_,_,facteur,_,_) = facteur

angle :: Config -> Float
angle (_,_,_,angleTortue,_) = angleTortue

symbolesTortue :: Config -> [Symbole]
symbolesTortue (_,_,_,_,liste) = liste

-- Action de la tortue 
avance :: Config -> EtatTortue -> EtatTortue
avance conf ((x,y),cap) = ((x + longueurPas conf * cos cap, y + longueurPas conf * sin cap), cap)

tourneAGauche :: Config -> EtatTortue -> EtatTortue
tourneAGauche conf (point,cap) = (point, cap + angle conf)

tourneADroite :: Config -> EtatTortue -> EtatTortue
tourneADroite conf (point,cap) = (point , cap - angle conf)


--Supprime tous les symboles qui ne sont pas des ordres pour la tortue (Version filtage de motif + garde )
filtreSymbolesTortue :: Config -> Mot -> Mot
filtreSymbolesTortue _ [] = []
filtreSymbolesTortue conf (x:xs) | x `elem` symbolesTortue conf = x : filtreSymbolesTortue conf xs
                                 | otherwise = filtreSymbolesTortue conf xs

--Supprime tous les symboles qui ne sont pas des ordres pour la tortue (Version liste en compréhension )
filtreSymbolesTortue' :: Config -> Mot -> Mot
filtreSymbolesTortue' conf mot = [ symbole | symbole <- mot, symbole `elem` symbolesTortue conf]

--Supprime tous les symboles qui ne sont pas des ordres pour la tortue (Version utilisant filter et l'application partiel)
filtreSymbolesTortueVersionFilter :: Config -> Mot -> Mot
filtreSymbolesTortueVersionFilter conf = filter (\x-> x `elem` symbolesTortue conf)

-- type EtatDessin = (EtatTortue, Path)
-- type EtatTortue = (Point, Float)
-- type Path = [Point]
interpreteSymbole :: Config -> EtatDessin -> Symbole -> EtatDessin
interpreteSymbole conf (etatTortue,path) 'F' =  (avance conf etatTortue, fst (avance conf etatTortue) : path)
interpreteSymbole conf (etatTortue,path) '+' =  (tourneAGauche conf etatTortue , path )
interpreteSymbole conf (etatTortue,path) '-' =  (tourneADroite conf etatTortue , path )
interpreteSymbole _ etatDessin _ =  etatDessin


-- Comment avoir une Picture ? => line :: Path -> Picture
-- Comment avoir un Path ? => snd EtatDessin :: Path
-- Comment avoir un EtatDessin ? => interpreteSymbole :: Config -> EtatDessin -> Symbole -> EtatDessin
-- Config ok , Symbole ok car Mot = [Symbole] , EtatDessin non ...
-- un Etat dessin c'est un tuple => (EtatTortue, Path) 
               --   Comment avoir un EtatTortue ? =>  etatInitial :: Config -> EtatTortue
               --   Comment avoir le Path ou [Point] => EtatTortue = (Point, Float)         
                         --   Donc [fst EtatTortue]          
-- Un fold prend une fonction binaire a deux arguments => une valeur de départ(un EtatInitial) et une liste à plier(le mot filtré).
interpreteMot :: Config -> Mot -> Picture
interpreteMot conf mot = Line (snd etatDessin)
     where etatDessin = foldl (interpreteSymbole conf) etatDessinDebut listeMotFiltre
           listeMotFiltre = filtreSymbolesTortue conf mot
           etatDessinDebut= (etatTortue, [fst etatTortue])
           etatTortue = etatInitial conf
