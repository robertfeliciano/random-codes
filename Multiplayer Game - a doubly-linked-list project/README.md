# Multiplayer Game - My First Doubly Linked List

For this assignment we had to implement a doubly linked list from scratch in Java. We had done similar things before by implementing singly linked lists from scratch, but this was the first assignment where we were expected to do it alone and with a meaning behind it: create a "multiplayer game".

A MultiplayerGame consists of GameEntities, which can be either GamePieces or GamePlayers. First, players are added to the Game. Then, Pieces with "strength" values are added to specific players. The following is an example of how the doubly linked list would be strucured:

-------> Player1 <-> BluePiece <-> PurplePiece <-> Player2 <-> YellowPiece <-> Player3 <-> Player4 <-------
(Imagine Player1 and Player4 are connected in the example, I couldn't figure out how to draw it on my keyboard).

### Editing the Game

The Game can be edited by the user. GamePlayers and GamePieces can be removed and added at any time. To see this in action, I have left some examples in the main method of MultiplayerGame.java that can be commented/uncommented. 

This whole program runs on the console of an IDE; there is no GUI involved for this project. All files were contributed to by myself only.
