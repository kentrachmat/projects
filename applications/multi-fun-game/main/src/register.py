"""
author : Benedictus Kent Rachmat - Hichem Karfa
version : 2.0
Projet L3S6
"""

#import the dependencies
from numpy import full
from PyQt5.QtWidgets import (QMessageBox, QPushButton, QLineEdit,QVBoxLayout, QWidget, QLabel)
from constant import *
from base import Base
import smtplib 
import re

#this class is for the the regisration form
class Register(QWidget, Base):
    # the constructor
    def __init__(self):
        #calling the parent constructor
        super().__init__()
        #initialize the layout box
        self.layout = QVBoxLayout()
        #initialize the box
        self.msg = QMessageBox()
        #language
        self.lang 
        self.resize(500, 120)
        #callinng the setup function
        self.setup()
        self.setLayout(self.layout)

    #refresh the page with thee given language
    def refresh(self, lang):
        for i in reversed(range(self.layout.count())): 
            self.layout.itemAt(i).widget().setParent(None)
        self.lang = lang
        self.setup()

    #setup the form
    def setup(self): 
        #the title
        self.label = QLabel("%s" % self.lang['register_title'])
        self.setWindowTitle(self.lang['login_title'])
        self.layout.addWidget(self.label)

        #the full name input
        label_fullname = QLabel('%s : ' % self.lang['register_fullname'])
        self.lineEdit_fullname = QLineEdit()
        self.layout.addWidget(label_fullname)
        self.layout.addWidget(self.lineEdit_fullname)

        #the email input
        label_email = QLabel('%s : ' % self.lang['register_email'])
        self.lineEdit_email = QLineEdit()
        self.layout.addWidget(label_email)
        self.layout.addWidget(self.lineEdit_email)

        #the username input
        label_username = QLabel('%s : ' % self.lang['login_user'])
        self.lineEdit_username = QLineEdit()
        self.lineEdit_username.setPlaceholderText('kent')
        self.layout.addWidget(label_username)
        self.layout.addWidget(self.lineEdit_username)

        #the password input
        label_password= QLabel('%s : ' % self.lang['login_pass'])
        self.lineEdit_password = QLineEdit()
        self.lineEdit_password.setPlaceholderText('12345')
        self.layout.addWidget(label_password)
        self.layout.addWidget(self.lineEdit_password)

        #the login input
        button_login = QPushButton('%s' % self.lang['register_save'])
        button_login.clicked.connect(self.uploadData)
        self.layout.addWidget(button_login)

    #uplooad data when click submit
    def uploadData(self):
        #get all the variables
        user = self.lineEdit_username.text()
        password = self.lineEdit_password.text()
        fullname = self.lineEdit_fullname.text()
        email = self.lineEdit_email.text()

        #check if the form is empty or not
        if(user == "" or password == "" or fullname == "" or email == ""):
            self.msg.setText(self.lang['register_nok'])
            self.msg.exec_()
            return

        #check if the password less than length 4
        if(len(password) <= 4):
            self.msg.setText(self.lang['register_nok_pass'])
            self.msg.exec_()
            return

        #check if the email valid or not
        if(not self.checkEmail(email)):
            self.msg.setText(self.lang['register_nok_email'])
            self.msg.exec_()
            return

        #check if there's another user inn the database
        if(not self.dataRequest.userValid(user)):
            self.msg.setText(self.lang['register_nok_user'])
            self.msg.exec_()
            return
        else:
            #save the data
            self.saveData(user, password, fullname, email)

    #check the email with regex
    def checkEmail(self,email):
        return re.fullmatch(REGEX_EMAIL, email)  

    #save the daata to the database
    def saveData(self, user, password, fullname, email):
        #if the saving process successfull
        if(self.dataRequest.saveUser(user, password,fullname, email)):
            #send the email with the given configuration
            self.sendEmail(email, fullname, user, password)
            #ok notification
            self.msg.setText(self.lang['register_ok'])
            self.msg.exec_()

    #send the email to the desination
    def sendEmail(self, email, fullname, user, password):
        #the subject
        message = 'Subject: {}\n\n{}'.format(self.lang['email_registration_subject'], self.lang['email_registration_text'] % (fullname, user, password))
        #the serer connectioon with port 465 because it's smtp gmail
        server = smtplib.SMTP_SSL("smtp.gmail.com", 465)
        server.login(FROM, PASS)
        server.sendmail(FROM, email, message.encode('utf-8').strip())
        server.quit()