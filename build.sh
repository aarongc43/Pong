#!/bin/bash

javac -cp jaylib-5.0.0-0.jar main.java

if [ $? -eq 0 ]; then
    java -XstartOnFirstThread -cp jaylib-5.0.0-0.jar:. main
else 
    echo "Compilation failed"
fi
