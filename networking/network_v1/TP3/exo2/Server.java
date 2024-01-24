package exo2;

import java.net.*;
import java.io.*;
import java.nio.channels.IllegalBlockingModeException;
import java.text.SimpleDateFormat;
import java.util.*;

/** 
 * Server est une classe qui implémente un serveur TCP qui accepte simultanément plusieurs connexions.
 * Chaque client qui se connecte peut envoyer des messages, qui seront alors reçus de tous les clients actuellement connectés.
 * Exemple d'utilisation : java exo2.Server 2021 ou java exo2.Server
 * 
 * @author Benedictus Kent RACHMAT, Hichem KARFA 
 * @version 1.0
*/
public class Server {
 
    /**
     * La fonction principale qui est appelée lorsque la commande est exécutée.
     * Elle prend au maximum un argument si ce nombre est supérieur à 1 le programme s'arrête et affiche un message d'erreur.
     * Si aucun argument n'est donné, le port attribué au serveur sera l'année en cours.
     * @param argv un tableau contenant les arguments
     *     -argv[0] = le numéro du port qui sera attribué au serveur.
     */
    public static void main(String[] argv) {

        //Gestions des arguments
        if (argv.length > 1) {
            System.out.println("Nombre d'arguments incorrects");
            System.exit(1);
        }

        int port = getPort(argv);
        int ID   = 1 ;

        //Création de la socket serveur.
        ServerSocket server = createServerSocket(port);
        affichageDebutServeur(port);

        List<ServerThread> list_connected = Collections.synchronizedList(new ArrayList<>());

        //Traitements des requêtes
        while (true) {
            //Création d'une socket par client
            Socket socketForClient  = createSocketClient(server);
            ID++;
            //Envoie du message au client
            sendMessage(socketForClient);
            //Gestion des threads
            ServerThread thread = new ServerThread(socketForClient, list_connected, Integer.toString(ID));
            list_connected.add(thread);
            thread.start();

            //Affichage sur la sortie standard et sauvegarde des logs dans un fichier texte
            String logs = createLogs(socketForClient,ID);
            saveLogsOnFile(logs);

            
        }
    }

    /**
     * Récupère un tableau contenant un argument et renvoie un numéro de port sous forme d'entier.
     * Si aucun n'argument n'est donné, le numéro du port sera l'année en cours.
     * @param argv un tableau contenant un argument argv[0].
     * @return Un numéro de port sous forme d'entier.
     */
    private static int getPort(String [] argv) {
        int port = 0;
        try {
            if(argv.length == 1){
                port = Integer.parseInt(argv[0]);
            }
            else {
                port = Calendar.getInstance().get(Calendar.YEAR);
            }

            if (port < 0) {
                throw new Exception();
            }
        } catch (Exception e) {
            System.out.println("Numéro de port incorrect");
            System.exit(1);
        }
        return port;
    }


    /**
     * Crée un socket serveur, lié au port spécifié.
     * @param port le numéro de port(entre 1 et 65535), ou 0 pour utiliser un numéro de port qui est automatiquement attribué.
     * @return socket , la socket serveur 
     */
    private static ServerSocket createServerSocket(int port) {
        ServerSocket socket = null;
        try{
            socket = new ServerSocket(port);
        }
        catch (IOException e){
            System.out.println("Une erreur d'E/S s'est produite lors de la création de la socket serveur. "+ e.getMessage());
            System.exit(1);
        }
        catch (SecurityException e){
            System.out.println("Erreur lors de la création de la socket du serveur : le  gestionnaire de sécurité n'autorise pas l'opération.");
            System.exit(1);
        } catch (IllegalArgumentException e) {
            System.out.println("Erreur lors de la création de la socket du serveur : Le port est en dehors de la plage spécifiée pour les ports valides(entre 0 et 65535)");
            System.exit(1);
        }
        return socket;
    }

    /**
     * Cette méthode permet de gérer l'affichage lors de la création du serveur.
     * @param port le numéro de port du serveur.
     */
    private static void affichageDebutServeur(int port){
        String adress = "";
        try{
            InetAddress inetAddress =InetAddress.getLocalHost();
            adress=inetAddress.toString();
        }
        catch(Exception e){
            adress="?";
        }
        System.out.println("** Le serveur est en ligne **");
        System.out.println("Détail du serveur : " + adress + " | numéro de port : " + port);
        System.out.println("l'enregistrement des logs de connexion se situe dans le fichier : exo2/connection.txt");
        System.out.println("l'enregistrement des logs de discussion se situe dans le fichier : exo2/chat.txt");
        System.out.println("\nClient(s) connecté :");
    }

    /**
     * Crée une socket et ouvre une nouvelle connexion avec un client.
     * @param server le serveur qui accepte la connexion.
     * @return la socket permettant de communiquer avec le client.
     */
    private static Socket createSocketClient(ServerSocket server){
        Socket socketClient = null;
        try{
            socketClient = server.accept();
        }
        catch(IOException e){
            System.out.println("Une erreur d'E S s'est produite lors de la création d'un socket cliente " +e.getMessage());
            System.exit(1);
        }
        catch(SecurityException e){
            System.out.println("Erreur lors de la création d'une socket cliente : le  gestionnaire de sécurité n'autorise pas l'opération.");
            System.exit(1);
        }catch(IllegalBlockingModeException e){
            System.out.println("Erreur lors de la création d'une socket cliente : le canal est en mode non bloquant, et il n'y a pas de connexion prête à être accepté");
            System.exit(1);
        }
        return socketClient;
    }

    /**
     * Cette méthode permet de générer la chaine de caractère représentant les logs du serveur.
     * @param socketClient la socket permettant de communiquer avec le client.
     * @param cptConnexion le nombre de connexion total sur le serveur.
     * @return la chaine de caractère crée.
     */
    private static String createLogs(Socket socketClient,int cptConnexion){
        String logs="";
        try{
            SimpleDateFormat formatDate = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
            logs = socketClient.getInetAddress().getHostAddress() + " (" +socketClient.getInetAddress().getHostName() + ") s'est connecté à " + formatDate.format(new Date());
            logs+="\nNombre de connexion total : "+(cptConnexion-1);
        }
        catch(Exception e){
            String msg ="Erreur lors de la création des logs :  "+e.getMessage();
            System.out.println(msg);
        }
        return(logs);
    }

    /**
     * Cette méthode permet d'afficher sur la sortie standard et de sauvegarder dans un fichier texte les logs de connexion.
     * @param logs une chaine de caractère contenant les logs du serveur.
     */
    private static void saveLogsOnFile(String logs){
        System.out.println(logs);
        String pathfile = "exo2/connection.txt";
        try{
            PrintStream saveText = new PrintStream(new FileOutputStream(pathfile, true));
            saveText.println(logs);
            saveText.close();
        }catch(Exception e){
            System.out.println("Une erreur est survenue lors de la sauvegarde des logs " + e.getMessage());
        }
    }


    /**
     * Cette méthode permet d'envoyer un message au  moment de la connexion du client.
     * @param socketClient la socket permettant de communiquer avec le client.
     */
    private static void sendMessage(Socket socketClient){
        try{
            OutputStream output = socketClient.getOutputStream();
            PrintStream write = new PrintStream(output, true);
            write.println("Bienvenue sur le serveur "+InetAddress.getLocalHost());
        }
        catch(IOException e){
            String msg ="Erreur lors de l'envoie du message "+ e.getMessage();
            System.out.println(msg);
            saveLogsOnFile(msg);
        }
    }

}