--Q3)
pasPascal :: [Integer] -> [Integer]
pasPascal [] = [1]
pasPascal xs = zipWith (+) (0:xs) (xs++[0]) 

--Q4) 
pascal :: [[Integer]]
pascal = iterate pasPascal [1]



