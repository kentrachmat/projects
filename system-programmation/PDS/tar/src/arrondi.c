/*Exo 18
arrondit l’entier n au multiple de 512 supérieur*/
long arrondi512(long n)
{
    return n>=0 ? ((n + 511) / 512) * 512 : (n / 512) * 512; 
}
