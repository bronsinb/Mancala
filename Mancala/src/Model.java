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
		
		for (ModelPit p : pList) {
			p.addStone(startingStones);
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
	 * pitPos is retrieved from a pit listener. when a pit is pressed it calls this function with it's position as the parameter
	 * 
	 * @param pitPos
	 */
	public void play (int pitPos) {
		saveState();
		
		
	}
	
	public void updateViews() {
		for (ChangeListener cL : listeners) { //notify all listeners that the state has changed.
			cL.stateChanged(event); //model object is passed to the stateChanged functions.
		}
	}

}
