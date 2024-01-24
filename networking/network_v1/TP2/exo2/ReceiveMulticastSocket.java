package exo2;
import java.io.IOException;
import java.net.*;


/**
 * Cette classe permet d'ouvrir une connexion UDP Multicast sur l'adresse 224.0.0.1 port 7654
 * tous les paquets reçus sont affichés à l'écran sous forme de chaînes de caractères
 * Exemple d'utilisation : java exo2.ReceiveMulticastSocket
 * 
 * @author Benedictus Kent RACHMAT, Hichem KARFA 
 * @version 1.0
 */
public class ReceiveMulticastSocket {

    /**
     * Méthode à appeler au lancement du programme 
     * @param args argument du terminal
     */
    public static void main(String[] args){
        createServeurUDP();
    }

    /**
     * Créer un serveur UDP avec le port 7654 et l'address IP 224.0.0.1
     */
    private static void createServeurUDP() {
        //Récupération des informations nécessaires pour l'écoute(adresse Multicast,numéro de port,taille du buffer de reception)
        int maxLength = 1024;
        InetAddress group           = getAddress("224.0.0.1");
        byte[] receivebuf           = new byte[maxLength];
        int portServeur             = 7654;

        //Création du paquet et de la socket UDP
        DatagramPacket packet       = new DatagramPacket(receivebuf, receivebuf.length);
        MulticastSocket socketUdp   = createSocket(portServeur);
        try{
            socketUdp.joinGroup(group);
        }
        catch (Exception e){
            System.out.println("Error : "+e.getMessage());
            System.exit(1);
        }

        //Reception et affichages des messages 
        serveurListening(socketUdp,packet);

        //Fermeture de la socket
        socketUdp.close();
    }

     /** 
     * Construit une MulticastSocket et la lie au port spécifié en paramètre. 
     * @param port le port choisie pour créer la socket.
     * @return l'instance DatagramSocket crée.
     **/
    private static MulticastSocket createSocket(int port) {
        MulticastSocket socket = null;
        try {
            socket = new MulticastSocket(port);
        }
        catch (IOException e) {
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
     * Récupère une InetAddress à partir d'un nom d'hôte donnée en premier argument.
     * Si l'hôte n'est pas connu la méthode affiche un message d'erreur.
     * @param nomHote le nom de l'hôte (nom de la machine).
     * @return InetAddress associé au nom de l'hôte.
     */
    public static InetAddress getAddress(String nomHote){
        InetAddress address = null;
        try {
            address = InetAddress.getByName(nomHote);
        } catch (UnknownHostException e) {
            System.out.println("Aucune adresse IP pour l'hôte "+nomHote+" n'a pu être trouvée.");
            System.exit(1);
        }
         catch(SecurityException e)
        {
            System.out.println("Une erreur liée au gestionnaire de sécurité est apparu lors de la récupération de l'adresse IP" +e.getMessage());
            System.exit(1);
        }
        return address;
    }

    /**
     * Mets la socket en écoute sur un port UDP et d'affiche les messages reçus sous la forme de chaine de caractères.
     * Affiche aussi l'adresse Ip de l'émetteur ainsi que son numéro port.
     * @param socketUdp la socket qui écoutera sur un port.
     * @param receivepacket le buffer qui contiendra les paquets reçus.
     */
    private static void serveurListening(MulticastSocket socketUdp,DatagramPacket receivepacket){
        System.out.println("*** Serveur en écoute *** ");
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
