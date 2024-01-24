"""
author : Benedictus Kent Rachmat - Hichem Karfa
version : 2.0
Projet L3S6
"""

#import the dependencies
import pygame
from setting import *
from survivalGame import SurvivalGame
from snakeGame import SnakeGame
from pongGame import PongGame
from sys import exit
from composant import Composant

#this class if for the main menu when the user login
class MainMenu(pygame.sprite.Sprite):
    #the constructor
    def __init__(self):
        #calling the constructor of pygame.sprite.Sprite
        super().__init__()
        pygame.init()
        #the variable
        self.title = "Multi Fun Jeux"
        self.screen = None 
        self.WIDTH =  None
        self.HEIGHT = None
        self.composant = []
        self.tab_game = []

    #start the main program with the dependencies inside the function
    def start(self):
        self.generationWindows()
        self.createGame()
        self.loadComposant()
        self.resizeComposant()
        self.displayMenu()
        self.gestionEvent()

    #create the games for the application
    def createGame(self):
        self.tab_game.append(SurvivalGame())
        self.tab_game.append(PongGame())
        self.tab_game.append(SnakeGame())
        #self.tab_game.append(CarGame())

    #generate the window with the fix width and height
    def generationWindows(self):
        SIZE = self.WIDTH, self.HEIGHT = SCREEN_WIDTH, SCREEN_HEIGHT
        #set the title
        pygame.display.set_caption(self.title)
        self.screen = pygame.display.set_mode(SIZE)

    #load the composant for the background
    def loadComposant(self):
        #load the background image
        main_background = pygame.image.load("../image/bg/foggy.png").convert_alpha()
        main_background = pygame.transform.scale(main_background, (self.WIDTH,self.HEIGHT))
        main_backgroundRect = main_background.get_rect(topleft=(0, 0))
        #load the composant
        background = Composant(main_background, main_backgroundRect)
        self.composant.append(background)

        #text initialize
        font = pygame.font.SysFont(None, 24)
        texteListeDesJeuSurface = font.render('liste des jeux :', True, "black")
        texteListeDesJeuxRect = texteListeDesJeuSurface.get_rect(topleft=(50, 20))
        texteListeDesJeu = Composant(texteListeDesJeuSurface, texteListeDesJeuxRect)
        self.composant.append(texteListeDesJeu)

    #resize the composant to the tab game
    def resizeComposant(self):
        for i in range(len(self.tab_game)):
            self.tab_game[i].get_play_button_rect().x = 50
            self.tab_game[i].get_play_button_rect().y = 50 + (i * 200) % self.screen.get_height()

    #display the menu 
    def displayMenu(self):
        for i in range(len(self.composant)):
            self.screen.blit(self.composant[i].getSurface(), (self.composant[i].x(), self.composant[i].y()))
        for i in range(len(self.tab_game)):
            self.screen.blit(self.tab_game[i].get_play_button(), self.tab_game[i].get_play_button_rect())
        pygame.display.flip()

    #event handling
    def gestionEvent(self):
        running = True
        while running:
            #make the program running
            for event in pygame.event.get():
                if event.type == pygame.QUIT:
                    #if quit it will close the program
                    running = False
                    pygame.quit()
                    print("Fermeture du jeu, Merci d'avoir joué et à la prochaine ! ")
                    exit()

                elif event.type == pygame.MOUSEBUTTONDOWN:
                    #event when the mouse is inside the box
                    for game in self.tab_game:
                        if game.get_play_button_rect().collidepoint(event.pos):
                            print("Chargement du jeu")
                            running = False
                            #play the game
                            game.start(self.screen)
            pygame.display.update()

if __name__ == '__main__':
    #start the game menu
    menu = MainMenu()
    menu.start()