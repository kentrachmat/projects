"""
author : Benedictus Kent Rachmat - Hichem Karfa
version : 2.0
Projet L3S6
"""

#import the dependencies
import pygame
from setting import *

#this class is for the spell where the player can use to kill the enemy
class Spell(pygame.sprite.Sprite):

    # the constructor
    def __init__(self, myId, src, name, dmg, pSpeedSpell, personnage, ennemie ,posX=0, poxY=0 ):
        #calling the pygame.sprite.Sprite constructor
        super().__init__()
        #initialize the variable
        self.id = myId
        self.src = src
        self.image = pygame.image.load(src)
        self.rect = self.image.get_rect()
        #the image
        self.originImage = self.image
        #name
        self.name = name
        #damage
        self.dmg = dmg
        #the speed
        self.speedSpell = pSpeedSpell
        #position x
        self.rect.x = posX
        #position y
        self.rect.y = poxY
        #the person who use it
        self.personnage = personnage
        #the enemy 
        self.ennemie = ennemie
        print("enemy = ")
        print(ennemie)
        #the angle
        self.angle = 0

    #move the spell with the speed declared on the constructor
    def move(self):
        self.rotate()
        self.rect.x += self.speedSpell
        if (self.rect.x > SCREEN_WIDTH):
            self.remove()

        for monster in self.personnage.game.check_collision(self,self.ennemie):
            self.remove()
            monster.damage(self.personnage.attaqueBase)

    #maje tge spell rotate when projected
    def rotate(self):
        #the aangle rotated
        self.angle -= 6
        #transforming the image
        self.image = pygame.transform.rotozoom(self.originImage,self.angle,1)
        self.rect = self.image.get_rect(center = self.rect.center)

    #remove the spell when hit
    def remove(self):
        self.personnage.all_spell.remove(self)

    #set the position x
    def set_pos_x(self, newPos):
        self.rect.x = newPos

    #set the position y
    def set_pos_y(self, newPos):
        self.rect.y = newPos

    #get the position x
    def get_pos_x(self):
        return self.rect.x

    #get the position y
    def get_pos_y(self):
        return self.rect.y

    #get the id
    def get_id(self):
        return self.id

    #get the source
    def get_src(self):
        return self.src

    #get the name
    def get_name(self):
        return self.name

    #get the damage
    def get_dmg(self):
        return self.dmg

    #get the speed
    def get_speed(self):
        return self.speedSpell
