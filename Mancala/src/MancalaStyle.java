/**
 * File containing interface of styling (Strategy pattern)
 */
import java.awt.Color;

import javax.swing.*;

public interface MancalaStyle {
	/**
	 * Styles game pits
	 * @param b Game pit being styled
	 */
	public void styleGamePits(JButton b);
	/**
	 * Styles player pits
	 * @param b player pit being styled
	 */
	public void stylePlayerPits(JButton b);
	/**
	 * Styles different JPanels of board
	 * @param panel JPanel being styled
	 */
	public void stylePanel(JPanel panel);
	/**
	 * Styles text of component
	 * @param t Component that is stylized
	 */
	public void styleText(JComponent t);
	/**
	 * Styles JButtons
	 * @param b JButton being styled
	 */
	public void styleButtons(JButton b);
	/**
	 * Styles stones
	 * @return Color
	 */
	public Color styleStones();
}
