#!/bin/bash

javac -cp jaylib-5.0.0-0.jar Main.java Game.java Paddle.java Ball.java Scoreboard.java

if [ $? -eq 0 ]; then
    java -XstartOnFirstThread -cp jaylib-5.0.0-0.jar:. Main
else 
    echo "Compilation failed"
fi
