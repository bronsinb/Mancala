import java.awt.*;
import java.awt.geom.Rectangle2D;
import java.util.ArrayList;

import javax.swing.*;

/**
 * Class that draws Game pits
 * @author Bronsin Benyamin Pouran
 *
 */
public class GamePit extends JComponent{
	private ArrayList<Stone> stones;
	private Rectangle2D.Double s;

	public GamePit(int stoneAmount, String player) {
		stones = new ArrayList<>();
		s = new Rectangle2D.Double(0, 0, 100, 100);
		this.setLayout(new GridLayout(3,3));
		for(int i = 0; i < stoneAmount; i++) {
			stones.add(new Stone(10));
		}
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

	public void addStone(Stone s) {
		// TODO Auto-generated method stub
		stones.add(s);
	}

	public void clear() {
		// TODO Auto-generated method stub
		stones.clear();
	}

}
