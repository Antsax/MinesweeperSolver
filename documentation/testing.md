# Testing document

## JUnit testing

The testing of the functions and classes are only done when it is seen to be necessary or when it's functionality is in question (uncertain). 

The UI was not tested nor the Main class. These are not essential for the functionality of the program so I didn't regard them as important. Also the BoardReader -class was not tested, as it simply uses functions from differnet, already tested classes. The solving algorithm is only tested by measuring its time of solving.

The tests where time was recorded, I used System.currentTimeMillis() -method. I didn't test functions that required an input from the user.

You can see the full test instructions from the user's guide.

Please note that the jacoco test coverage report shows the entirety of the program. I wasn't able to exclude certain methods or classes in the pom.xml file, so they are visible in the report. They are purposefully empty. Just ignore them.

## Empirical testing

### Generating field and mines

The following results come from testing the method generateField() from the Gamefield -class. This information was gathered by starting a timer, calling the function and ending the timer once the method has finished executing. I tested with differnt sizes and number of mines. The method includes a for-loop that generates the squares and a Fisher-Yates mine -generating algorithm. 

| Width x Height | Mines        | Time (ms)  |
| -------------  |:------------:| ----------:|
| 100 x 100      | 150          | 16ms       |
| 250 x 250      | 350          | 26ms       |
| 500 x 500      | 1000         | 96ms       | 
| 1000 x 1000    | 2500         | 56ms       |
| 1000 x 1000    | 5000         | 69ms       |
| 1000 x 1000    | 20000        | 86ms       |
| 1000 x 1000    | 100 000      | 224ms      |
| 1000 x 1000    | 150 000      | 424ms      |
| 1000 x 1000    | 200 000      | 623ms      |
| 1000 x 1000    | 500 000      | 724ms      |

The most time consuming part is the generation of the mines. Generating the squares in general takes the least amount of time. As the statistic shows, more mines means more time consumed in generating the field. As we can see, the 500 x 500 grid and 1000 x 1000 grid have almost identical time when the mines are equal to 2% of the entire board. 

### Solving algorithm time and success rate

I tested the solving algorithm with the different grid sizes and different amount of mines. The algorithm then tells me in what time it was able to (or not able to) solve the map. I ran 20 runs for each mine count and took the percentage that the algorithm was able to solve each stage.

I chose not to count the time it took for it solve each stage, as my algorithm is only able to solve simple grids and the time stands in the range of 40ms - 100ms. It is unable to solve difficult grids due to a missing functionality. 

The amount of mines amounts to 15% of the map.

[Image of graph](https://github.com/Antsax/MinesweeperSolver/blob/master/documentation/graph.jpg)

