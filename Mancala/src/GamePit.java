import java.awt.*;
import java.awt.geom.Rectangle2D;
import java.util.ArrayList;

import javax.swing.*;

/**
 * Class that draws Game pits
 * @author Bronsin Benyamin Pouran
 *
 */
public class GamePit extends JButton implements Pit{
	private ArrayList<Stone> stones;
	private Rectangle2D.Double s;
	private Color stoneColor;

	public GamePit(int stoneAmount, String player, int size, Color stoneColor) {
		stones = new ArrayList<>();
		this.stoneColor = stoneColor;
		this.setSize(size, size);
		this.setLayout(new GridLayout(0, 5));
		for(int i = 0; i < stoneAmount; i++) {
			this.add(new Stone(10, stoneColor));
		}
	}
	
	@Override
	public void addStone(int s) {
		// TODO Auto-generated method stub
		for(int i = 0; i < s; i++) {
			stones.add(new Stone(10, stoneColor));
		}
	}

	@Override
	public void clear() {
		// TODO Auto-generated method stub
		stones.clear();
	}

	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D g2 = (Graphics2D) g;	
		for(Stone s: stones) {
			this.add(s);
		}
	}

	@Override
	public ArrayList<Stone> getStones() {
		// TODO Auto-generated method stub
		return stones;
	}
}
