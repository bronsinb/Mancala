/**
 * File containing class of Player Pit
 */
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
	private Color stoneColor;
	private String player;
	
	/**
	 * Constructs a Player pit object
	 * @param player Name of player
	 * @param stoneColor Color of stones
	 */
	public PlayerPit(String player, Color stoneColor) {
		stones = new ArrayList<>();
		this.player = player;
		this.stoneColor = stoneColor;
		this.setPreferredSize(new Dimension(100, 200));
		this.setEnabled(false);
		this.setLayout(new GridLayout(15, 5));
	}

	@Override
	public void addStone(int s) {
		// TODO Auto-generated method stub
		for(int i = 0; i < s; i++) {
			Stone add = new Stone(10, stoneColor);
			stones.add(add);
			this.add(add);
		}
	}
	
	@Override
	public int clear() {
		// TODO Auto-generated method stub
		int num = stones.size();
		stones.clear();
		this.removeAll();
		return num;
	}

	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D g2 = (Graphics2D) g;
		for(Stone i: stones) {
			this.add(i);
		}
	}
	
	@Override
	public ArrayList<Stone> getStones() {
		// TODO Auto-generated method stub
		return stones;
	}
	
	/**
	 * Get name of player
	 * @return player Name of player
	 */
	public String getName() {
		return player;
	}
}
