from sly import Parser
from jadelexer  import JadeLexer

INDENT_SPACE = "\t"
        
def indent (liste) :
    return [INDENT_SPACE+s for s in liste]

INIT = '''
import jademachine
jm = jademachine.JadeMachine()
s = jm.myturtle.getscreen()
'''
END = '''
jm.myturtle.hideturtle()
s.exitonclick() 
'''

class JadeParser(Parser):
    # Get the token list from the lexer (required)
    tokens = JadeLexer.tokens
    debugfile = 'parser.out'
    start = 'programme'
    
    @_('sequence')
    def programme(self,p) :
        # semantic action : 
        return INIT + '\n'.join(p.sequence) + '\n'+ END   

    #--- sequence -> instruction sequence | instruction
    @_('sequence instruction')
    def sequence(self, p):
        p.sequence.extend(p.instruction)
        return p.sequence

    @_('instruction')
    def sequence(self, p): 
        return p.instruction
    
    #--- instruction -> simple | bloc  | inst_if | inst_while
    @_('simple')
    def instruction(self, p): 
        return p.simple
    @_('bloc')
    def instruction(self, p):
        return p.bloc
    @_('inst_if')
    def instruction(self, p):
        return p.inst_if
    @_('inst_while')
    def instruction(self, p):
        return p.inst_while
    
    @_('INST0')
    def simple(self,p):
        return [f'jm.exec0("{p.INST0}")']
    @_('INST1 entier')
    def simple(self,p):
        return [f'jm.exec1("{p.INST1}", {p.entier})']

    #--- bloc -> { sequence }
    @_('LEFTBRACE  sequence RIGHTBRACE')
    def bloc(self, p):
        return p.sequence

    #--- inst_if  -> IF cond intruction else_part
    @_('IF cond instruction else_part')
    def inst_if(self, p):   
        return [f"{p.IF} {p.cond} :",*indent(p.instruction),*p.else_part]  
    
    #--- else_part -> ELSE instruction | epsilon
    @_('ELSE instruction')
    def else_part(self, p):
        return [f"{p.ELSE} :",*indent(p.instruction)]
    @_('')
    def else_part(self, _):
        return [""] 

    #--- inst_while  -> WHILE cond instruction
    @_('WHILE cond instruction')
    def inst_while(self, p):
        return [f"{p.WHILE} {p.cond} :",*indent(p.instruction)]

    #--- cond  -> LEFTPAR expr_bool RIGHTPAR
    @_('LEFTPAR expr_bool RIGHTPAR')
    def cond(self, p): 
        return f'{p.LEFTPAR} {p.expr_bool} {p.RIGHTPAR}'
    
    #--- expr -> expr_bool OR term | term
    @_('expr_bool OR term')
    def expr_bool(self,p) :
        return f'{p.expr_bool} {p.OR} {p.term}'
    @_('term')
    def expr_bool(self,p) :
        return p.term
  
    #--- term -> term AND factor | factor
    @_('term AND factor')
    def term(self,p) :
        return f'{p.term} {p.AND} {p.factor}'
    @_('factor')
    def term(self,p) :
        return p.factor
    
    #--- l1_factor -> NOT factor | LEFTPAR expr_bool RIGHTPAR | BOOLEAN |
    @_('NOT factor')
    def factor(self,p) :
        return f'{p.NOT} {p.factor}'
    @_('LEFTPAR expr_bool RIGHTPAR')
    def factor(self,p) :
        return f'{p.LEFTPAR} {p.expr_bool} {p.RIGHTPAR}'
    @_('BOOLEAN')
    def factor(self,p) :
        return f'{p.BOOLEAN}'
    @_('comparison')
    def factor(self,p) :
        return p.comparison
    
    #--- comparison -> entier COMP_OP entier 
    @_('entier COMP_OP entier')
    def comparison(self,p) :
        return f'{p.entier0} {p.COMP_OP} {p.entier1}'
    
    #--- entier -> VARIABLE | NATURAL
    @_('VARIABLE')
    def entier(self,p) : 
        return f'jm.{p.VARIABLE}'
    @_('NATURAL')
    def entier(self,p) :
        return f'{p.NATURAL}'

if __name__ == '__main__':
    lexer = JadeLexer()
    parser = JadeParser()

    while True:
        try:
            text = input('jade > ')
            result = parser.parse(lexer.tokenize(text))
            print(result) 
            #exec(result)
        except EOFError:
            break