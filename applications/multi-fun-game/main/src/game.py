"""
author : Benedictus Kent Rachmat - Hichem Karfa
version : 2.0
Projet L3S6
"""

#import the dependencies
import abc
from setting import *
import pygame

#this is the game class to manage all the game needs 
class Game(pygame.sprite.Sprite):
    #the constructor
    def __init__(self, src_button, src_screen, title):
        #calling the parent constructor
        super().__init__()
        self.title = title

        #FPS handling
        self.clock = pygame.time.Clock()

        #game window
        self.screen = None

        #game background 
        self.background = pygame.image.load(src_screen).convert_alpha()

        #game's buttons
        self.play_button = pygame.image.load(src_button).convert_alpha()
        self.play_button = pygame.transform.scale(self.play_button, (250, 150))
        self.play_button_rect = self.play_button.get_rect()

        #Key currently pressed
        self.pressed = {}

        #game state
        self.running = False

    #get the play button rectangle
    def get_play_button_rect(self):
        return self.play_button_rect

    #get the play button
    def get_play_button(self):
        return self.play_button

    #get the background
    def get_background(self):
        return self.background

    #start the program by configurating and start the game
    def start(self, screen):
        pygame.display.set_caption(self.title)
        self.screen = screen
        self.screen.blit(self.background, (0, 0))
        self.running = True
        #setup and start the game
        self.setupGame()
        self.startGame()

    #start the actuall game
    def startGame(self):
        while self.running:
            #exit handling 
            for event in pygame.event.get():
                self.eventListener(event)
            #play the game
            self.play()
            #pygame update
            pygame.display.update()
            self.clock.tick(FPS)

    #event listener method when the game is closing
    def eventListener(self,event):
        if event.type == pygame.QUIT:
            self.running = False
            pygame.quit()
            print("Fermeture du jeu, Merci d'avoir joué et à la prochaine ! ")
            exit()

    #abstract method for the child class, setup game
    @abc.abstractmethod
    def setupGame(self, screen):
        pass

    #abstract method for the child class, the code for the running game
    @abc.abstractmethod
    def play(self, screen):
        pass
