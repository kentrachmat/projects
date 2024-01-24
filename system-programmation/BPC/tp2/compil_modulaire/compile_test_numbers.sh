#!/bin/bash

cc -c numbers-test.c -o module/numbers-test.o
cc -c put_numbers.c -o module/put_numbers.o
cd module
cc -o numbers-test numbers-test.o put_numbers.o 
./numbers-test