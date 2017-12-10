/**
 * File containing tester
 */
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

/**
 * Class that tests Mancala board
 * @author Bronsin Benyamin Pouran
 */
public class MancalaTest {
	
	/**
	 * Main method that tests Mancala board
	 * @param args Not Used
	 */
	public static void main(String[] args) {	
		
		JFrame start = new JFrame();
		
		JPanel gameInfo = new JPanel();
		gameInfo.setLayout(new GridLayout(10, 0));
		JTextField playerNameOne = new JTextField("Player 1 Name: ");
		playerNameOne.setEditable(false);
		JTextField playerNameInput = new JTextField();
		gameInfo.add(playerNameOne);
		gameInfo.add(playerNameInput);
		
		JTextField playerNameTwo = new JTextField("Player 2 Name: ");
		playerNameTwo.setEditable(false);
		JTextField playerNameTwoInput = new JTextField();
		gameInfo.add(playerNameTwo);
		gameInfo.add(playerNameTwoInput);
		
		JTextField stones = new JTextField("3 or 4 Stones: ");
		stones.setEditable(false);
		JTextField stonesInput = new JTextField();
		gameInfo.add(stones);
		gameInfo.add(stonesInput);
		
	    JButton colorButton = new JButton("Wood");
	    colorButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				if(!playerNameTwoInput.getText().equals("") && !playerNameInput.getText().equals("") && !stonesInput.getText().equals("")){
					if("34".contains(stonesInput.getText())) {
					GameFrame comp = new GameFrame(Integer.parseInt(stonesInput.getText()), new ColoredStyle(), playerNameInput.getText(), playerNameTwoInput.getText());
					start.dispose();
					
					comp.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
					comp.setVisible(true);
					}
					else {
						JOptionPane.showMessageDialog(null, "Stone count is not 3 or 4!");
					}
				}
				else {
					JOptionPane.showMessageDialog(null, "Either name or stone fields are empty!");
				}
			}
	    	
	    });
	    
	    JButton bwButton = new JButton("Black & White");
	    bwButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				if(!playerNameTwoInput.getText().equals("") && !playerNameInput.getText().equals("") && !stonesInput.getText().equals("")){
					if("34".contains(stonesInput.getText())) {
					GameFrame comp = new GameFrame(Integer.parseInt(stonesInput.getText()), new BlackWhiteStyle(), playerNameInput.getText(), playerNameTwoInput.getText());
					start.dispose();
					
					comp.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
					comp.setVisible(true);
					}
					else {
						JOptionPane.showMessageDialog(null, "Stone count is not 3 or 4!");
					}
				}
				else {
					JOptionPane.showMessageDialog(null, "Either name or stone fields are empty!");
				}
			}
	    	
	    });
	    
	    JPanel styleStart = new JPanel();
	    styleStart.setLayout(new GridLayout(0, 2));
	    styleStart.add(colorButton);
	    styleStart.add(bwButton);
	    
	    gameInfo.add(styleStart);
	    
		
		start.add(gameInfo);
		
		start.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		start.pack();
		start.setVisible(true);
	}
}
