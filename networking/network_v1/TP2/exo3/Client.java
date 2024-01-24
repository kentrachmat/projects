package exo3;

import java.net.*;
import java.io.*;
import java.util.Scanner;
import exo3.*;

/** Cette classe permet d'envoyer un message à une personne particulière avec un port désigné.
 * Exemple d'utilisation : java exo3.Client a13p13 1500
 * 
 * @author Benedictus Kent RACHMAT, Hichem KARFA 
 * @version 1.0
 */ 
public class Client {

     /**
     * La fonction qu'on va appeller lorsque la commande est exécutée
     * @param args les arguments
     *    -args[0] le nom de la machine de destination  
     *    -args[1] le port de destination
     */
    public static void main(String[] args) {
        if (args.length == 2) {
        Scanner sc = new Scanner(System.in);
        InetAddress personal  = null; 
        InetAddress address   = null;
        DatagramSocket socket = null;
        int port = 0;
        String input;
        String final_str;
  
        try {
            address  = getAddress(args[0]);
            port     = getNumPortByArgs(args[1]);
            socket   = new DatagramSocket(port);
            personal = getPersonalAddress();
        } catch (IOException e) {
            System.out.println(e);
            System.exit(1);
        }
        
        Reader reader = new Reader(socket); 
        reader.start();
        System.out.println("Bonjour "+personal.getHostName()+"/"+System.getProperty("user.name")+" ! "+"connecté au port "+port);

        try {
            while (true) {
                //Récupération de la saisie de l'utilisateur.
                input = sc.nextLine();
                final_str = System.getProperty("user.name")+" -> "+ input;

                //Création du paquet
                DatagramPacket packet = new DatagramPacket (final_str.getBytes(), final_str.length(), address, port);
                
                //Envoie du message
                socket.send(packet);
                
                //Cas d'arrêt
                if(input.equals("leave")){
                    System.exit(1);
                }
            }
        } catch(IOException e) {
            System.out.println(e);
        }

        } else {
            System.out.println("Nombre d'arguments incorrects");
            System.exit(1); 
        }
    }

    /**
     * Récupère une InetAddress à partir d'un nom d'hôte donnée en premier argument.
     * Si l'hôte n'est pas connu la méthode affiche un message d'erreur.
     * @param nomHote le nom de l'hôte (nom de la machine).
     * @return InetAddress associé au nom de l'hôte.
     */
    public static InetAddress getAddress(String nomHote) {
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
     * Récupère une InetAddress personnelle.
     * @return InetAddress personnelle
     */
    public static InetAddress getPersonalAddress() {
        InetAddress address = null;
        try {
            address = InetAddress.getLocalHost();
        } catch (UnknownHostException e) {
            System.out.println("Address personelle n'a pu être trouvée.");
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
    private static int getNumPortByArgs(String numPort) {
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
}
