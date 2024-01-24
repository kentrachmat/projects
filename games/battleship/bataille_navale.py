#!/usr/bin/python3
# -*- coding: utf-8 -*-

# Auteur : Kent

from random import randint
from datetime import datetime 

DISPOSITIONS="HV"

RATE = 0
TOUCHE = 1
COULE = 2
FICHIER_RESULT = 'bataille_navale_scores.txt'

def jouer (nom,descr):
    """
    str, str -> ()
    procédure de jeu complet de bataille navale,
    le nom du joueur est donné par le paramètre nom, 
    et le jeu est décrit dans le fichier descr.

    CU : le fichier jeu doit exister et être conforme
    à un fichier de description.
    """
    jeu = cree_jeu(descr)
    decrire_le_jeu(jeu)
    nbre_tirs = 0
    while not tous_coules(jeu):
        tir = lire_un_tir (nom)
        nbre_tirs += 1
        nav,res = analyse_un_tir (jeu,tir)
        if res == RATE:
            print ("raté.")
        elif res == TOUCHE:
            print (nav + " touché.")
        else:
            print (nav + " coulé.")
    sauver_result(nom,descr,nbre_tirs)
    print ("Terminé en {0} tirs".format(nbre_tirs))

def lire_donnees(num_descr):
    """
    str -> tuple
    renvoie un triplet dont les deux premières composantes sont 
    et la troisième une liste de couples (nature, taille) où
    nature est une chaîne de caractères décrivant la nature du navire
    et taille un entier désignant le nombre de cases occupées par ce navire.
    Toutes ces données sont lues dans un fichier nommé 'jeu'+num_descr+'.txt'.

    CU : le fichier 'jeu'+num_descr+'.txt' doit exister et être au bon format, 
    ie un  fichier texte contenant :
    larg : haut
    nature1 : taille1
    nature2 : taille2
    ...
    """
    f = open('jeu'+num_descr+'.txt','r')
    l = f.readlines()
    l1,l2,l3 =[],[],[]
    for c in l:
        l1+= ["".join([j for j in c if j!="\n"])]
    for a in l1:
        l2+= [a.split(':')]
    t = tuple([int(i) for i in l2[0]])
    for k in range(1,len(l2)):
        l3 += ([ l2[k][0] , int(l2[k][1])],)
    t += (tuple(l3),)
    return t      
          
def sauver_result(nom, jeu, nbre):
    f = open(FICHIER_RESULT,'a')
    f.write(str(nom)+':'+str(jeu)+':'+str(nbre)+':'+str(datetime.today())+'/n')
    f.close()

def cree_jeu (descr):
    """
    str -> dict
    renvoie un nouveau jeu de bataille navale construit à partir des données 
    lues dans le fichier descr.


    Le jeu est représenté par un dictionnaire à quatre clés :
    - 'plateau' pour représenter le plateau de jeu (l'espace maritime) avec ses navires
    - 'nb_cases_occupees' dont la valeur associée est le nombre de cases du plateau
                          occupées par les navires
    - 'touches' dictionnaire contenant deux champs :
                * l'un nomme 'nb_touches' contenant un entier 
                * l'autre nomme 'etats_navires' qui contient un dictionnaire
                  donnant pour chaque navire le nombre de tirs 
                  qu'ils peuvent encore recevoir avant d'être coulés
    - 'coups_joues' ensemble des coups joués depuis le début de la partie.

    CU : le fichier doit contenir une description correcte du jeu (cf lire_donnees)
    """    
    l, h, l_nav = lire_donnees(descr)
    plateau = cree_plateau(l, h, l_nav)
    nb_cases_occupees = 0
    etats_navires = {}
    for i in l_nav:
        nb_cases_occupees += i[1]
        etats_navires[i[0]] = i[1]
    touches = {'nb_touches':0,'etats_navires':etats_navires}
    return {'touches':touches,'coups_joues':set(),'nb_cases_occupees':nb_cases_occupees,'plateau':plateau}

def cree_plateau (l, h, l_nav):
    """
    int, int, list -> dict
    renvoie un plateau de largeur l et de hauteur h occupé par les navires 
    de l_nav.
    La disposition est aléatoire.

    CU : les dimensions doivent permettre le placement de tous les navires
    """
    a = {'larg':l,'haut':h}
    for c in l_nav:
        places = placer(a,c)
        for s in places:
            a[s] = c[0]
    return a

