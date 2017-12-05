/**
 * File containing pit interface
 */
import java.util.ArrayList;

/**
 * Interface for pits
 * @author Bronsin Benyamin Pouran
 *
 */
public interface Pit{
	/**
	 * Adds stones to pit
	 * @param s Amount being added
	 */
	void addStone(int s);
	/**
	 * Clears pit and returns amount cleared
	 * @return Amount cleared
	 */
	int clear();
	/**
	 * Gets arraylist that has the stones
	 * @return ArrayList of Stones
	 */
	ArrayList<Stone> getStones();
}
