"""
author : Benedictus Kent Rachmat - Hichem Karfa
version : 2.0
Projet L3S6
"""

#import the dependencies
from languages import *
from dataRequest import DataRequest
from PyQt5.QtWidgets import QMessageBox

#this class is the base to create the from by initializing the language
class Base():
	#the constructor
	def __init__(self):
		#the language, french by default
		self.lang = fr
		#request to the database
		self.dataRequest = DataRequest()

	#set the language
	def setLang(self, lang):
		if lang == "Fr":
			self.lang = fr
		elif lang == "English":
			self.lang = eng

	#close event when the user press the exit button
	def closeEvent(self, event):
		msgBox = QMessageBox()
		msgBox.setIcon(QMessageBox.Information)
		msgBox.setText(self.lang['notif_exit'])
		msgBox.setWindowTitle(self.lang['notif_confirm'])
		msgBox.setStandardButtons(QMessageBox.Yes | QMessageBox.No)
		val = msgBox.exec()
		if val == QMessageBox.Yes: 
			event.accept()
		else: 
			event.ignore()
		self.close()
