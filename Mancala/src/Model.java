/**
 * Tai Dao - 11/21/2017 4:00 PM - 5:45 PM
 */
import java.util.ArrayList;

import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

public class Model {
	private boolean playerATurn;
	private boolean firstTurnCompleted = false;
	private ArrayList<ModelPit> pList;
	private ArrayList<ModelPit> stateA;
	private ArrayList<ModelPit> stateB;
	
	private ModelPit aPitGoal;
	private ModelPit bPitGoal;
	private int currentPos;
	private int nextPosAfterCurrentPos;
	
	private GameFrame refToBronsinsFrame;
	
	/**
	 * Initializes each pit with a set number of stones. 
	 * 
	 * Inits goal pits.
	 * Inits listeners.
	 * Makes player A the first player by setting playerAturn to true.
	 * 
	 * @param startingStones
	 */
	Model(int startingStones, GameFrame gameFrame) {
		refToBronsinsFrame = gameFrame;
		
		pList = new ArrayList<ModelPit> ();
		stateA = new ArrayList<ModelPit> (12);
		stateB = new ArrayList<ModelPit> (12);
		for (int i = 0; i <= 11; i ++) {
			pList.add(new ModelPit(startingStones)); //Each Pit will be initialized with 3 or 4 stones and
			//added to an arrayList of Pits
		}
		aPitGoal = new ModelPit(0);
		bPitGoal = new ModelPit(0);
		playerATurn = true;
	}
	
	/**
	 * This saveState should be called at the start of the player's turn. Always save state b4 making a move.
	 */
	public void saveStateA() {

		int[] data = new int[12];
		
		for (int i = 0; i < pList.size(); i++) {
			pList.get(i).returnStones();
			//System.out.println("RETURNING SIZE Plist: " + pList.size());
			stateA.add(pList.get(i));
			//System.out.println("RETURNING SIZE A: " + stateA.size());
			System.out.println("RETURNING STONES: " + stateA.get(i).returnStones());
		}
	}
	public void saveStateB() {
		stateB.clear();
		for (int i = 0; i < pList.size(); i++) {
			stateB.add(pList.get(i));
		}
	}
	
	/**
	 * If the undo button is pressed this is called.
	 */
	public void undo() {
		System.out.println("Undo Button Pressed.");
		if (!firstTurnCompleted) {
			System.out.println("There is nothing to undo!");
		}
		else {
			System.out.println("Else undo!");
			pList.clear();
			System.out.println("RETURNING SIZE Plist: " + pList.size());
			if(!playerATurn) {
				changePlayerTurns();
				System.out.println("Else undo A!");
				for (int i = 0; i < stateA.size(); i++) {
					pList.add(stateA.get(i)); 
					//System.out.println("RETURNING SIZE Plist: " + pList.size());
					System.out.println("Number of Stones for stateA[" + i + "]" + pList.get(i).returnStones());
				}			
				updateBronsinModel();
			}
			else {
				changePlayerTurns();
				System.out.println("Else undo B!");
				for (int i = 0; i < stateB.size(); i++) {
					pList.add(stateB.get(i)); 
					System.out.println("RETURNING SIZE Plist: " + pList.size());
					//System.out.println("Number of Stones for[" + i + "]" + pList.get(i).returnStones());
					System.out.println("Number of Stones for stateB[" + i + "]" + pList.get(i).returnStones());
				}
				updateBronsinModel();
			}
		}
	}
	
	/**
	 * pitPos is retrieved from a pit listener. when a pit is pressed it calls this function with it's position as the parameter
	 * 
	 * This function checks if the selected pit is valid for the current player's turn.
	 * 
	 * @param pitPos
	 */
	public void checkCorrectPitSelected (int pitPos) {
		//First check if the pit is empty to begin with....
		if (checkIfSelectedPitIsEmpty(pitPos)) {
			System.out.println("That pit is empty! Select a pit with stones on your side!");
		}
		else { //Then check if the pit belongs to the player... 
			if (playerAsTurn() && pitPos <= 5) {
				saveStateA(); // saves current state before moving
				moveStonesPlayerA(pitPos);
				updateBronsinModel();
				firstTurnCompleted = true;
			}
			else if (!playerAsTurn() && pitPos > 5) {
				saveStateB();
				moveStonesPlayerB(pitPos);
				updateBronsinModel();
			}
			else {
				System.out.println("Wrong Pit Selected. Please choose your own Pit.");
			}
			
		}
	}
	
