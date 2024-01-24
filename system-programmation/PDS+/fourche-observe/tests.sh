#!/bin/bash
# This script will test the return of multif
# Usage: ./tests.sh 
 

MyFunctTest(){
    ./multif $1 $2
    result=$?
    if [ $3 -eq $result ]
    then
        echo "./multif $1 $2 ||  expected == $3  result == $result  (fok)"
    else
        echo "./multif $1 $2 || expected == $3 result == $result (Error)"
    fi

}

### Main script starts here 

### Test the return
make
echo -e "\n --------[Partie 1/3] test the return of multif -------"
MyFunctTest true true 0
MyFunctTest true false 1
MyFunctTest false true 1
MyFunctTest false false 1
MyFunctTest sleep2 true 0
MyFunctTest false sleep2 1


### Test the paralelism 
echo -e "\n --------[Partie 2/3] test that the functions(f0,f1...) run in parallel -------"
SECONDS=0
./multif sleep5 sleep5
duration=$SECONDS
echo "$(($duration % 60)) seconds elapsed. with ./multif sleep5 sleep5"


### Test the script
echo -e "\n -------- [Partie 3/3] Test that the script displays an error, when [expected =/= result] -------"
MyFunctTest false false 0
