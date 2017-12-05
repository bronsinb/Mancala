/**
 * File containing a Strategy pattern style
 */
import java.awt.Color;

import javax.swing.*;

/**
 * Class that styles board into Black and white
 * @author Bronsin Benyamin Pouran
 *
 */
public class BlackWhiteStyle implements MancalaStyle{

	@Override
	public void styleGamePits(JButton b) {
		// TODO Auto-generated method stub
		b.setBackground(Color.black);
	}

	@Override
	public void stylePlayerPits(JButton b) {
		// TODO Auto-generated method stub
		b.setBackground(Color.black);
	}

	@Override
	public void stylePanel(JPanel panel) {
		// TODO Auto-generated method stub
		panel.setBackground(Color.white);
	}

	@Override
	public void styleText(JComponent t) {
		// TODO Auto-generated method stub
		t.setForeground(Color.black);
	}

	@Override
	public void styleButtons(JButton b) {
		// TODO Auto-generated method stub
		b.setBackground(Color.white);
	}

	@Override
	public Color styleStones() {
		// TODO Auto-generated method stub
		return Color.white;
	}

}
