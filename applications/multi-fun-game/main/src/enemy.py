"""
author : Benedictus Kent Rachmat - Hichem Karfa
version : 2.0
Projet L3S6
"""

#import the dependencies
import pygame
from personnage import Personnage

#this is the class for designing the enemy
class Enemy(Personnage):
    #the constructor
    def __init__(self, src , mySpell, groups,pdv, game):
        #the Personnage constructor
        super().__init__(src, mySpell, groups,pdv, game)

    #check if the enemy can move or not
    def personnageCanMove(self):
        #player get the damage if it hits, false otherwise
        if self.game.check_collision(self, self.game.playerGroup):
            self.game.player.damage(2)
        return not(self.game.check_collision(self, self.game.playerGroup))



