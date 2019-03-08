# Implementation document

This document will give a basic understanding of how the program functions and the structure of the algorithm. We will also discuss the space and time complexities. 

## Structure

The program is split into 4 parts: game, utilities UI and algorithms. The game is the platform for which the algorithm is created. It is responisble for creating the map, the mines and tiles in it and the interaction mechanic. Our algorithm will use this section as it's working area. 

The UI is, well, exactly that, the UI. It provides the graphical interface, which helps the user understand what is happening within the game and how the algorithm works.

Algorithms is the main part of the program. It includes all the necessary algorithms for implementing the solver. The main algorithm is in fact the solver and all of the other alogirhtms work as fill-ups.

Utilites are data-structures and other tools that help implement the algorithm.

##  Step-by-step functionality

1. Call the Main -class, which in turn creates all the necessary components (Gamefield, Inspector, Asker, UI, GamConsole) for the program to work
2. Gamefield creates the map using the methods generateField(), which uses generateMinesFisherYates() and countMines() -methods to fill the map.
3. The Asker works as the starting interface, which asks for the user to select the width and height of the game, and generate the necessary mines.
4.  GameConsole is the primary platform for the user to interact with dynamically. The console then implements the Inspector class to directly interact with the board. You may also use the algorithm to solve the map.
5. The game terminates if the user wins, loses or closes the program. 

## Time and space complexities

We have two main algorithms for the project: the field generating algorithm and the solving algorithm.

The field-generating algorithm implements a Fisher-Yates -algorithm, which includes two for-loops looping through and generating squares and values. We also have a mine-generating Fisher-Yates algorithm which puts the mines in the field. So the **time complexity** is O(2(n*m) + mines) = **O(nm + mines)** where n and m are width and height. The **space complexity** is just width * height, so **O(nm)**.

The solving algorithm uses three 2-D arrays of the map and eight SuperLists that contain contemporary information of a specific section of the board. These lists usually have 2-10 elements inside them, so they are relatively small compared to the arrays. For this reason we will exclude the SuperLists from the space complexity and only inspect the arrays. Now the **space complexity** is O(3(nm)) = **O(nm)**. The time is also relative to the size of the board. The algorithm calls for the tankSolver() algorithm in each loop, which in turn calls tankSegregate() and tankRecurse(). We also try to solve every single square with solveSingle() method. In each loop (300 loops) we call four different methods, that all have for-loops (these are also the most time-consuming parts of those methods). In total, there are 8 for-loops that have for-loops inside of them (iterating the entire map). So the **time complexity** is O(300 * 8 * nm) = **O(2400nm)**.

## Flaws and improvements

The GUI is incomplete and was not implemented. It would have been more readable if we had a visual understanding of what's happening. 

The testing could also be better. I didn't test the project until the very end and by then I had forgotten a lot of things. Should've tested the same time I was coding.

The solver is pretty poor. I wanted to implement a functionality that would've boosted the solving percentage but it proved to be difficult. Currently it solves the easy and medium levels relatively well. Anything beyond that is almost impossible to solve.

I had some redundancy in the code, especially the boardReader -class. I could've done this better but at the time I just wanted for the algorithm to work so I made some poor choices.

## Sources

[Minesweeper AI Guide](https://luckytoilet.wordpress.com/2012/12/23/2125/)

[Thesis by David Becerra](https://dash.harvard.edu/bitstream/handle/1/14398552/BECERRA-SENIORTHESIS-2015.pdf?sequence=1)

[Post by Magnus Hoff](https://magnushoff.com/minesweeper/)