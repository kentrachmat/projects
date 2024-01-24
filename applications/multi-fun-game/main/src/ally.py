"""
author : Benedictus Kent Rachmat - Hichem Karfa
version : 2.0
Projet L3S6
"""

#import the dependencies
import pygame
from personnage import Personnage

# this class is to creaate the ally herited by the parent class
class Ally(Personnage):
    #the constructor
    def __init__(self, src , spell, groups, pdv, game):
        #callinng the parent constructor
        super().__init__(src, spell, groups, pdv, game)
