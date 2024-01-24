package exo2; 

import java.io.*;
import java.text.SimpleDateFormat;  
import java.net.*;
import java.util.*;
 
/** ServerThread est une classe qui hérite de la classe Thread, un nouveau thread est créé pour chaque nouvelle connexion d'un client
 * Cette classe donne une multitude d'action possible pour le client.
 * il peut par exemple envoyer des messages à tout le monde, ou une seule personne.
 * @author Benedictus Kent RACHMAT, Hichem KARFA 
 * @version 1.0
 */
public class ServerThread extends Thread {
    /** Voici la liste des différents attributs d'un thread pour un client */
    private Socket socket;
    private String id;
    private String privateChat;
    private List<ServerThread> connected = Collections.synchronizedList(new ArrayList<>());
    private Boolean left;
    private Boolean running;
    private PrintStream write;
    private BufferedReader reader;
    private PrintStream saveText;
    private PrintStream saveChat;
    private SimpleDateFormat formatDate;


    /**
     * ServerThread est le constructeur de la classe
     * il permet donc d'initialiser les différents attributs d'un objet ServeurThread
     * @param socket la socket du client
     * @param connected la liste des utilisateurs connectés
     * @param ID l'ID du client
     */
    public ServerThread(Socket socket, List<ServerThread> connected, String ID) {
        try{
            this.id             = ID;
            this.socket         = socket;
            this.connected      = connected;
            this.left           = true;
            this.running        = true;
            InputStream input   = this.socket.getInputStream();
            this.write          = new PrintStream(this.socket.getOutputStream(), true);
            this.reader         = new BufferedReader(new InputStreamReader(input));
            this.saveText       = new PrintStream(new FileOutputStream("exo2/connection.txt", true)); 
            this.saveChat       = new PrintStream(new FileOutputStream("exo2/chat.txt", true)); 
            this.formatDate     = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
            this.privateChat    = "-1";
        } catch (IOException e) {
            this.write.println("Une erreur est survenue lors de l'initialisation d'un serverThread : IOException Exception \n" + e.getMessage());
        } 
    }

    /**
     * Getter pour l'identifiant d'un autre client (pour un message privée)
     * @return l'identifiant de l'autre personne pour un message privée
     */
    public String getPrivateChat(){
        return this.privateChat;
    }

    /**
     * Getter sur la socket du client
     * @return le socket
     */
    public Socket getSocket(){
        return this.socket;
    }

    /**
     * Getter pour l'identifiant de l'utilisateur
     * @return l'identifiant de l'utilisateur
     */
    public String getID(){
        return this.id;
    }

    /**
     * Getter permettant d'obtenir des informations du Thread
     * @param id l'identifiant de l'utilisateur
     * @return le Thread
     */
    public ServerThread getServerThread(String id){
        synchronized (this.connected) {
            for (ServerThread tmp : this.connected) 
            {
                if(tmp.getID().equals(id)){
                    return tmp;
                }
            }
            return null;
        }  
    }

    /**
     * Cette méthode permet de vérifier si l'utilisateur a quitté le tchat ou non
     * @return vrai si l'utilisateur a quitté la conversation, faux sinon
     */
    public Boolean getLeft(){
        return this.left;
    }
 
    /**
     * Implémenter l'objet run d'interface Runnable
     * C'est la méthode principale qui de gérer les différentes actions possibles pour un client
     */
    public void run() {
        try {
            this.write.println("-----------------------------");
            this.write.println("| Bonjour "+this.socket.getInetAddress().getHostName()+" |");
            this.write.println(this.formatDate.format(getNowDate()));

            while(this.running){
                try{
                String choice = generateMenu();
                this.privateChat = "-1";

                switch(choice) {
                    case "1":
                        changeUser();
                        Thread.sleep(800);   
                        break;
                    case "2":
                        getListConnnected();
                        Thread.sleep(1500);   
                        break;
                    case "3":
                        this.write.println("Entrer dans le tchat");
                        this.write.println("Pour quitter le tchat, veuillez entrer 'exit'");
                        getCurrentOnline();
                        this.left = false;

                        Thread.sleep(1000);   
                        enterChat();
                        Thread.sleep(1000);   
                        break;
                    case "4":
                        Boolean answer = choosePerson();
                        Thread.sleep(1500);  
                        if(answer){
                            this.left = false;
                            enterChat();
                        } 
                        break;
                    case "5":
                        this.running = false;
                        exit();
                        Thread.sleep(1500);   
                        this.socket.close();
                        break;
                  }

                } catch (InterruptedException e) {
                    this.write.println("Interrupted Exception \n" + e.getMessage());
                }
            }
  
        } catch (NullPointerException e) {
            this.write.println("Null Pointer Exception \n" + e.getMessage());
            exit();
        } catch (IOException e) {
            this.write.println("IOException Exception \n" + e.getMessage());
        } 
    }