	/**
	 * This function checks if the selected pit doesn't have any stones. If it doesn't have any stones
	 * it returns true.
	 * 
	 * returns true // if no stones
	 */
	public boolean checkIfSelectedPitIsEmpty (int pitPos) {
		int stonesInSelectedPit = pList.get(pitPos).returnStones();
		if (stonesInSelectedPit == 0) {
			return true;
		}
		else {
			return false;
		}
	}
	
	/**
	 * This function returns true if it is player A's turn. It returns false if it isn't player A's turn.
	 * 
	 * @return playerATurn
	 */
	public boolean playerAsTurn() {
		return playerATurn;
	}
	
	/**
	 * This function changes the current player's turn to the other player's turn.
	 *
	 */
	public void changePlayerTurns() {
		playerATurn = !playerATurn;
	}
	
	/**
	 * Gets number of stones in current pit and empties it.
	 * 
	 * Move stones according to player A's POV.
	 */
	public void moveStonesPlayerA(int pitPos) {
		currentPos = pitPos;
		int stonesInHand = pList.get(currentPos).returnStones();
		pList.get(currentPos).emptyPit(); //Pick up stones in hand... So pit is empty...
		
		while (stonesInHand != 0) { // While we still have stones in our hand...
			
			currentPos++; //move to next pit
			
			if (currentPos >= 12) {
				currentPos = 0;
			} // wrap around // reset position

			// Capture Condition... LAST STONE... Next pit is still on your side.... AND IT's EMPTY
			if (stonesInHand == 1 && currentPos <= 5 && pList.get(currentPos).isEmpty()) { 
					System.out.println("Capture Condition is MET!");
					capture(currentPos);
					stonesInHand--;
			} 
			else { // else continue on and don't capture

				if (currentPos != 6) { //if current position has not already advanced past own goal
					pList.get(currentPos).addStone(1); // continue  by adding 1 stone to that pit
					stonesInHand--; //now we have 1 less stone in our hand
				}
				else { // AKA Case (currentPos == 6)... We have advanced passed our own goal...
					System.out.println("Player A SCORED A GOAL!!!");
					aPitGoal.addStone(1); //since we have advanced pass our goal we should put a stone in our goal
					stonesInHand--;
					
					if (stonesInHand == 0 ) { //if we have no stones left after placing a stone in our own goal... free turn for us
						System.out.println("Free turn Player A. Go Again...");
						changePlayerTurns(); // gets negated at the exit of the while loop therefore still player A's turn
						break;
					}
					else { // else continue on adding stones to pits until stones in hand is empty
						pList.get(currentPos).addStone(1);
						stonesInHand--;
					}
				}
				
			}
		} // end of while loop. stones in hand is now empty
		
		// Check if the game has ended. If it hasn't change turns.
		if (endGameConditionMet()) {
			whoIsWinner();
		}
		else {
			changePlayerTurns();
			if (playerATurn) {
				System.out.println();
				System.out.println("Now Player A's Turn");
			}
			else {
				System.out.println();
				System.out.println("Now Player B's Turn");
			}
		}
	}
	
	/**
	 * Gets number of stones in current pit and empties it.
	 * 
	 * Move stones according to player B's POV.
	 */
	
