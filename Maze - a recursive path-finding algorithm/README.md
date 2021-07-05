# Finding a Path in a User-Generated Maze

There are few things I enjoy programming as much as sorting algorithms, and this path-finding algorithm is one of them. 
This program allows a user to create a maze by:
1. Entering the number of rows the maze will have
2. Entering the number of columns the maze will have
3. Clicking boxes in the maze that represent the path the program can take.
    * The program always starts at the top-left box, labeled (0,0) and ends at the bottom-right box, labeled (number of columns - 1, numbers of rows - 1).

I worked on the files named Maze.java, MazeTest.java, and PairInt.java. GridColors.java and TwoDimGrid.java were provided by my professor as they construct the GUI and don't contribute to the algorithm.
The files were written by Elliot B. Koffman and Paul A.T. Wolfgang, who are the authors of my textbook you can find [here](https://bcs.wiley.com/he-bcs/Books?action=index&itemId=0471692646&itemTypeId=BKS&bcsId=2200).

To run the program, please download all the files and save them in the same directory. You may then run MazeTest.java in any IDE of your choice.

Currently, MazeTest.java will find the shortest path. You can change what the program does in main method by commenting/uncommenting specified lines. The program can do the following:
* Find any path in the maze. This would just be the first one it finds/
* Find *all* the possible paths in the maze.
* Find the *shortest* possible path in the maze.