    /**
     * cette méthode permet d'entrer dans un chat et d'envoyer un message à tous les utilisateurs connectés à ce chat
     */
    public void enterChat(){
        try{
        while (!this.left) {
            String message = this.reader.readLine();
            if (message == null) {
                exit();
                break;
            } 

            /*if(this.id.equals("admin"))
            {
                if (message.equals("kick")){
                    kick();
                }
                continue;
            }*/

            if (message.equals("exit")){
                this.left = true;
                sendNotification(this.id);
                this.privateChat = "-1";
                Thread.sleep(1000);   
                this.write.println("Retour au menu principale");
            }  else {
                sendMessage(this.id, message);
            }
                this.saveChat.println(this.formatDate.format(getNowDate())+" | "+ this.id + " : " + message);
        }

    } catch (IOException e) {
        this.write.println("IOException Exception \n" + e.getMessage());
    } catch (NullPointerException e) {
        this.privateChat = "-1";
        this.write.println("Null Pointer Exception \n" + e.getMessage());
    } catch (InterruptedException e) {
        this.write.println("Interrupted Exception \n" + e.getMessage());
    }
        this.saveText.close();
        this.saveChat.close();
    }

    /**
     * cette méthode permet d'envoyer un message à d'autres utilisateurs
     * @param id l'id d'utilisateur
     * @param message le message a envoyé
     */
    public void sendMessage(String id, String message) {
        synchronized (this.connected) {
            for (ServerThread tmp : this.connected) {
                if (!tmp.getLeft()) {
                    if(this.privateChat != "-1"){
                        if(!tmp.getServerThread(this.privateChat).getPrivateChat().equals(this.id)){
                            this.write.println("Cette personne a quitté le chat");
                        } else {
                            tmp.getServerThread(this.privateChat).write.println(id+" : "+ message);
                        }
                        break;
                    }
                    else if(tmp.getPrivateChat() == "-1"){
                        tmp.write.println(id+" : "+ message);
                    }
                  
                } 
            }
        }    
    }

    /**
     * cette méthode permet d'envoyer une notification aux autres utilisateurs afin d'informer qu'on a quitté le chat
     * @param id l'id d'utilisateur
     */
    public void sendNotification(String id) {
        synchronized (this.connected) {
            for (ServerThread tmp : this.connected) {
                if(this.privateChat != "-1"){
                    if(tmp.getServerThread(this.privateChat).getPrivateChat().equals(this.id)){
                        tmp.getServerThread(this.privateChat).write.println(id + " quitté le chat");
                        break;
                    }
                }
                else if(!tmp.getLeft() && tmp.getPrivateChat() == "-1"){
                    tmp.write.println(id + " quitté le chat");
                 }
            }
        }    
    }

    /**
     * cette méthode permet d'obtenir les listes des utilisateurs connectés à ce serveur
     */
    public void getListConnnected() {
        synchronized (this.connected) {
            this.write.println(this.connected.size()+" personnes sont connectées");
            this.connected.forEach(tmp -> this.write.println("-"+tmp.getID()));
        }    
    }

    /**
     * cette méthode permet d'obtenir la date actuelle
     * @return la date actuelle
     */
    public Date getNowDate(){
        return new Date();
    }

    /**
     * cette méthode permet d'obtenir les utilisateurs actuels qui sont en ligne
     */
    public void getCurrentOnline() {
        synchronized (this.connected) {
            int n = 0;
            for(int i=0; i< this.connected.size(); i++){
                if(!this.connected.get(i).getLeft() && this.connected.get(i).getPrivateChat() == "-1"){
                    n++;
                    this.connected.get(i).write.println(this.id+" rejoint le tchat");
                }
            }
            this.write.println(n+" personnes sont en ligne");
        }    
    }

