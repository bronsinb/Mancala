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
	private int index;

	public GamePit(int stoneAmount, int index, int size, Color stoneColor) {
		stones = new ArrayList<>();
		this.stoneColor = stoneColor;
		this.setSize(size, size);
		this.setLayout(new GridLayout(0, 5));
		this.index = index;
		for(int i = 0; i < stoneAmount; i++) {
			Stone add = new Stone(10, stoneColor);
			stones.add(add);
		}
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
	public void setStone(int s) {
		stones.clear();
		for (int i = 0; i < s; i++) {
			Stone stoneToAdd = new Stone(10, stoneColor);
			stones.add(stoneToAdd);
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
		for(Stone s: stones) {
			this.add(s);
		}
	}

	@Override
	public ArrayList<Stone> getStones() {
		// TODO Auto-generated method stub
		return stones;
	}

	public int getIndex() {
		return index;
	}

	public void setIndex(int index) {
		this.index = index;
	}
}
