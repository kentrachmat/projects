package exo1;

import java.net.*;
import java.io.*;
  
/** Client est une classe qui prend le port et le nom d'hôte du serveur auquel se connecter 
 *  Exemple d'utilisation : java exo1.Server 2021
 * 
 * @author Benedictus Kent RACHMAT, Hichem KARFA 
 * @version 1.0
 */ 
public class Client {
    
     /**
     * La fonction qu'on va appeller lorsque la commande est exécutée
     * @throws IOException ils se produisent chaque fois qu'une opération d'entrée ou de sortie échoue ou est interprétée
     * @param argv les arguments
     */
    public static void main(String[] argv) throws IOException {
        if (argv.length < 2) {
            System.out.println("Nombre d'arguments incorrects");
            System.exit(1);
        }
 
        InetAddress hostname = getAddress(argv[0]);
        int port             = getPort(argv[1]);
        //etablissement de la liaison
        Socket socket = null; 
        try{
            socket= new Socket(hostname, port);
            //obtention du flux d'acquisition et d'émission
            InputStream input = socket.getInputStream();
            OutputStream output = socket.getOutputStream();

            //lecture 
            BufferedReader reader = new BufferedReader(new InputStreamReader(input));
            String msg = reader.readLine();

            //ecriture 
            PrintStream write = new PrintStream(output, true);
            write.println(System.getProperty("user.name"));

            //affichage du message
            System.out.println(msg);
            
        
        } catch (IOException e) {
            System.out.println("IOException : " + e.getMessage());
        }finally{
            socket.close(); 
        }
   
    }

    /**
     * Récupère une InetAddress à partir d'un nom d'hôte donnée en premier argument.
     * Si l'hôte n'est pas connu la méthode affiche un message d'erreur.
     * 
     * @param name le nom de l'hôte (nom de la machine).
     * @return InetAddress associé au nom de l'hôte.
     */
    public static InetAddress getAddress(String name) {
        InetAddress address = null;
        try {
           address = InetAddress.getByName(name);
        } catch (Exception e) {
            System.out.println("Le serveur n'est pas trouvé ");
            System.exit(1);
        }
        return address;
    }

    /**
     * Récupère un numéro de port sous forme d'une chaine de caractere, 
     * le parse en entier et vérifie qu'il est correct.
     * 
     * @param port Numéro de port sous forme d'une chaine de caractere.
     * @return Un numéro de port sous forme d'entier. 
     */
    private static int getPort(String port) {
        int p = 0;
        try {
            p = Integer.parseInt(port);
            if (p < 0) {
                throw new Exception();
            }
        } catch (Exception e) {
            System.out.println("Numéro de port incorrect");
            System.exit(1);
        }
        return p;
    }
}