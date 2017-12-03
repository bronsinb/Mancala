/**
 * Tai Dao - 11/21/2017 4:00 PM - 5:45 PM
 */
import java.util.ArrayList;

import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

public class Model {
	private boolean playerATurn;
	private boolean freeTurn;
	
	private ArrayList<ModelPit> pList;
	private ArrayList<ModelPit> savedPList;
	
	private ModelPit aPitGoal;
	private ModelPit bPitGoal;
	private int currentPos;
	
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
		savedPList = new ArrayList<ModelPit> ();
		for (int i = 0; i <= 11; i ++) {
			pList.add(new ModelPit(startingStones)); //Each Pit will be initialized with 3 or 4 stones and
			//added to an arrayList of Pits
		}
		aPitGoal = new ModelPit(0);
		bPitGoal = new ModelPit(0);
		playerATurn = true;
		freeTurn = false;
	}
	
	/**
	 * This saveState should be called at the start of the player's turn. Always save state b4 making a move.
	 */
	public void saveState() {
		savedPList = pList;
	}
	
	/**
	 * If the undo button is pressed this is called.
	 */
	public void undo() {
		pList = savedPList;
		updateBronsinsModel();
	}

	
	/**
	 * When called updates all views based on the data structure ArrayList<ModelPit> pList
	 */
	public void updateBronsinsModel() {
		
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
				saveState(); // saves current state before moving
				System.out.println("Correct Pit Selected A");
				moveStonesPlayerA(pitPos);
				updateBronsinModel();

			}
			else if (!playerAsTurn() && pitPos > 5) {
				saveState();
				System.out.println("Correct Pit Selected B");
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
		
		System.out.println("The current pos is: " + currentPos);
		System.out.println("Stones Left In My Hand: " + stonesInHand);
		
		while (stonesInHand != 0) { // While we still have stones in our hand...
			
			currentPos++; //move to next pit
			////System.out.println("DEBUG: CurrentPos: " + currentPos);
			if (currentPos >= 12) {
				currentPos = 0;
				System.out.println("Wrap around condition met");
			} // wrap around // reset position
			////System.out.println("DEBUG: CurrentPosReset: " + currentPos);
			
			/**
			 * Test Code
			 */
			System.out.println("The current pos is: " + currentPos);
			System.out.println("Stones Left In My Hand: " + stonesInHand);
			/**
			 * End Test Code
			 */

			int nextPosAfterCurrentPos = currentPos + 1;
			System.out.println("CurrentPositionToAdd1Stone is: " + currentPos);
			// Capture Condition... LAST STONE... Next pit is still on your side.... AND IT's EMPTY
			if (stonesInHand == 1 && nextPosAfterCurrentPos <= 5 && pList.get(currentPos).isEmpty()) { 
					// TODO perform capture method here....
					System.out.println("Capture Condition Occured!");
					stonesInHand--;
			} 
			else { // else continue on and don't capture

				if (currentPos != 6) { //if current position has not already advanced past own goal
					System.out.println("CurrentPositionToAdd1Stone is: " + currentPos);
					pList.get(currentPos).addStone(1); // continue  by adding 1 stone to that pit
					stonesInHand--; //now we have 1 less stone in our hand
					System.out.println("Remaining Stones In Hand: " + stonesInHand);
				}
				else { // AKA Case (currentPos == 6)... We have advanced passed our own goal...
					System.out.println("SCORE A GOAL!!!" + currentPos);
					aPitGoal.addStone(1); //since we have advanced pass our goal we should put a stone in our goal
					stonesInHand--;
					System.out.println("Remaining Stones In Hand: " + stonesInHand);
					
					if (stonesInHand == 0 ) { //if we have no stones left after placing a stone in our own goal... free turn for us
						//TODO Perform Free Turn Method Here.
							System.out.println("Free turn for Player A Again...");
							changePlayerTurns(); // gets negated at the exit of the while loop therefore still player A's turn
						break;
					}
					else { // else continue on adding stones to pits until stones in hand is empty
						pList.get(currentPos).addStone(1);
						stonesInHand--;
						System.out.println("Remaining Stones In Hand: " + stonesInHand);
					}
				}
				
			}
		} // end of while loop. stones in hand is now empty
		
		// Check if the game has ended. If it hasn't change turns.
		if (endGameConditionMet()) {
			System.out.println("Game Over. Player X has won!");
			//TODO function to count stones in each goal to determine who won.
		}
		else {
			changePlayerTurns();
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
			int nextPosAfterCurrent = currentPos + 1;
			
			// Capture Condition... LAST STONE... Next pit is still on your side.... AND IT's EMPTY
			if (stonesInHand == 1 && nextPosAfterCurrent > 5 && (nextPosAfterCurrent != 12) && pList.get(currentPos).isEmpty()) { 
					// TODO perform capture method here....
					stonesInHand--;
			} 
			else { // else continue on and don't capture
		
				currentPos++; //move to next pit
				System.out.println("DEBUG: CurrentPos: " + currentPos);
				if (currentPos <= 12) {
					currentPos = 0;
					System.out.println("DEBUG: CurrentPosInBetween: " + currentPos);
					} // wrap around // reset position
				System.out.println("DEBUG: CurrentPosReset: " + currentPos);
				
				if (currentPos != 0) { //if current position has not already advanced past own goal
					pList.get(currentPos).addStone(1); // continue adding 1 stone to that pit
					stonesInHand--; //now we have 1 less stone in our hand
				}
				else { // AKA Case (currentPos == 0)... We have advanced pass our own goal...
					bPitGoal.addStone(1); //since we have advanced pass our goal we should put a stone in our goal
					stonesInHand--;
					if (stonesInHand == 0 ) { //if we have no stones left after placing a stone in our own goal... free turn for us
						//TODO Perform Free Turn Method Here.
							System.out.println("Free turn for Player B Again...");
							changePlayerTurns(); // gets negated at the exit of the while loop therefore still player B's turn
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
			System.out.println("Game Over. Player X has won!");
			//TODO function to count stones in each goal to determine who won.
		}
		else {
			changePlayerTurns();
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
	 * Updates Bronsin's Model
	 */
	public void updateBronsinModel () {
		refToBronsinsFrame.updateViews();
	}
	
	public int getStonesFromModelIndex (int index){
		int stonesInIndex = pList.get(index).returnStones();
		//System.out.println("Index#:" + index + " Stones In It = " + stonesInIndex);
		return stonesInIndex;
	}
	public int getStonesForPlayerA() {
		return aPitGoal.returnStones();
	}
	public int getStonesForPlayerB() {
		return bPitGoal.returnStones();
	}
}
