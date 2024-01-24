-- Q1)  Version "yoyo"
alterne :: [a] -> [a]
alterne [] = []
alterne (x:xs) = x:alterne' xs

alterne' :: [a] -> [a]
alterne' [] = []
alterne' (_:xs) = alterne xs

-- Q1) Version utilisant drop
alterneV2 :: [a] -> [a]
alterneV2 [] = []
alterneV2 (x:xs) = x : alterneV2 (drop 1 xs)

-- Q1) version utilisant filter
alterneV3 :: (Integral a) => [a] -> [a]
alterneV3 [] = [] 
alterneV3 x = filter odd x

-- Q2) 
combine :: (a -> b -> c) -> [a] -> [b] -> [c]
combine _ _ [] = []
combine _ [] _ = []
combine f (x:xs) (y:ys) = f x y : combine f xs ys 
