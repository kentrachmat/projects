"""
author : Benedictus Kent Rachmat - Hichem Karfa
version : 2.0
Projet L3S6
"""

#import the dependencies
import pygame

#this is the parent class to design the ally and enemy
class Personnage(pygame.sprite.Sprite):

    #the constructor
    def __init__(self, src, mySpell, groups, pdv, game):
        #calling the pygame.sprite.Sprite constructor
        super().__init__(groups)
        #the person image
        self.image = pygame.image.load(src).convert_alpha()
        self.rect = self.image.get_rect()
        self.rect.right = 5

        # attack score by default
        self.attaqueBase = 20

        #initialize the spell, game and pdv
        self.all_spell = pygame.sprite.Group()
        self.spell = mySpell
        self.game = game
        self.pdv = pdv

        # person direction
        self.direction = pygame.math.Vector2()

    #check if the person can move
    def personnageCanMove(self):
        return True

    #move the person to right with a certain distance
    def moove_right(self, distance):
        if self.personnageCanMove():
            self.rect.x += distance

    #move the person to left with a certain distance
    def moove_left(self, distance):
        if self.personnageCanMove():
            self.rect.x -= distance

    #move the person to up with a certain distance
    def moove_up(self, distance):
        if self.personnageCanMove():
            self.rect.y -= distance

    #move the person to down with a certain distance
    def moove_down(self, distance):
        if self.personnageCanMove():
            self.rect.y += distance

    #move the person with a certain speed
    def move(self, pspeed):
        self.rect.center += self.direction * pspeed

    #check if for the left border
    def isInBorderLeft(self):
        return self.get_rectangle().right <= 0

    #submit a damage with a certain amount
    def damage(self, amount):
        #substract the health bar with the amount
        self.pdv -= amount

        #check if the pdv is less than 0
        if self.pdv <= 0:
            self.game.score += 10
            self.rect.x = 2500
            self.pdv = 100

    #update the health bar
    def update_pdv_bar(self, image):
        #the rgb color
        bar_color = (111, 210, 46)
        #position ajust
        bar_position = [self.rect.x + 50, self.rect.y + 50, self.pdv, 5]
        #draw the health bar
        pygame.draw.rect(image, bar_color, bar_position)

    #get the y position
    def get_y(self):
        return self.rect.y

    #set the y position
    def set_pos_y(self, pos):
        self.rect.y = pos

    #get the x position
    def get_x(self):
        return self.rect.x

    #set the position x
    def set_pos_x(self, pos):
        self.rect.x = pos

    #get the rectangle 
    def get_rectangle(self):
        return self.rect

    #set the rectangle
    def set_rectangle(self, rect):
        self.rect = rect

    #get the surface image 
    def get_surface(self):
        return self.image
