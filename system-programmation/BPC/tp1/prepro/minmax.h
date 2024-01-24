#include "abs.h"

static inline int min(int x,int y) { return (x+y-abs(x-y))/2; }
static inline int max(int x,int y) { return (x+y+abs(x-y))/2; }
