import turtle

class UnknownInstruction (Exception) :
    pass
"""
 Simulate Jade simple instruction, using turtle
 
 readonly attributes :
     posx, posy : turtle current position (integers)
     step : step length (integer)
     
 commands :
     exec0(comand_name) for command without parameter
     exec1(comand_name, parm) for command with one parameter
 
"""
class JadeMachine () :
    
    def __init__(self) :
        self.myturtle = turtle.Turtle()
        self.myturtle.goto(0,0)
        
    # private attributes
    _posx = 0
    _posy = 0
    _step = 25
    
    # getters : ( jm.posx instead of jm.posx() )
    @property
    def step(self): 
        return self._step 
    @property
    def posx(self): 
        return self._posx 
    @property
    def posy(self): 
        return self._posy 

    # private methods :
    def __turn_and_move(self, angle) :
        self.myturtle.setheading(angle)
        self.myturtle.forward(self.step)
        x, y = self.myturtle.pos()
        self._posx, self._posy = round(x), round(y)
        print(angle, (self.posx, self.posy) )
        
    def __nord(self) :
        self.__turn_and_move(90)      
    def __sud(self) :
        self.__turn_and_move(-90)
    def __est(self) :
        self.__turn_and_move(0)
    def __ouest(self) :
        self.__turn_and_move(180)
    def __lever(self) :
        self.myturtle.penup()
    def __baisser(self) :
        self.myturtle.pendown()        
    def __setstep(self,val) :
        assert isinstance(val,int)
        assert val > 0
        self._step = val
        
    # publicly accessible commands without parameter
        
    _inst0 = {
        'north' : __nord,
        'south' : __sud,
        'east' : __est,
        'west' : __ouest,
        'penup' : __lever,
        'pendown' : __baisser,
    }
    
    # publicly accessible commands with one parameter
    _inst1 = {
        'setstep' : __setstep,
    }
    
    """
    execute command without argument
    raise UnknownInstruction if command doesn't exist
    """
    def exec0(self,name) :
        try :
            self._inst0[name](self)
        except KeyError as e :
            raise UnknownInstruction(f'{name}')

    """
    execute command with one argument
    raise UnknownInstruction if command doesn't exist
    """
    def exec1(self,name, param) :
        try :
            self._inst1[name](self, param)
        except KeyError as e :
            raise UnknownInstruction(f'{name}')
        
