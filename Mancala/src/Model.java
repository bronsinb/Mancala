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
	
	private ChangeEvent event;
	private ArrayList<ChangeListener> listeners;
	
	/**
	 * Initializes each pit with a set number of stones. 
	 * 
	 * Inits goal pits.
	 * Inits listeners.
	 * Makes player A the first player by setting playerAturn to true.
	 * 
	 * @param startingStones
	 */
	Model(int startingStones) {
		pList = new ArrayList<ModelPit> ();
		savedPList = new ArrayList<ModelPit> ();
		for (int i = 0; i <= 11; i ++) {
			pList.add(new ModelPit(startingStones)); //Each Pit will be initialized with 3 or 4 stones and
			//added to an arrayList of Pits
		}
		aPitGoal = new ModelPit(0);
		bPitGoal = new ModelPit(0);
		listeners = new ArrayList<ChangeListener> ();
		playerATurn = true;
		freeTurn = false;
		event = new ChangeEvent(this); // this refers to this Model object...
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
		updateViews();
	}

	
	/**
	 * When called updates all views based on the data structure ArrayList<ModelPit> pList
	 */
	public void updateViews() {
		for (ChangeListener cL : listeners) { //notify all listeners that the state has changed.
			cL.stateChanged(event); //model object is passed to the stateChanged functions.
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
				saveState(); // saves current state before moving
				System.out.println("Correct Pit Selected");
				moveStonesPlayerA(pitPos);

			}
			else if (!playerAsTurn() && pitPos > 5) {
				System.out.println("Correct Pit Selected");
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
		int currentPos = pitPos;
		int stonesInHand = pList.get(currentPos).emptyPit(); //Pick up stones in hand... So pit is empty...
		
		while (stonesInHand != 0) { // While we still have stones in our hand...
			int nextPosAfterCurrent = currentPos++;
			
			// Capture Condition... LAST STONE... Next pit is still on your side.... AND IT's EMPTY
			if (stonesInHand == 1 && nextPosAfterCurrent <= 5 && pList.get(currentPos).isEmpty()) { 
					// TODO perform capture method here....
					stonesInHand--;
			} 
			else { // else continue on and don't capture
		
				currentPos++; //move to next pit
				if (currentPos != 6) { //if current position is not past player A's goal aka not positiot 6 (b/c pos 6 is player B's pit)
					pList.get(currentPos).addStone(1); // continue adding 1 stone to that pit
					stonesInHand--; //now we have 1 less stone in our hand
				}
				else if (currentPos == 11) { // In this case we must skip player B's goal and go straight back to pit 0
					currentPos = 0; //reset the current position to 0
					pList.get(currentPos).addStone(1);
					stonesInHand--;
				}
				else { // Case: We have advanced pass Player A's Side...
					
					aPitGoal.addStone(1); //since we have advanced pass we should put a stone in Player A's goal
					stonesInHand--;
			
					if (stonesInHand == 0 ) { //if the number of stones left in hand is 0 and we just added to A's goal.. free turn for A
						//TODO Perform Free Turn Method Here.
							// Still Player A's Turn.... so exit this while loop
						break;
					}
					else { // else continue on adding stones to pits until stones in hand is empty
						pList.get(currentPos).addStone(1);
						stonesInHand--;
					}
				}
				
				//Now there are no more stones in hand... check if either side's pits are empty. Then change turns.
				//Check for win condition
				
				if (endGameConditionMet()) {
					//Function for  GAME OVER!!! GG
				}
				
				//Check if player still has free turn	
				if (freeTurn) {
					//don't change PlayerTurns
				} else {
					changePlayerTurns(); //change PlayerTurns
				}
			}
		}
	}
	
	/**
	 * Gets number of stones in current pit and empties it.
	 * 
	 * Move stones according to player B's POV.
	 */
	
	public void moveStonesPlayerB(int pitPos) {
		int currentPos = pitPos;
		int stonesInHand = pList.get(pitPos).emptyPit();
		
		while (stonesInHand != 0) {
	
		}
	}
	
	/**
	 * Checks if a win condition has been met yet
	 * 
	 * @return ???
	 */
	public boolean endGameConditionMet () {
		
		return false;
	}

}
