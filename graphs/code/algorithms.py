import sys
import networkx as nx
import matplotlib.pyplot as plt

def openFileSudoku(datas):
    """
    cette fonction renvoie les données dans une liste de liste, 
    elle lira chaque ligne jusqu'à ce qu'elle atteigne la fin
    donc nous pouvons connaître les arêtes connectées et la valeur.
    """
    data = []
    for line in open(datas, 'r').readlines():
        try:
            divise1 = line.split("\n")
            divise2 = divise1[0].split(" ")
            data.append( [int(i) for i in divise2])
        except: 
            sys.exit("Format donnee incorrecte !!")
    return data

def createFileSudoku(S, datas):
    """
    créer le fichier de sortie où 
    la première est l'abscisse 
    la deuxième est l'ordonnee et
    la troisième est la valeur
    """
    with open(datas, 'w') as f:
        for i in range(1,10):
            for j in range(1,10):
                f.write("{} {} {}\n".format(i,j,S.nodes[(9 * (j - 1)) + (i - 1)]["color"]))
    f.close()

def openFile(datas):
    """
    cette fonction renvoie les données dans une liste de tuple, 
    elle lira chaque ligne jusqu'à ce qu'elle atteigne la fin
    donc nous pouvons connaître les arêtes connectées.
    """
    data = []
    for line in open(datas, 'r').readlines():
        try:
            divise1 = line.split("\n")
            divise2 = divise1[0].split(" ")
            data.append((divise2[0],divise2[1]))
        except: 
            sys.exit("Format donnee incorrecte !!")
    return data

def createFile(S, datas):
    """
    créer le fichier de sortie où la première colonne est les sommet 
    et la deuxième colonne est le code couleur
    """
    with open(datas, 'w') as f:
        for i in S.nodes():
            f.write("{} {}\n".format(i,S.nodes[i]["color"]))
    f.close()

def showGraph(S):
    """
    afficher le graph en utilisant networkx et pyplot
    """
    pos = nx.spring_layout(S)
    color_list = []
    for i in S.nodes():
        color_list.append(get_color(S.nodes[i]['color']))
    nx.draw(S,pos, node_size=300 ,with_labels=True, font_weight='bold',font_size=8, node_color=color_list)
    plt.show() 

def get_color(key):
    """
    obtenir la couleur associée au code
    """
    color_listes = {
    1 : "blue",
    2 : "red",
    3 : "green",
    4 : "orange",
    5 : "yellow",
    6 : "brown",
    7 : "black",
    8 : "pink",
    9 : "purple",
    0 : "grey"
    }
    return color_listes.get(key)

def glouton_naif(S):
    list_sommet,list_sommet_color = [],[]
    associate_color               = 1
    treat_sommet                  = 0

    #mettre tous les sommets dans une liste
    for sommet in S.nodes():
        list_sommet.append(sommet)
    
    #la boucle continuera à fonctionner jusqu'à ce que tous les sommets soient recouverts de couleur
    while S.number_of_nodes() > len(list_sommet_color):
        check_color_neighbour = True

        #une boucle pour tous les voisins de notre premier sommet
        for i in nx.neighbors(S, list_sommet[treat_sommet]):

            #si le voisin a la même couleur que la couleur associée, on met le check_color_neighbour à faux car on ne veut pas que le voisin ait la même couleur
            if S.nodes[i]["color"] == associate_color:
                check_color_neighbour = False

        #associer la couleur du vertex et l'ajouter à notre list_sommet_color
        if check_color_neighbour:
            S.nodes[list_sommet[treat_sommet]]["color"] = associate_color
            list_sommet_color.append(list_sommet[treat_sommet])
            
        treat_sommet += 1

        #si le compteur de vertex atteint la fin, nous le redémarrons au premier sommet et réessayons l'algorithme avec une couleur différente 
        if treat_sommet == len(list_sommet):
            #nous avons supprimé le sommet de notre liste car il est déjà coloré et nous n'en avons plus besoin
            for sommet in list_sommet_color:
                if sommet in list_sommet:
                    list_sommet.remove(sommet)

            treat_sommet = 0
            associate_color += 1

