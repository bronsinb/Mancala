import java.awt.Color;
import java.util.ArrayList;

import javax.swing.*;

public interface MancalaStyle {
	public void styleGamePits(JButton b);
	public void stylePlayerPits(JButton b);
	public void stylePanel(JPanel panel);
	public void styleText(JComponent t);
	public void styleButtons(JButton b);
	public Color styleStones();
}
