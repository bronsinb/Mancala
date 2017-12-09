/**
 * File containing class for painting Game Board
 */
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.*;

import javax.swing.*;

/**
 * Frame class that paints gameboard
 * @author Bronsin Benyamin Pouran
 */
public class GameFrame extends JFrame{
	private Model model;
	
	private GamePit[] pits;
	private PlayerPit[] playerPits;
	private JPanel grid;
	private JTextArea name;
	private String A;
	private String B;
	
	/**
	 * Constructs Frame that paints Mancala gameboard
	 * @param stoneAmount Amount of initial stones
	 * @param style Style of Mancala
	 * @param a Player A's name
	 * @param b Player B's name
	 */
	public GameFrame(int stoneAmount, MancalaStyle style, String a, String b) {
		A = a;
		B = b;
		model = new Model(stoneAmount, this);  //Tai's Model
		name = new JTextArea(" "+ a + "'s Turn ");
		name.setFont(new Font("SansSerif", Font.BOLD, 17));
		name.setEditable(false);
		this.setLayout(new BorderLayout());
		pits = new GamePit[12];
		playerPits = new PlayerPit[2];
		grid = new JPanel(new GridLayout(4, 6));
		style.stylePanel(grid);
		
		setSize(1000, 400);
		
		playerPits[1] = new PlayerPit(a, style.styleStones());
		playerPits[1].setText("A");
		style.stylePlayerPits(playerPits[1]);
		style.styleText(playerPits[1]);
		playerPits[0] = new PlayerPit(b, style.styleStones());
		playerPits[0].setText("B");
		style.stylePlayerPits(playerPits[0]);
		style.styleText(playerPits[0]);
		
		for(int i = 6; i > 0; i--) {
			JLabel text = new JLabel("B" + (i), SwingConstants.CENTER);
			text.setFont(new Font("SansSerif", Font.BOLD, 17));
			style.styleText(text);
			grid.add(text);
		}
	
		for(int i = 0; i < pits.length; i++) {
			int c = i; // clone of i for anonymous class
			pits[i] = new GamePit(stoneAmount, 0, 50, style.styleStones());
			style.styleGamePits(pits[i]);
			
			// Add action listeners to every single pit button. When pressed calls 
			//model.checkCorrectedPitSelected passing the index of that pit button.
			pits[i].addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					model.checkCorrectPitSelected(c);
				}
			});
		}
		
		int index = 11;
		
		for(int i = pits.length - 1; i >= pits.length - 6; i--) {
			pits[i].setIndex(index);
			grid.add(pits[i]);
			index--;
		}
		
		for(int i = 0; i < pits.length - 6; i++) {
			pits[i].setIndex(i);
			grid.add(pits[i]);
		}
		
		for(int i = 0; i < 6; i++) {
			JLabel text = new JLabel("A" + (i + 1), SwingConstants.CENTER);
			text.setFont(new Font("SansSerif", Font.BOLD, 17));
			style.styleText(text);
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
		JLabel playerA = new JLabel(playerPits[0].getName(), SwingConstants.CENTER);
		playerA.setFont(new Font("SansSerif", Font.PLAIN, 18));
		style.styleText(playerA);
		playerA.setPreferredSize(new Dimension(200, 40));
		JLabel playerB = new JLabel(playerPits[1].getName(), SwingConstants.CENTER);
		playerB.setFont(new Font("SansSerif", Font.PLAIN, 18));
		style.styleText(playerB);
		playerB.setPreferredSize(new Dimension(200, 40));
		top.add(playerA, BorderLayout.LINE_START);
		JLabel mancalaLogo = new JLabel("MANCALA", SwingConstants.CENTER);
		mancalaLogo.setFont(new Font("SansSerif", Font.BOLD, 22));
		top.add(mancalaLogo, BorderLayout.CENTER);
		style.styleText(mancalaLogo);
		top.add(playerB, BorderLayout.LINE_END);
		
		JPanel bottom = new JPanel(new FlowLayout());
		style.stylePanel(bottom);
		JButton undo = new JButton();
		style.styleButtons(undo);
		style.styleText(undo);
		undo.setText("UNDO");
		undo.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				model.undo();
			}
		});
		
		bottom.add(undo);
		bottom.add(name);
		
		this.add(top, BorderLayout.PAGE_START);
		this.add(west, BorderLayout.LINE_START);
		this.add(grid, BorderLayout.CENTER);
		this.add(east, BorderLayout.LINE_END);
		this.add(bottom, BorderLayout.PAGE_END);
		
		this.setResizable(false);
	}
	
	/**
	 * Tai's update Views function. Actually updates Bronsin's model which updates the view.
	 */
	public void updateViews() {
		for (int i = 0; i < pits.length; i++) {
			pits[i].clear();
			pits[i].addStone(model.getStonesFromModelIndex(i));
		}
		playerPits[0].clear();
		playerPits[0].addStone(model.getStonesForPlayerB());
		playerPits[1].clear();
		playerPits[1].addStone(model.getStonesForPlayerA());
		repaint();
		revalidate();
	}
	/**
	 * Tai's update turns function. Updates Bronsin's name TextArea to display who's turn it is.
	 */
	public void updateViewTurns() {
		if(model.playerAsTurn()) {
			name.setText(" " + A + "'s Turn ");
		}
		else if(!model.playerAsTurn()){
			name.setText(" " + B + "'s Turn ");
		}
	}
}
