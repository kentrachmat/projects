# -*- coding: utf-8 -*-

import random
from hash_functions import HashFunctions
from bloomfilter import BloomFilter
import os

def random_word():
    """
    Returns a word with random letters whose length is between 4 and 7.

    :rtype: string
    """
    letters = [ chr(i) for i in range(ord('a'),ord('z')+1) ] + [ chr(i) for i in range(ord('A'),ord('Z')+1) ]
    length = 4 + random.randint(0,4)
    str = ""
    for i in range(length):
        str = str + random.choice(letters)
    return str

def write_txt():
    if not os.path.exists("res.txt"):
        with open('res.txt',"w") as file:
            ens = set()
            for i in range(2**10):
                ens.add(random_word())

            for nb_fct in range(1,9):
                for log in range(10,21):
                    nb_test = 0
                    nb_fp = 0
                    hash_function = HashFunctions(nb_fct)
                    bloom_filter = BloomFilter(log,hash_function)

                    for l in ens:
                        bloom_filter.add(l)

                    for k in range(1,(2**14)+1):
                        rw = random_word()
                        if not rw in ens:
                            nb_test += 1
                            if bloom_filter.contains(rw):
                                nb_fp += 1
                    taux_fp = nb_fp/nb_test
                    file.write("%d %d %d %d %f\n"%(log,nb_fct,nb_test,nb_fp,taux_fp))
                    print("Log de la taille du filtre: %d\n- Le nombre de fonctions: %d\n- Le nombre de mots testes: %d\n- Le nombre de faux positifs: %d\n- Le taux de faux positifs: %f\n"%(log,nb_fct,nb_test,nb_fp,taux_fp))
                print("\n")
                file.write("\n \n")
    else:
        print("res.txt a été créé")        
        
if __name__ == "__main__":
    hashes = HashFunctions(8)
    bf = BloomFilter(16, hashes)
    w = random_word()
    liste = {random_word() for x in range(2**10)}
    bf.add("timoleon")
    if bf.contains("timoleon"):
        print("%s est present" % ("timoleon"))
    if bf.contains(w):
        print("%s est present" % (w))
    write_txt()
