import Test.QuickCheck
import Control.Concurrent (threadDelay)
import Data.Maybe (isJust)

main :: IO ()
main = mapM_ ecrit arbres
      where ecrit a = do writeFile "arbre.dot" a
                         threadDelay 1000000
            arbres  = arbresDot "gcfxieqzrujlmdoywnbakhpvst"

--Q1
data Arbre c v = Feuille
                | Noeud c v (Arbre c v) (Arbre c v)
     deriving (Show,Eq)


instance (Arbitrary c,Arbitrary v) => Arbitrary (Arbre c v) where
    arbitrary = sized go
       where go n | n <=0 = pure Feuille
             go n = oneof[ pure Feuille
                           , do c <- arbitrary
                                v <- arbitrary
                                g <- go (n-1)
                                d <- go (n-1)
                                pure(Noeud c v g d)
                          ]

instance Arbitrary Couleur
                    where arbitrary = oneof [pure R,pure N]


--Q2
mapArbre :: (c->c2) -> (v->v2) -> Arbre c v -> Arbre c2 v2
mapArbre _ _ Feuille = Feuille
mapArbre f g (Noeud c v xs ys) = Noeud (f c) (g v) (mapArbre f g xs) (mapArbre f g ys)

--Q3
hauteur :: Arbre c v -> Int
hauteur Feuille = 0
hauteur (Noeud _ _ xs ys) = 1 + max (hauteur xs) (hauteur ys)

taille :: Arbre c v -> Int
taille Feuille = 0
taille (Noeud _ _ xs ys) = 1 + taille xs + taille ys

--Q4
dimension :: Num t => (t -> t -> t) -> Arbre c v -> t
dimension _ Feuille = 0
dimension op (Noeud _ _ xs ys) = 1 + op (dimension op xs) (dimension op ys)

hauteur' :: Arbre c v -> Int
hauteur' = dimension max

taille' :: Arbre c v -> Int
taille' = dimension (+)

--Q5
peigneGauche :: [(c,a)] -> Arbre c a
peigneGauche [] = Feuille
peigneGauche ((c,a):xs) = Noeud c a (peigneGauche xs) Feuille

-- Elle vérifie que la hauteur de l'arbre créé vaut bien le nombre de couples (c,a) donc le noeud que l'on veut créer
--Q6
prop_hauteurPeigne :: [(c, v)] -> Bool
prop_hauteurPeigne xs = length xs == hauteur (peigneGauche xs)


--Q7
prop_mapArbre::[(c,a)] -> Bool
prop_mapArbre xs = taille arbre == taille (mapArbre id id arbre)
       where arbre = peigneGauche xs

prop_hauteur'Peigne :: [(c, v)] -> Bool
prop_hauteur'Peigne xs = length xs == hauteur' (peigneGauche xs)

prop_tailleArbre :: Arbre c a -> Bool
prop_tailleArbre Feuille = True
prop_tailleArbre xs =  ((2^hauteur xs) - 1) >= taille xs

prop_taille'Peigne :: [(c, v)] -> Bool
prop_taille'Peigne xs = length xs == taille' (peigneGauche xs)

--Q8) 
estComplet :: Arbre c a -> Bool
estComplet Feuille = True
estComplet (Noeud _ _ g d ) = estComplet g && estComplet d && hauteur g == hauteur d

estComplet' :: Arbre c a -> Bool
estComplet' = isJust . go
      where go :: Arbre c v -> Maybe Int
            go Feuille = Just 0
            go (Noeud _ _ g d )= case(go g ,go d) of
                                    (Just n,Just m) -> if n==m
                                                       then Just (n+1)
                                                       else Nothing
                                    _                -> Nothing
--Q9)


--Q10) : Les peignes à gauche complets sont les arbres vides et les arbres a un élément. On peut tester cela avec QuickCheck.
prop_estComplet :: [(c, a)] -> Bool
prop_estComplet [] = True
prop_estComplet [_] = True
prop_estComplet xs = not( estComplet (peigneGauche xs))


