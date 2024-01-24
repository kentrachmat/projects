#include <stdio.h>

float square(float x)
{
  return x*x;
}

int is_within_distance(float x1, float y1, float x2, float y2, float dist)
{
  return (square(y2-y1) + square(x2-x1) < square (dist));
}

int main(void)
{
  printf("%d\n", is_within_distance(1,2,3,4,5)); // 1
  printf("%d\n", is_within_distance(10,2,3,4,6)); //0
}
