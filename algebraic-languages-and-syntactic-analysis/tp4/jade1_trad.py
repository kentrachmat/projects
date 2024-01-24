
import jademachine
jm = jademachine.JadeMachine()
s = jm.myturtle.getscreen()
jm.exec0("pendown")
jm.exec1("setstep", 20)
while jm.posx < 200 :
	jm.exec0("east")
	if jm.posx < 100 :
		jm.exec0("north")
	else :
		jm.exec0("south")

jm.myturtle.hideturtle()
s.exitonclick() 

