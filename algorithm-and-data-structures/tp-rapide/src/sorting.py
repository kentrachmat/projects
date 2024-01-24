# -*- coding: utf-8 -*-

"""
:mod:`sorting` module : sorting functions module for quicksort assignment

:author: ` FIL - FST - Univ. Lille <http://portail.fil.univ-lille1.fr>`_

:date: 2018, january
"""

import copy
import random
import numpy as np

def merge (t1,t2, cmp):
    """
    Given two sorted arrays, creates a fresh sorted array.
    
    :param t1: An array of objects
    :type t1: Array
    :param t2: An array of objects
    :type t1: Array
    :param cmp: A comparison function, returning 0 if a == b, -1 is a < b, 1 if a > b
    :type cmp: function
    :return: A fresh array, sorted.
    :rtype: array
    
    .. note::
    
       time complexity of merge is :math:`O(n_1+n_2)` with
       :math:`n_1` and :math:`n_2` resp. the length of *t1* and *t2*

    >>> import numpy
    >>> def cmp (x,y): 
    ...    if x == y:
    ...       return 0
    ...    elif x < y:
    ...       return -1
    ...    else:
    ...       return 1
    >>> t1 = numpy.array([0,2,5,6])
    >>> t2 = numpy.array([1,3,4])
    >>> merge(t1,t2,cmp)
    array([0, 1, 2, 3, 4, 5, 6])
    """
    n1 = len(t1)
    n2 = len(t2)
    t = np.zeros(n1+n2,dtype=type(t1[0]))
    i = j = k = 0
    while i < n1 and j < n2:
        if cmp(t1[i],t2[j]) < 0:
            t[k] = t1[i]
            i = i + 1
        else:
            t[k] = t2[j]
            j = j + 1
        k = k + 1
    while i < n1:
        t[k] = t1[i]
        i = i + 1
        k = k + 1
    while j < n2:
        t[k] = t2[j]
        j = j + 1
        k = k + 1
    return t


