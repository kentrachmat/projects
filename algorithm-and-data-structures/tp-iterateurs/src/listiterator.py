# -*- coding: utf-8 -*-

""":mod:`listiterator` module : list implementation with iterator interaction

Provides constructor, selectors and modifiers for mutable lists.
Lists of this module must be traversed via iterators. 

An iterator for lists allows the programmer to traverse the list in
either direction and adding elements to the list during iteration.  

An iterator has no current element; its cursor position always lies
between the element that would be returned by a call to :code:`previous` and
the element that would be returned by a call to `next`. 

An iterator for a list of length n has n+1 possible cursor positions,
as illustrated by the carets (^) below:

.. code::

                      Element(0)   Element(1)   Element(2)   ... Element(n-1)
 cursor positions:  ^            ^            ^            ^                  ^

:author: `FIL - FST - Univ. Lille <http://portail.fil.univ-lille1.fr>`_

:date: 2021, february

"""

class EmptyList (Exception):
    """
    Exception for empty lists
    """
    def __init__ (self,msg):
        self.message = msg

class NoSuchElementException (Exception):
    """
    Exception for iterators not positionned
    """
    def __init__ (self,msg):
        self.message = msg

            
class List:
    '''
    Double-linked lists
    '''
    
    class Cell:
        '''
        Double-linked cells
        '''
        
        def __init__(self, value, next_cell, prev_cell):
            '''
            Creates a new cell with the specified values, and the links
            to the next and previous cells (if any).

            :param value: A value
            :type value: Any
            :param next_cell: The successor of this cell, if any or None otherwise
            :type next_cell: Cell
            :param prev_cell: The predecessor of this cell, if any or None otherwise
            :type prev_cell: Cell
            '''
            self.value = value
            self.next = next_cell
            self.prev = prev_cell

        def __print_without_iterator_forward (self):
            """
            Print all the list from the cell until the end
            """
            print(self.value, end=' ')
            if self.next != None:
                self.next.__print_without_iterator_forward ()
            else:
                print()

        def __print_without_iterator_reversed (self):
            """
            Print all the list from the cell back to the beginning of the list
            """
            print(self.value, end=' ')
            if self.prev != None:
                self.prev.__print_without_iterator_reversed ()
            else:
                print()

    def __init__ (self):
        """
        Creates a new empty list.
        """    
        self.head = None
        self.tail = None

    def is_empty (self):
        """
        Returns True if the list is empty, False otherwise.
        
        :rtype: boolean
        """
        return self.head == None and self.tail == None

    def cons (self, value):
        """
        Add the value :code:`value` at the begining of the list
        
        :param value: The value to be added.
        :type value: Any

        UC: not compatible with iterators
        """
        if self.head == None:
            self.head = self.tail = List.Cell(value, None, None)
        else:
            self.head = List.Cell(value, self.head, None)
            self.head.next.prev = self.head



    def print (self,reverse=False):
        """
        :param reverse: True if the the list *l* must be printed from the end to the beginning
        :type reverse: boolean
        """
        if self.is_empty():
            raise EmptyList("The list has no elements")
        if reverse:
            self.tail._Cell__print_without_iterator_reversed()
        else:
            self.head._Cell__print_without_iterator_forward()


    def get_listiterator (self, inv = False):
        """
        Creates a new iterator for the list
        :return: An iterator at the beginning of the list
        :rtype: ListIterator
        """
        if not inv:
            return List.ListIterator(self)
        else:
            return List.ListIterator(self, True)


    class ListIterator:
        '''
        Iterator over double-linked lists
        '''
        
        def __init__(self, list, inv = False):
            '''
            Builds a ListIterator on the provided list.
            The iterator is at the beginning of the list.

            :param list: The list to iterate on
            :type list:List
            '''
            self.l = list
            if not inv:
                self.before = None
                self.after = list.head
            else:
                self.before = list.tail
                self.after = None
            
            # Beware! Your attributes cannot have the same names as the class methods.
            #pass
                
        def hasNext (self):
            """
            Returns :code:`True` if this list iterator has more elements when
            traversing the list in the forward direction. (In other words,
            returns :code:`True` if :code:`self.next()` would return an element rather than
            throwing an exception.)
            
            :rtype: boolean
            """
            return self.after != None


        def next (self):
            """
            Returns the next element in the list and advances the cursor
            position. This method may be called repeatedly to iterate through
            the list, or intermixed with calls to :code:`self.previous()` to go back and
            forth. (Note that alternating calls to next and previous will
            return the same element repeatedly.)

            Throws NoSuchElementException if there is no such element.

            :rtype: Type of the elements of the list
            """
            if not self.hasNext() :
                raise NoSuchElementException("The list doesn't contains next")
            else :
                suivant = self.after
                self.after = suivant.next
                self.before = suivant
                return suivant.value
                

        def hasPrevious (self):
            """
            Returns :code:`True` if this list iterator has more elements when
            traversing the list in the reverse direction. (In other words,
            returns :code:`True` if :code:`self.previous()` would return an
            element rather than throwing an exception.)
            
            :rtype: boolean
            """
            return self.before != None

        def previous (self):
            """
            Returns the previous element in the list and moves the cursor
            position backwards. This method may be called repeatedly to
            iterate through the list backwards, or intermixed with calls to
            :code:`self.next()` to go back and forth. (Note that alternating 
            calls to next and previous will return the same element repeatedly.)
            
            Throws NoSuchElementException if there is no such element.
            
            :rtype: Type of the elements of the list
            """
            if not self.hasPrevious() :
                raise NoSuchElementException("The list doesn't contains next")
            else :
                precedent = self.before
                self.after = precedent
                self.before = precedent.prev
                return precedent.value
        
        def add (self,value):
            """
            Inserts the specified element into the list. The element is
            inserted immediately before the element that would be returned by
            :code:`next()`, if any, and after the element that would be returned by
            :code:`previous()`, if any. (If the list contains no elements, the new
            element becomes the sole element on the list.) The new element is
            inserted before the implicit cursor: a subsequent call to :code:`next()`
            would be unaffected, and a subsequent call to :code:`previous()` would
            return the new element.
            
            :param value: The element
            :type value: Any
            """
            if self.l.is_empty():
                self.before = List.Cell(value, None, None)
                self.l.head = self.l.tail = self.before
                
            elif self.after == None:
                self.before.next = List.Cell(value, None, self.before)
                self.before = self.l.tail = self.before.next

            elif self.before == None:
                self.before = List.Cell(value, self.after, None)
                self.after.prev = self.l.head = self.before

            else:
                self.before.next = self.after.prev = List.Cell(value,self.after, self.before)
                self.before = self.before.next

        def remove (self):
            """
            Removes from the list the last element that was returned by
            :code:`next()`. This call can only be made once per call to next.
            """
            if (self.l.is_empty()):
                raise EmptyList("La liste est vide !")

            elif (self.after == None):
                if (self.before.next == None and self.before.prev == None):
                    self.before = self.l.head = self.l.tail = None
                else:
                    self.before.prev.next = None
                    self.left = self.l.tail = self.before.prev

            elif (self.before == None):
                if (self.l.head == self.after and self.l.tail == self.after):
                    self.l.head = self.l.tail = self.after = None
                else:
                    self.before = self.after
                    self.after = self.after.next
                    self.after.prev = self.before = None
                    self.l.head = self.after
            else:
                if (self.before == self.l.head):
                    self.after.prev = self.before = self.before.prev
                    self.l.head = self.after
                else:
                    self.after.prev = self.before = self.before.prev
                    self.before.next = self.after
           