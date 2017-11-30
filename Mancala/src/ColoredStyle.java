import java.awt.Color;
import java.awt.Graphics2D;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JPanel;

public class ColoredStyle implements MancalaStyle{

	@Override
	public void styleGamePits(JButton b) {
		// TODO Auto-generated method stub
		b.setBackground(new Color(182, 155, 76));
	}

	@Override
	public void stylePlayerPits(JButton b) {
		// TODO Auto-generated method stub
		b.setBackground(new Color(182, 155, 76));
		
	}

	@Override
	public void stylePanel(JPanel panel) {
		// TODO Auto-generated method stub
		panel.setBackground(new Color(130, 82, 1));
	}

	@Override
	public void styleText(JComponent t) {
		// TODO Auto-generated method stub
		t.setForeground(Color.white);
	}

	@Override
	public void styleButtons(JButton b) {
		// TODO Auto-generated method stub
		b.setBackground(new Color(182, 155, 76));
	}

	@Override
	public Color styleStones() {
		// TODO Auto-generated method stub
		return Color.DARK_GRAY;
	}


	
}
