import Parser
import Data.Char

--main :: IO ()
--main = _

type Nom = String

data Expression = Lam Nom Expression
                | App Expression Expression
                | Var Nom
                | Lit Litteral
                deriving (Show,Eq)

data Litteral = Entier Integer
              | Bool   Bool
              deriving (Show,Eq)

-- Q1. Définition d'un analyseur syntaxique, qui consomme tous les espaces
-- au début de la chaîne à analyser, et retourne toujours () pour ignorer ces espaces.

espacesP :: Parser ()
espacesP = do _ <- many (carQuand isSpace)
              pure ()


--Q2. Définition d'un analyseur syntaxique qui analyse le premier nom apparaissant dans le chaine

nomP :: Parser Nom
nomP = do nom <- some (carQuand isLetter)
          espacesP
          pure nom


--Q3)  Définition d'un analyseur syntaxique pour les variables
--runParser varP "" |  runParser varP "abc def"
--Nothing              Just (Var "abc","def")
----- Version simple 
varP :: Parser Expression
varP = do nom <- some (carQuand isLetter)
          espacesP
          pure (Var nom)

----- Version fmap functor
varP' :: Parser Expression
varP' = Var <$> nomP

--Q4) Définition de la fonction applique

----- Version filtrage de motif  
applique :: [Expression] -> Expression
applique [] = undefined
applique [e] = e
applique (e1:e2:es) = applique(App e1 e2 :es)

----- Version utilisant l'application partielle, fold1 prend les 2 premiers éléments de la liste et leur applique la fonction
applique' :: [Expression] -> Expression
applique' = foldl1 App


--Q5) 
--runParser exprsP "a"   | runParser exprsP "a b"
--Just (Var "a","")      | Just (App (Var "a") (Var "b"),"")
exprsP :: Parser Expression
exprsP = do expression <- some exprP
            pure(applique' expression)


-- 6. Définition d'un analyseur syntaxique, qui reconnait une expression utilisant \ x ou λ
--runParser lambdaP "\\ x -> x" |  runParser lambdaP "\\ x -> x x"
--Just (Lam "x" (Var "x"),"")   | Just (Lam "x" (App (Var "x") (Var "x")),"")
lambdaP :: Parser Expression
lambdaP = do _ <- car 'λ' <|> car '\\'
             espacesP
             nom <- nomP
             _ <- chaine "->"
             espacesP
             Lam nom <$> exprsP


--Q7 Extension de expr pour le lambda 
--exprP :: Parser Expression
--exprP = varP <|> lambdaP


--Q8) analyseur syntaxique pour les expressions parenthésées
-- runParser exprParentheseeP "(x y)"  | runParser exprParentheseeP "(\\x -> x)"
-- Just (App (Var "x") (Var "y"),"")   |Just (Lam "x" (Var "x"),"")
exprParentheseeP :: Parser Expression
exprParentheseeP = do _ <- car '('
                      espacesP
                      e <- exprsP
                      espacesP
                      _ <- car ')'
                      espacesP
                      pure e

--exprP :: Parser Expression
--exprP = varP <|> lambdaP <|> exprParentheseeP

--Q9) l'analyseur syntaxique pour les nombres  
-- runParser nombreP "123"      | runParser nombreP "  123"
-- Just (Lit (Entier 123),"")   | Nothing
nombreP :: Parser Expression
nombreP = do cs <- some (carQuand isLetter)
             espacesP
             pure(Lit(Entier(read cs)))

--Q10) analyseur synthaxique d'un boolean
-- runParser booleenP "True" 
-- Just (Lit (Bool True),"")

-----l'analyseur syntaxique pour les booleen
booleenP :: Parser Expression
booleenP = do xs <- chaine "True" <|> chaine "False"
              espacesP 
              pure (Lit(Bool(read xs)))  

-----ajout des nombres et les booléens à exprP
exprP :: Parser Expression
exprP = varP <|> lambdaP <|> exprParentheseeP <|> nombreP <|> booleenP

--Q11) corresponds à exprsP éventuellement précédé d’espaces.
expressionP :: Parser Expression
expressionP = espacesP >> exprsP


--Q12) résultat de l’analyse syntaxique
ras :: String -> Expression
ras xs = case runParser expressionP xs of 
                                    Just(e, "") -> e
                                    _ -> error "Erreur d'analyse syntaxique"
                                    
--Interprète simple
--Q13) Deux sortes de valeurs : des littéraux et des fonctions.
data ValeurA = VLitteralA Litteral
             | VFonctionA (ValeurA -> ValeurA)


--Q14) ex VFonctionA undefinedemple 
--Exemple | VLitteralA (Entier 12) | VLitteralA (Bool True)
instance Show ValeurA where
    show (VFonctionA _) = "λ"
    show (VLitteralA (Entier e)) = show e
    show (VLitteralA (Bool b)) = show b


type Environnement a = [(Nom, a)]

--Q15
--interpreteA :: Environnement ValeurA -> Expression -> ValeurA
--interpreteA _ (Lit l) = VLitteralA l
--interpreteA env (Var xs) = case lookup xs env of Just val -> val
--                                                 Nothing -> error "Nothing"
--Il manque la parti fonction



--Q16
negA :: ValeurA
negA = VFonctionA neg' where neg' (VLitteralA (Entier n)) = VLitteralA (Entier (-n))
                             neg' _ = undefined