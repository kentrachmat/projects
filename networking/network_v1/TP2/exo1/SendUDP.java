package exo1;
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.net.SocketException;
import java.nio.channels.IllegalBlockingModeException;

/** Cette classe permet d'envoyer des paquets UDP en unicast à un utilisateur désigné.
 * Exemple d'utilisation : java exo1.SendUDP a13p13 1500 "you are welcome"
 * 
 * @author Benedictus Kent RACHMAT, Hichem KARFA 
 * @version 1.0
 */ 
public class SendUDP {

    /**
     * Méthode à appeler au lancement du programme 
     * @param args argument du terminal
     */
    public static void main(String[] args){
        createClientUdp(args);
    }

    /** 
     * Cette fonction permet de créer une socket et d'envoyer un paquet(Datagramme).
     * @param args un tableau contenant trois arguments : 
     *    -args[0] le nom de la machine de destination  
     *    -args[1] le port de destination
     *    -args[2] le message à envoyer
    */
    public static void createClientUdp(String[] args){
        if (args.length == 3) {   
                //Récupération des informations nécessaires pour l'envoi(adresse Ip, numéro de port, message à envoyer)
                InetAddress address = getAddress(args[0]);
                int portServeur     = getNumPort(args[1]);
                int maxLength = 1024;
                if (args[2].length() > maxLength){
                    System.out.println("Le message est trop grand, le nombre maximum de caractère est de "+maxLength);
                    System.exit(1);
                }
                byte[] message  = args[2].getBytes();  

                //Création du paquet et de la socket UDP
                DatagramPacket packet    = new DatagramPacket(message, message.length, address, portServeur);
                DatagramSocket socketUDP = createDatagramSocket();

                //Envoi du message
                sendPacket(socketUDP,packet);

                //Fermeture de la socket
                socketUDP.close();
        }
        else{
            System.out.println("Nombre d'arguments incorrects");
        }
    }

    /**
     * Récupère une InetAddress à partir d'un nom d'hôte donnée en premier argument.
     * Si l'hôte n'est pas connu la méthode affiche un message d'erreur.
     * 
     * @param nomHote le nom de l'hôte (nom de la machine).
     * @return InetAddress associé au nom de l'hôte.
     */
    public static InetAddress getAddress(String nomHote){
        InetAddress address = null;
        try {
            address = InetAddress.getByName(nomHote);
        } catch (UnknownHostException e) {
            System.out.println("Aucune adresse Ip pour l'hôte "+nomHote+" n'a pu être trouvée.");
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
     * Vérifie qu'un numéro de port est correct et retourne ce port sous forme d'un entier.
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
     * Construit une socket datagramme et la lie à n’importe quel port disponible sur la machine hôte locale. 
     * La socket sera liée à l’adresse wildcard, une adresse IP choisie par le noyau.
     * @return l'instance DatagramSocket crée
     **/
    private static DatagramSocket createDatagramSocket() {
        DatagramSocket socket = null;
        try {
            socket = new DatagramSocket();
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
     * Envoie un paquet datagramme depuis une socket passé en paramètre. 
     * @param socketUDP la socket qui envoie le paquet.
     * @param packet le paquet à envoyer.
     **/
    private static void sendPacket(DatagramSocket socketUDP,DatagramPacket packet){
        try {
            socketUDP.send(packet);
            System.out.println("le message a bien été envoyé !");
        } 
        catch (IOException e) {
            System.out.println("Une erreur d'E/S est survenue lors de l'envoi du message");
        }
        catch (SecurityException e) {
            System.out.println("Une erreur de sécurité est survenue lors de l'envoi du message");
        }
        catch (IllegalBlockingModeException e) {
            System.out.println("Une erreur est survenue lors de l'envoi du message(cette socket est déjà associée a un canal en non-blocking mode)");
        }
        catch (IllegalArgumentException e) {
            System.out.println("Une erreur est survenue lors de l'envoi du message(l'adresse connectée et l'adresse du paquet sont différents");
        }
        finally{
            socketUDP.close();
        }
    }
}

