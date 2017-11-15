import java.awt.*;
import java.awt.geom.*;

import javax.swing.*;

public class GameFrame extends JFrame{
	private GamePit[] pits;
	private PlayerPit[] playerPits;
	private JPanel grid;
	private Rectangle2D.Double gameBoard;
	
	public GameFrame(int stoneAmount) {
		this.setBackground(Color.WHITE);
		this.setLayout(new BorderLayout(15, 0));
		pits = new GamePit[12];
		playerPits = new PlayerPit[2];
		grid = new JPanel(new GridLayout(4, 6));
		gameBoard = new Rectangle2D.Double(0, 0, 1000, 400);
		
		setSize(1000, 400);
		
		String A = "A";
		String B = "B";
		
		playerPits[0] = new PlayerPit(A, Color.WHITE);
		playerPits[1] = new PlayerPit(B, Color.white);
		
		for(int i = 6; i > 0; i--) {
			JLabel text = new JLabel("B" + (i), SwingConstants.CENTER);
			grid.add(text);
		}
	
		for(int i = 0; i < pits.length; i++) {
			if(i < 6) {
				pits[i] = new GamePit(stoneAmount, "A", 50, Color.WHITE);
			}
			else {
				pits[i] = new GamePit(stoneAmount, "B", 50, Color.WHITE);
			}
		}
		
		for(int i = 0; i < pits.length; i++) {
			grid.add(pits[i]);
		}
		
		for(int i = 0; i < 6; i++) {
			JLabel text = new JLabel("A" + (i + 1), SwingConstants.CENTER);
			grid.add(text);
		}
		
		JPanel west = new JPanel();
		west.setPreferredSize(new Dimension(200, 300));
		west.add(playerPits[0]);
		
		JPanel east = new JPanel();
		east.setPreferredSize(new Dimension(200, 300));
		east.add(playerPits[1]);
		
		JPanel top = new JPanel();
		top.setLayout(new BorderLayout());
		JLabel playerA = new JLabel("Player A", SwingConstants.CENTER);
		playerA.setPreferredSize(new Dimension(200, 40));
		JLabel playerB = new JLabel("Player B", SwingConstants.CENTER);
		playerB.setPreferredSize(new Dimension(200, 40));
		top.add(playerA, BorderLayout.LINE_START);
		top.add(new JLabel("MANCALA", SwingConstants.CENTER), BorderLayout.CENTER);
		top.add(playerB, BorderLayout.LINE_END);
		
		JPanel bottom = new JPanel(new FlowLayout());
		JButton undo = new JButton();
		undo.setText("UNDO");
		
		JButton next = new JButton();
		next.setText("NEXT");
		
		bottom.add(undo);
		bottom.add(next);
		
		this.add(top, BorderLayout.PAGE_START);
		this.add(west, BorderLayout.LINE_START);
		this.add(grid, BorderLayout.CENTER);
		this.add(east, BorderLayout.LINE_END);
		this.add(bottom, BorderLayout.PAGE_END);
		
		this.setResizable(false);
	}
	
	public static void main(String[] args) {		
		GameFrame comp = new GameFrame(4);
		
		comp.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		comp.setVisible(true);
	}
}