def glouton(S):
    dict_sommet_degree            = {}
    list_sommet,list_sommet_color = [],[]
    associate_color               = 1
    treat_sommet                  = 0

    #remplir le dictionnaire par les sommets et leur degré
    for sommet in S.nodes() :
        dict_sommet_degree[sommet] = S.degree[sommet]
    
    #trier la liste des sommets du plus haut degré au plus bas
    list_sommet = sorted(dict_sommet_degree.keys(), key = dict_sommet_degree.get, reverse = True)

    #la boucle continuera à fonctionner jusqu'à ce que tous les sommets soient recouverts de couleur
    while S.number_of_nodes() > len(list_sommet_color):
        check_color_neighbour = True

        #une boucle pour tous les voisins de notre premier sommet
        for i in nx.neighbors(S, list_sommet[treat_sommet]):

            #si le voisin a la même couleur que la couleur associée, on met le check_color_neighbour à faux car on ne veut pas que le voisin ait la même couleur
            if S.nodes[i]["color"] == associate_color:
                check_color_neighbour = False
                break

        #associer la couleur du vertex et l'ajouter à notre list_sommet_color
        if check_color_neighbour:
            S.nodes[list_sommet[treat_sommet]]["color"] = associate_color
            list_sommet_color.append(list_sommet[treat_sommet])
            
        treat_sommet += 1

        #si le compteur de vertex atteint la fin, nous le redémarrons au premier sommet et réessayons l'algorithme avec une couleur différente 
        if treat_sommet == len(list_sommet):
            #nous avons supprimé le sommet de notre liste car il est déjà coloré et nous n'en avons plus besoin
            for sommet in list_sommet_color:
                if sommet in list_sommet:
                    list_sommet.remove(sommet)

            treat_sommet = 0
            associate_color += 1

def check_sudoku_color(S):
    """
    vérifie dans une graphe si le sommet et leurs voisins ont la même couleur, 
    renvoie False s'ils en ont et True sinon
    """
    for sommet in S.nodes:
        for neighbors in nx.neighbors(S, sommet):
            if S.nodes[neighbors]["color"] == S.nodes[sommet]["color"]:
                return False
    return True

def color_check(S, sommet, color):
    """
    vérifie si le voisin d'un sommet donné ont la même couleur que dans le paramètre, 
    False s'ils en ont et True sinon.
    """
    for neighbors in nx.neighbors(S, sommet):
        if S.nodes[neighbors]["color"] == color:
            return False
    return True

def sudoku_solver(G, sommet):
    """
    cet algorithme est basé sur la méthode de backtracking.
    pour colorer un sommet, on lance un backtracking avec un graphe un peu plus complet 
    et puis on recommence jusqu'a ce que le graphe soit complet. Si on tombe sur un cas ou on ne peut plus colorer le sudoku, 
    et que le sudoku n'est pas fini, on fait un return pour returner sur le graphe précédent.
    """
    #vérifier que le sommet actuel ne dépasse la longueur totale des sommets -1
    #on doit ajouter le +1 sinon on va avoir un erreur (RecursionError: maximum recursion depth exceeded)
    if sommet > 80:
        G = sudoku_solver(G, (sommet % 9) + 1)
        return G

    if (sommet + 1) == G.number_of_nodes():
        if G.nodes[sommet]["color"] == 0:
            for color in range(1, 10):

                #vérifie si le voisin d'un sommet donné ont la même couleur
                if color_check(G, sommet, color):
                    G.nodes[sommet]["color"] = color
                    break
        return G

    if G.nodes[sommet]["color"] == 0:
        for color in range(1, 10):

            #vérifie si le voisin d'un sommet donné ont la même couleur
            if color_check(G, sommet, color):
                G.nodes[sommet]["color"] = color
                G = sudoku_solver(G, sommet + 9)
                
                #vérifie dans une graphe si le sommet et leurs voisins ont la même couleur
                if check_sudoku_color(G):
                    return G
                else:
                    G.nodes[sommet]["color"] = 0
    
    else:
        G = sudoku_solver(G, sommet + 9)
    return G
