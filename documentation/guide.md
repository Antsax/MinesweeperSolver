# User's manual

## Testing

Tests can be run in the terminal with the command 

`mvn test`

The test coverage file can be generated with

`mvn test jacoco:report`

You can inspect the report on your browser. The file can be found in *target/site/jacoco/index.html*

## Execution

You can run the program with the command 

*mvn compile exec:java -Dexec.mainClass=game.Main*

## Generating JAR

With the command 

`mvn package`

you can generate a jar-file into the *index* repository. The name of the file is *MinesweeperSolver-1.0-SNAPSHOT.jar*

With

`java -jar MinesweeperSolver-1.0-SNAPSHOT.jar`

you will be able to run the program.

## Generating JavaDoc

You can generate the JavaDoc file with the command

`mvn javadoc:javadoc`

## While running

The program will be run on the terminal. Once there, it will ask for an input of width, height and number of mines. After this it will ask if you want to solve the game yourself or if you would like for the algorithm to solve it.

If you choose the algorithm (Y), then the algorithm will solve the game.

If you choose no (N), you can play the game yourself. Give the coordinate as x,y and to flag add 'f' in the front. E.g f4,5. When you hit a mine you lose. When you have solved the map you win.