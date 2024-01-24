#include <stdio.h>
#include <string.h>
#include <netinet/in.h>
#include <arpa/inet.h>
#include <stdlib.h>
#include <unistd.h>
#include <errno.h>
#include <sys/types.h>
#include <sys/socket.h>
#include <netdb.h>
#include <assert.h>

#define DESTPORT 53           
//  RFC 1035, 3.1 : To simplify implementations, the total length of a domain name (i.e., label octets and label length octets) is restricted to 255 octets or less.
#define MAX_DOMAIN_NAME_LENGTH 256                   
#define NS_ANSWER_LENGTH 512
// 16 octets sont assurés dans la query (12 fixés entête + 4 fixés question)
#define FIXED_FIELDS 16                              
#define TYPE_FIELD_LENGTH 2
#define TYPE_MEANING_LENGTH 6
#define CLASS_FIELD_LENGTH 2
#define TTL_LENGTH 4
#define RDLENGTH_LENGTH 2
#define IP_STR_LENGTH 4
#define POINTER_VALUE 192

unsigned char answer[NS_ANSWER_LENGTH];

int hostnameToQname(char** argv, unsigned char* qname) {
    char* hostname = argv[2];
    char* point = ".";
    char* token = strtok(hostname, point);       
    int token_length, index = 0;

    while (token != NULL) {
        token_length = strlen(token);
        qname[index++] = token_length;
        for (int i = 0; i < token_length; i++) {
            qname[index++] = token[i];               
        }
        token = strtok(NULL, point);
    }
    return EXIT_SUCCESS;
}

int queryBuilder(unsigned char* qname, int qname_length, unsigned char* query) {
    int index = 12;

    query[0] = 0x08; query[1] = 0xbb; query[2] = 0x01; query[3] = 0x00;
    query[4] = 0x00; query[5] = 0x01; query[6] = 0x00; query[7] = 0x00;
    query[8] = 0x00; query[9] = 0x00; query[10] = 0x00; query[11] = 0x00;

    for (int i = 0; i < qname_length; i++) {
        query[index++] = qname[i];
    }

    // fin du QNAME
    query[index] = 0x00; 

    // la partie Question fait 4 octets
    query[index + 1] = 0x00; 
    query[index + 2] = 0x01;                           
    query[index + 3] = 0x00; 
    query[index + 4] = 0x01;
    
    return EXIT_SUCCESS;
}

int requestBuilder(struct sockaddr_in* addr_serveur, char** argv) {
    memset(addr_serveur, 0, sizeof(struct sockaddr_in));             /* évite les valeurs incohérentes en mettant tout à zéro */
    addr_serveur->sin_family      = AF_INET;                         /* famille d’adresse = AF_INET ==> IPv4 Internet protocols */
    addr_serveur->sin_port        = htons(DESTPORT);                 /* numero de port dest */  // 53 réservé pour DNS
    addr_serveur->sin_addr.s_addr = inet_addr(argv[1]);              /* @IPv4 dest */      // inet_addr() fait une conversion "w.x.y.z" à binaire
    fprintf(stderr, " conversion de l'@IPv4, pour la chaine \"%s\" ... ", argv[1]);
    fprintf(stderr, "[ok]\n");

    return EXIT_SUCCESS;
}

int socketBuilder(int* sock) {
    fprintf(stderr, " creation du socket en mode UDP ... ");
    *sock = socket(PF_INET, SOCK_DGRAM, 0);

    if (sock < 0) {  
        fprintf(stderr,"[erreur] : ");
        switch(errno) {
        case ENFILE:
            fprintf(stderr,"La table des descripteurs par processus est pleine.");
            break;
        case EMFILE:
            fprintf(stderr,"La table des fichiers est pleine.");
            break;
        case EACCES:
            fprintf(stderr,"La creation d'une telle socket n'est pas autorisee.");
            break;
        case ENOMEM:
            fprintf(stderr,"Pas suffisament d'espace pour allouer les buffers necessaires.");
            break;
        case EINVAL:
            fprintf(stderr,"Protocole inconnu, ou famille de protocole inexistante.");
            break;
        default:
            fprintf(stderr,"Erreur inconnue");
            break;
        }
        return EXIT_FAILURE;
    }

    fprintf(stderr, "[ok]\n");

    return EXIT_SUCCESS;
}

