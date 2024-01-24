"""
author : Benedictus Kent Rachmat - Hichem Karfa
version : 2.0
Projet L3S6
"""

#import the dependencies
import enemy

#static variable for the image
ORC_SRC = "../image/enemy/orc.png"

#this class is to design the orc (enemy)
class Orc(enemy.Enemy):
    #initialize the booster speed
    baseSpeed = 1

    #the constructor
    def __init__(self, groups, pdv, game, mySpell=None):
        #calling the enemy constructor and by default we have speed value equal to 1
        super().__init__(ORC_SRC, mySpell, groups, pdv, game)
        self.speed = 1

    #get the speed
    def get_speed(self):
        return self.speed
