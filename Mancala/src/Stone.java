import java.awt.*;
import java.awt.geom.Ellipse2D;

import javax.swing.*;

/**
 * This class draws a stone
 * @author Bronsin Benyamin Pouran 
 */
public class Stone extends JComponent{
	private Ellipse2D stone;
	
	public Stone(double size) {
		stone = new Ellipse2D.Double(3, 3, size, size);
	}
	
	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D g2 = (Graphics2D) g;
		g2.fill(stone);
	}
}
