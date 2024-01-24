from sly import Lexer
from sly.lex import LexError

class Lexer (Lexer):  
    # token types :
    tokens = {LETTRE, ENTIER, OUVRANTE, FERMANTE}
    # token specifications :
    
    LETTRE = '[a-zA-Z]'
    ENTIER = '[0-9]+'
    OUVRANTE = '[(]'
    FERMANTE = '[)]'
    
    def ENTIER(self,t) :
        t.value = int(t.value)
        return t

    @_(r'\n+')
    def ignore_newline(self, t):
        self.lineno += len(t.value)
 
if __name__ == '__main__':
    
    analyseur = Lexer()   
    #source = input("Entrez un texte à analyse : ")
    source = "(a(bc)2)3(ba)2"
    print("Mot à analyser : ",source)
    tokenIterator = analyseur.tokenize(source)
    try :
        for tok in tokenIterator :
            print(f'token -> type: {tok.type}, valeur: {tok.value} ({type(tok.value)}), ligne : {tok.lineno}')
    except LexError as erreur :
        print("Erreur à l'anayse lexicale ", erreur)
        