int sendRequest(int* sock, unsigned char* query, int query_len, struct sockaddr_in addr_serveur, ssize_t* len) {
    fprintf(stderr, " envoi du message ... ");
    
    *len = sendto(*sock, query, query_len, 0, (struct sockaddr*) &addr_serveur, sizeof(struct sockaddr_in));

    if (*len < 0) {
      fprintf(stderr,"[erreur] : ");
      switch(errno) { 
      case EBADF:  
        fprintf(stderr,"Descripteur de socket invalide.");
        break; 
        
      case EFAULT: 
        fprintf(stderr,"Un parametre pointe en dehors de l'espace d'adressage accessible.");
        break; 
      case EINVAL: 
        fprintf(stderr,"Un argument invalide a ete transmis.");
        break; 
      case ENOMEM: 
        fprintf(stderr,"Pas assez de memoire pour le noyau.");
        break; 
      case ENOTSOCK:  
        fprintf(stderr,"L'argument s n'est pas une socket.");
        break;
      case EPIPE:  
        fprintf(stderr,"L'ecriture  est  impossible  (correspondant  absent).  Dans  ce  cas,  le processus recevra egalement un signal SIGPIPE sauf s'il a activee l'option MSG_NOSIGNAL.");
        break;
      default:
        fprintf(stderr,"Erreur inconnue");
        break;
      }
      return EXIT_FAILURE;
    }

    fprintf(stderr, "[ok]\n longueur du message envoye : %lu\n", *len);
    return EXIT_SUCCESS;
}

int receiveMessage(ssize_t* len, int* sock, unsigned char* answer, struct sockaddr_in* remoteRecvAddr, socklen_t* remoteRecvAddrLen) {
    fprintf(stderr, " reception du message ... ");
    
    *len = recvfrom(*sock, answer, NS_ANSWER_LENGTH, 0, (struct sockaddr *) remoteRecvAddr, (socklen_t *) remoteRecvAddrLen);
    
    if (*len < 0) {
      fprintf(stderr,"[erreur] : ");
      switch(errno) { 
      case ENOTCONN: 
        fprintf(stderr,"La socket est associee a un protocole oriente connexion et n'a pas encore ete connectee (voir connect(2) et accept(2)).");
        break;
      case ENOTSOCK: 
        fprintf(stderr,"L'argument s ne correspond pas a une socket.");
        break; 
      case EINVAL: 
        fprintf(stderr,"Un argument est invalide.");
        break;
        
      default:
        fprintf(stderr,"Erreur inconnue");
        break;
      }
      return EXIT_FAILURE;
    }

    fprintf(stderr, "[ok]\n longueur du message recu : %lu\n", *len);

    // infos de l'@IPv4 distante / port distant utilises
    // normalement le #DESTPORT UDP de la machine visee DESTNAME, un message
    // peut etre envoye sur ce port local par quelqu'un d'autre, entre notre envoi et la reception.
    fprintf(stderr," - port  distant  : %hu\n", ntohs(remoteRecvAddr->sin_port));
    fprintf(stderr," - @IPv4 distante : %s\n", inet_ntoa(remoteRecvAddr->sin_addr));
    return EXIT_SUCCESS;
}

void afficheurHexa(ssize_t* len, unsigned char* answer) {
    for (int i = 0; i < *len; i++) {
      fprintf(stdout," %.2X", answer[i] & 0xff);

      if (((i+1)%16 == 0) || (i+1 == *len)) {
        for (int j = i+1 ; j < ((i+16) & ~15); j++) {
          fprintf(stdout,"   ");
        }
        fprintf(stdout,"\t");
        for (int j = i & ~15; j <= i; j++)
          fprintf(stdout, "%c", answer[j] > 31 && answer[j] < 128 ? (char)answer[j] : '.');
          fprintf(stdout,"\n");
      }
    }
}

void afficheurBits(unsigned char x) {
    for(int i = sizeof(x)<<3; i; i--)
        putchar('0'+((x>>(i-1))&1));
}

unsigned concatenateHex(unsigned x, unsigned y) {
    return x * 256 + y;
}

int afficheurHeader(unsigned char* answer, int* index, int* ancount, int* nscount, int* arcount) {
    puts("\n-- ENTETE --\n");
    printf("%.2X %.2X : IDENTIFIANT", answer[*index], answer[*index + 1]); 
    *index += 2;

    printf("\n%.2X %.2X : FLAGS = ", answer[*index], answer[*index + 1]);
    afficheurBits(answer[*index]); 
    fprintf(stdout, " ") ;
    afficheurBits(answer[*index + 1]); 
    *index += 2; 

    printf("\n%.2X %.2X : QDCOUNT (Nombre de questions)", answer[*index], answer[*index + 1]); 
    *index += 2;
    
    printf("\n%.2X %.2X : ANCOUNT (Nombre de reponses)", answer[*index], answer[*index + 1]); 
    *ancount += concatenateHex(answer[*index], answer[*index + 1]);
    *index += 2;

    printf("\n%.2X %.2X : NSCOUNT (Nombre d'authorities)", answer[*index], answer[*index + 1]); 
    *nscount += concatenateHex(answer[*index], answer[*index + 1]);
    *index += 2;

    printf("\n%.2X %.2X : ARCOUNT (Nombre d'additionnels)\n", answer[*index], answer[*index + 1]); 
    *arcount += concatenateHex(answer[*index], answer[*index + 1]);
    *index += 2;

    return EXIT_SUCCESS;
}

