package exo3;

import java.net.*;
import java.io.*;

/**
 * ReaderMulticast est une classe qui hérite de le classe Thread, elle permet d'extraire les paquets reçus et de les afficher à l'écran sous forme de chaine de caractères
 * 
 * @author Benedictus Kent RACHMAT, Hichem KARFA 
 * @version 1.0
 */
public class ReaderMulticast extends Thread {

    /** MulticastSocket variable */
    public MulticastSocket socket;
    
    /**
     * Initialisation de la socket
     *
     * @param psocket le socket
     * @param address adresse de la multicast
     */
    public ReaderMulticast(MulticastSocket psocket, InetAddress address) {
        socket = psocket;
        try{
            socket.joinGroup(address);
        }
        catch (Exception e) {
            System.out.println("Error : " + e.getMessage());
            System.exit(1);
        }
    }

    /**
     * Cette methode exécute le thread qui aura pour 
     * tâches d'extraire les paquets reçus et de les afficher 
     * à l'écran sous forme de chaine de caractères
     */
    public void run() {
        byte[] buffer = new byte[1024];
        DatagramPacket incoming = new DatagramPacket(buffer, buffer.length);
        String msg;
        while(true) {
            try {
                socket.receive(incoming);
                msg = new String(incoming.getData(), 0, incoming.getLength());
                System.out.println(incoming.getAddress().getHostName()+" ("+incoming.getPort()+") : "+msg);
            } catch(IOException e) {
                System.out.println(e);
                System.exit(1);
            }
        }
    }
}