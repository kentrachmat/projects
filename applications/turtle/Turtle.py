#Author : Kent

from turtle import *

def dessiner(m,l,a):
    """
    It makes the drawing possible with these parameters.
    :param m:list ( The direction )
    :param l:int ( length )
    :param a:int ( angle )
    CU : None
    """
    for i in m:
        if i == 'F':
            forward(l)
        elif i == '2F':
            forward(l*2)
        elif i == '-':
            left(a)
        elif i == '+':
            right(a)
    return

#Square
#dessiner(['F','-','F','-','F','-','F'],200,90)

#Hexagon
#dessiner(['F', '-', 'F', '-', 'F', '-', 'F', '-', 'F', '-', 'F'],100,60)

#House
#dessiner(['F','-','-','-','-','F','-','-','-','-','F','-','F','-','-','-','F','-','-','-','F','-','-','-'],100,30)

#Sheep
#pu()
#goto(-300,0)
#pd()
#speed(100)
#dessiner(['F','-','-','F','-','-','F','-','-','F','-','-','F','-','-','F','-','-','F','-','-','F','-','F','+','+','+','2F','+','+','+','2F','+','+','+','2F','+','+','+','2F','+','+','+','2F','2F','+','+','+','2F','+','+','+','2F','+','+','+','+','+','+','2F','2F','-','-','-','2F','-','-','-','2F','-','-','-','2F','-','-','-','F','+','+','+','2F','+','+','+','F','+','+','+','2F','-','-','-','2F','F','-','-','-','2F','-','-','-','F','-','-','-','2F'],60,30)

def derive (so,r):
    """
    Returns the derivation of 2 liste so and r.
    :param so: list 
    :param r:  list
    return : list
    CU : None
    Examples:
    >>> derive (['F', '+', 'F'],['F', '-', 'F'])
    ['F', '-', 'F', '+', 'F', '-', 'F']
    >>> derive (['F', '-', 'F'],['F', '+', 'F'])
    ['F', '+', 'F', '-', 'F', '+', 'F']
    >>> derive (['F', '+', 'F'],['F', '+', 'F'])
    ['F', '+', 'F', '+', 'F', '+', 'F']
    """
    l=[]
    for c in so:
        if c=='F':
            l+=r
        else:
            l+=[c]
    return l

def derive_n (so,r,n):
    """
    Returns the derivation of 2 liste so and r n times.
    :param so: list 
    :param r:  list
    :param n:  int
    return : list
    CU : None
    Examples:
    >>> derive_n (['F', '+', 'F'],['F', '-', 'F'],1)
    ['F', '-', 'F', '+', 'F', '-', 'F']
    >>> derive_n (['F', '+', 'F'],['F', '-', 'F'],2)
    ['F', '-', 'F', '-', 'F', '-', 'F', '+', 'F', '-', 'F', '-', 'F', '-', 'F']
    >>> derive_n (['F', '+', 'F'],['F', '-', 'F'],3)
    ['F', '-', 'F', '-', 'F', '-', 'F', '-', 'F', '-', 'F', '-', 'F', '-', 'F', '+', 'F', '-', 'F', '-', 'F', '-', 'F', '-', 'F', '-', 'F', '-', 'F', '-', 'F']
    """
    for c in range(n):
        so=derive (so,r) 
    return so

def Fractales(l1,l2,n,a):
    """
    it will draw something given by 2 param of lists and depends by n times of derivations,
    the bigger n is there're more detail ( angles ) on the drawing. 
    :param l1: list 
    :param l2:  list
    :param a:  int
    :param n:  int
    CU : None
    """
    assert 0<=n<=5
    speed(300)
    s=derive_n (l1,l2,n)
    l=3**(5-n)
    return dessiner(s,l,a)

# Create a cool modified square
Fractales(['F', '-', '-', 'F', '-', '-', 'F', '-', '-'],['F', '+', 'F', '-', 'F', '-', 'F', '+', 'F'],4,90)

def deriver_sur_place (l1,l2):
    """
    Returns the derivation of 2 liste so and r n times.
    :param l1: list 
    :param l2:  list
    return : list
    CU : None
    Examples:
    >>> deriver_sur_place (['F', '+', 'F'],['F', '-', 'F'])
    ['F', '-', 'F', '+', 'F', '-', 'F']
    >>>
    ['F', '+', 'F', '-', 'F', '+', 'F']
    >>> deriver_sur_place (['F', '+', 'F'],['F', '+', 'F'])
    ['F', '+', 'F', '+', 'F', '+', 'F']
    """
    for i,c in enumerate(l1):
        if c == 'F':
            l1[i] = l2
    return [val for a in l1 for val in a]

def deriver_n_sur_place(l1,l2,n):
    """
    Returns the derivation of 2 liste so and r n times.
    :param l1:  list 
    :param l2:  list
    :param n:   int
    return : list
    CU : None
    Examples:
    >>> deriver_n_sur_place (['F', '+', 'F'],['F', '-', 'F'],1)
    ['F', '-', 'F', '+', 'F', '-', 'F']
    >>> deriver_n_sur_place (['F', '+', 'F'],['F', '-', 'F'],2)
    ['F', '-', 'F', '-', 'F', '-', 'F', '+', 'F', '-', 'F', '-', 'F', '-', 'F']
    >>> deriver_n_sur_place (['F', '+', 'F'],['F', '-', 'F'],3)
    ['F', '-', 'F', '-', 'F', '-', 'F', '-', 'F', '-', 'F', '-', 'F', '-', 'F', '+', 'F', '-', 'F', '-', 'F', '-', 'F', '-', 'F', '-', 'F', '-', 'F', '-', 'F']
    """
    for c in range(n):
        l1=deriver_sur_place(l1,l2)
    return l1
    
if __name__ == '__main__':
    import doctest
    doctest.testmod(optionflags=doctest.NORMALIZE_WHITESPACE | doctest.ELLIPSIS, verbose = True)  