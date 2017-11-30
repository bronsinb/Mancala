import java.awt.*;
import java.awt.geom.Ellipse2D;

import javax.swing.*;

/**
 * This class draws a stone
 * @author Bronsin Benyamin Pouran 
 */
public class Stone extends JComponent{
	private Ellipse2D stone;
	private Color color;
	
	public Stone(double size, Color color) {
		stone = new Ellipse2D.Double(0, 0, size, size);
		this.color = color;
	}
	
	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D g2 = (Graphics2D) g;
		g2.setColor(color);
		g2.fill(stone);
	}
	
	public Ellipse2D getStone() {
		return stone;
	}
}
