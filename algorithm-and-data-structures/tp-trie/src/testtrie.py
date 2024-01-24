from trie import *

if __name__ == "__main__":
    t = new_trie()
    add(t,"banane")
    add(t,"citronnier")
    add(t,"citron")
    add(t,"pomme")
    add(t,"poire")
    add(t,"ci")
    
    add(t,"carreau")
    add(t,"car")
    add(t,"carton")
    add(t,"carrelage")
    print_trie(t)

