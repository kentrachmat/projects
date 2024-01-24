import ard
from ard import Ard
import lexer

LEXER = lexer.Lexer()

class Analyseur (Ard) :
        # S -> E R S   | epsilon 
        # E -> LETTRE  | OUVRANTE S FERMANTE
        # R -> ENTIER  | epsilon

    def __init__(self) :
        self._axiom = self._S

    def _S(self) :
        if self._current.type == 'LETTRE' or self._current.type == 'OUVRANTE': 
            # S -> ERS
            print('S->ERS', self._current,  self._current.index)
            self._E()
            self._R()
            self._S()
            
        elif self._current.type in ('FERMANTE', 'EOD'):
            # S -> epsilon
            print('S->epsilon', self._current, self._current.index)
            pass

        else :
            raise ard.SyntaxError("NO_RULE","S", self._current)
  
    def _E(self):
        if self._current.type == 'LETTRE':
            # E -> LETTRE
            print('E->LETTRE', self._current, self._current.index)
            self._next()

        elif self._current.type == 'OUVRANTE':
            # E -> OUVRANTE S FERMANTE
            print('E->(S)', self._current,  self._current.index)
            self._next()
            self._S()
            self._next()

        else :
            raise ard.SyntaxError("NO_RULE","E", self._current)
            
    def _R(self):
        if self._current.type == 'ENTIER':
            # R -> ENTIER
            print('R->ENTIER', self._current, self._current.index)
            self._next()
            
        else:
            # R -> epsilon
            print('R->epsilon', self._current, self._current.index)
            pass

if __name__ == '__main__':
    c = Analyseur()
    try :
        #source = input("Entrez un texte à analyse : ")
        source = "(a)b"
        print("Mot à analyser : ",source)
        c.parse(source ,LEXER)
    except ard.SyntaxError as e :
        print (e)
