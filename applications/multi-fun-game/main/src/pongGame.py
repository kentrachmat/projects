"""
author : Benedictus Kent Rachmat - Hichem Karfa
version : 2.0
Projet L3S6
"""

#import the dependencies
import pygame
from game import Game
from setting import *
from item import *
from composant import Composant

#static variable for the images
BUTTON_IMG_SRC = "../image/button/mainMenu/pong.jpg"
SCREEN_GAME = "../image/bg/game/pong/pongTerrain.jpg"

#this class is for the third game we can play the pong game with 2 player
class PongGame(Game):
    # the constructor
    def __init__(self):
        #initialize the image and the name in the menu
        super().__init__(BUTTON_IMG_SRC, SCREEN_GAME, "Pong")
        #initialize the font
        font = pygame.font.SysFont(None, 24)

        #show the text
        self.lpText_surf = font.render('Points = ', True, "black")
        self.lpText_rect = self.lpText_surf.get_rect(topright=(SCREEN_WIDTH - 50, 0))

        self.paddleLeft = None
        self.paddleRight = None

        self.composant = []
        self.ball = None
        self.vitesseY = 10
        self.vitesseX = 10
        self.vitessePaddleRight = 0
        self.vitessePaddleLeft = 0

    #setup the game, replace the parennt method
    def setupGame(self):
        #add the left paddle
        self.paddleLeft = pygame.Rect( 10,SCREEN_HEIGHT/2 - 30,10,80)
        #add the right paddle
        self.paddleRight= pygame.Rect( SCREEN_WIDTH - 20,SCREEN_HEIGHT/2 - 30,10,80)
        #add the ball
        self.ball = pygame.Rect( SCREEN_WIDTH/2 ,SCREEN_HEIGHT/2 - 30,20,20)

    #call the event listener from the parent
    def eventListener(self, event):
        super().eventListener(event)

        #up direction
        if event.type == pygame.KEYDOWN :
            if event.key == pygame.K_DOWN:
                self.vitessePaddleRight += 10
            if event.key == pygame.K_UP:
                self.vitessePaddleRight -= 10
        if event.type == pygame.KEYUP:
            if event.key == pygame.K_DOWN:
                self.vitessePaddleRight -= 10
            if event.key == pygame.K_UP:
                self.vitessePaddleRight += 10

            # up direction
        if event.type == pygame.KEYDOWN:
            if event.key == pygame.K_s:
                self.vitessePaddleLeft += 10
            if event.key == pygame.K_z:
                self.vitessePaddleLeft -= 10
        if event.type == pygame.KEYUP:
            if event.key == pygame.K_s:
                self.vitessePaddleLeft -= 10
            if event.key == pygame.K_z:
                self.vitessePaddleLeft += 10






    #add the composant
    def addComposant(self, imgSrc, **kwargs):
        surface = pygame.image.load(imgSrc).convert_alpha()
        rect = surface.get_rect(**kwargs)
        theComposant = Composant(surface, rect)
        self.composant.append(theComposant)
        return theComposant



    #play the game and display it to the layout
    def play(self):
        self.updateGame()
        self.displayGame()

    #updaate the game conditions
    def updateGame(self):
        self.ball.x += self.vitesseX
        self.ball.y += self.vitesseY
        self.paddleRight.y += self.vitessePaddleRight
        self.paddleLeft.y += self.vitessePaddleLeft
        if self.ball.top <= 0 or self.ball.bottom >= SCREEN_HEIGHT:
            self.vitesseY = self.vitesseY * -1

        if self.ball.left <= 0 or self.ball.right >= SCREEN_WIDTH:
            self.ball.center = (SCREEN_WIDTH/2, SCREEN_HEIGHT/2)


        if self.ball.colliderect(self.paddleLeft) or self.ball.colliderect(self.paddleRight):
            self.vitesseX = self.vitesseX * -1



        if self.paddleRight.bottom > SCREEN_HEIGHT:
            self.paddleRight.bottom =  SCREEN_HEIGHT


    #display the layout baackground
    def displayGame(self):
        self.screen.blit(self.get_background(), (0, 0))


        # show the composant
        pygame.draw.rect(self.screen,"Orange",self.paddleLeft)
        pygame.draw.rect(self.screen,"Orange",self.paddleRight)
        pygame.draw.rect(self.screen, "Orange", self.ball)
        for i in range(len(self.composant)):
            self.screen.blit(self.composant[i].getSurface(), (self.composant[i].x(), self.composant[i].y()))

        # show the score
        pygame.draw.rect(self.screen, 'Orange', self.lpText_rect, 10)
        self.screen.blit(self.lpText_surf, self.lpText_rect)
