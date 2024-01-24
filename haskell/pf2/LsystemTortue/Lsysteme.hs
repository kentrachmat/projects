module LsystemTortue.Lsysteme
( 
lsysteme,LSysteme,Regles
) 
where

type Symbole  = Char
type Mot      = [Symbole]
type Axiome   = Mot
type Regles   = Symbole -> Mot
type LSysteme = [Mot]

-- Mot suivant version recursive
motSuivant :: Regles -> Mot -> Mot
motSuivant _ [] = []
motSuivant f (y:ys) = f y ++ motSuivant f ys

-- Mot suivant version curryfié avec la fonction concatMap
motSuivant' :: Regles -> Mot -> Mot
motSuivant' = concatMap

-- Mot suivant version liste en compréhension
motSuivant'' :: Regles -> Mot -> Mot
motSuivant'' f mot = concat[f x | x <- mot]

lsysteme :: Axiome -> Regles -> LSysteme
lsysteme [] _ = []
lsysteme x f = motSuivant f x : lsysteme (motSuivant f x) f
 

-- Exemple de L-systeme : avec l'alphabet {F,+,-} et l'axiome u0 = F
floconDeKoch :: Regles
floconDeKoch 'F' = ['F','-','F','+','+','F','-','F']
floconDeKoch '+' = ['+']
floconDeKoch '-' = ['-']
floconDeKoch _ = error "Symbole inconnu"