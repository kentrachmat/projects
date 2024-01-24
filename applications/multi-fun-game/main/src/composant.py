"""
author : Benedictus Kent Rachmat - Hichem Karfa
version : 2.0
Projet L3S6
"""

#import the dependencies
from pickle import TRUE
import pygame

#this class is to design the composant
class Composant(pygame.sprite.Sprite):
    #the constructor
    def __init__(self, surface, rect):
        #calling the pygame.sprite.Sprite class
        super().__init__()
        #initialize the surface and retangle
        self.surface = surface
        self.rect = rect
        self.vitesseX = 10
        self.vitesseY= 10

    #get the x position value
    def x(self):
        return self.rect.x

    #get the y position value
    def y(self):
        return self.rect.y


    def setX(self):
        self.move_down(self.vitesseX)

    def setY(self):
        self.move_right(self.vitesseY)


    def getVitesseX(self):
        return self.vitesseX

        # get the y position value

    def getVitesseY(self):
        return self.vitesseY

    #get the surface value
    def getSurface(self):
        return self.surface

    #get the rectangle value
    def getRect(self):
        return self.rect

    #move to right with a certain distance
    def move_right(self, distance):
        if self.CanMove():
            self.rect.x += distance

    #move to left with a certain distance
    def move_left(self, distance):
        if self.CanMove():
            self.rect.x -= distance

    #move to up with a certain distance
    def move_up(self, distance):
        if self.CanMove():
            self.rect.y -= distance

    #move to down with a certain distance
    def move_down(self, distance):
        if self.CanMove():
            self.rect.y += distance

    #check if the composant can move
    def CanMove(self):
        return TRUE
