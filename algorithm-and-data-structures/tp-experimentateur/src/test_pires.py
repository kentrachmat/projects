# -*- coding: utf-8 -*-

"""
:mod:`test` module : test module for experiences assignment

:author: `FIL - IEEA - Univ. Lille1.fr <http://portail.fil.univ-lille1.fr>`_

:date: 2018, january
"""

import sys
import experience
import marker
import sorting
from functools import cmp_to_key

global cpt

def compare (m1,m2):
    '''
    Compares two markers

    :param m1: A marker 
    :type m1: Marker
    :param m2: Another marker
    :type m2: Marker
    :return: -1 if *m1 < m2*, 0 if *m1* = *m2* or 1 when *m1* > *m2*
    :rtype: int
    '''
    global cpt
    cpt+=1
    return m1.cmp(m2)

def dicho_search(liste, searched) :
    found = False
    min_pos = 0
    max_pos = len(liste) - 1
    
    while min_pos <= max_pos and not found :
        middle_pos = (min_pos + max_pos) // 2
        element = liste[middle_pos]
        cpt = compare(searched, element)
        if cpt == -1:
            max_pos = middle_pos - 1
        elif cpt == 1 :
            min_pos = middle_pos + 1
        else :
            found = True
    return found 
    
# STRATEGY 1
def negative_markers1(markers,positive):
    """
    Computes the list of negative markers from the list of markers and
    the list of positive markers.

    :param markers: The list of markers 
    :type markers: list of str
    :param positive: The list of positive markers
    :type positive: list of str
    :return: The list of negative markers
    :rtype: list of str
    """
    negative = []

    for element in markers:
        found = False
        index = 0
        while index < len(positive) and not found :
            if compare(positive[index], element) == 0:
                found = True
            index += 1
        if not found:
            negative.append(element)  
    return negative

# STRATEGY 2
def negative_markers2(markers,positive):
    """
    Computes the list of negative markers from the list of markers and the list of positive markers.

    :param markers: The list of markers 
    :type markers: list of str
    :param positive: The list of positive markers
    :type positive: list of str
    :return: The list of negative markers
    :rtype: list of str
    """
    negative = []
    # Trier 'positive' grâce au module sorting, qui vous est fourni (pensez à l'importer)
    positive = sorting.merge_sort(positive,compare)
    
    for element in markers :
        if not dicho_search(positive, element) :
            negative.append(element)
    return negative

# STRATEGY 3
def negative_markers3(markers,positive):
    negative = []

    positive = sorting.merge_sort(positive,compare)
    markers = sorting.merge_sort(markers,compare)
    index = 0
    i = 0

    while index < len(markers) and i < len(positive) :
        found = dicho_search(positive[i:], markers[index])
        if found :
            i += 1
        else :
            negative.append(markers[index])
        index += 1
    while index < len(markers) :
        negative.append(markers[index])
        index += 1 
    return negative
     
if __name__ == "__main__":

    if len(sys.argv) < 2:
        print("Usage: {} <p> <m>\n\n<p>: nombre de marqueurs positifs\n<m>: nombre de marqueurs".format(sys.argv[0]))
        sys.exit(1)
        
    m =int(sys.argv[1])

    assert (m > 0), "The number of markers must be greater than 0"
    
    for n in range(1,1+m):

        exp = experience.Experience(n,m)
        markers = exp.get_markers()
        positive = exp.get_positive_markers()

        cpt = 0
        negative_markers1(markers,positive)
        nb_cpt1 = cpt

        cpt = 0
        negative_markers2(markers,positive)
        nb_cpt2 = cpt

        cpt = 0
        negative_markers3(markers,positive)
        nb_cpt3 = cpt
       
        print("{} {} {} {} {}".format(n,m,nb_cpt1,nb_cpt2,nb_cpt3))