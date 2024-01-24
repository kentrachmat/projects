#include <stdio.h>

union test_u {
  unsigned int val;
  unsigned char data[sizeof(int)];
};
int main() {
union test_u test;
int i;
test.val = 0x41424344;
for (i = 0; i < sizeof(int); i++) {
    putchar(test.data[i]);
  }
}