def est_placable(esp, nav, pos, disp):
    """
    dict, tuple, tuple, str -> bool
    
    CU : disp = 'H' ou 'V'
    """
    h = esp['haut']
    l = esp['larg']
    nom,taille = nav
    pos_l,pos_h = pos    
    if pos in esp:          
        return False        
    if pos_h>h or pos_l>l:       
        return False
    if pos_h<=0 or pos_l<=0:      
        return False
    if disp == 'V':
        return 1 <= (pos_l+taille ) <= l or 1 <= (pos_l-taille) <= l  
    if disp == 'H':
        return 1 <= (pos_h+taille ) <= h or 1 <= (pos_h-taille) <= h 
    return False
    
def placer(esp, nav):
    """
    dict, tuple -> NoneType
    place le navire nav dans l'espace maritime esp.
    Choix de l'emplacement aléatoire.

    CU : il doit rester de la place
    """
    h = esp['haut']
    l = esp['larg']
    nom,taille = nav
    disp = DISPOSITIONS[randint(0,1)]
    pos = (0,0)
    while est_placable(esp, nav, pos, disp)==False:
        pos = (randint(1,l),randint(1,h))
    pos_l,pos_h = pos
    if disp == 'H':
        if 1<=(pos_h+taille)<= h:
            plc = [(pos_l,pos_h+i) for i in range(taille)]
        else:
            plc = [(pos_l,pos_h-i) for i in range(taille)]
    elif disp == 'V':
        if 1<=(pos_l+taille )<=l:
            plc = [(pos_l+i,pos_h) for i in range(taille)]
        else:
            plc = [(pos_l-i,pos_h) for i in range(taille)]
    return tuple(plc)
    
def decrire_le_jeu (jeu):
    """
    dict -> ()
    imprime une description du jeu.
    CU : aucune
    """
    print("Dimensions du plateau de jeu :\n")
    print(' - largeur :',jeu['plateau']['larg'])
    print(' - hauteur :',jeu['plateau']['haut'])
    print('Navires :')
    for i in jeu['touches']['etats_navires']:
        print ('-'+i+' :',jeu['touches']['etats_navires'][i],'case(s)')
    print ("À vous de jouer en répondant  par deux nombres séparés par une virgule.")

def lire_un_tir (nom):
    """
    str -> tuple
    renvoie un couple d'entiers lus sur l'entrée standard

    CU : l'entrée doit être de la forme xxx,yyy avec xxx et yyy
         une représentation décimale de deux nombres entiers
    """
    p=nom.split(',')
    for c in p:
        if not 0<=len(c)< 3:
            return print('Please enter the right format')
    return (int(p[0]),int(p[1]))
  
def analyse_un_tir (jeu,tir):
    """
    dict, tuple -> str,int
    renvoie 
      - ("",RATE) si tir raté
      - (nav,TOUCHE) si nav touché
      - (nav,COULE) si nav coulé
    et modifie l'état du jeu en conséquence.
    CU : aucune 
    """
    a=tir
    ships = ''
    jeu['coups_joues'] = jeu['coups_joues'] | set([a])
    if a in jeu['plateau']:
        for c in jeu['plateau']:
            if c == a:
                ships = jeu['plateau'][c]
                jeu['touches']['nb_touches'] += 1
                if jeu['touches']['etats_navires'][ships] > 0:
                    jeu['touches']['etats_navires'][ships] -= 1
                    if jeu['touches']['etats_navires'][ships] == 0:
                        print(jeu)
                        return ships,COULE
                    else:
                        print(jeu)
                        return ships,TOUCHE
                jeu['nb_cases_occupees'] -= 1
    else:
        print(jeu)
        return "",RATE

def tous_coules (jeu):
    """
    dict -> bool
    renvoie True si tous les navires du plateau de jeu ont été coulés
            et False dans le cas contraire.
    CU : aucune
    """
    return all([jeu['touches']['etats_navires'][i] == 0 for i in jeu['touches']['etats_navires']])
     
if __name__ == '__main__':
     import sys
     if len (sys.argv) != 3:
         jouer ('Jean Bart','1')
     else:
         jouer (sys.argv[1],sys.argv[2])
