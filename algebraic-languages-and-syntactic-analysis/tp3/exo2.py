from sly import Parser
from fns import FonctionsLexer

LEXER = FonctionsLexer()

class Exo2(Parser) :
    tokens = FonctionsLexer.tokens
    start = 'e'

    # e -> G(e)
    @_('MONA OUVR e FERM')
    def e(self, p):
        return p.e

    # e -> F(e,e)
    @_('DIA OUVR e COMMA e FERM')
    def e(self, p):
        return p.e0 + p.e1

    # e -> c 
    @_('CONST')
    def e(self, p):
        return 1

if __name__ == '__main__':
    parser = Exo2()
    while True:
        try:
            text = input('test exo2 > ')
            result = parser.parse(LEXER.tokenize(text))
            print(result)
        except EOFError:
            break