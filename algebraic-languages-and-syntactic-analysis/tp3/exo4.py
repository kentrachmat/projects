from sly import Parser
from fns import FonctionsLexer

LEXER = FonctionsLexer()

class Exo4(Parser) :
    tokens = FonctionsLexer.tokens
    start = 'e'

    # e -> G(e)
    @_('MONA OUVR e FERM')
    def e(self, p):
        return p.e + 1

    # e -> F(e,e)
    @_('DIA OUVR e COMMA e FERM')
    def e(self, p):
        return p.e1 + 1 if (p.e0 == 0 and p.e1 != 0)  else p.e0 + 1

    # e -> c 
    @_('CONST')
    def e(self, p):
        return 0

if __name__ == '__main__':
    parser = Exo4()
    while True:
        try:
            text = input('test exo4 > ')
            result = parser.parse(LEXER.tokenize(text))
            print(result)
        except EOFError:
            break