import java.awt.*;
import java.awt.geom.*;
import java.util.ArrayList;

import javax.swing.*;

/**
 * Class that Draws player pits and manages player pit
 * @author Bronsin Benyamin Pouran
 */
public class PlayerPit extends JComponent{
	private ArrayList<Stone> stones;
	private Rectangle2D.Double s;
	
	public PlayerPit(String player) {
		stones = new ArrayList<>();
		s = new Rectangle2D.Double(0, 0, 100, 200);
		
		this.setLayout(new GridLayout(1, 200));
	}

	public void addStone(Stone s) {
		// TODO Auto-generated method stub
		stones.add(s);
	}
	
	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D g2 = (Graphics2D) g;
		g2.draw(s);
		for(Stone s: stones) {
			this.add(s);
		}
	}

	public void clear() {
		// TODO Auto-generated method stub
		stones.clear();
	}

}
