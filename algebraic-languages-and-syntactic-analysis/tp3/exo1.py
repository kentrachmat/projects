from sly import Parser
from fns import FonctionsLexer

LEXER = FonctionsLexer()

class Exo1(Parser) :
    tokens = FonctionsLexer.tokens
    start = 'e'

    # e -> G(e)
    @_('MONA OUVR e FERM')
    def e(self, p):
        return None

    # e -> F(e,e)
    @_('DIA OUVR e COMMA e FERM')
    def e(self, p):
        return None

    # e -> c 
    @_('CONST')
    def e(self, p):
        return None

if __name__ == '__main__':
    parser = Exo1()
    while True:
        try:
            text = input('test exo1 > ')
            result = parser.parse(LEXER.tokenize(text))
            print(result)
        except EOFError:
            break