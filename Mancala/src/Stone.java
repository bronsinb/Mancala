import java.awt.Graphics2D;
import java.awt.geom.Ellipse2D;

/**
 * This class draws a stone
 * @author Bronsin Benyamin Pouran 
 */
public class Stone {
	private Ellipse2D stone;
	
	public Stone(double x, double y, double size) {
		stone = new Ellipse2D.Double(x, y, size, size);
	}
	
	public void draw(Graphics2D g) {
		g.fill(stone);
	}
}
