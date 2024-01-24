import Graphics.Gloss ( display, white, Display(InWindow), Picture)
import LsystemTortue.Tortue ( interpreteMot )


main :: IO ()
main = display (InWindow "L-système" (1000, 1000) (0, 0)) white dessin

-- Exemple d'image avec le flocon de von Koch
dessin :: Picture
dessin = interpreteMot (((-150,0),0),100,1,pi/3,"F+-") "F+F--F+F"






