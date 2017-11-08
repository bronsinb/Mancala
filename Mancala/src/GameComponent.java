import java.awt.*;
import java.awt.geom.*;

import javax.swing.*;

public class GameComponent extends JFrame{
	private GamePit[] pits;
	private PlayerPit[] playerPits;
	private JPanel grid;
	private Rectangle2D.Double gameBoard;
	
	public GameComponent(int stoneAmount) {
		pits = new GamePit[12];
		playerPits = new PlayerPit[2];
		grid = new JPanel();
		grid.setSize(500, 400);
		grid.setLayout(new GridLayout(2, 6));
		gameBoard = new Rectangle2D.Double(0, 0, 1000, 400);
		
		setSize(1000, 400);
		
		String A = "A";
		String B = "B";
		
		playerPits[0] = new PlayerPit(A);
		playerPits[1] = new PlayerPit(B);
	
		for(int i = 0; i < pits.length; i++) {
			if(i < 6) {
				pits[i] = new GamePit(stoneAmount, "A");
			}
			else {
				pits[i] = new GamePit(stoneAmount, "B");
			}
		}
		
		for(int i = 0; i < pits.length; i++) {
			grid.add(pits[i]);
		}
		
		this.add(grid, BorderLayout.CENTER);
		this.add(playerPits[0], BorderLayout.WEST);
		this.add(playerPits[1], BorderLayout.EAST);
		
		//this.setResizable(false);
	}
	
	public static void main(String[] args) {		
		GameComponent comp = new GameComponent(4);
		
		comp.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		comp.setVisible(true);
	}
}
