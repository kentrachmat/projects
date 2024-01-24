"""
author : Benedictus Kent Rachmat - Hichem Karfa
version : 2.0
Projet L3S6
"""

#import the dependencies
from psycopg2 import OperationalError 
from constant import * 
import psycopg2
import hashlib
import sys 

#class for the sql queries request
class DataRequest():
    #the constructor
    def __init__(self):
        try:
            #check if the database connection is successfull
            print("connection database success")
            self.conn = psycopg2.connect(dbname=DB_NAME, user=DB_USER, password=DB_PASS, host=DB_HOST, port=DB_PORT, sslmode=DB_SSL)
            self.cursor = self.conn.cursor()
        except OperationalError as err:
            #error handling
            self.print_psycopg2_exception(err)
            self.conn = None

    #print the information why is the connection error
    def print_psycopg2_exception(self, err):
        err_type, err_obj, traceback = sys.exc_info()
        line_num = traceback.tb_lineno
        print ("\npsycopg2 ERROR:", err, "on line number:", line_num)
        print ("psycopg2 traceback:", traceback, "-- type:", err_type)
        print ("\nextensions.Diagnostics:", err.diag)
        print ("pgerror:", err.pgerror)
        print ("pgcode:", err.pgcode, "\n")

    #get all users 
    def getUsers(self):
        sql = "select * from users"
        self.cursor.execute(sql)
        return self.cursor.fetchall()

    #search the users with the given name and password
    def searchUsers(self, name, password):
        sql = "select * from users where username = '%s' and password = '%s'" % (name, self.encrypt(password))
        self.cursor.execute(sql)
        return self.cursor.fetchone()

    #check if the user is not blocked by the admin
    def userValid(self, user, block= False):
        sql = "select * from users where username = '%s'" % user
        if block:
            sql += "and block=0"
        self.cursor.execute(sql)
        return self.cursor.fetchone() == None

    #save new user to the database
    def saveUser(self,user, password,fullname, email):
        password = self.encrypt(password)
        sql = "insert into users (username, password, name, level, email) values ('%s', '%s', '%s', 2, '%s')" %(user, password, fullname, email)
        self.cursor.execute(sql)

        self.conn.commit()
        count = self.cursor.rowcount
        return count == 1

    #block user when the user reach a certain aamount of attempt
    def blockAccount(self, user):
        sql = "update users set block = 1 where username='%s'" % (user)
        self.cursor.execute(sql)

        self.conn.commit()
        count = self.cursor.rowcount
        return count == 1

    #encrypt the password with md5 hashlib
    def encrypt(self, text):
        hash_object = hashlib.md5(text.encode())
        md5_hash = hash_object.hexdigest()
        return md5_hash