import java.awt.Graphics2D;

/**
 * Interface for pits
 * @author Bronsin Benyamin
 *
 */
public interface Pit {
	void draw(Graphics2D g);
	void addStone(Stone s);
	void clear();
}
