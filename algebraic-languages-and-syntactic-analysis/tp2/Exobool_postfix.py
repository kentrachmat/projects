import ard
from ard import Ard
import lexer

LEXER = lexer.Lexer()

class ExoboolPostfix (Ard) :

    def __init__(self) :
        self._axiom = self._E

    def _E(self) :
        if self._current.type in ('CONSTANT','IDENT','OPEN_BRACKET','NOT'):
            return self._Ep(self._T())
        else :
            raise ard.SyntaxError("NO_RULE","E", self._current)
   
    def _Ep(self, value) :
        if self._current.type in ('OR') :
            self._next()
            value2 = self._Ep(self._T())
            return (value +' '+ value2 +' or')
        elif self._current.type in ('CLOSE_BRACKET','EOD') :
            return value
        else :
            raise ard.SyntaxError("NO_RULE","Ep", self._current)
        
    def _T(self) :
        if self._current.type in ('CONSTANT','IDENT','OPEN_BRACKET','NOT'):
            return self._Tp(self._F())
        else :
            raise ard.SyntaxError("NO_RULE","T", self._current)
 
    def _Tp(self, value) :
        if self._current.type in ('AND') :
            self._next()
            value2 = self._Tp(self._F())
            return (value +' '+ value2+' and')
        elif self._current.type in ('OR','EOD','CLOSE_BRACKET') :
            return value
        else :
            raise ard.SyntaxError("NO_RULE","Tp", self._current)

    def _F(self) :
        if self._current.type in ('CONSTANT') :
            value = self._current.value.lower()
            self._next()
            return "True" if value == 'true' else "False"
        elif self._current.type in ('IDENT'):
            value = self._current.value
            self._next()
            return value
        elif self._current.type in ('OPEN_BRACKET'):
            self._next()
            value = self._E()
            self._eat('CLOSE_BRACKET')
            return value
        elif self._current.type in ('NOT'):
            self._next()
            return (self._F()+' not')
        else :
            raise ard.SyntaxError("NO_RULE","F", self._current)


if __name__ == '__main__' :
    c = ExoboolPostfix()
    try :
        #source = input("Entrez un texte à analyse : ")
        source = "(a||b)&&(c||d)"
        resultat = c.parse(source, LEXER)
        print(f"Mot à analyser : {source} \nRésultat : {resultat}")
    except ard.SyntaxError as e :
        print (e)