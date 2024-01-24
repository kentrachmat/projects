package exo3;

import java.net.*;
import java.io.*;

/**
 * Reader est une classe qui a hérité le classe Thread, qui nous permet de faire plusieurs choses à la fois.
 * 
 * @author Benedictus Kent RACHMAT, Hichem KARFA 
 * @version 1.0
 */
public class Reader extends Thread {
    /** l'état de la boucle */
    public boolean active;
    /** le datagram socket */
    public DatagramSocket datagramSocket;
    
    /**
     * Initialisation de la socket
     * 
     * @param socket le socket
     */
    public Reader(DatagramSocket socket) {
        datagramSocket = socket; 
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
                datagramSocket.receive(incoming);
                msg = new String(incoming.getData(), 0, incoming.getLength());
                System.out.println(incoming.getAddress().getHostName()+" ("+incoming.getPort()+") : "+msg);
            } catch(IOException e) {
                System.out.println(e);
                System.exit(1);
            }
        }
    }
}