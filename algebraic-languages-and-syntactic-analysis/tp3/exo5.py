from sly import Parser
from fns import FonctionsLexer

LEXER = FonctionsLexer()

class Exo5(Parser) :
    tokens = FonctionsLexer.tokens
    start = 'e'

    # e -> G(e)
    @_('MONA OUVR e FERM')
    def e(self, p): 
        return [p.e[0], p.e[1] * 3, p.e[2] + 1]

    # e -> F(e,e)
    @_('DIA OUVR e COMMA e FERM')
    def e(self, p): 
        return [p.e0[0] + p.e1[0], p.e0[1] + p.e1[1], p.e1[2] + 1 if (p.e0[2] == 0 and p.e1[2] != 0)  else p.e0[2] + 1]

    # e -> c 
    @_('CONST')
    def e(self, p): 
        return [1, p.CONST, 0]

if __name__ == '__main__':
    parser = Exo5()
    while True:
        try:
            text = input('test exo5 > ')
            result = parser.parse(LEXER.tokenize(text))
            print(result)
        except EOFError:
            break