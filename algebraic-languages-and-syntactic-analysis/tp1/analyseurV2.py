import ard
from ard import Ard
import lexer

LEXER = lexer.Lexer()

class AnalyseurV2 (Ard) :
        # S -> E R S   | epsilon 
        # E -> LETTRE  | OUVRANTE S FERMANTE
        # R -> ENTIER  | epsilon

    def __init__(self) :
        self._axiom = self._S
    
    def _S(self) :
        if self._current.type == 'LETTRE' or self._current.type == 'OUVRANTE': 
            # S -> ERS
            return self._E() * self._R() + self._S()
            
        elif self._current.type in ('FERMANTE', 'EOD'):
            return ''
        
        else :
            raise ard.SyntaxError("NO_RULE","S", self._current)
  
    def _E(self)  :
        if self._current.type == 'LETTRE':
            # E -> LETTRE
            mot = self._current.value
            self._next()
            return mot
        
        elif self._current.type == 'OUVRANTE':
            # E -> OUVRANTE S FERMANTE
            self._next()
            mot = self._S()
            self._next()
            return mot

        else :
            raise ard.SyntaxError("NO_RULE","E", self._current)
        
    def _R(self)  :
        if self._current.type == 'ENTIER':
            # R -> ENTIER
            nb = int(self._current.value)
            self._next()
            return nb

        else :
            # R -> epsilon
            return 1 

if __name__ == '__main__':
    c = AnalyseurV2()
    try :
        #source = input("Entrez un texte à analyse : ")
        source = '(a(bc)2)3(ba)2'
        print("Mot à analyser : ",source)
        print(c.parse(source ,LEXER))
    except ard.SyntaxError as e :
        print (e)
