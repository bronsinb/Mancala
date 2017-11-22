/**
 * Tai Dao - 11/21/2017 4:00 PM - 5:45 PM
 */
import java.util.ArrayList;

import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

public class Model {
	private boolean playerATurn;
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
		saveState();
		if (playerAsTurn() && pitPos <= 5) {
			System.out.println("Correct Pit Selected");
			moveStonesPlayerA(pitPos);
		}
		else if (!playerAsTurn() && pitPos > 5) {
			System.out.println("Correct Pit Selected");
		}
		else {
			System.out.println("Wrong Pit Selected. Please try again.");
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
	public void setPlayerAsTurn() {
		playerATurn = !playerATurn;
	}
	
	/**
	 * Gets number of stones in current pit and empties it.
	 * 
	 * Move stones according to player A's POV.
	 */
	public void moveStonesPlayerA(int pitPos) {
		int currentPos = pitPos;
		int stonesInHand = pList.get(pitPos).emptyPit();
		
		while (stonesInHand != 0) {
			currentPos++; //move to next pit
			if (currentPos != 6) { //if current position is not the player B's position...
				pList.get(currentPos).addStone(1); //add 1 stone to that pit
				stonesInHand--; //now we have 1 less stone in our hand
			}
			else {
				aPitGoal.addStone(1); //if current pos is in player B's pit then first... add stone to player A's goal
				stonesInHand--;
				
				if (stonesInHand == 0 ) { //if the number of stones left in hand is 0 and we just added to a goal.. free turn
					//Do Free Turn here.
					break; //Exit while loop
				}
				else { // else continue on adding stones to pit until stones in hand is empty
					pList.get(currentPos).addStone(1);
					stonesInHand--;
				}
			}
			
			if (winConditionMet()) {
				
			}
			else {
				playerATurn = false;
			}
		}
	}
	
	/**
	 * Gets number of stones in current pit and empties it.
	 * 
	 * Move stones according to player B's POV.
	 */
	
	public void moveStonesPlayerB(int pitPos) {
		
	}
	
	/**
	 * Checks if a win condition has been met yet
	 * 
	 * @return ???
	 */
	
	public boolean winConditionMet () {
		return false;
	}

}
