#!/bin/bash

for i in {1..100}
do
  for j in {1..1000}
  do
    printf "Welcome $j times ($i) "
  done
  printf '\n'
  sleep .1
done
