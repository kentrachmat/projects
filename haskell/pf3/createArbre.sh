#!/bin/bash
touch arbre.dot
echo "digraph \"arbre\"{ }" > arbre.dot
dot -Txlib arbre.dot
