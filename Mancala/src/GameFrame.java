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
	
	public GameFrame(int stoneAmount, MancalaStyle style, String a, String b) {
		this.setLayout(new BorderLayout());
		pits = new GamePit[12];
		playerPits = new PlayerPit[2];
		grid = new JPanel(new GridLayout(4, 6));
		style.stylePanel(grid);
		
		setSize(1000, 400);
		
		playerPits[1] = new PlayerPit(a, style.styleStones());
		playerPits[1].setText("B");
		style.stylePlayerPits(playerPits[1]);
		style.styleText(playerPits[1]);
		playerPits[0] = new PlayerPit(b, style.styleStones());
		playerPits[0].setText("A");
		style.stylePlayerPits(playerPits[0]);
		style.styleText(playerPits[0]);
		
		for(int i = 6; i > 0; i--) {
			JLabel text = new JLabel("B" + (i), SwingConstants.CENTER);
			text.setFont(new Font("SansSerif", Font.BOLD, 17));
			style.styleText(text);
			grid.add(text);
		}
	
		for(int i = 0; i < pits.length; i++) {
			if(i < 6) {

        int c = i; // clone of i for anonymous class
				pits[i] = new GamePit(stoneAmount, 0, 50, style.styleStones());
				pits[i].addActionListener(new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent arg0) {
						// TODO Auto-generated method stub
						GamePit pit = (GamePit) arg0.getSource();
						int num = pit.clear();
						if (num != 0) {
							int j = -1;
							for (int i = pit.getIndex(); i < num + pit.getIndex(); i++) {
								if(i < 11) {
									pits[i + 1].addStone(1);
								}
								else {
									pits[j + 1].addStone(1);
									j++;
								}
							}
						}
						repaint();
						revalidate();
					}
				});

				style.styleGamePits(pits[i]);
				
				// Add action listeners to every single pit button.
				pits[i].addActionListener(new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent e) {
						// TODO Auto-generated method stub
						model.checkCorrectPitSelected(c);
					}
				});
				
			}
			else {

  			int c = i; // clone of i for anonymous class
				pits[i] = new GamePit(stoneAmount, 0, 50, style.styleStones());
				pits[i].addActionListener(new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent arg0) {
						// TODO Auto-generated method stub
						GamePit pit = (GamePit) arg0.getSource();
						int num = pit.clear();
						if (num != 0) {
							int j = -1;
							for (int i = pit.getIndex(); i < num + pit.getIndex(); i++) {
								if(i < 11) {
									pits[i + 1].addStone(1);
								}
								else {
									pits[j + 1].addStone(1);
									j++;
								}
							}
						}
						repaint();
						revalidate();
					}
				});

				style.styleGamePits(pits[i]);
				
				// Add action listeners to every single pit button again for part B.
				pits[i].addActionListener(new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent e) {
						// TODO Auto-generated method stub
						model.checkCorrectPitSelected(c);
					}
				});
			}
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

		JButton next = new JButton();		
		style.styleButtons(next);
		style.styleText(next);
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
}
