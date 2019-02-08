# Implementation document

This document will give a basic understanding of how the program functions and the structure of the algorithm. We will also discuss the space and time complexities. 

## Structure

The program is split into 3 parts: game, UI and algorithms. The game is the platform for which the algorithm is created. It is responisble for creating the map, the mines and tiles in it and the interaction mechanic. Our algorithm will use this section as it's working area. 

The UI is, well, exactly that, the UI. It provides the graphical interface, which helps the user understand what is happening within the game and how the algorithm works.

Algorithms is the main part of the program. It includes all the necessary algorithms for implementing the solver. The main algorithm is in fact the solver and all of the other alogirhtms work as fill-ups.

##  Step-by-step functionality

1. Call the Main -class, which in turn creates all the necessary components (Gamefield, Inspector, Asker, UI, GamConsole) for the program to work
2. Gamefield creates the map using the methods generateField(), which uses generateMines() and countMines() -methods to fill the map.
3. The Asker works as the starting interface, which asks for the user to select the width and height of the game, and generate the necessary mines.
4.  GameConsole is the primary platform for the user to interact with dynamically. The console then implements the Inspector class to directly interact with the board.
5. The game terminates if the user wins, loses or closes the program. 

## Time and space complexities

Will fill in later

## Flaws and improvements

Will fill in later

## Sources

[Minesweeper AI Guide](https://luckytoilet.wordpress.com/2012/12/23/2125/)

[Thesis by David Becerra](https://dash.harvard.edu/bitstream/handle/1/14398552/BECERRA-SENIORTHESIS-2015.pdf?sequence=1)

[Post by Magnus Hoff](https://magnushoff.com/minesweeper/)