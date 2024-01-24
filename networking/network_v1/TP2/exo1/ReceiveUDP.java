package exo1;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.SocketException;

/**
 * Cette classe fait office de serveur et permet d'être en écoute sur un port UDP donné en argument et d'afficher les messages reçus sous la forme de chaine de caractères.
 * Exemple d'utilisation : java exo1.ReceiveUDP 1500
 * 
 * @author Benedictus Kent RACHMAT, Hichem KARFA 
 * @version 1.0
 */
public class ReceiveUDP {
    
    /**
     * Méthode à appeler au lancement du programme 
     * @param args argument du terminal
     */
    public static void main(String[] args) {createServeurUDP(args);}

    /**
     * Cette fonction crée un serveur UDP qui écoute sur un port UDP donné et d'affiche les messages reçus sous la forme de chaine de caractères.
     * @param args un tableau contenant un argument : args[0] le port UDP en écoute.
     */
    private static void createServeurUDP(String[] args) {
        if (args.length == 1) {
            //Récupération des informations nécessaires pour l'écoute(numéro de port,taille du buffer de reception)
            int maxLength = 1024;
            byte[] receivebuf = new byte[maxLength];
            int portServeur = getNumPort(args[0]);

            //Création du paquet et de la socket UDP
            DatagramPacket packet    = new DatagramPacket(receivebuf, receivebuf.length);
            DatagramSocket socketUdp = createDatagramSocket(portServeur);

            //Ecoute sur un port donnée et affichage des messages 
            serveurListening(socketUdp,packet,portServeur);

            //Fermeture de la socket
            socketUdp.close();
        }
        else {
            System.out.println("Nombre d'arguments incorrects");
            System.exit(1); 
            }
    }

    /**
     * Vérifie qu'un numéro de port est correct et retourne ce port sous forme d'un entier.
     * 
     * @param numPort Numéro de port sous forme d'une chaine de caractère.
     * @return le numéro de port sous forme d'entier. 
     */
    private static int getNumPort(String numPort) {
        int port = 0;
        try {
            port = Integer.parseInt(numPort);
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
     * Construit une socket de datagramme et la lie au port spécifié en paramètre. 
     * La socket sera liée à l’adresse wildcard, une adresse IP choisie par le noyau.
     * @param portServeur le port choisie pour créer la socket.
     * @return l'instance DatagramSocket crée.
     **/
    private static DatagramSocket createDatagramSocket(int portServeur) {
        DatagramSocket socket = null;
        try {
            socket = new DatagramSocket(portServeur);
        }
        catch (SocketException e) {
            System.out.println("Une erreur est survenue dans lors de la création de la socket " +e.getMessage());
            System.exit(1);
        }
        catch(SecurityException e)
        {
            System.out.println("Une erreur du gestionnaire de securité est apparu lors de la création d'une socket" +e.getMessage());
            System.exit(1);
        }
        return socket;
    }

    /**
     * Mets la socket en écoute sur un port UDP et d'affiche les messages reçus sous la forme de chaine de caractères.
     * Affiche aussi l'adresse Ip de l'émetteur ainsi que son numéro port.
     * @param socketUdp la socket qui écoutera sur un port.
     * @param receivepacket le buffer qui contiendra les paquets reçus.
     * @param port le numéro de port en écoute.
     */
    private static void serveurListening(DatagramSocket socketUdp,DatagramPacket receivepacket,int port){
        System.out.println("*** Serveur en écoute sur le port "+port+" ***");
        while (true) {
            try {
                socketUdp.receive(receivepacket); //On remplit le paquet avec les données reçues
            }
            catch (Exception e) {
                System.out.println("Une erreur est survenue lors de la réception d'un message : " + e.getMessage());
                socketUdp.close();
                System.exit(1);
            }
            System.out.println("Un message vient d'être reçu.");
            System.out.println("l'IP de l'expediteur : "+receivepacket.getAddress());
            System.out.println("port de l'expediteur : "+receivepacket.getPort());
            System.out.println("nom d'hôte de l'expediteur : "+receivepacket.getAddress().getHostName());
            System.out.println("Message : "+new String(receivepacket.getData(), 0, receivepacket.getLength())+"\n");  
        }
    }
}
