package hw3;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;


class MultiplayerGameTest {
	
	@Test
	void testHasPiece() {
		//tests the hasPiece function
		MultiplayerGame g1 = new MultiplayerGame(5);
		g1.addGamePiece(0, "Thorn", 50);
		g1.addGamePiece(1, "Hawkmoon", 35);
		g1.addGamePiece(2, "Atheon", 100);
		g1.addGamePiece(2, "Oryx", 150);
		assertTrue(g1.hasGamePiece("Oryx"));
		assertFalse(g1.hasGamePiece("Clovis"));
	}
	
	@Test
	void testSize() {
		//tests the size function
		MultiplayerGame g1 = new MultiplayerGame(5);
		g1.addGamePiece(0, "Thorn", 50);
		g1.addGamePiece(1, "Hawkmoon", 35);
		g1.addGamePiece(2, "Atheon", 100);
		g1.addGamePiece(2, "Oryx", 150);
		assertTrue(g1.size() == 4);
	}
	
	@Test
	void testRemovePiece() {
		//tests removePiece
		MultiplayerGame g1 = new MultiplayerGame(5);
		g1.addGamePiece(0, "Thorn", 50);
		g1.addGamePiece(1, "Hawkmoon", 35);
		g1.addGamePiece(2, "Atheon", 100);
		g1.addGamePiece(2, "Oryx", 150);
		g1.removeGamePiece(2, "Atheon");
		assertTrue(g1.size() == 3);
	}
	
	@Test
	void testRemovePlayer() {
		//tests removePlayer
		MultiplayerGame g1 = new MultiplayerGame(5);
		g1.addGamePiece(0, "Thorn", 50);
		g1.addGamePiece(1, "Hawkmoon", 35);
		g1.addGamePiece(2, "Atheon", 100);
		g1.addGamePiece(2, "Oryx", 150);
		g1.removePlayer(2);
		g1.removePlayer(1);
		assertTrue(g1.size() == 1);
	}
	
	@Test
	void testRemoveAllPieces() {
		//tests removeAllPieces
		MultiplayerGame g1 = new MultiplayerGame(5);
		g1.addGamePiece(0, "Thorn", 50);
		g1.addGamePiece(1, "Hawkmoon", 35);
		g1.addGamePiece(2, "Atheon", 100);
		g1.addGamePiece(2, "Oryx", 150);
		g1.removeAllGamePieces(1);
		assertTrue(g1.size() == 3);
	}
	
	@Test
	void testIncStrength() {
		//tests incrementStrength()
		MultiplayerGame g2 = new MultiplayerGame(1);
		g2.addGamePiece(0, "Thorn", 50);
		g2.increaseStrength(0, 25);
		String x = g2.toString();
		assertTrue(x.contains("75"));
		assertFalse(x.contains("50"));
	}
	
	@Test
	void testToString() {
		//tests the toString, incrementStrength, and removePlayer functions
		MultiplayerGame g3 = new MultiplayerGame(3);
		g3.addGamePiece(2, "BluePiece", 5);
		g3.addGamePiece(1, "PurplePiece", 2);
		g3.addGamePiece(1, "OrangePiece", 17);
		g3.removePlayer(1);
		g3.increaseStrength(2,3);
		assertEquals(g3.toString(), "[Player0; Player2; GamePiece: BluePiece strength: 8]");
	}
	
	@Test
	void testPrevTracker() {
		//this tests both initializeTurnTracker and prevPlayer
		MultiplayerGame g3 = new MultiplayerGame(3);
		g3.addGamePiece(2, "BluePiece", 5);
		g3.addGamePiece(1, "PurplePiece", 2);
		g3.addGamePiece(1, "OrangePiece", 17);
		g3.initializeTurnTracker();
		g3.prevPlayer();
		assertTrue(g3.getTurnTracker() == g3.getIndex()[2]);
	}
	
	@Test
	void testNextTracker() {
		//tests the nextPlayer() function for turnTracker
		MultiplayerGame g3 = new MultiplayerGame(3);
		g3.addGamePiece(2, "BluePiece", 5);
		g3.addGamePiece(1, "PurplePiece", 2);
		g3.addGamePiece(1, "OrangePiece", 17);
		g3.initializeTurnTracker();
		g3.nextPlayer();
		assertTrue(g3.getTurnTracker() == g3.getIndex()[1]);
	}
	
	@Test
	void testNextEntity() {
		//tests the nextEntity() function for turnTracker
		MultiplayerGame g3 = new MultiplayerGame(3);
		g3.addGamePiece(2, "BluePiece", 5);
		g3.addGamePiece(1, "PurplePiece", 2);
		g3.addGamePiece(1, "OrangePiece", 17);
		g3.initializeTurnTracker();
		g3.nextEntity();
		//assertTrue(g3.currentEntityToString().equals(g3.getIndex()[0].next.toString()));
		//the above line works with the following modifications to the MultiplayerGame.java
		//change "currentEntityToString()" return type from void to String
		//then just return turnTracker.tostring
	}
	
	@Test
	void testSwap() {
		//tests the swap function
		MultiplayerGame g0 = new MultiplayerGame(3);
		g0.addGamePiece(1, "Thorn", 10);
		g0.addGamePiece(1, "Gjallarhorn", 15);
		g0.addGamePiece(2, "Atheon", 20);
		g0.addGamePiece(2, "Oryx", 50);
		assertEquals(g0.toString(), "[Player0; Player1; GamePiece: Gjallarhorn strength: 15;"
				+ " GamePiece: Thorn strength: 10; Player2; GamePiece: Oryx strength: 50; GamePiece: Atheon strength: 20]");
		g0.swapPieces(1, 2);
		assertEquals(g0.toString(), "[Player0; Player1; GamePiece: Atheon strength: 20; GamePiece: Oryx strength: 50;"
				+ " Player2; GamePiece: Thorn strength: 10; GamePiece: Gjallarhorn strength: 15]");
	}
}
