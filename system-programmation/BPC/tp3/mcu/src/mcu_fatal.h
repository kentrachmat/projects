/* 
   mcu - terminaison sur erreur
*/

#ifndef _MCU_FATAL_H_
#define _MCU_FATAL_H_

#include "mcu_macros.h"

/* Termine l'exécution du programme si le prédicat assert est faux
   - affiche le message sur la sortie d'erreur
   - termine le programme par exit() et retourne la valeur status.
*/
extern void fatal(int assert, const char message[], int status);

#endif

