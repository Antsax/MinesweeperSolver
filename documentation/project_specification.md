# Project Specification

This program uses the game [Minesweeper]("https://en.wikipedia.org/wiki/Minesweeper_(video_game)") as 
its basis. The idea is to create an algorithm that is able to solve the game with near perfection using logic. In some scenarios, Minesweeper can not be solved with pure logic, as there are situations where probability comes to play and a perfect estimation can not be provided. 

## Data structures

In order for the program to work, it needs to know its surrounding. More specifially, a revealed 
square's neighbouring revealed squares. This is most easily achieved using an array, since we can trace
an understandable, two-dimensional board with it. An ArrayList can also be used for the same purposes. We may also want to utilize a queue to keep track of the already checked neighbouring squares. 

## Algorithms

An algorithm that is able to correctly deduce which squares do not have mines and which ones do is the key for this program to work. We need to define an algorithm, that is able to read the number on each neighbouring square and then reveal mine-free squares that have still not been revealed. So, in general:

1. At the start, click on a random location on the map. If it's a mine, restart. Otherwise, continue
2. Iterate through the neighbouring squares of the revealed one and determine, which squares are mine-free, and reveal them.
3. Select the next square to process. The process of choosing the next square is yet to be solved. 
4. Go back to phase 2

I am not aware of any algorithms that are specifically suitable for this kind of problem or made directly for Minesweeper

## Complexities

### Time

The most time consuming parts of the algorithm is creating an array of the neighbouring squares of each square and iterating through them. If we were examining an *n x m* board, then the algorithm would have to loop through every single square. In this case the time complexity would be *O(nm)*. Taking into account of creating an array for each square, the total time complexity would total *O(nm + nm)* = **_O(nm)_**

### Space

As mentioned before, we would need to create an array for each square to detremine its neigbouring squares. Because of this remark, the space complexity would equal the size of the board, so **_O(nm)_**

## Sources

[Minesweeper AI Guide](https://luckytoilet.wordpress.com/2012/12/23/2125/)

[Thesis by David Becerra](https://dash.harvard.edu/bitstream/handle/1/14398552/BECERRA-SENIORTHESIS-2015.pdf?sequence=1)

[Post by Magnus Hoff](https://magnushoff.com/minesweeper/)
