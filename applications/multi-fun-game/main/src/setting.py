"""
author : Benedictus Kent Rachmat - Hichem Karfa
version : 2.0
Projet L3S6
"""

#initial screen width and height
SCREEN_WIDTH = 1920 
SCREEN_HEIGHT = 1080

#import the dependencies
import pygame

pygame.init()
currentScreen = pygame.display.Info()

#initialize the FPD and size
SIZE = WIDTH, HEIGHT = currentScreen.current_w, currentScreen.current_h
FPS = 60