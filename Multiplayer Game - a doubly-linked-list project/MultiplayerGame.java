package hw3;

public class MultiplayerGame {
	
	
	private GameEntity[] index; 
	private GameEntity turnTracker;
	
	public MultiplayerGame(int n) {
		//creates a new multiplayer game object
		index = new GameEntity[n];
		for (int i = 0; i < n; i++) {
			index[i] = new GamePlayer(null, null, i);
		}
		index[0].prev = index[n-1];
		index[n-1].next = index[0];
		for (int i = 1; i < n-1; i++) {
				index[i-1].next = index[i];
				index[i+1].prev = index[i];
				index[i].prev = index[i-1];
				index[i].next = index[i+1];
		}
	}
	
	public GameEntity getIndex()[] {
		//returns the GameEntity at the specified index
		//call it like: getIndex()[x], where x is an integer
		return index;
	}
	
	public int size() {
		//returns the size of the multiplayer game
		//the only thing that contributes to size is game pieces, not players
		int count = 0;
		GameEntity current = index[0];
		while (current.next != index[0]) {
			if (current.next.isGamePlayer()) {
				current = current.next;
			}
			else {
				current = current.next;
				count++;
			}
		}
		return count;
	}
	
	public void addGamePiece(int playerId, String name, int strength) {
		//adds a game piece to the specified player's collection of pieces
		if(!(pExists(playerId))) {
			throw new IllegalArgumentException("addGamePiece: no such player");
		}
		GameEntity current = index[playerId].next;
		while (!(current.isGamePlayer())) {
			if(current.getName().equals(name)) {
				throw new IllegalArgumentException("addGamePiece: duplicate entity");
			}
			current = current.next;
		}
		if (index[playerId].next.isGamePlayer()) {
			GamePiece gp = new GamePiece(index[playerId], index[playerId].next, name, strength);
			gp.next = index[playerId].next;
			gp.prev = index[playerId];
			index[playerId].next = gp;		
			gp.next.prev = gp;
		}
		else {
			GamePiece gp = new GamePiece(index[playerId], index[playerId].next, name, strength);
			gp.next = index[playerId].next;
			gp.prev = index[playerId];
			index[playerId].next = gp;		
			gp.next.prev = gp;
		}
	}
	
	public boolean pExists(int id) {
		//helper function that checks if a player exists or not
		GameEntity current = index[0];
		GameEntity player = index[id];
		while(current.next != index[0]) {
			if (current.getName().equals(player.getName())) {
				return true;
			}
			current = current.next;
		}
		if (current.getName() == player.getName()) {
			return true;
		}
		if (current.next == index[0] && current.getName().equals(player.getName())) {
			return true;
		}
		return false;
	}
	
	public boolean exists(int id, String name) {
		//helper function that checks if the specified player has the gamepiece specified by name
		GameEntity current = index[id].next;
		while(!(current.isGamePlayer())) {
			if(current.getName().equals(name)) {
				return true;
			}
			else {
				current = current.next;
			}
		}
		return false;
	}
	
	public void removeGamePiece(int playerId, String name) {
		//removes a GamePiece from the doubly-linked list
		if(!(pExists(playerId))) {
			throw new IllegalArgumentException("removeGamePiece: no such player");
		}
		if(!(exists(playerId, name))) {
			throw new IllegalArgumentException("removeGamePiece: entity does not exist");
		}
		GameEntity current = index[playerId].next;
		while (!(current.isGamePlayer())) {
			if(current.getName().equals(name)) {
				current.prev.next = current.next;
				current.next.prev = current.prev;
				current = current.next;
			}
			else {
				current = current.next;
			}
		}
	}

	public boolean hasGamePiece(String name) {
		//checks if any player in the game has specified gamepiece
		GameEntity current = index[0].next;
		while (current != index[0]) {
			if (current.isGamePlayer()) {
				current = current.next;
			}
			else if (current.getName().equals(name)){
				return true;
			}
			else {
				current = current.next;
			}
		}
		return false;
	}
	
