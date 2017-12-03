import java.awt.Graphics2D;
import java.util.ArrayList;

/**
 * Interface for pits
 * @author Bronsin Benyamin
 *
 */
public interface Pit{
	void addStone(int s);
	void setStone(int s);
	int clear();
	ArrayList<Stone> getStones();
}
