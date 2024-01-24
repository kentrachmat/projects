"""
author : Benedictus Kent Rachmat - Hichem Karfa
version : 2.0
Projet L3S6
"""

#import the dependencies
from ally import Ally
import pygame
from item import *
from spell import Spell

#this class is too design the player, and the parent is an Ally because he is the good guy
class Player(Ally):
    # the constructor
    def __init__(self, pSpell, groups, pdv, game):
        #initialize the player image called by the ally class
        super().__init__(PLAYER_IMG_SRC, pSpell, groups, pdv, game)

        # PdV et and resistance (health)
        self.pdv = pdv
        self.max_health = 100

        # attacking value and boolean
        self.attack = 10
        self.attacking = False
        self.attack_cooldown = 400
        self.attacking_time = 0

        # the speed
        self.speed = 5

        # the tools too hit the enemy
        self.all_spell = pygame.sprite.Group()

        self.game = game

    #reduce the damage with a certain amount
    def damage(self,amount):
        self.pdv -= amount
        if self.pdv < 0:
            self.game.game_over()

    #keyboard handling 
    def event(self):
        keys = pygame.key.get_pressed()
        left, middle, right = pygame.mouse.get_pressed()

        #up direction
        if keys[pygame.K_UP] or keys[pygame.K_z]:
            self.direction.y = -1
        #down direction
        elif keys[pygame.K_DOWN] or keys[pygame.K_s]:
            self.direction.y = 1
        else:
            self.direction.y = 0

        #right direction
        if keys[pygame.K_RIGHT] or keys[pygame.K_d]:
            #check if the player is in the square or not
            if self.playerCanMove():
                self.direction.x = 1
        #left direction
        elif keys[pygame.K_LEFT] or keys[pygame.K_q] and self.rect.x > 0:
            self.direction.x = -1
        else:
            self.direction.x = 0

        # attack the enemy
        if left and not self.attacking:
            self.attacking = True
            #update the spell state
            self.spell.update()
            self.attacking_time = pygame.time.get_ticks()
            self.attak_ennemie()

    #attack the enemy
    def attak_ennemie(self):
        print("the player hits !")
        #get the spell by calling the Spell class
        newSpell = Spell(self.spell.get_id(), self.spell.get_src(), self.spell.get_name(), self.spell.get_dmg(),
                         self.spell.get_speed(), self, self.game.ennemieGroup, self.rect.x + 150, self.rect.y + 200)
        #add it to our variable
        self.all_spell.add(newSpell)

    #update the health bar
    def update_pdv_bar(self, image):
        #the color
        bar_color = (111, 210, 46)
        #poosition
        bar_position = [self.rect.x + 100, self.rect.y + 75, self.pdv, 5]
        #drawing the health bar
        pygame.draw.rect(image, bar_color, bar_position)

    #update the event cooldown and move with a certain speed
    def update(self):
        self.event()
        self.cooldown()
        self.move(self.speed)

    #cooldown method to have a spare time in the game
    def cooldown(self):
        current_time = pygame.time.get_ticks()

        if self.attacking:
            if (current_time - self.attacking_time) >= self.attack_cooldown:
                self.attacking = False

    #check if the player can move
    def playerCanMove(self):
        return not (self.game.check_collision(self, self.game.ennemieGroup))