	public void removeAllGamePieces(int playerId) {
		//removes all pieces from specified player
		GameEntity current = index[playerId].next;
		if(!(pExists(playerId))) {
			throw new IllegalArgumentException("removeAllGamePieces: no such player");
		}
		while (!(current.isGamePlayer())) {
			String name = current.getName();
			this.removeGamePiece(playerId, name);
			current = current.next;
		}
	}
	
	public void increaseStrength(int playerId, int n) {
		//increases strength for all GamePieces of the specified player
		if(!(pExists(playerId))) {
			throw new IllegalArgumentException("increaseStrength: no such player");
		}
		GameEntity current = index[playerId].next;
		while (!(current.isGamePlayer())) {
			GamePiece c = (GamePiece) current;
			c.updateStrength(n);
			current = current.next;
		}
	}
	
	public void removePlayer(int playerId) {
		//removes a player and all of their pieces
		if(!(pExists(playerId))) {
			throw new IllegalArgumentException("removePlayer: no such player");
		}
		this.removeAllGamePieces(playerId);
		index[playerId].prev.next = index[playerId].next;
		index[playerId].next.prev = index[playerId].prev;
	}
	
	public void swapPieces(int playerId1, int playerId2) {
		//swaps all pieces between specified players
		int p1 = playerId1;
		int p2 = playerId2;
		if ((!(pExists(p1))) || (!(pExists(p2)))) {
			throw new IllegalArgumentException("swap: no such player");
		}
		int n1 = numPieces(p1);
		GameEntity current1 = index[p1].next;
		GameEntity current2 = index[p2];
		while(!(current1.isGamePlayer())) {
			GamePiece c = (GamePiece) current1;
			addGamePiece(p2, c.getName(), c.getStrength());
			current1 = current1.next;
		}
		removeAllGamePieces(p1);
		for (int i = 0; i < n1+1; i++) {
			current2 = current2.next;
		}
		//System.out.println(current2.toString());
		while(!(current2.isGamePlayer())) {
			GamePiece e = (GamePiece) current2;
			addGamePiece(p1, e.getName(), e.getStrength());
			current2 = current2.next;
			removeGamePiece(p2, e.getName());
		}
	}
	
	public int numPieces(int playerId) {
		//returns the number of pieces the specified player has
		int num = 0;
		GameEntity current = index[playerId].next;
		while (!(current.isGamePlayer())) {
			num++;
			current = current.next;
		}
		return num;
	}
	
	public String toString() {
		//returns a String representation of MultiPlayer Game.
		//example: [Player0; Player1; GamePiece: GreenPiece strength 44]
		StringBuilder sb = new StringBuilder();
		GameEntity current = index[0];
		sb.append("[");
		while (current.next != index[0]) {
			sb.append(current.toString());
			sb.append("; ");
			current = current.next;
		}
		sb.append(current.toString());
		sb.append("]");
		return sb.toString();
	}
	
	public String toStringReverse() {
		//reverse order of string representaion of Multiplayer Game
		StringBuilder sb = new StringBuilder();
		GameEntity current = null;
		GameEntity start = null; //DLL is cyclic -- so let's keep track of where we start
		//Find the last un-removed player
		for (int i = index.length - 1; i >= 0 ; i--) {
			if (index[i] != null) { 
				// or however you check that the player has been removed
				current = index[i];
				start = index[i];
				break;
			}
		}
		//Find that player's last piece
		while (!current.next.isGamePlayer()) {
			current = current.next;
			start = start.next;
		}
		sb.append("[");
		sb.append(current.toString());
		current = current.prev;
		while (current != start) { //check that we haven't cycled back to the beginning
			sb.append("; ");
			sb.append(current.toString());
			current = current.prev;
		}
		sb.append("]");
		return sb.toString();
	}
	
	//-----------processing a turn-----------
	
	public void initializeTurnTracker() {
		//initializes turntracker to point at the first player
		turnTracker = index[0];
	}
	
	public void nextPlayer() {
		//moves turntracker over to the next PLAYER, NOT piece
		GameEntity current = turnTracker;
		while (!(current.next.isGamePlayer())) {
			current = current.next;
		}
		turnTracker = current.next;
	}
	
