""":mod:`` module : HashFunctions implements multiple hashing for characters.

:author: `FIL - Univ. Lille <http://portail.fil.univ-lille1.fr>`_

:date: 2021

"""
import random

class HashFunctions:
    def __init__(self, nb):
        '''
        Build hash functions for 128 characters

        :param nb: Number of hash functions to be used
        :type nb: int
        '''
        self.random_tab = [ [random.randint(1,32000) for j in range(128)] for i in range(nb) ]

    def nb_functions(self):
        '''
        Return the number of functions implemented by the object

        :return: Number of functions
        :rtype: int

        >>> h = HashFunctions(10)
        >>> h.nb_functions()
        10
        '''
        return len(self.random_tab)

    def hash(self, n_hash, s):
        '''
        Return the hash associated to a string and a given hash function

        :param n_hash: the number of the hash function (starting at 0)
        :type n_hash: int
        :param s: The string to compute the hash on
        :type s: str
        :return: The hash value
        :rtype: int
        :UC: 0 <= n_hash < self.nb_functions()
        '''
        return sum([self.random_tab[n_hash][ord(i)] for i in s])

    
    def __getitem__(self, tup):
        return self.hash(tup[1], tup[0])
