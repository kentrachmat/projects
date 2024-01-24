#include <stdio.h>
#include <stdlib.h>
#include <unistd.h>
#include <string.h>
#include <sys/types.h>
#include <sys/socket.h>
#include <arpa/inet.h>
#include <netinet/in.h>
   
#define MAX_TAMPON 1024
#define MAX_IP 30
   
int main(int argc, char ** argv) {

    int sock;                                // identifiant de socket
    char buffer[MAX_TAMPON], ip[MAX_IP];     // buffer pour les message recues et l'ip d'expéditeur
    struct sockaddr_in servAddr, clientAddr; // struct pour connnaitre (@IP,#Port) de reception et l'emetteur
    unsigned int len = sizeof(clientAddr), n;
    
    if (argc != 2){
        puts("Veuillez saisir le format correct !!\n");
        puts(">> ./receive {PORT}");
        puts("ex : ./receive 2022\n");
        exit(EXIT_FAILURE);
    }

    // création de la socket en mode Datagram (UDP) :
    if ( (sock = socket(AF_INET, SOCK_DGRAM, 0)) < 0 ) {
        perror("erreur création de socket");
        exit(EXIT_FAILURE);
    }

    puts("Le serveur en mode écoute... (^C pour sortir)");

    // initialisation de Mon (@IP,#Port) pour reception :
    servAddr.sin_family      = AF_INET;
    servAddr.sin_addr.s_addr = INADDR_ANY;
    servAddr.sin_port        = htons(atoi(argv[1]));
       
    // liaison entre la socket et Mon (@IP,#Port) :
    if (bind(sock, (const struct sockaddr *) &servAddr, sizeof(servAddr)) < 0){
        perror("erreur de bind");
        exit(EXIT_FAILURE);
    }

    while(1){
        // réception d’un datagramme UDP depuis Mon(@IP,#Port) :
        n = recvfrom(sock, 
                    (char *)buffer /*buffer de réception*/,
                    MAX_TAMPON /*taille max buffer*/, 
                    0 /*offset*/, 
                    ( struct sockaddr *) &clientAddr,
                    &len);

        // obtenir l'IP de l'expéditeur
        strcpy(ip, (char*)inet_ntoa((struct in_addr)clientAddr.sin_addr));

        buffer[n] = '\0';

        // afficher le message
        printf("%s > %s\n",ip, buffer);
    }

    return 0;
}