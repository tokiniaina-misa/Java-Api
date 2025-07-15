#!/bin/bash

echo " Compilation..."
javac src/*.java
if [ $? -ne 0 ]; then
  echo " Échec compilation"
  exit 1
fi

echo " Lancement du serveur..."
java -cp src Main
