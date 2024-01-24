from sly import Lexer
from sly.lex import LexError
import re
#import operator

class JadeLexer(Lexer):
    # token types :
    tokens = {BOOLEAN, NATURAL, VARIABLE, 
              IF, ELSE, WHILE,
              INST0, INST1,
              LEFTBRACE,RIGHTBRACE,LEFTPAR,RIGHTPAR,
              AND,OR,NOT,
              #SUM,SUB,MUL,DIV,
              COMP_OP}
    # token specifications :
    
    BOOLEAN = '(?i)true|false'
    _int = r'(0|[1-9]\d*)'
    #_number = rf'({_int}([.]\d*)?|[.]\d+)'
    NATURAL = _int
    VARIABLE = r'(?i)posx|posy|step'
    
    IF = '(?i)if'
    ELSE = '(?i)else'
    WHILE = '(?i)while'

    INST0 = r'(?i)north|south|east|west|penup|pendown'
    INST1 = r'(?i)setstep'

    LEFTBRACE = '{'
    RIGHTBRACE = '}'
    LEFTPAR = '[(]'
    RIGHTPAR = '[)]'

    AND = '&&'
    OR = '[|][|]'
    NOT = '!'
    #     SUM = '[+]'
    #     SUB = '[-]'
    #     MUL = '[*]'
    #     DIV = '[/]'
    
    COMP_OP = '==|!=|<=|>=|<|>'
    #IDENT = r'[A-Za-z](_?[A-Za-z0-9])*' 
    

    ignore = ' \t'
    
 
    def NUMBER(self,t) :
        t.value = float(t.value)
        return t
    
    def BOOLEAN(self,t) :
        t.value = t.value.lower()=='true'
        return t
    
    def STEP(self,t) :
        parts = re.split('[ ]+',t.value)
        t.value = int(parts[1])
        return t

    
    @_(r'\n+')
    def ignore_newline(self, t):
        self.lineno += len(t.value)
 

if __name__ == '__main__':
    
    analyseur = JadeLexer()

    print('entrez un texte à analyser')
    source = input()

    tokenIterator = analyseur.tokenize(source)
    try :
        for tok in tokenIterator :
            print(f'token -> type: {tok.type}, valeur: {tok.value} ({type(tok.value)}), ligne : {tok.lineno}')
    except LexError as erreur :
        print("Erreur à l'anayse lexicale ", erreur)
        
    

