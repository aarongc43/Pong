# Pong

A simple implementation of the classic Pong game in Java with the Raylib port
Jaylib.

![Pong Game Demo](assets/demo.mov)

## Table of Contents
1. [Overview](#overview)
2. [Features](#features)
3. [Prerequisites](#prerequisites)
4. [Installation](#installation)
5. [How to Play](#how-to-play)
6. [Code Structure](#code-structure)
7. [Demo Video](#demo-video)

## Overview

This Pong game was created to learn about the Raylib game programming library.

## Features

- Two-player gameplay
- Ball physics with random bounce angles
- Increasing ball speed as the game progresses
- Score tracking

## Prerequisites

To run this game, you'll need:

- Java Development Kit (JDK) 8 or higher
- Raylib library for Java (Jaylib) https://github.com/electronstudio/jaylib

## Installation

1. Clone this repository or download the source code.
2. Ensure you have the JDK installed on your system.
3. Download and set up the Raylib library for Java (Jaylib). You can find instructions on the [Jaylib GitHub page](https://github.com/electronstudio/jaylib).
4. Add the Jaylib JAR file to your project's classpath.
5. Run the build script

## How to Play

1. Run the `build.sh` file to start the game.
2. Player 1 (left paddle) uses 'W' and 'S' keys to move up and down.
3. Player 2 (right paddle) uses the up and down arrow keys to move.
4. The ball will start moving in a random direction.
5. Try to hit the ball with your paddle and get it past your opponent's paddle to score points.
6. The game continues indefinitely. You can close the window to end the game.

## Code Structure

The game is structured into several Java classes:

- `Main.java`: Entry point of the application.
- `Game.java`: Manages the game loop, updates, and rendering.
- `Ball.java`: Handles the ball's movement and collision detection.
- `Paddle.java`: Manages the paddles' movement and rendering.
- `Scoreboard.java`: Keeps track of and displays the score.