char* afficheurQuestion(unsigned char* answer, int* index) {
    puts("\n-- QUESTION --\n");

    unsigned int qname_size = 1; 
    unsigned int shift = 0;
    while(answer[*index + shift] != 0) {
        printf("%.2X ", answer[*index + shift]);
        qname_size++;
        shift++;
    }
    printf(": ");

    char* qname = malloc(sizeof(char) * qname_size); 
    unsigned int qname_index = 0;           
    memset(qname, 0, qname_size * sizeof(char)); 
    unsigned int answer_index = *index + 1;
    
    int dot = answer[*index];
    while (dot != 0) {
        for (int i = 0; i < dot; i++) {
            qname[qname_index++] = answer[answer_index++];
        }
        dot = answer[answer_index];
        if (dot == 0) {
            break;
        }
        answer_index++;
        qname[qname_index++] = '.';
    }

    printf("%s", qname);
    *index += qname_size;
    printf("\n%.2X %.2X : QTYPE = %s", answer[*index], answer[*index + 1], answer[*index + 1] == 1 ? "A" : "CNAME"); 
    *index += 2;

    printf("\n%.2X %.2X : QCLASS = IN", answer[*index], answer[*index + 1]); 
    *index += 2;
    return qname;
}

int afficheurAnalyse(unsigned char* answer){
    unsigned int index = 0; 
    unsigned int ancount = 0;  
    unsigned int nscount = 0;  
    unsigned int arcount = 0;
    unsigned int all;
    char *qname;
    afficheurHeader(answer, (int*) &index, (int*) &ancount, (int*) &nscount, (int*)&arcount);

    all = ancount + nscount + arcount;
    qname = afficheurQuestion(answer, (int*) &index);
 
    printf("\n\n-- RÉPONSES --\n");

    unsigned char names[all][MAX_DOMAIN_NAME_LENGTH];
    unsigned char interpreted_names[all][MAX_DOMAIN_NAME_LENGTH];
    unsigned char type[all][TYPE_FIELD_LENGTH];
    unsigned char type_meaning[all][TYPE_MEANING_LENGTH];
    unsigned char class[all][CLASS_FIELD_LENGTH];
    unsigned char rdlength[all][RDLENGTH_LENGTH];
    unsigned int rdlength_value[all]; 
    unsigned char ttl[all][TTL_LENGTH];
    unsigned char rddata[all][MAX_DOMAIN_NAME_LENGTH];
    unsigned char rddata_interpreted[all][MAX_DOMAIN_NAME_LENGTH];

    for (int i = 0; i < (int)all; i++) {
        memset(names[i], 0, MAX_DOMAIN_NAME_LENGTH * sizeof(unsigned char));
        memset(interpreted_names[i], 0, MAX_DOMAIN_NAME_LENGTH * sizeof(unsigned char));
        memset(type[i], 0, TYPE_FIELD_LENGTH * sizeof(unsigned char));
        memset(type_meaning[i], 0, TYPE_MEANING_LENGTH * sizeof(unsigned char));
        memset(class[i], 0, CLASS_FIELD_LENGTH * sizeof(unsigned char));
        memset(rdlength[i], 0, RDLENGTH_LENGTH * sizeof(unsigned char));
        memset(ttl[i], 0, TTL_LENGTH * sizeof(unsigned char));
        memset(rddata[i], 0, MAX_DOMAIN_NAME_LENGTH * sizeof(unsigned char));
        memset(rddata_interpreted[i], 0, MAX_DOMAIN_NAME_LENGTH * sizeof(unsigned char));
    }

    memset(rdlength_value, 0, sizeof(unsigned int));

    // lire les données et les insérer dans les arrays
    for (int i = 0; i < (int) all; i++) {
        unsigned int which;
        if ((unsigned int)i < ancount) {
            which = 1;
        } else if ((unsigned int)i < ancount + nscount) {
            which = 2;
        } else {
            which = 3;
        }

        //  insere les les noms 
        unsigned int index_pointer = index; 
        unsigned int name_index = 0; 
        unsigned int lus = 0;
        unsigned char current_octet = answer[index_pointer];
        if (current_octet) {
            while (current_octet) {
                if (current_octet >= POINTER_VALUE) {
                    printf("%.2X %.2X ", answer[index_pointer], answer[index_pointer + 1]);
                    index_pointer = concatenateHex(answer[index_pointer] - (unsigned int) POINTER_VALUE, answer[index_pointer + 1]);
                    while (current_octet) {
                        names[i][name_index++] = answer[index_pointer++];
                        current_octet = answer[index_pointer];
                        if (current_octet >= POINTER_VALUE) {
                            index_pointer = concatenateHex(answer[index_pointer] - (unsigned int) POINTER_VALUE, answer[index_pointer + 1]);
                        }
                    }
                    lus += 2;
                } if (current_octet > 0 && current_octet < POINTER_VALUE) {
                    names[i][name_index] = answer[index_pointer++];
                    printf("%.2X ", names[i][name_index++]);
                    current_octet = answer[index_pointer];
                    lus++;
                }
            }
            index += lus;
            printf(": ");
        } else {
            index++;
        }
        
        name_index = 0;          
        unsigned int interpreted_name_index = 0;
        
        int dot = names[i][name_index++];  
        while (dot != 0) {
            for (int z = 0; z < dot; z++) {
                interpreted_names[i][interpreted_name_index++] = names[i][name_index++];
            }
            dot = names[i][name_index];
            if (dot == 0) {
                break;
            }
            name_index++;
            interpreted_names[i][interpreted_name_index++] = '.';
        }
        printf("\"%s\"\n", interpreted_names[i]);

        // le type
        for (int y = 0; y < TYPE_FIELD_LENGTH; y++) {
            type[i][y] = answer[index];
            printf("%.2X ", answer[index++]);
        }
        printf(": ");

        if (concatenateHex(type[i][0], type[i][1]) == (unsigned) 1) {
            type_meaning[i][0] = 'A';
        } else if (concatenateHex(type[i][0], type[i][1]) == (unsigned) 5){
            type_meaning[i][0] = 'C';
            type_meaning[i][1] = 'N';
            type_meaning[i][2] = 'A';
            type_meaning[i][3] = 'M';
            type_meaning[i][4] = 'E';
        } else {
            type_meaning[i][0] = 'S';
            type_meaning[i][1] = 'K';
            type_meaning[i][2] = 'I';
            type_meaning[i][3] = 'P';
        }
        printf("TYPE = %s\n", type_meaning[i]);

        // la classe
        for (int y = 0; y < CLASS_FIELD_LENGTH; y++) {
            class[i][y] = answer[index];
            printf("%.2X ", answer[index++]);
        }
        printf(": CLASS = IN\n");

        // le TTL 
        for (int y = 0; y < TTL_LENGTH; y++) {
            ttl[i][y] = answer[index];
            printf("%.2X ", answer[index++]);
        }
        printf(": TTL\n");

        // le RDLENGTH
        rdlength_value[i] = concatenateHex(answer[index], answer[index + 1]);
        for (int y = 0; y < RDLENGTH_LENGTH; y++) {
            rdlength[i][y] = answer[index];
            printf("%.2X ", answer[index++]);
        }
        printf(": RDLENGTH\n");

        // le RDDATA 
        if (type_meaning[i][0] == 'C') {
            index_pointer = index; 
            name_index = 0;
            current_octet = answer[index_pointer];
            while (current_octet) {
                if (current_octet >= POINTER_VALUE) {
                    printf("%.2X %.2X ", answer[index_pointer], answer[index_pointer + 1]);
                    index_pointer = concatenateHex(answer[index_pointer] - (unsigned int) POINTER_VALUE, answer[index_pointer + 1]);
                    while (current_octet) {
                        rddata[i][name_index++] = answer[index_pointer++];
                        current_octet = answer[index_pointer];
                        if (current_octet >= POINTER_VALUE) {
                            index_pointer = concatenateHex(answer[index_pointer] - (unsigned int) POINTER_VALUE, answer[index_pointer + 1]);
                        }
                    }
                    break;
                } if (current_octet > 0 && current_octet < POINTER_VALUE) {
                    rddata[i][name_index] = answer[index_pointer++];
                    printf("%.2X ", rddata[i][name_index++]);
                    current_octet = answer[index_pointer];
                }
            }
            index += rdlength_value[i];
            printf(": ");

            name_index = 0;          
            interpreted_name_index = 0;
            
            dot = rddata[i][name_index++];  
            while (dot != 0) {
                for (int z = 0; z < dot; z++) {
                    rddata_interpreted[i][interpreted_name_index++] = rddata[i][name_index++];
                }
                dot = rddata[i][name_index];
                if (dot == 0) {
                    break;
                }
                name_index++;
                rddata_interpreted[i][interpreted_name_index++] = '.';
                interpreted_name_index++;
            }
            printf("\"%s\"\n", rddata_interpreted[i]);
            if (which == 1)
                printf("\n*** REPONSE : \"%s\" EST LE NOM CANONIQUE (VRAI NOM) DE LA MACHINE \"%s\" (ALIAS) ***", rddata_interpreted[i], interpreted_names[i]);
            if (which == 2)
                printf("\n*** AUTHORITE : \"%s\" EST UN SERVEUR DE NOM FAISANT AUTHORITE SUR LE DOMAINE \"%s\" ***", rddata_interpreted[i], interpreted_names[i]);
            if (which == 3)
                printf("\n*** ADDITIONEL : \"%s\" EST UNE RÉFÉRENCE POUR \"%s\" ***", rddata_interpreted[i], interpreted_names[i]);
        } else if (type_meaning[i][0] == 'A') {
            for (unsigned int y = 0; y < rdlength_value[i]; y++) {
                rddata[i][y] = answer[index];
                printf("%.2X ", answer[index++]);
            }

            printf(": ");
            lus = 0;
            
            for (int a = 0; a < IP_STR_LENGTH; a++) {
                lus += sprintf((char *)rddata_interpreted[i] + lus, "%d", rddata[i][a]);
                if (a < IP_STR_LENGTH - 1) {
                    *(rddata_interpreted[i] + lus) = '.';
                    lus++;
                }
            }

            printf("\"%s\"\n", rddata_interpreted[i]);
            if (which == 1)
                printf("\n*** REPONSE : \"%s\" EST L’ADRESSE IP DE \"%s\" (et donc de \"%s\") ***", rddata_interpreted[i], interpreted_names[i], qname);
            if (which == 2)
                printf("\n*** AUTHORITE : \"%s\" A POUR ADRESSE IP \"%s\" ***", interpreted_names[i], rddata_interpreted[i]);
            if (which == 3)
                printf("\n*** ADDITIONEL : \"%s\" A POUR ADRESSE IP \"%s\" ***", interpreted_names[i], rddata_interpreted[i]);
        } else {
            index += rdlength_value[i];
            printf("\n*** Le champs QTYPE a une valeur inhabituelle alors nous ne traiterons pas cette partie de réponse. ***");
        }

        printf("\n---------------------------------\n");
    }
    free(qname);
    return EXIT_SUCCESS;
}

