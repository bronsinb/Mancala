import java.awt.*;
import java.awt.geom.*;
import java.util.ArrayList;

import javax.swing.*;

/**
 * Class that Draws player pits and manages player pit
 * @author Bronsin Benyamin Pouran
 */
public class PlayerPit extends JButton implements Pit{
	private ArrayList<Stone> stones;
	private Rectangle2D.Double s;
	
	public PlayerPit(String player) {
		stones = new ArrayList<>();
		this.setPreferredSize(new Dimension(100, 200));
		//this.setSize(sizeW, sizeH);
		this.setEnabled(false);
		this.setLayout(new GridLayout(15, 5));
	}

	@Override
	public void addStone(int s) {
		// TODO Auto-generated method stub
		for(int i = 0; i < s; i++) {
			stones.add(new Stone(10));
		}
		for(Stone i: stones) {
			this.add(i);
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
		for(Stone i: stones) {
			this.add(i);
		}
	}
}