	public void nextEntity() {
		//moves turntracker over to the next entity
		//can be either a player or piece
		turnTracker = turnTracker.next;
	}
	
	public void prevPlayer() {
		//moves turntracker over to previous PLAYER, NOT piece
		GameEntity current = turnTracker;
		while(!(current.prev.isGamePlayer())) {
			current = current.prev;
		}
		turnTracker = current.prev;
	}
	
	public GameEntity getTurnTracker() {
		//returns whatever entity turnTracker is pointing to
		return turnTracker;
	}
	
	public void currentEntityToString() {
		//gives string representation of whatever the turntracker is currently pointing at
		turnTracker.toString();
	}
	
	public static void main(String[] args) {
		/*
		MultiplayerGame g0 = new MultiplayerGame(3);
		g0.addGamePiece(1, "Thorn", 10);
		g0.addGamePiece(1, "Gjallarhorn", 15);
		g0.addGamePiece(1, "Ace", 20);
		g0.addGamePiece(1, "Last Word", 17);
		g0.addGamePiece(2, "Atheon", 20);
		g0.addGamePiece(2, "Oryx", 50);
		g0.addGamePiece(2, "Taniks", 40);
		g0.addGamePiece(2, "Savathun", 60);
		System.out.println(g0.toString());
		g0.swapPieces(1, 2);
		System.out.println(g0.toString());
		*/
		//System.out.println(g0.pExists(2));
		/*
		MultiplayerGame g1 = new MultiplayerGame(3);
		g1.addGamePiece(2, "BluePiece", 5);
		g1.addGamePiece(1, "PurplePiece", 2);
		g1.addGamePiece(1, "OrangePiece", 17);
		g1.removeAllGamePieces(1);
		System.out.println(g1.size());
		//g1.removePlayer(1);
		System.out.println(g1.toString());
		//System.out.println(g1.hasGamePiece("BluePiece"));
		//System.out.println(index[0].getName());
		//g1.increaseStrength(2, 5);
		//System.out.println(index[2].next);
		//System.out.println(g1.size());
		 */
		/*Expected output: 
		[Player0; Player1; GamePiece: OrangePiece strength: 17; GamePiece: PurplePiece 
		strength: 2; Player2; GamePiece: BluePiece strength: 5]
		*/
		
		/*
		MultiplayerGame g2 = new MultiplayerGame(3);
		g2.addGamePiece(2, "BluePiece", 5);
		g2.addGamePiece(1, "PurplePiece", 2);
		g2.addGamePiece(1, "OrangePiece", 17);
		
		System.out.println(g2.toString());
		//g2.removePlayer(1);
		System.out.println(g2.numPieces(1));
		g2.swapPieces(1,2);
		System.out.println(g2.toString());
		//g2.increaseStrength(2,3);
		//System.out.println(g2.toString());
		//g2.swapPieces(1, 2);
		//System.out.println(g2.toString());
		//System.out.println(g2.toStringReverse());
		 */
		/*Expected output:
		[Player0; Player2; GamePiece: BluePiece strength: 8]
		*/
		
		
		MultiplayerGame g3 = new MultiplayerGame(1);
		//g3.addGamePiece(2, "BluePiece", 5);
		g3.addGamePiece(0, "PurplePiece", 2);
		g3.addGamePiece(0, "OrangePiece", 17);
		g3.addGamePiece(0, "YellowPiece", 2);
		g3.addGamePiece(0, "BlackPiece", 17);
		//g3.addGamePiece(1,  "PurplePiece", 2); throws duplicate entity
		//g3.removeGamePiece(1, "PurplePiece");
		System.out.println(g3.toString());
		//g3.removePlayer(1);
		//g3.swapPieces(1, 2);
		//g3.removeAllGamePieces(2);
		//g3.removeAllGamePieces(2);
		//g3.removePlayer(1);
		System.out.println(g3.toString());
		//System.out.println(g3.exists(1, "PurplePiece"));
		/*
		Expected output:
		[Player0; Player1; GamePiece: OrangePiece strength: 17; Player2; 
		GamePiece: BluePiece strength: 5]
		*/
	}

}
