# usage : python3 jade_to_py.py jade_filename
#  outputs python translation on standard output
    
from jadelexer  import JadeLexer
from jadeparser import JadeParser
import sys

lexer = JadeLexer()
parser = JadeParser()

if len(sys.argv)<=1 :
    print('missing file name')
else :
    with open(sys.argv[1],'r') as input :
        it = lexer.tokenize(input.read())
        result = parser.parse(it)
        print(result)
        if(len(sys.argv) == 3 and sys.argv[2] == "show"):
            exec(result)
        