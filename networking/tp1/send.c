#include <stdio.h>
#include <stdlib.h>
#include <unistd.h>
#include <string.h>
#include <sys/types.h>
#include <sys/socket.h>
#include <arpa/inet.h>
#include <netinet/in.h>
#include <arpa/inet.h>
#include <netdb.h>

#define MAX_TAMPON 1024

int main(int argc, char ** argv) {
    
    int sock;                       // identifiant de socket
    char *message = argv[3];        // le message à envoyer
    struct sockaddr_in serverAddr;  // struct pour Fixer (@IP,#Port) de destination
    //struct hostent *hp;           // struct pour Résolution Symbolique
   
    if (argc != 4){
        puts("Veuillez saisir le format correct !!\n");
        puts(">> ./send {PORT} {IP ADDRESS DEST} {MESSAGE}\n");
        puts("ex : ./send 2022 127.0.0.1 'Bonjour utilisateur !!'\n");
        exit(EXIT_FAILURE);
    }

    // création de la socket en mode Datagram (UDP) :
    if ((sock = socket(AF_INET, SOCK_DGRAM, 0)) < 0){
        puts("erreur création de socket");
        exit(EXIT_FAILURE);
    }

    // initialisation de l'(@IP,#Port) de destination :
    //hp = gethostbyname("Kent.local"); 
    serverAddr.sin_family = AF_INET;
    serverAddr.sin_port = htons(atoi(argv[1]));
    //memcpy(&serverAddr.sin_addr, hp->h_addr, hp->h_length);
    serverAddr.sin_addr.s_addr = inet_addr(argv[2]);
       
    // envoi d'un datagramme UDP vers (@IP,#Port) de destination
    sendto(sock, 
        (const char *)message /*buffer emission avec message*/, 
        strlen(message) /*longueur message a emmettre*/,
        0 /*offset*/, 
        (const struct sockaddr *) &serverAddr, 
        sizeof(serverAddr));

    // fermeture du socket.
    close(sock);

    printf("Le message a été envoyé à l'addresse : %s | port : %s\n", argv[2], argv[1]);
    
    return 0;
}