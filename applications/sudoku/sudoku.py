#! /usr/bin/python3
#Author : Kent

from pysat.solvers import Minisat22

# For Sudoku, the variables represent triples (i, j, k) with i, j and k in [0,8].
# These triples have the following meaning: the variable (i, j, k) is true iff
# in the sudoku solution, the coordinate cell (i, j) contains the number k + 1.

# We define l as being the digits in [0,8]
l = list(range(0, 9))

# pos contains all possible triples (i, j, k)
pos = [(i,j,k) for i in l for j in l for k in l]


def encode(i,j,k):
    """
    The encode function takes a triplet (i, j, k) of pos as argument
    and returns a number that indicates the variable corresponding to this triplet.
    """
    return 1 + i + j* 9 + k * 81

def decode(n):
    """
    Decode takes as argument takes a number between 1 and 729 which
    represents a variable representing a triplet (i, j, k) and returns the corresponding triplet.
    """
    m = n-1
    i = m % 9
    m= m//9
    j = m % 9
    k = m//9
    return (i,j,k)

# Instantiate the variable phi1 by the SAT constraint which indicates that
# any box contains a value
phi1 =[[encode(i,j,k)
        for i in l]
        for j in l
        for k in l]

# Instantiate the phi2 variable by the SAT constraint which indicates that
# a cell contains at most one value
phi2 = [[-encode(i,j,k1),-encode(i,j,k2)]
        for i in l
        for j in l
        for k1 in l
        for k2 in l
        if k1!=k2]

# Instantiate the phi3 variable by the SAT constraint which indicates that
# on a line at most once each value
phi3 = [[-encode(i1,j,k),-encode(i2,j,k)]
        for i1 in l
        for i2 in l
        for j in l
        for k in l
        if i1!=i2]

# Instantiate the phi4 variable by the SAT constraint which indicates that
# on a column at most once each value
phi4 = [[-encode(i,j1,k),-encode(i,j2,k)]
        for i in l
        for j1 in l
        for j2 in l
        for k in l
        if j1!=j2]

# Instantiate the variable phi5 by the SAT constraint which indicates that
# on a square at most once each value.
def carre(i1,j1,i2,j2):
    """
    Indicates whether the boxes (i1, j1) and (i2, j2) belong to the same square.
    The result is irrelevant (i.e. can be True or False) when
    (i1, j1) and (i2, j2) are on the same row or the same column.
    """
    if i1//3 == i2//3 and j1//3 == j2//3:
        return True
    else:
        return False

phi5 = [[-encode(i1,j1,k),-encode(i2,j2,k)]
        for i1 in l
        for i2 in l
        for j1 in l
        for j2 in l
        for k in l
        if carre(i1,j1,i2,j2) and (i1 != i2 or j1 != j2)]

# Instantiate the variable phi6 by the SAT constraint which
# represents the grid of the statement.
# encode(i,j,k)
# i represent the vertical line - 1
# j represent the horizontal line - 1
# j represent the value on a specific position - 1
phi6 =[[encode(0,4,1)],[encode(0,7,0)],[encode(0,8,6)],
       [encode(1,1,2)],[encode(1,5,6)],[encode(1,8,7)],
       [encode(2,4,8)],[encode(2,6,5)],
       [encode(3,1,7)],[encode(3,3,8)],[encode(3,5,1)],[encode(3,7,5)],
       [encode(4,0,3)],[encode(4,2,5)],[encode(4,6,2)],[encode(4,8,1)],
       [encode(5,1,1)],[encode(5,3,5)],[encode(5,5,2)],[encode(5,7,6)],
       [encode(6,2,6)],[encode(6,4,5)],
       [encode(7,0,7)],[encode(7,3,6)],[encode(7,7,4)],
       [encode(8,0,1)],[encode(8,1,4)],[encode(8,4,2)]]

# This part of the program launches the SAT solver with the conjunction of the constraints,
# ie the concatenation of the lists representing them.
with Minisat22(bootstrap_with=phi1+phi2+phi3+phi4+phi5+phi6) as m:
    if m.solve():
        model = [decode(v) for v in m.get_model() if v >0] 

        r = [[0 for i in l] for j in l]
        for (i,j,k) in model:
            r[i][j] += k+1
        print("\n")
        for ligne in r:
            print(ligne)

    else:
        print("There's no solution")
