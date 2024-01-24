"""
author : Benedictus Kent Rachmat - Hichem Karfa
version : 2.0
Projet L3S6
"""

#import the dependencies
import enemy

#static variable for the image
TAXI_SRC = "../image/enemy/Orc/PNG/PNG Sequences/Walking/taxi-removebg-preview.png"

#this class is to design the orc booster(enemy)
class OrcBoost(enemy.Enemy):
    #initialize the booster speed
    baseSpeed = 5

    #the constructor
    def __init__(self, groups,pdv ,mySpell=None):
        #calling the enemy constructor and by default we have speed value equal to 1
        super().__init__(TAXI_SRC, mySpell, groups,pdv)
        self.speed = 1

    #get the current speed 
    def get_speed(self):
        return self.speed
