# -*- coding: utf-8 -*-

""":mod:`displayer` module : 

:author: `FIL - IEEA - Univ. Lille1.fr <http://portail.fil.univ-lille1.fr>`_

:date: 2016, march

"""

from tree import *

global numero
  
def traverse (tree,fileId):
  global numero
  numero = numero + 1  
  if not is_empty(tree):
    numero_courant = numero
    print("N%d [label=\"%d\"]\n" % (numero,get_root_value(tree)), file=fileId);    
    fg = traverse(get_left_son(tree),fileId)
    fd = traverse(get_right_son(tree),fileId)
    if not (is_empty(get_left_son(tree))):
      print("N%d -> N%d\n" % (numero_courant,fg),file=fileId)
    else:
      print("N%d -> N%d [style=invis]\n" % (numero_courant,fg),file=fileId)

    if not (is_empty(get_right_son(tree))):
      print("N%d -> N%d\n" % (numero_courant,fd),file=fileId)
    else:
      print("N%d -> N%d [style=invis]\n" % (numero_courant,fd),file=fileId)
    return numero_courant             
  else:
    print("N%d [style=invis]\n" % (numero),file=fileId);
    return numero
              
def tree2file (tree, filename):
  global numero
  numero = 0
  f = open(filename,'w')
  print("digraph G {\n",file=f)
  traverse(tree,f)
  print("}\n",file=f)
  f.close()
