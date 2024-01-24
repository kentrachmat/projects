# -*- coding: utf-8 -*-

from listiterator import List, NoSuchElementException
import time
import os

def print_with_iterator (l):
    """
    Print elements of a list using an iterator.
    
    :param l: The list to be printed
    :type l: dict
    """
    it = List.ListIterator(l)
    while it.hasNext() :
        print(it.next() , end=' ')
    print('\n')
    

def print_with_iterator_reverse (l):
    """
    Print elements of a list using an iterator in reverse order.
    
    :param l: The list to be printed
    :type l: dict
    """
    it = List.ListIterator(l)
    
    while it.hasNext() :
        it.next()
    while it.hasPrevious() :
        print(it.previous(), end=' ')
    print('\n')

def print_with_iterator_reverse_bis (l):
    """
    Print elements of a list using an iterator in reverse order.
    
    :param l: The list to be printed
    :type l: dict
    """
    iterator = l.get_listiterator(True)
    while iterator.hasPrevious():
        print(iterator.previous(), end=" ")
    print('\n')

def ordered_insert (l, value):
    """
    Add *value* to list *l* such that *l* is kept ordered.
    
    :param l: An ordered list.
    :type l: List
    :param value: The value to be inserted.
    :type value: same as elements of *l*
    """
    i = l.get_listiterator()
    if l.is_empty() or ((i.before == None) and (value < l.head.value)):
        i.add(value)
        return
    while i.hasNext():
        i.next()
        if ((i.after != None) and (value >= i.before.value) and (value < i.after.value)):
            i.add(value)
            return
        else:
            i.add(value)
            return

def get (l, i):
    """
    Get the i-th element of *l*. Raise Exception if *i* is not valid.
    With i=0, we get the head of the list

    :param l: A list.
    :type l: List
    :return: the i-th element    
    :rtype: Type of the elements of the list

    Throws NoSuchElementException if *i* is out of bounds.
    """
    it = l.get_listiterator()
    for a in range(i):
        it.next()
    return it.after

if __name__ == "__main__":
    l = List()
    for i in reversed(range(1,5)):
        l.cons(i)
        
    l.print()

    # test 0 : impression renversee
    print ('--- test 0 ---')
    l.print(reverse=True)
    print('\n')

    # test 1 : impression avec iterateurs
    print ('--- test 1 ---')
    print_with_iterator(l)
    print_with_iterator_reverse(l)

    # test 2 : verification des exceptions
    print ('--- test 2 ---')
    try:
        it = l.get_listiterator()
        while True:
            it.next()
    except NoSuchElementException:
        print("Exception levée avec next")
    try:
        it = l.get_listiterator()
        while True:
            it.previous()
    except NoSuchElementException:
        print("Exception levée avec previous")
    print('\n')    
    
    # test 3 : insertion avant le 3eme element
    print ('--- test 3 ---')
    it = l.get_listiterator()
    print(it.next())
    print(it.next())
    it.add(23)
    assert(it.previous() == 23)
    print_with_iterator(l)
    print_with_iterator_reverse(l)

    # test 4 : insertion apres le dernier element
    print ('--- test 4 ---')
    it = l.get_listiterator ()
    while (it.hasNext()):
         it.next()
    it.add(45)
    assert(it.previous() == 45)
    print_with_iterator(l)
    print_with_iterator_reverse(l)
    
    # test 5 : insertion avant le premier element
    print ('--- test 5 ---')
    it = l.get_listiterator ()
    it.add(0)
    assert(it.previous() == 0)
    print_with_iterator(l)
    print_with_iterator_reverse(l)
    
    # test 6 : insertion avant le dernier element avec l'iterateur placé en fin
    print ('--- test 6 ---')
    it = l.get_listiterator (True)
    it.previous()
    it.add(445)
    assert(it.previous() == 445)
    print_with_iterator(l)
    print_with_iterator_reverse(l)
    
    # test 7 : affichage à l'envers avec l'itérateur placé en fin
    print ('--- test 7 ---')
    print_with_iterator_reverse_bis(l)
    
    # test 8 : ajout après le dernier élément
    print ('--- test 8 ---')
    it = l.get_listiterator (True)
    it.add(5)
    assert(it.previous() == 5)
    print_with_iterator(l)
    print_with_iterator_reverse(l)
       
    # test 9 : inserer trié, à vous d'écrire ce test
    print ('--- test 9 ---')
    lis = List()
    for i in reversed(range(6,10)):
        lis.cons(i)
    for j in reversed(range(5)):
        lis.cons(j) 
    ordered_insert(lis,5)
    print_with_iterator(lis)
    print_with_iterator_reverse(lis)
   
    # test 10 : suppression en tete
    print ('--- test 10 ---')
    it = lis.get_listiterator()
    it.remove()
    print_with_iterator(lis)
    print_with_iterator_reverse(lis)

    # test 11 : suppression en queue
    print ('--- test 11 ---')
    while it.hasNext():
        it.next()
    it.remove()
    print_with_iterator(lis)
    print_with_iterator_reverse(lis)

    # test 12 : (non-)efficacite de get
    print ('--- test 12 ---')
    if not os.path.exists("tp3.dat"):
        with open("tp3.dat","w") as data:
            for i in range (1,11):
                length = 100*i
                liste = List()
                for j in range(1,length):
                    liste.cons(j)
            
                time1 = time.process_time()
                for k in range(1,length):
                    get(liste, k)

                time2 = time.process_time()
                diff1 = time2 - time1
            
                time1 = time.process_time()
                it = liste.get_listiterator()
                while(it.hasNext()) :
                    it.next()

                time2 = time.process_time()
                diff2 = time2 - time1
                data.write("{:2f} {:2f} {:2f}\n".format(length,diff1, diff2))
    else:
        print("le fichier tp3.dat a été créé")