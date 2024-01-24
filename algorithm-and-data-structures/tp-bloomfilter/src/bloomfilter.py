# -*- coding: utf-8 -*-

""":mod:`bloomfilter` module : Implements a bloomfilter.

:author: `FIL - Univ. Lille <http://portail.fil.univ-lille1.fr>`_

:date: 2021

"""
import hash_functions

class BloomFilter:
    
    def __init__ (self, n, hashes):
        """
        Creates a new empty Bloom filter of size :math:`2^n`
        
        :param n: the log of the size of the filter (the filter will be of size :math:`2^n`)
        :type n: int
        :param hashes: the hash functions
        :type hashes: hashctions
        """
        self.length = n
        self.size   = [False for x in range(2**n)]
        self.hash   = hashes
        
    def add (self, e):
        """
        Adds *e* to the Bloom filter.

        :param e: The element to be added
        :type e: Any
        :rtype: None
        """
        con = 2**self.length
        for x in range(self.hash.nb_functions()):
            self.size[self.hash.hash(x,e) % con] = True

    def contains (self, e):
        """
        Returns True if *e* is stored in the Bloom filter

        :param e: The element to be tested
        :type e: Any
        :rtype: bool
        """
        """
        con = 2**self.length
        find= False

        for x in range(self.hash.nb_functions()):
            if self.size[self.hash.hash(x,e) % con]:
                find = True
        return find 
        """
        return all([True if self.size[self.hash.hash(x,e) % 2**self.length] else False for x in range(self.hash.nb_functions())])

    
    def __contains__(self, e):
        return self.contains(e)
