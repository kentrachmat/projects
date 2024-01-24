"""
author : Benedictus Kent Rachmat - Hichem Karfa
version : 2.0
Projet L3S6
"""

#import the dependencies
import pygame
from game import Game
from orc import Orc
from player import Player
from setting import *
from spell import Spell
from item import *

#static variable for the images
BUTTON_IMG_SRC = "../image/button/mainMenu/goblin.PNG"
SCREEN_GAME = "../image/bg/game/PNG/game_background_4/game_background_4.png"

#this class is for the first game which is the survival game where the people can shoot and kill the zombies
class SurvivalGame(Game):

    # the constructor
    def __init__(self):
        #initialize the image and the name in the menu
        super().__init__(BUTTON_IMG_SRC, SCREEN_GAME, "Zombieland")
        #initialize the variable
        #the orcs initially empty list
        self.all_orc = [] 
        #the player
        self.player = None
        self.playerGroup = pygame.sprite.Group()
        self.ennemieGroup = pygame.sprite.Group() 
        self.visibleGroup = pygame.sprite.Group()
        font = pygame.font.SysFont(None, 24)
        #the initial score
        self.score = 0
        self.lpText_surf = font.render('Points = ' + str(self.score) ,True, "black")
        self.lpText_rect = self.lpText_surf.get_rect(topright=(SCREEN_WIDTH - 50, 0))

    #the game setup before the game starts replace the method before
    def setupGame(self):
        self.all_orc = self.spawnEnnemy(3, SCREEN_WIDTH-100, 800)
        #initialize the sword speed and image
        spell = Spell(SWORD_ID, SWORD_IMG, SWORD_NAME, SWORD_DMG, SWORD_SPEED, None, self.ennemieGroup)
        #spawn the player
        self.spawnPlayer(spell)

    #spaawn the enemy randomly from the right side of the screen
    def spawnEnnemy(self, nombre, x, y):
        new_orc = [] * nombre
        #loop the enemy
        for i in range(nombre):
            #made the orc
            orc = Orc([self.ennemieGroup,self.visibleGroup],100,self)
            orc.set_rectangle(orc.get_surface().get_rect(midbottom=(x + (i * 250), y)))
            new_orc.append(orc)
        return new_orc

    #event listener
    def eventListener(self, event):
        super().eventListener(event)

    #move the person to the right with the certain amount of distance
    def mooveOneRight(self, personnage, distance):
        personnage.moove_right(distance)

    #move all persson in the list to the right with the certain amount of distance
    def mooveAllRight(self, all_personne, distance):
        for i in range(len(all_personne)):
            self.mooveOneRight(all_personne[i], distance)

    #move the person to the left with the certain amount of distance
    def mooveOneLeft(self, personnage, distance):
        #set the border in the left
        if personnage.isInBorderLeft():
            personnage.set_pos_x(SCREEN_WIDTH)
        personnage.moove_left(distance)
        self.player.update_pdv_bar(self.screen)

    #move all the person to the left with the certain amount of distance
    def mooveAllLeft(self, all_personne, distance):
        #loop all monster
        for monster in all_personne :
            self.mooveOneLeft(monster, distance)
            monster.update_pdv_bar(self.screen)

    #check if the sword hits the enemy
    def check_collision(self,sprite,group):
        return pygame.sprite.spritecollide(sprite,group,False,pygame.sprite.collide_mask)

    #move the person to up with the certain amount of distance
    def mooveOneUp(self, personnage, distance):
        personnage.moove_up(distance)

    #move all the person to up with the certain amount of distance
    def mooveAllUp(self, all_personne, distance):
        #loop all the person
        for i in range(len(all_personne)):
            self.mooveOneUp(all_personne[i], distance)
    
    #move the person to the bottoom with the certain amount of distance
    def mooveOneDown(self, personnage, distance):
        personnage.moove_down(distance)

    #move all the person to the bottom with the certain amount of distance
    def mooveAllDown(self, all_personne, distance):
        #loop all the person
        for i in range(len(all_personne)):
            self.mooveOneDown(all_personne[i], distance)

    #spawn the player with the given spell
    def spawnPlayer(self, spell):
        #create the person class with the given spell and health
        self.player = Player(spell, [self.playerGroup, self.visibleGroup],100,self)
        self.player.set_rectangle(self.player.get_surface().get_rect(midbottom=(200, 900)))

    #game over when the health of the person is empty
    def game_over(self):
        font = pygame.font.SysFont(None, 24)
        #lost text
        lostText_surf = font.render('Vous avez perdu  ', True, "red")
        lostText_rect = lostText_surf.get_rect(topright=(SCREEN_WIDTH /2 , SCREEN_HEIGHT /2))
        self.screen.blit(lostText_surf, lostText_rect)
        #initialize running to false
        self.running = False

    #play the game after the click on the menu logo, will replace the method before
    def play(self):
        #background initialize
        self.screen.blit(self.get_background(), (0, 0))

        #showing the score
        font = pygame.font.SysFont(None, 24)
        self.lpText_surf = font.render('Points = ' + str(self.score), True, "black")
        pygame.draw.rect(self.screen, 'Orange', self.lpText_rect)
        pygame.draw.rect(self.screen, 'Orange', self.lpText_rect, 10)
        self.screen.blit(self.lpText_surf, self.lpText_rect)

        # showing the objects such as enemy, player, and visible
        self.visibleGroup.draw(self.screen)

        # movinng the objects
        for spell in self.player.all_spell :
            spell.move()

        # smoing the orc to the left
        self.mooveAllLeft(self.all_orc, Orc.baseSpeed)

        # update display
        self.visibleGroup.update()

        # showing the sword
        self.player.all_spell.draw(self.screen)
