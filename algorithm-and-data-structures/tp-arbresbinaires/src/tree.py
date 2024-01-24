# -*- coding: utf-8 -*-

""":mod:`tree` module : 

:author: `FIL - IEEA - Univ. Lille1.fr <http://portail.fil.univ-lille1.fr>`_

:date: 2016, march

"""

def is_empty (tree):
    """
    Predicates that test *tree* is an empty tree.

    :param tree: The tree
    :type tree: dict or None
    :return: True if *tree* is empty, False otherwise
    :rtype: bool
    """
    return tree is None
    
def empty_tree ():
    """
    :return: an empty Tree
    """
    return None

def create (value, left, right):
    """
    :param value: The label of the node
    :type value: any
    :param left: The left son
    :type left: dict or None
    :param right: The left son
    :type right: dict or None    
    :return: a tree
    :rtype: dict
    """
    return { "value" : value, "left" : left, "right" : right }

def change_left (tree, new):
    """
    :param tree: The tree for which the left son will be changed
    :type tree: dict 
    :param new: The new left son 
    :type new: dict or None
    :return: nothing
    """
    assert(tree is not None)
    tree["left"] = new

def change_right (tree, new):
    """
    :param tree: The tree for which the right son will be changed
    :type tree: dict 
    :param new: The new right son
    :type new: dict or None
    :return: nothing
    """
    assert(tree is not None)
    tree["right"] = new

def get_root_value (tree):
    """
    :param tree: The tree 
    :type tree: dict 
    :return: The value of the root
    :rtype: any
    """
    assert(tree is not None)
    return tree["value"]

def get_left_son (tree):
    """
    :param tree: The tree 
    :type tree: dict 
    :return: The left son of the root
    :rtype: dict or None
    """
    assert(tree is not None)
    return tree["left"]

def get_right_son (tree):
    """
    :param tree: The tree 
    :type tree: dict 
    :return: The right son of the root
    :rtype: dict or None
    """
    assert(tree is not None)
    return tree["right"]

def is_a_leaf (tree):
    """
    :param tree: The tree 
    :type tree: dict 
    :return: True if the node is a leaf, False otherwise
    :rtype: bool
    """
    return is_empty(get_right_son(tree)) and is_empty(get_left_son(tree))

      

    
    
