
import Graphics.Gloss ( white, animate, Display(InWindow), Path, Picture(Line), Point )
import LsystemTortue.Tortue (Config,Mot,Symbole,EtatTortue)
import LsystemTortue.Lsysteme(lsysteme,LSysteme)

type EtatDessin=([EtatTortue], [Path])

main :: IO ()
main = animate (InWindow "L-système" (1000, 1000) (0, 0)) white dessin

dessin :: Float -> Picture
dessin = brindilleAnime

-- Exemple :

brindilleAnime :: Float -> Picture
brindilleAnime = lsystemeAnime brindille (((0, -400), pi/2), 800, 1/3, 25*pi/180, "F+-[]")


brindille :: LSysteme
brindille = lsysteme "F" regles
    where regles 'F' = "F[-F]F[+F]F"
          regles  s  = [s]

broussailleAnime :: Float -> Picture
broussailleAnime = lsystemeAnime broussaille (((0, -400), pi/2), 500, 2/5, 25*pi/180, "F+-[]")

broussaille :: LSysteme
broussaille = lsysteme "F" regles
    where regles 'F' = "FF-[-F+F+F]+[+F-F-F]"
          regles  s  = [s]

-- Voici les fonctions qui changent :

--type EtatTortue = (Point, Float)
--type EtatDessin = ([EtatTortue], [Path])
interpreteSymbole :: Config -> EtatDessin -> Symbole -> EtatDessin
interpreteSymbole config (x:xs,y:ys) 'F' = (avance config x:xs, (fst (avance config x) : y): ys)
interpreteSymbole config (x:xs,path) '+' = (tourneAGauche config x:xs, path)
interpreteSymbole config (x:xs,path) '-' = (tourneADroite config x:xs ,path)
interpreteSymbole config (x:xs, y:ys) '[' = (x:x:xs, (fst x : y):ys)
interpreteSymbole config (x:k:xs, y:ys) ']' = (k:xs , (fst k : y):ys)


interpreteMot :: Config -> Mot -> Picture
interpreteMot conf mot = Line (head (snd etatDessin))
     where etatDessin = foldl (interpreteSymbole conf) ([etatInitial conf], [[fst (etatInitial conf)]]) (filtreSymbolesTortue conf mot)




--Fonction qui ne change pas : 
lsystemeAnime :: LSysteme -> Config -> Float -> Picture
lsystemeAnime lsys (etat,longueur ,facteur,angleTortue,liste)  instant = interpreteMot (etat,longueur * facteur ^ enieme,facteur,angleTortue,liste) ( lsys!! enieme)
                where enieme = round instant `mod` 8






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