def merge_sort (t,cmp):
    """
    A sorting function implementing the merge sort algorithm
    
    :param t: A array of integers
    :type t: array
    :param cmp: A comparison function, returning 0 if a == b, -1 is a < b, 1 if a > b
    :type cmp: function
    :return: A fresh array, sorted.
    :rtype: array

    .. note::
    
       time complexity of merge is :math:`O(n_1+n_2)` with
       :math:`n_1` and :math:`n_2` resp. the length of *t1* and *t2*

    >>> import generate
    >>> def cmp_element (x,y): 
    ...    return x.cmp(y)
    >>> t = generate.random_array(10)
    >>> merge_sort(t,cmp_element)
    array([0, 1, 2, 3, 4, 5, 6, 7, 8, 9], dtype=object)
    """
    n = len(t)
    if n <= 1:
        # cas de base
        return copy.deepcopy(t)
    else:
        # cas general
        t1 = merge_sort((t[0:((n-1)//2+1)]),cmp)
        t2 = merge_sort((t[((n-1)//2+1):n]),cmp)
        return merge(t1,t2,cmp)
    
def quicksort (t, cmp, index = 0):
    """
    A sorting function implementing the quicksort algorithm on the whole array *t*
    
    :param t: An array of Element
    :type t: NumPy array
    :param cmp: A comparison function, returning 0 if a == b, -1 is a < b, 1 if a > b
    :type cmp: function
    :return: Nothing

    .. note::
       *t* is modified during the sort process
    """
    s = {'left' : 0, 'right' : len(t) -1, 'data': t}
    quicksort_slice(s, cmp, index)


def quicksort_slice (s, cmp, index):
    """
    A sorting function implementing the quicksort algorithm
    
    :param s: A slice of an array, that is a dictionary with 3 fields :
              data, left, right representing resp. an array of objects and left
              and right bounds of the slice.
    :type s: dict
    :param cmp: A comparison function, returning 0 if a == b, -1 is a < b, 1 if a > b
    :type cmp: function
    :return: Nothing
    """
    if s['right'] > s['left'] :
        p1, p2 = partition(s, cmp, index)
        quicksort_slice(p1, cmp, index)
        quicksort_slice(p2, cmp, index)
        

def naive_pivot(s):
    """
    Returns a position for the pivot.
    Systematically returns the first position of the slice as a naive choice.

    >>> s = {'data': None, 'left': 2, 'right': 10}
    >>> naive_pivot(s)
    2
    >>> s = {'data': None, 'left': 3, 'right': 10}
    >>> naive_pivot(s)
    3
    """
    return s['left']

def partition (s, cmp, pivot_pos):
    """
    Creates two slices from *s* by selecting in the first slice all
    elements being less than the pivot and in the second one all other
    elements.

    :param s: A slice represented as a dictionary with 3 fields :

              - data: the array of objects,
              - left: left bound of the slice (a position in the array),
              - right: right bound of the slice.
    :type s: dict
    :param cmp: A comparison function, returning 0 if a == b, -1 is a < b, 1 if a > b
    :type cmp: function
    :param pivot_pos: The position at which we take the pivot in ``s['data']``
    :type pivot_pos: int
    :return: A couple of slices, the first slice contains all elements that are 
             less than the pivot, the second one contains all elements that are 
             greater than the pivot, the pivot does not belong to any slice.
             At the end, in the array the pivot is after the left slice and before 
             the right slice.
    :rtype: tuple

    >>> import generate
    >>> import element
    >>> import numpy
    >>> def cmp (x,y): 
    ...    if x == y:
    ...       return 0
    ...    elif x < y:
    ...       return -1
    ...    else:
    ...       return 1
    >>> t = numpy.array([element.Element(i) for i in [5, 6, 1, 3, 4, 9, 8, 2, 7]])
    >>> p = {'left':0,'right':len(t)-1,'data':t}
    >>> p1,p2 = partition(p,cmp2,0)
    >>> list(p1['data'][p1['left']:p1['right']+1])
    [1, 3, 4, 2]
    >>> list(p2['data'][p2['left']:p2['right']+1])
    [6, 9, 8, 7]
    """
    t = s['data']
    
    if pivot_pos == 1:
        pivot_index = random_pivot(s)
        t[s['left']], t[pivot_index] = t[pivot_index] , t[s['left']]
    
    elif pivot_pos == 2:
        pivot_index = optimal_pivot(s, cmp)
        t[s['left']], t[pivot_index] = t[pivot_index] , t[s['left']]
        
    pivot_index = s['left']
    
    i = s['left'] + 1
    l = s['right'] 
    while i < l + 1 :
        if cmp(t[i], t[pivot_index]) < 0 :
            muter_tab(t, i, pivot_index)
            pivot_index += 1
        i += 1
    return  ({'left':s['left'],'right':pivot_index-1,'data':t},
             {'left':pivot_index+1,'right':s['right'],'data':t})

def cmp2 (x,y):
    if x == y:
        return 0
    elif x < y:
        return -1
    else:
        return 1


def random_pivot(s):
    """
    Returns a random index of the slice of the array.
    :param s: A slice of an array, that is a dictionnary with 3 fields.
              data, left, right, an array of objects and left and right bounds of the slice.
    :type s: dict
    :return: a random index of the slice of the array.
    :rtype: int.
    """
    return random.randint(s['left'], ['right'])
    
    
def muter_tab(tab,index, index_pivot) :
    """
    Mutate the array "tab" by placing the element with index "index"
    before the element with index "index_pivot"
    
    :param tab: An array of Element
    :type tab: NumPy array
    :pram index: the index of the element to be moved
    :type index: int
    :pram index_pivot:
    :type index_pivot: int
    :return: Nothing
    """
    j = index
    
    while index_pivot < j :    
        tab[j], tab[j-1] = tab[j-1], tab[j] 
        j = j - 1 


if __name__ == "__main__":
    import doctest
    doctest.testmod()