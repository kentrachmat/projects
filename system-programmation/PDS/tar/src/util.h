#ifndef _UTIL_H_
#define _UTIL_H_
extern void readArchive(struct posix_header *s,long sizeMax);
extern int check_chksum(struct posix_header* s);
extern void droits(mode_t mode, char * droit);
extern void check_type(char c, char *type1, char *type2);

#endif
