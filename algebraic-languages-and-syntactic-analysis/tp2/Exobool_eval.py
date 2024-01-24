import ard
from ard import Ard
import lexer

LEXER = lexer.Lexer()

class ExoboolEval (Ard) :

    def __init__(self) :
        self._axiom = self._E

    def _E(self) :
        if self._current.type in ('CONSTANT','IDENT','OPEN_BRACKET','NOT') :
            return self._Ep(self._T())
        else :
            raise ard.SyntaxError("NO_RULE","E", self._current)
   
    def _Ep(self, value) :
        if self._current.type in ('OR') :
            self._next()
            value2 = self._Ep(self._T())
            return (value or value2)
        elif self._current.type in ('CLOSE_BRACKET','EOD') :
            return value
        else :
            raise ard.SyntaxError("NO_RULE","Ep", self._current)
        
    def _T(self) :
        if self._current.type in ('CONSTANT','IDENT','OPEN_BRACKET','NOT') :
            return self._Tp(self._F())
        else :
            raise ard.SyntaxError("NO_RULE","T", self._current)
 
    def _Tp(self, value) :
        if self._current.type in ('AND') :
            self._next()
            value2 = self._Tp(self._F())
            return (value and value2)
        elif self._current.type in ('OR','EOD','CLOSE_BRACKET') :
            return value
        else :
            raise ard.SyntaxError("NO_RULE","Tp", self._current)

    def _F(self) :
        if self._current.type in ('CONSTANT') :
            value = self._current.value.lower()
            self._next()
            return value == 'true'
        elif self._current.type in ('IDENT') :
            self._next()
            return False
        elif self._current.type in ('OPEN_BRACKET') :
            self._next()
            value = self._E()
            self._eat('CLOSE_BRACKET')
            return value
        elif self._current.type in ('NOT')  :
            self._next()
            return (not(self._F()))
        else :
            raise ard.SyntaxError("NO_RULE","F", self._current)

if __name__ == '__main__' :
    c = ExoboolEval()
    try :
        #source = input("Entrez un texte à analyse : ")
        source = "(true || (!false || true)) && false"
        resultat = c.parse(source, LEXER)
        print(f"Mot à analyser : {source} \nRésultat : {resultat}")
    except ard.SyntaxError as e :
        print (e)