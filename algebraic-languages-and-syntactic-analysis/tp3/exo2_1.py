from sly import Parser
from lexer_td2 import Lexer

LEXER = Lexer()

class Exo2_1(Parser) :
    tokens = Lexer.tokens
    start = 'e'

    # e -> e || t 
    @_('e OR t')
    def e(self, p): 
        return None

    # e -> t
    @_('t')
    def e(self, p): 
        return None

    # t -> t && f
    @_('t AND f')
    def t(self, p): 
        return None

    # t -> f
    @_('f')
    def t(self, p): 
        return None
    
    # f -> !f
    @_('NOT f')
    def f(self, p): 
        return None

    # f -> (e)
    @_('OPEN_BRACKET e CLOSE_BRACKET')
    def f(self, p): 
        return None

    # f -> c
    @_('CONSTANT')
    def f(self, p): 
        return None

    # f -> i
    @_('IDENT')
    def f(self, p): 
        return None

if __name__ == '__main__':
    parser = Exo2_1()
    while True:
        try:
            text = input('test exo2.1 > ')
            result = parser.parse(LEXER.tokenize(text))
            print(result)
        except EOFError:
            break