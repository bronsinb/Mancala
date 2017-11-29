import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.*;

import javax.swing.*;

public class GameFrame extends JFrame{
	private Model model = new Model(4); //Creating Model here.... Temporarily set to 4 stones....
	
	private GamePit[] pits;
	private PlayerPit[] playerPits;
	private JPanel grid;
	private Rectangle2D.Double gameBoard;
	
	public GameFrame(int stoneAmount, MancalaStyle style) {
		this.setLayout(new BorderLayout());
		pits = new GamePit[12];
		playerPits = new PlayerPit[2];
		grid = new JPanel(new GridLayout(4, 6));
		style.stylePanel(grid);
		gameBoard = new Rectangle2D.Double(0, 0, 1000, 400);
		
		setSize(1000, 400);
		
		String A = "A";
		String B = "B";
		
		playerPits[0] = new PlayerPit(A);
		style.stylePlayerPits(playerPits[0]);
		playerPits[1] = new PlayerPit(B);
		style.stylePlayerPits(playerPits[1]);
		
		for(int i = 6; i > 0; i--) {
			JLabel text = new JLabel("B" + (i), SwingConstants.CENTER);
			grid.add(text);
		}
	
		for(int i = 0; i < pits.length; i++) {
			if(i < 6) {
				int b = i; // clone of i for anonymous class
				
				pits[i] = new GamePit(stoneAmount, "A", 50);
				style.styleGamePits(pits[i]);
				
				// Add action listeners to every single pit button.
				pits[i].addActionListener(new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent e) {
						// TODO Auto-generated method stub
						model.checkCorrectPitSelected(b);
					}
				});
				
			}
			else {
				pits[i] = new GamePit(stoneAmount, "B", 50);
				style.styleGamePits(pits[i]);
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
		style.stylePanel(west);
		west.setPreferredSize(new Dimension(200, 300));
		west.add(playerPits[0]);
		
		JPanel east = new JPanel();
		style.stylePanel(east);
		east.setPreferredSize(new Dimension(200, 300));
		east.add(playerPits[1]);
		
		JPanel top = new JPanel();
		style.stylePanel(top);
		top.setLayout(new BorderLayout());
		JLabel playerA = new JLabel("Player A", SwingConstants.CENTER);
		playerA.setPreferredSize(new Dimension(200, 40));
		JLabel playerB = new JLabel("Player B", SwingConstants.CENTER);
		playerB.setPreferredSize(new Dimension(200, 40));
		top.add(playerA, BorderLayout.LINE_START);
		top.add(new JLabel("MANCALA", SwingConstants.CENTER), BorderLayout.CENTER);
		top.add(playerB, BorderLayout.LINE_END);
		
		JPanel bottom = new JPanel(new FlowLayout());
		style.stylePanel(bottom);
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
		GameFrame comp = new GameFrame(4, new BlackWhiteStyle());
		
		comp.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		comp.setVisible(true);
	}
}
