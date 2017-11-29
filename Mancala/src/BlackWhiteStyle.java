import java.awt.Color;

import javax.swing.JButton;
import javax.swing.JPanel;

public class BlackWhiteStyle implements MancalaStyle{

	@Override
	public void styleGamePits(JButton b) {
		// TODO Auto-generated method stub
		b.setBackground(Color.white);
	}

	@Override
	public void stylePlayerPits(JButton b) {
		// TODO Auto-generated method stub
		b.setBackground(Color.white);
	}

	@Override
	public void stylePanel(JPanel panel) {
		// TODO Auto-generated method stub
		panel.setBackground(Color.BLACK);
	}

}