	public void moveStonesPlayerB(int pitPos) {
		currentPos = pitPos;
		int stonesInHand = pList.get(currentPos).returnStones();
		pList.get(currentPos).emptyPit(); //Pick up stones in hand... So pit is empty...
		
		while (stonesInHand != 0) { // While we still have stones in our hand...

			currentPos++; //move to next pit
			if (currentPos >= 12) {
				currentPos = 0;
				System.out.println("Wrap around condition met");
			} // wrap around // reset position

			// Capture Condition... LAST STONE... Next pit is still on your side.... AND IT's EMPTY
			if (stonesInHand == 1 && currentPos > 5 && pList.get(currentPos).isEmpty()) { 
				System.out.println("Capture Condition is MET!");
				capture(currentPos);
				stonesInHand--;
			} 
			else { // else continue on and don't capture

				if (currentPos != 0) { //if current position has not already advanced past own goal
					pList.get(currentPos).addStone(1); // continue  by adding 1 stone to that pit
					stonesInHand--; //now we have 1 less stone in our hand
				}
				else { // AKA Case (currentPos == 0)... We have advanced passed our own goal...
					System.out.println("Player B SCORED A GOAL!!!");
					bPitGoal.addStone(1); //since we have advanced pass our goal we should put a stone in our goal
					stonesInHand--;
					
					if (stonesInHand == 0 ) { //if we have no stones left after placing a stone in our own goal... free turn for us
						System.out.println("Free turn for Player B Again...");
						changePlayerTurns(); // gets negated at the exit of the while loop therefore still player A's turn
						break;
					}
					else { // else continue on adding stones to pits until stones in hand is empty
						pList.get(currentPos).addStone(1);
						stonesInHand--;
					}
				}
			}
		} // end of while loop. stones in hand is now empty
		
		// Check if the game has ended. If it hasn't change turns.
		if (endGameConditionMet()) {
			whoIsWinner();
		}
		else {
			changePlayerTurns();
			if (playerATurn) {
				System.out.println();
				System.out.println("Now Player A's Turn");
			}
			else {
				System.out.println();
				System.out.println("Now Player B's Turn");
			}
		}
	}
	
	/**
	 * Checks if a the game has ended.
	 * 
	 * @return true // if the game has ended.
	 */
	public boolean endGameConditionMet () {
		int numberOfEmptyPitsA =0;
		int numberOfEmptyPitsB =0;
		
		//Check # of Empty Pits in Player A's Side
		for (int i = 0; i <=5 ; i++) {
			if (pList.get(i).isEmpty()) {
				numberOfEmptyPitsA++;
			}
		}
		
		//Check # of Empty Pits in Player B's Side
		for (int i = 6; i <=11 ; i++) {
			if (pList.get(i).isEmpty()) {
				numberOfEmptyPitsB++;
			}
		}
	
		if (numberOfEmptyPitsA == 6 || numberOfEmptyPitsB == 6) {
			return true;
		}
		else {
			return false;
		}
	}
	
	/**
	 * This function determines who won after the end game condition was met
	 */
	public void whoIsWinner() {
		int totalStonesA = aPitGoal.returnStones();
		int totalStonesB = bPitGoal.returnStones();
		for (int i = 0; i <=5 ; i++) {
			aPitGoal.addStone(pList.get(i).returnStones());
		}
		for (int i = 6; i <=11 ; i++) {
			bPitGoal.addStone(pList.get(i).returnStones());
		}
		if (totalStonesA > totalStonesB) {
			System.out.println("Player A has Won!");
		}
		else if (totalStonesB > totalStonesA) {
			System.out.println("Player B has Won!");
		}
		else {
			System.out.println("DRAW!");
		}
	}
	/**
	 * This function returns the pit to capture given the current pit
	 * @param capturer
	 * @return indexToCapture
	 */
	public int getIndexToCapture(int capturer) { 
		switch (capturer) {
		case 0: return 11;
		case 1: return 10;
		case 2: return 9;
		case 3: return 8;
		case 4: return 7;
		case 5: return 6;
		case 6: return 5;
		case 7:	return 4;
		case 8:	return 3;
		case 9: return 2;
		case 10: return 1;
		case 11: return 0;
		default: return 9999;
		}
	}
	
	/**
	 * This function contains the logic for stealing stones from the enemy pit.
	 */
	public void capture(int capturingPit) {
		int enemyPit = getIndexToCapture(capturingPit);
		int stonesInEnemyPit = pList.get(enemyPit).returnStones();
		pList.get(enemyPit).emptyPit();
		pList.get(capturingPit).addStone(1); // our single capturing stone
		pList.get(capturingPit).addStone(stonesInEnemyPit); //Steal stones
	}
	/**
	 * Updates Bronsin's Model
	 */
	public void updateBronsinModel () {
		refToBronsinsFrame.updateViews();
	}
	public int getStonesFromModelIndex (int index){
		int stonesInIndex = pList.get(index).returnStones();
		return stonesInIndex;
	}
	public int getStonesForPlayerA() {
		return aPitGoal.returnStones();
	}
	public int getStonesForPlayerB() {
		return bPitGoal.returnStones();
	}
}
