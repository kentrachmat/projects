import algorithms as ft
import networkx as nx
import time as tm
import sys

def main():
    """
    Cette fonction est appelée lorsque le programme est exécuté, 
    elle lit les données et essaie de résoudre avec un algorithme choisi par l'utilisateur.
    et il montrera le graph avec les couleurs distribuées en utilisant networkx
    """

    #lit les données du fichier txt et le transformer en liste de tuples
    data = ft.openFile(sys.argv[2])

    #créer le graph en utilisant les données et également les arêtes d'un sommet à l'autre.
    S    = nx.Graph(data)  
    ans = 0

    #définir la couleur par défaut sur tous les sommet
    for sommet in S.nodes :
        S.nodes[sommet]["color"] = 0

    #menu pour l'utilisateur afin qu'il puisse choisir l'algorithme pour résoudre le problème 
    while ans != "1" and ans != "2":
        print("-- Veuillez choisir l'algorithme pour résoudre ce problème de Antennes GSM --")
        print("1. Glouton")
        print("2. Glouton Naïf")
        ans = input("input : ")

    #enregistrer le temps (début)
    debut   = tm.time()

    #exécuter l'algorithme
    if(ans == "1"):
        ans = "Glouton"
        ft.glouton(S)
    else:
        ans = "Glouton Naïf"
        ft.glouton_naif(S)

    #enregistrer le temps (fin)
    fin     = tm.time()

    #montrer à l'utilisateur les informations concernant l'algorithme, par exemple le temps pris, 
    #le fichier de sortie et le graph
    print("\nL'algorithme est fini vous avez choisi {}".format(ans))
    print("La solution du problème est dans : {}".format(sys.argv[4]))
    print("Les données du problème est dans : {}".format(sys.argv[2]))
    print("Le temps de l'algorithme : {} s".format(round((fin - debut), 10)))
    print("Le graphique sera affiché dans une nouvelle fenêtre")

    #créer le fichier de sortie avec le code couleur associé aux données 
    ft.createFile(S,sys.argv[4])

    tm.sleep(2)

    #créer le graph
    ft.showGraph(S)
 
if __name__ == "__main__":
    
    #vérifier si les arguments sont corrects ou non
    if(len(sys.argv) != 5):
        print("Veuillez utiliser le bon format d'entrée !")
        print("> python3 gsm.py -i [data/nom de les données].txt -o [nom de l'output].txt")
        exit()

    main()