--Q11 
complet :: Int -> [(c, a)] -> Arbre c a
complet 0 _ = Feuille
complet _ [] = error "Ceci n'est pas un arbre complet"
complet 1 [(c,v)]= Noeud c v Feuille Feuille
complet n xs = Noeud c v (complet (n-1) liste1) (complet (n-1) liste2)
            where c = fst(xs !! quot (length xs)  2)     -- On remarque qu'il faut prendre la valeur se trouvant au milieu de la liste Exemple :
                  v = snd(xs !! quot (length xs)  2)     -- A B C D E F G [H] I J K L M N O   |TAILLE = 15 | N = 4           
                  liste1= take (quot (length xs) 2) xs   -- A B C [D] E F G   ET   I J K [L] M N O |TAILLE = 7 | N = 3
                  liste2= drop (quot (length xs+1) 2) xs -- A [B] C | E [F] G ET I [J] K | M [N] O  |TAILLE = 3 | N = 2
                                                         --   A  C     E   G      I   K    M     O    TAILLE = 1 | N = 1   taille = (2^n -1) Feuille = 2n

--Q12 : La fonction voulue est repeat
repeat' :: a -> [a]
repeat' = repeat

--Q13
createList :: [((), Char)]
createList = zip (repeat' ()) ['a'..]

--Q14 
aplatit :: Arbre c a -> [(c, a)]
aplatit Feuille = []
aplatit (Noeud c v xs ys) = aplatit xs ++ [(c,v)]  ++ aplatit ys

complet4 :: Arbre Integer Char
complet4 =Noeud 1 'h' (Noeud 2 'd' (Noeud 3 'b' (Noeud 4 'a' Feuille Feuille) (Noeud 4 'c' Feuille Feuille)) (Noeud 3 'f' (Noeud 4 'e' Feuille Feuille) (Noeud 4 'g' Feuille Feuille))) noeud2
            where noeud2 = Noeud 2 'l' (Noeud 3 'j' (Noeud 4 'i' Feuille Feuille) (Noeud 4 'k' Feuille Feuille)) (Noeud 3 'n' (Noeud 4 'm' Feuille Feuille) (Noeud 4 'o' Feuille Feuille))

prop_applatit :: Bool
prop_applatit = map snd (aplatit complet4) == "abcdefghijklmno"

--Q15
element :: Eq a => a -> Arbre c a -> Bool
element _ Feuille = False
element elmt (Noeud _ v xs ys) | elmt == v = True
                               | otherwise = element elmt xs || element elmt ys

prop_element :: Arbre c Char -> Bool
prop_element xs = elem 'b' (map snd (aplatit xs))  == element 'b' xs
            
--Q16 
noeud :: (c -> String) -> (a -> String) -> (c,a) -> String
noeud fc fv (c,v) = fv v ++ " [color=" ++ couleur ++ ", fontcolor=" ++ couleur ++ "]"
  where couleur = fc c

--Q17
arcs :: Arbre c a -> [(a,a)]
arcs Feuille = []
arcs (Noeud _ _ Feuille Feuille) = []
arcs (Noeud _ v xs@(Noeud _ vFils _ _) Feuille) = (v,vFils) : arcs xs
arcs (Noeud _ v Feuille ys@(Noeud _ vFils _ _)) = (v,vFils) : arcs ys
arcs (Noeud _ v xs@(Noeud _ vFilsxs _ _) ys@(Noeud _ vFilsys _ _)) = [(v, vFilsxs), (v,vFilsys)] ++ arcs xs ++ arcs ys

--Q18
arc :: (a -> String) -> (a,a) -> String
arc f (v1, v2) = f v1 ++ " -> " ++ f v2

--Q19
dotise :: String -> (c -> String) -> (a -> String) -> Arbre c a -> String
dotise nom fCouleur conversionValeur arbre = unlines result
 where result = [entete, listeNoeuds, listeArcs, fin]
       entete = unlines ["digraph \"" ++ nom ++ "\" {", "node [fontname=\"DejaVu-Sans\", shape=circle]"]
       listeNoeuds = unlines [ noeud fCouleur conversionValeur x | x <- aplatit arbre]
       listeArcs = unlines [ arc conversionValeur x | x <- arcs arbre]
       fin = "}"


--Q20 
elementR :: Ord a => a -> Arbre c a -> Bool
elementR _ Feuille = False
elementR elmt (Noeud _ v xs ys) | elmt == v = True
                                | elmt < v = elementR elmt xs
                                | otherwise = elementR elmt ys

--Q21
data Couleur = R | N deriving Show
type ArbreRN = Arbre Couleur

conversionCouleur :: Couleur -> String
conversionCouleur R = "red"
conversionCouleur N = "black"

valeurToString :: (Show a) => a -> String
valeurToString valeur = filtreValue (show valeur)
   where filtreValue :: String -> String
         filtreValue  = filter  (`notElem` "'")

showC :: Show c => Arbre c val -> String
showC (Noeud c _ _ _) = show c
showC Feuille = ""


--Q22 
equilibre :: ArbreRN a -> ArbreRN a
equilibre Feuille = Feuille
equilibre (Noeud _ z (Noeud R y (Noeud R x a b) c) d) = Noeud R y (Noeud N x a b) (Noeud N z c d)
equilibre (Noeud _ z (Noeud R x a (Noeud R y b c)) d) = Noeud R y (Noeud N x a b) (Noeud N z c d)
equilibre (Noeud _ x a (Noeud R z (Noeud R y b c) d)) = Noeud R y (Noeud N x a b) (Noeud N z c d)
equilibre (Noeud _ x a (Noeud R y b (Noeud R z c d))) = Noeud R y (Noeud N x a b) (Noeud N z c d)
equilibre arbre = arbre

--Q23
racineEnNoir :: ArbreRN a -> ArbreRN a
racineEnNoir Feuille = Feuille
racineEnNoir (Noeud _ v fg fd) = Noeud N v fg fd

insertion :: Ord a => a -> ArbreRN a -> ArbreRN a
insertion valeur arbre = racineEnNoir (ins valeur arbre)
  where ins v Feuille = Noeud R v Feuille Feuille -- arbre est vide, l’arbre résultat contient un seul nœud, rouge, portant la valeur 
        ins v (Noeud c r xs ys) | v == r = Noeud c r xs ys -- la valeur est déjà dans l’arbre, elle n’est pas ajoutée
                                | v < r = equilibre (Noeud c r (ins v xs) ys)  --
                                | otherwise = equilibre (Noeud c r xs (ins v ys))

--Q24 

-- La racine doit être en noir.
prop_racine_noire :: Show c => Arbre c val -> Bool
prop_racine_noire Feuille = True 
prop_racine_noire xs = showC xs == show N

prop_racine_noire' :: ArbreRN val -> Bool
prop_racine_noire' Feuille = True 
prop_racine_noire' (Noeud N _ _ _) = True 
prop_racine_noire' (Noeud R _ _ _) = False 

-- Un nœud rouge ne doit pas avoir de fils rouge.
prop_filsRouge :: Show a => Arbre a val -> Bool
prop_filsRouge Feuille = True
prop_filsRouge (Noeud c v fg fd) | showC (Noeud c v fg fd) == show N = prop_filsRouge fg && prop_filsRouge fd
                                 | showC fg == show R = False
                                 | showC fd == show R = False
                                 | otherwise = prop_filsRouge fg && prop_filsRouge fd


-- Si l'arbre est aplati, la liste doit être triée dans l'ordre croissant
prop_arbre_rouge_noir :: Ord v => ArbreRN v -> Bool
prop_arbre_rouge_noir arbre = arbreRougNoir (map snd (aplatit arbre))

arbreRougNoir :: (Ord a) =>  [a] -> Bool
arbreRougNoir [] = True
arbreRougNoir [_] = True
arbreRougNoir (x:xs@(y:_)) = (x<=y) && arbreRougNoir xs

--Q25 --Comparaison de valeurs
(====) :: Ord a => ArbreRN a -> ArbreRN a -> Bool
(====) Feuille Feuille = True
(====) (Noeud R v1 fg fd) (Noeud R v2 fg' fd') = v1 == v2 &&  (fg ==== fg')  &&  (fd ==== fd')
(====) (Noeud N v1 fg fd) (Noeud N v2 fg' fd') = v1 == v2 &&  (fg ==== fg') &&  (fd  ==== fd')
(====) _ _ = False

--Q25 -- Cette fonction renvoie True si les arbres sont égaux après avoir été rééquilibré
(~===) :: Ord a => ArbreRN a -> ArbreRN a -> Bool
(~===) a1 a2 = (====) (equilibre a1) (equilibre a2)


--Q26
arbresDot :: String -> [String]
arbresDot [] = []
arbresDot xs = map (dotise "arbre" conversionCouleur valeurToString) (arbresDot' xs Feuille)

arbresDot' :: (Ord a) => [a] -> ArbreRN a -> [ArbreRN a]
arbresDot' [] _ = []
arbresDot' (x:xs) arbre = ins : arbresDot' xs ins
                          where ins = insertion x arbre
