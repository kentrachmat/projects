package exo2;
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.InetAddress;
import java.net.MulticastSocket;
import java.net.UnknownHostException;

/** Cette classe permet d'envoyer des paquets UDP en Multicast sur l'adresse 224.0.0.1 port 7654.
 * Exemple d'utilisation : java exo2.SendMulticastSocket "Salut les gens"
 * 
 * @author Benedictus Kent RACHMAT, Hichem KARFA 
 * @version 1.0
 */
public class SendMulticastSocket {

    /**
     * Méthode à appeler au lancement du programme 
     * @param args argument du terminal
     */
    public static void main(String[] args){
        createClientUdp(args);
    }

     /** 
     * Cette fonction permet de créer une socket et d'envoyer un paquet (Datagramme) 
     * UDP en multicast sur l'adresse l'adresse 224.0.0.1 port 7654.
     * @param args un tableau contenant un argument : args[0] le message à envoyer.
    */
    private static void createClientUdp(String[] args){
        if (args.length == 1) {
                //Récupération des informations nécessaires pour l'envoi(adresse Ip,message à envoyer)
                InetAddress group  = getAddress("224.0.0.1");
                int portServeur = 7654;
                int maxLength = 1024;
                if (args[0].length() > maxLength){
                    System.out.println("Le message est trop grand, le nombre maximum de caractère est de "+maxLength);
                    System.exit(1);
                }
                byte[] message = args[0].getBytes();   

                //Création du paquet et de la socket UDP
                DatagramPacket packet = new DatagramPacket(message, message.length, group, portServeur); 
                MulticastSocket socket = createSocket(portServeur);

                //Envoi du message
                sendPacket(socket,packet);

                //Fermeture de la socket
                socket.close();
        }
        else {
            System.out.println("Nombre d'arguments incorrects");
        }
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
     * Construit une MulticastSoclet et la lie au port passé en parametre. 
     * La socket sera liée à l’adresse wildcard, une adresse IP choisie par le noyau.
     * @return l'instance MulticastSocket crée
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
     * Envoie un paquet datagramme depuis une socket passé en paramètre. 
     * @param socketUDP la socket qui envoie le paquet.
     * @param packet le paquet à envoyer.
     **/
    private static void sendPacket(MulticastSocket socketUDP,DatagramPacket packet){
        try {
            socketUDP.send(packet);
            System.out.println("le message a été envoyé à 224.0.0.1 en port 7654 !");
        } 
        catch (IOException e) {
            System.out.println("Une erreur d'E/S est survenue lors de l'envoi du message");
        }
        catch (SecurityException e) {
            System.out.println("Une erreur de sécurité est survenue lors de l'envoi du message");
        }
        finally{
            socketUDP.close();
        }
    }
}

