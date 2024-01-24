from sly import Parser
from lexer_td2 import Lexer

LEXER = Lexer()

class Exo2_2(Parser) :
    tokens = Lexer.tokens
    start = 'e'

    # e -> e || t 
    @_('e OR t')
    def e(self, p): 
        return p.e or p.t

    # e -> t
    @_('t')
    def e(self, p): 
        return p.t

    # t -> t && f
    @_('t AND f')
    def t(self, p): 
        return p.t and p.f

    # t -> f
    @_('f')
    def t(self, p): 
        return p.f
    
    # f -> !f
    @_('NOT f')
    def f(self, p): 
        return not p.f

    # f -> (e)
    @_('OPEN_BRACKET e CLOSE_BRACKET')
    def f(self, p): 
        return (p.e)

    # f -> c
    @_('CONSTANT')
    def f(self, p): 
        return p.CONSTANT.lower() == "true"

    # f -> i
    @_('IDENT')
    def f(self, p): 
        return False

if __name__ == '__main__':
    parser = Exo2_2()
    while True:
        try:
            text = input('test exo2.2 > ')
            result = parser.parse(LEXER.tokenize(text))
            print(result)
        except EOFError:
            break