    /**
     * cette méthode permet de changer le nom de l'utilisateur
     */
    public void changeUser(){
        try{
            String msg;
            Boolean error = false;
            this.write.print("Entrez votre nom d'utilisateur : ");
            msg = this.reader.readLine();

            synchronized (this.connected) {
                for (ServerThread tmp : this.connected) {
                    if(tmp.getID().equals(msg)){
                        error = true;
                        this.write.println("Ce nom a été utilisé");
                    }
                }
                if(!error){
                    this.id = msg;
                    this.write.println(""+this.id+" a été enregistré");
                }
            }
           
        } catch (IOException e) {
            this.write.println("IOException Exception \n" + e.getMessage());
        } 
    }

    /**
     * cette méthode permet de sélectionner la personne avec qui nous allons communiquer
     * @return vrai si on trouve la personne, faux sinon
     */
    public Boolean choosePerson(){
        String tmp;

        try{
            this.write.print("Veuillez entrer la personne à qui vous voulez parler : ");
            tmp = this.reader.readLine();
            synchronized (this.connected) {
                for(int i=0; i< this.connected.size(); i++){
                    if(this.connected.get(i).getID().equals(tmp) && !tmp.equals(this.id)){
                        this.privateChat = tmp;
                        this.write.println("connexion établie avec "+tmp);
                        this.write.println("Pour quitter le tchat, veuillez entrer 'exit'");
                        return true;
                    }
                }
            }  
        } catch (IOException e) {
            this.write.println("IOException Exception \n" + e.getMessage());
        } 
        this.write.println("la personne n'est pas connecté à ce serveur, \nveuillez vérifier les utilisateurs connectés");
        return false;
    }


    /**
     * Génère le menu lorsque l'utilisateur est connecté
     * @return l'entrée donnée par l'utilisateur
     */
    public String generateMenu(){
        String message = "0";
        try{
            this.write.println("-----------------------------");
            this.write.println("MENU  Voici votre nom d'utilisateur : "+ this.id);
            this.write.println("-----------------------------");
            this.write.println("1. Changer nom d'utilisateur");
            this.write.println("2. Afficher les utilisateurs connectés");
            this.write.println("3. Entrer dans le tchat");
            this.write.println("4. tchat avec une personne");
            this.write.println("5. Exit");
            this.write.println("-----------------------------");
            this.write.print("Input : ");
            message = this.reader.readLine();
        } catch (IOException e) {
            this.write.println("IOException Exception \n" + e.getMessage());
        } 
        return message;
    }

    /**
     * cette méthode permet de se déconnecter du serveur
     */
    public void exit(){
            Date date                   = new Date();  
            String messageDisconnect    = this.socket.getInetAddress().getHostAddress()+" ("+this.socket.getInetAddress().getHostName()+") est deconnecté à "+ this.formatDate.format(date);
            this.connected.remove(this);
            this.saveText.println(messageDisconnect);
            System.out.println(messageDisconnect);
            this.write.println("Sortie du serveur...");        
    }

    /**
     * cette méthode permet d'expulser quelqu'un d'autre du serveur
     * pas fini
     */
    public void kick(){
        Date date                   = new Date();  
        String msg;
        this.write.print("veuillez entrer l'utilisateur à expulser : ");
        try {
        msg = this.reader.readLine();

        synchronized (this.connected) {
                for (ServerThread tmp : this.connected) {
                    if(tmp.getID().equals(msg)){
                        String messageDisconnect    = tmp.getSocket().getInetAddress().getHostAddress()+" ("+ tmp.getSocket().getInetAddress().getHostName()+") est deconnecté à "+ this.formatDate.format(date);
                        this.connected.remove(tmp);
                        this.saveText.println(messageDisconnect);
                        System.out.println(messageDisconnect);
                        tmp.write.println("Vous avez été expulsé par l'administrateur..");
                        try{
                        tmp.getSocket().close(); 
                    } catch (IOException e){
                        this.write.println("IOException Exception \n" + e.getMessage());
                    }
                    }
                }
        } 

        } catch (IOException e){
            this.write.println("IOException Exception \n" + e.getMessage());
        }
            
}
}