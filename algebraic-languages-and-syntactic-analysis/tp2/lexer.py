from sly import Lexer
from sly.lex import LexError

class Lexer(Lexer):
    tokens = { CONSTANT, IDENT, NOT, OR, AND, OPEN_BRACKET, CLOSE_BRACKET }

    # String containing ignored characters between tokens
    ignore = '\t" "'

    # Regular expression rules for tokens
    CONSTANT  = '(?i)(True|False)'
    IDENT  = r'[A-Za-z](_?[A-Za-z0-9])*'
    NOT  = r'!'
    OR  = r'\|\|'
    AND = r'&&'
    OPEN_BRACKET = r'\('
    CLOSE_BRACKET = r'\)'

if __name__ == '__main__':
    analyseur = Lexer()   
    #source = input("Entrez un texte à analyse : ")
    source = "a && b || c !d (true) (false)"
    print("Mot à analyser : ",source)
    tokenIterator = analyseur.tokenize(source)
    try :
        for tok in tokenIterator :
            print('type=%r, value=%r' % (tok.type, tok.value))
    except LexError as erreur :
        print("Erreur à l'anayse lexicale ", erreur)
        

