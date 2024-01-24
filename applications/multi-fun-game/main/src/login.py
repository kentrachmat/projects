"""
author : Benedictus Kent Rachmat - Hichem Karfa
version : 2.0
Projet L3S6
"""

#import the dependencies
import sys
from PyQt5 import QtCore
from PyQt5.QtWidgets import (QComboBox, QApplication, QWidget, QPushButton, QLabel, QLineEdit, QGridLayout, QMessageBox)
from register import Register
from base import Base

#the tentative autorized
MAX_TENTATIVE = 2

#this class is to design thhe login form 
class LoginForm(QWidget, Base):
	#the constructor
	def __init__(self):
		#calling the parent class, base and Qwidget
		super().__init__()
		#initiaalize the variable
		self.w = None
		self.tentative = 0
		self.resize(500, 120)
		self.layout = QGridLayout()
		self.setup()
		self.setLayout(self.layout)

	#setting the setup for each input
	def setup(self):
		#the title
		self.setWindowTitle(self.lang['login_title'])

		#field for the user name
		label_name = QLabel('<font size="4"> %s </font>' % self.lang['login_user'])
		self.lineEdit_username = QLineEdit()
		self.lineEdit_username.setPlaceholderText('kent')
		self.layout.addWidget(label_name, 0, 0)
		self.layout.addWidget(self.lineEdit_username, 0, 1, 1, 3)

		#field for the password
		label_password = QLabel('<font size="4"> %s </font>' % self.lang['login_pass'])
		self.lineEdit_password = QLineEdit()
		self.lineEdit_password.setPlaceholderText('12345')
		self.layout.addWidget(label_password, 1, 0)
		self.layout.addWidget(self.lineEdit_password, 1, 1, 1, 3)

		#field for the login button
		button_login = QPushButton(self.lang['login'])
		button_login.clicked.connect(self.login)
		self.layout.addWidget(button_login, 2, 0, 1, 2)

		#field for the new aaccount menu
		button_register = QPushButton(self.lang['login_new_account'])
		button_register.clicked.connect(self.register)
		self.layout.addWidget(button_register ,2,2,1,2)

		#field for the language option menu
		combo = QComboBox(self)
		combo.addItem("Fr")
		combo.addItem("English")
		self.layout.addWidget(combo ,8,2,1,2)
		combo.activated[str].connect(self.setLang)  

	#set the new language
	def setLang(self, lang):
		#calling the parent method
		super().setLang(lang)
		self.refresh()

	#refresh the page when the language is changed
	def refresh(self):
		#change all the field component
		for i in reversed(range(self.layout.count())): 
			self.layout.itemAt(i).widget().setParent(None)
		self.setup()

	#keyboard handling
	def keyPressEvent(self, qKeyEvent):
		if qKeyEvent.key() == QtCore.Qt.Key_Return: 
			self.login()
		else:
			super().keyPressEvent(qKeyEvent)

	#if the user press the new user registration, it opens a new window
	def register(self):
		if self.w is None:
			self.w = Register()
		self.w.refresh(self.lang)
		#opening the new window
		self.w.show()

	#method use when user clicked login button
	def login(self):
		#get the username and the password inputed
		msg = QMessageBox()
		user = self.lineEdit_username.text()
		password = self.lineEdit_password.text()

		#check if the user is valid or not
		if(not self.dataRequest.userValid(user) and password !=""):
			#sql request
			sqlAnswer = self.dataRequest.searchUsers(user , password)
			#check if  the account is blocked or not
			if sqlAnswer != None and sqlAnswer[-1]:
				msg.setText(self.lang['login_acc_block'])
				msg.exec_()
				return
			#check if the user is found, enter the if statement if that's the case
			if sqlAnswer != None :
				msg.setText(self.lang['login_ok'])
				from mainMenu import MainMenu
				msg.exec_()
				#launching the main menu
				menu = MainMenu()
				menu.start()
				app.quit()
			else:
				if(self.dataRequest.userValid(user,True) or self.tentative >= MAX_TENTATIVE):
					self.dataRequest.blockAccount(user)
					msg.setText(self.lang['login_acc_block'])
					msg.exec_()
					return
				else:
					self.tentative+=1
					msg.setText('%s (%d/%d)'% (self.lang['login_nok'], self.tentative, MAX_TENTATIVE))
					msg.exec_()
		else:
			msg.setText('%s'% (self.lang['login_no_found']))
			msg.exec_()

if __name__ == '__main__':
	app = QApplication(sys.argv)
	form = LoginForm()
	#start the application
	form.show()
	sys.exit(app.exec_())