int main(int argc, char** argv) {
    char hostname[255];                                      
    int query_length, qname_length;

    // vérifier l'argument inséré
    if (argc != 3) {
        puts("Veuillez saisir le format correct !!\n");
        puts(">> ./dns {SERVEUR DNS} {NOM DE DOMAINE}");
        puts("ex : ./dns 8.8.8.8 www.univ-lille.fr\n");
        exit(EXIT_FAILURE);
    }
    
    // adapter l'url en Q-Name, et obtenir la taille du Q-Name
    unsigned char *qname = malloc(sizeof(char) * MAX_DOMAIN_NAME_LENGTH);

    // garder une copie du hostname, car l'argument va etre modifié
    strcpy(hostname, argv[2]);
    hostnameToQname(argv, qname);
    qname_length = strlen((const char *)qname);
    
    // une fois la taille de Q-Name connue, former la requête (+ 1 pour le 00 indiquant la fin du Q-Name)
    query_length = qname_length + FIXED_FIELDS + 1;                     
    unsigned char query[query_length];
    queryBuilder(qname, qname_length, query);

    // créer la structure qui retient à qui on va envoyer notre requête et sous quel protocole
    struct sockaddr_in serverAddr;
    requestBuilder(&serverAddr, argv);

    // creation du socket IPv4 UDP
    int sock = 0;
    socketBuilder(&sock);

    // envoi du message
    ssize_t len;
    sendRequest(&sock, query, query_length, serverAddr, &len);

    // reception du message
    struct sockaddr_in remoteRecvAddr;
    socklen_t remoteRecvAddrLength = sizeof(struct sockaddr_in);
    receiveMessage(&len, &sock, answer, &remoteRecvAddr, &remoteRecvAddrLength);
    
    // affichage de la reponse
    afficheurHexa(&len, answer);
    afficheurAnalyse(answer);

    free(qname);
    close(sock);
    return EXIT_SUCCESS;
}
