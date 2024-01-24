# -*- coding: utf-8 -*-

""":mod:`test` module : 

:author: `FIL - IEEA - Univ. Lille1.fr <http://portail.fil.univ-lille1.fr>`_

:date: 2016, march

"""

from tree import *

# Manipulation d'arbres binaires 

def arbre1 ():
    return empty_tree ()

def arbre2 ():
    return empty_tree ()

def arbre3 ():
    return empty_tree ()
    
def imprimer (tree):
    print("")

def taille (tree):
    return 0
    
def hauteur (tree):
    return 0

def nbFeuilles (tree):
    return 0

# Comptage d'arbres

def nbArbres (n):
    return 0
    
# Manipulation d'arbres binaires deherche 

def abr1 ():
    return empty_tree ()

def ajouter (value,tree):
    return tree

def abr2 ():
    return empty_tree ()

def abr3 ():
    return empty_tree ()

def appartient (value,tree):
    return False
    
# Entier mysterieux 

def construitArbreEntierMysterieux(i,j):
    return empty_tree ()

def jouer (n):
    pass

# Tests sur les arbres binaires 
def testArbreBinaire (tree):
    imprimer(tree)
    print("")
    print("Taille    : %d" % (taille (tree)))
    print("Hauteur   : %d" % (hauteur (tree)))
    print("nbFeuilles: %d" % (nbFeuilles (tree)))

# Tests sur les arbres binaires de recherche 
def testABR (tree):
    imprimer(tree)
    print("")
    print("Taille    : %d" % (taille (tree)))
    print("Hauteur   : %d" % (hauteur (tree)))
    print("nbFeuilles: %d" % (nbFeuilles (tree)))
    print("Valeurs présentes dans l'arbre : ")
    for i in range(1,10+1):
        if appartient(i,tree): 
            print("%d " % i)
    print("")

# Programme principal 
if __name__ == "__main__":

    testArbreBinaire(arbre1())
    testArbreBinaire(arbre2())
    testArbreBinaire(arbre3())
    
    for i in range(0,15+1):
        print("Le nombre d'arbres à %d noeuds est %d" % (i,nbArbres(i)))
  
    testABR(abr1())
    testABR(abr2())
    testABR(abr3())

    print("Arbre mysterieux entre 12 et 24:");
    imprimer (construitArbreEntierMysterieux(12,24))
    print("")
    jouer(100);
