import ard
from ard import Ard
import lexer

LEXER = lexer.Lexer()

class ExoboolArd (Ard) :

    def __init__(self) :
        self._axiom = self._E

    def _E(self):
        if self._current.type in ('CONSTANT','IDENT','OPEN_BRACKET','NOT'):
            self._T()
            self._Ep()
        else :
            raise ard.SyntaxError("NO_RULE","E", self._current)
   
    def _Ep(self):
        if self._current.type in ('OR') :
            self._next()
            self._T()
            self._Ep()
        elif self._current.type in ('CLOSE_BRACKET','EOD') :
            return
        else :
            raise ard.SyntaxError("NO_RULE","Ep", self._current)
        
    def _T(self) :
        if self._current.type in ('CONSTANT','IDENT','OPEN_BRACKET','NOT') :
            self._F()
            self._Tp()
        else :
            raise ard.SyntaxError("NO_RULE","T", self._current)
 
    def _Tp(self) :
        if self._current.type in ('AND'):
            self._next()
            self._F()
            self._Tp()
        elif self._current.type in ('OR','EOD','CLOSE_BRACKET') :
            return
        else :
            raise ard.SyntaxError("NO_RULE","Tp", self._current)

    def _F(self) :
        if self._current.type in ('CONSTANT') or self._current.type in ('IDENT'):
            self._next()
        elif self._current.type in ('OPEN_BRACKET'):
            self._next()
            self._E()
            self._eat('CLOSE_BRACKET')
        elif self._current.type in ('NOT'):
            self._next()
            self._F()
        else :
            raise ard.SyntaxError("NO_RULE","F", self._current)

if __name__ == '__main__' :
    c = ExoboolArd()
    try :
        #source = input("Entrez un texte à analyse : ")
        source = "true && false || !true"
        resultat = c.parse(source, LEXER)
        print(f"Mot à analyser : {source} \nRésultat : {resultat}")
    except ard.SyntaxError as e :
        print (e)
