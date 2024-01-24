-- Exercice 3
sommedeXaY :: (Enum a, Num a) => a -> a -> a
sommedeXaY a b = sum[a..b]

-- Exercice 4
somme :: Num a => [a] -> a
somme [] = 0
somme (x:xs) = x + somme xs

-- Exercice 5
last' :: [a] -> a
last' [] = error "last' : empty list"
last' a = head(reverse a)

last'' :: [a] -> a 
last''    = head.reverse

init' :: [a] -> [a]
init' [] = error "init' : empty list"
init' a = reverse (drop 1 (reverse a))

-- Exercice 6

(!!!) :: [b]-> Int -> b
(!!!) [] _ = error "!!! : empty list"
(!!!) (x:_) 0 = x
(!!!) (_:xs) a = (!!!) xs (a-1)

(+++) :: [a] -> [a] -> [a]
(+++) xs [] = xs
(+++) [] ys = ys
(+++) (x:xs) ys = x: (+++) xs ys

concat' :: [[a]] -> [a]
concat' [] = []
concat' (x:xs) = (+++) x (concat' xs)

map' :: (a -> b) -> [a] -> [b]
map' _ [] = []
map' fct (x:xs) = fct x : map' fct xs

--Exercice 7: Cela représente une fonction qui prend en paramètre une position de la liste l, et qui retourne la valeur à la position donnée.

-- Exercice 8
length' :: [a] -> Int
length' xs = somme(map (const 1) xs)

-- Exercice 9
fctRecursive :: (a -> a) -> a -> Int -> [a]
fctRecursive _ _ 0 = []
fctRecursive f x n = f x : fctRecursive f x (n - 1)

fctStandard :: (a -> a) -> a -> Int -> [a]
fctStandard f x n = take n (iterate f x)

-- Exercice 10
fctEntierConsecutif :: Int -> [Int]
fctEntierConsecutif n = fctStandard (1+) 0 (n+1)
