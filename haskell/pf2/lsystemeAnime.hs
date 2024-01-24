import Graphics.Gloss ( white, animate, Display(InWindow), Path, Picture(Line), Point )
import LsystemTortue.Tortue ( interpreteMot,Config)  
import LsystemTortue.Lsysteme (lsysteme,LSysteme,Regles)

main :: IO ()
main = animate (InWindow "L-système" (1000, 1000) (0, 0)) white dessinAnime

dessinAnime :: Float -> Picture
dessinAnime = vonKoch2Anime


lsystemeAnime :: LSysteme -> Config -> Float -> Picture
lsystemeAnime lsys (etat,longueur ,facteur,angleTortue,liste)  instant = interpreteMot (etat,longueur * (facteur ^ enieme),facteur,angleTortue,liste) ( lsys!! enieme)
                where enieme = round instant `mod` 8
      

-- Exemple 
vonKoch1Anime :: Float -> Picture
vonKoch1Anime = lsystemeAnime vonKoch1 (((-400, 0), 0), 800, 1/3, pi/3, "F+-")

vonKoch2Anime :: Float -> Picture
vonKoch2Anime = lsystemeAnime vonKoch2 (((-400, -250), 0), 800, 1/3, pi/3, "F+-")

hilbertAnime :: Float -> Picture
hilbertAnime = lsystemeAnime hilbert (((-400, -400), 0), 800, 1/2, pi/2, "F+-")

dragonAnime :: Float -> Picture
dragonAnime = lsystemeAnime dragon (((0, 0), 0), 50, 1, pi/2, "F+-")


vonKoch1 :: LSysteme
vonKoch1 = lsysteme "F" regles
    where regles 'F' = "F-F++F-F"
          regles  s  = [s]

vonKoch2 :: LSysteme
vonKoch2 = lsysteme "F++F++F++" regles
    where regles 'F' = "F-F++F-F"
          regles  s  = [s]

hilbert :: LSysteme
hilbert = lsysteme "X" regles
    where regles 'X' = "+YF-XFX-FY+"
          regles 'Y' = "-XF+YFY+FX-"
          regles  s  = [s]

dragon :: LSysteme
dragon = lsysteme "FX" regles
    where regles 'X' = "X+YF+"
          regles 'Y' = "-FX-Y"
          regles  s  = [s]