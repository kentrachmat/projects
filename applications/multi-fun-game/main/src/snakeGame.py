"""
author : Benedictus Kent Rachmat - Hichem Karfa
version : 2.0
Projet L3S6
"""

#import the dependencies
import pygame
from game import Game
import random
from setting import *
from constant import *
from item import *

#this class is for the second game which we can play the snake game, controling the snake too eat the fruit and make it longer
class SnakeGame(Game):
    # the constructor
    def __init__(self):
        #initialize the image and the name in the menu
        super().__init__(BUTTON_IMG_SRC, SCREEN_GAME, "Snake Game") 

    #the game setup before the game starts replace the method before
    def setupGame(self):
        #the font text
        self.font_style = pygame.font.SysFont("bahnschrift", 25)
        self.score_font = pygame.font.SysFont("comicsansms", 35)
        #the layout
        self.dis = pygame.display.set_mode((SCREEN_WIDTH, SCREEN_HEIGHT))
        #the snake block
        self.snakeBlock = 10
        #snake speed
        self.snakeSpeed = 15
        self.font=pygame.font.SysFont(None, 25)

    #when paused the game aand the music stops
    def paused(self):
        pygame.mixer.music.pause()
        while self.pause:
            for event in pygame.event.get():            
                if event.type == pygame.K_p:
                    self.unpause()
                    
            self.message("Game Paused", BLACK, [SCREEN_WIDTH/2, SCREEN_HEIGHT/2]) 

            pygame.display.update()
            self.clock.tick(60)

    #restart the music and the game
    def unpause(self):
        pygame.mixer.music.unpause()
        self.pause=False

    #update the snale state when a fruit is eaten
    def updateSnake(self, snakeBlock, snakeList):
        #loop for each body of the snake
        for x in snakeList:
            #draaw the snake with the color and the position
            pygame.draw.rect(self.dis, GREEN, [x[0], x[1], snakeBlock, snakeBlock])
 
    #message when the game is over
    def message(self, msg, color, coordinate):
        mesg = self.font_style.render(msg, True, color)
        self.dis.blit(mesg, coordinate)
 
    #quit the game aand return to the main menu
    def quitgame(self):
        self.gameOver = False
        self.gameClose = False

    #play the game after the click on the menu logo, will replace the method before
    def play(self):
        #play the music
        pygame.mixer.music.load(MUSIC)
        pygame.mixer.music.play(-1)
        self.gameOver = False
        self.gameClose = False

        #initialize the starting point
        x1 = SCREEN_WIDTH / 2
        y1 = SCREEN_HEIGHT / 2
    
        #change the x aand y
        x1_change = 0
        y1_change = 0
    
        #snake list
        snakeList = []
        snakeLength = 1
    
        #initialize the food x and y
        foodx = round(random.randrange(0, SCREEN_WIDTH - self.snakeBlock) / 10.0) * 10.0
        foody = round(random.randrange(0, SCREEN_HEIGHT - self.snakeBlock) / 10.0) * 10.0
    
        #check if the game is over yet
        while not self.gameOver:
            #check if the game is closed yet
            while self.gameClose == True:
                #condition when game is finished
                self.dis.fill(GREEN)
                pygame.mixer.music.stop()
                self.message("Vous avez perdu! Appuyez sur (C) pour restart ou sur (Q) pour accéder au menu principal !", RED, [SCREEN_WIDTH / 6, SCREEN_HEIGHT / 3])
                self.message("Félicitation votre score est " + str(snakeLength-1), RED, [SCREEN_WIDTH / 6, (SCREEN_HEIGHT / 3)+30 ])
                
                pygame.display.update()
    
                #keyboard haandling when game over
                for event in pygame.event.get():
                    if event.type == pygame.KEYDOWN:
                        if event.key == pygame.K_q:
                            self.gameOver = True
                            self.gameClose = False
                        if event.key == pygame.K_c:
                            self.play()
    
            #keyboard haandling when game is still running
            for event in pygame.event.get():
                if event.type == pygame.QUIT:
                    #quit
                    self.gameOver = True
                if event.type == pygame.KEYDOWN:
                    if event.key == pygame.K_LEFT:
                        #move left
                        x1_change = -self.snakeBlock
                        y1_change = 0
                    elif event.key == pygame.K_RIGHT:
                        #move right
                        x1_change = self.snakeBlock
                        y1_change = 0
                    elif event.key == pygame.K_UP:
                        #move up
                        y1_change = -self.snakeBlock
                        x1_change = 0
                    elif event.key == pygame.K_DOWN:
                        #move down
                        y1_change = self.snakeBlock
                        x1_change = 0
                    elif event.key == pygame.K_a:
                        #make the snake faster
                        self.snakeSpeed += 5
                    elif event.key == pygame.K_z:
                        #make the snake sloower
                        if(self.snakeSpeed > 10):
                            self.snakeSpeed -= 5
                    elif event.key == pygame.K_q:
                        #quit the game
                            self.gameOver = True
                            self.gameClose = False
                    elif event.key==pygame.K_p:
                        #pause the game
                        self.pause=True
                        self.paused() 
    
            #screen width initialized
            if x1 >= SCREEN_WIDTH or x1 < 0 or y1 >= SCREEN_HEIGHT or y1 < 0:
                self.gameClose = True

            x1 += x1_change
            y1 += y1_change

            #fill the color blue for the background
            self.dis.fill(BLUE)
            #instruction text
            self.message("Appuyez sur A pour accélérer et sur Z pour décélérer", BLACK, [0,0])
            self.message("SCORE: " + str(snakeLength-1), BLACK, [0,50])
            pygame.draw.rect(self.dis, WHITE, [foodx, foody, self.snakeBlock, self.snakeBlock])

            #tried to initialized with real picture but it's too small
            # apple = pygame.image.load(r"../image/snake/apple.png")
            # apple = pygame.transform.scale(apple, (10, 10))
            # self.dis.blit(apple, [foodx, foody, self.snakeBlock, self.snakeBlock])

            #append the snake body
            snakeHead = []
            snakeHead.append(x1)
            snakeHead.append(y1)
            snakeList.append(snakeHead)
            
            #if the list is longer than the length, delete the first list
            if len(snakeList) > snakeLength:
                del snakeList[0]
    
            #if the head hits the body, game over
            for x in snakeList[:-1]:
                if x == snakeHead:
                    self.gameClose = True
    
            #update the snake state
            self.updateSnake(self.snakeBlock, snakeList)
    
            #update the staate layoout
            pygame.display.update()

            #randomly show the food
            if x1 == foodx and y1 == foody:
                foodx = round(random.randrange(0, SCREEN_WIDTH - self.snakeBlock) / 10.0) * 10.0
                foody = round(random.randrange(0, SCREEN_HEIGHT - self.snakeBlock) / 10.0) * 10.0
                snakeLength += 1
    
            self.clock.tick(self.snakeSpeed)
    
        #quit the game and return to the main menu
        pygame.quit()
        from mainMenu import MainMenu
        menu = MainMenu()
        menu.start()
        quit()