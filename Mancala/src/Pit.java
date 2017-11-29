import java.awt.Graphics2D;
import java.util.ArrayList;

/**
 * Interface for pits
 * @author Bronsin Benyamin
 *
 */
public interface Pit{
	void addStone(int s);
	void clear();
	ArrayList<Stone> getStones();
}
