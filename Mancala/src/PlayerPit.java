import java.awt.Graphics2D;
import java.awt.geom.RectangularShape;
import java.util.ArrayList;

/**
 * Class that Draws player pits and manages player pit
 * @author Bronsin Benyamin Pouran
 */
public class PlayerPit implements Pit{
	private ArrayList<Stone> stones;
	private RectangularShape s;
	
	public PlayerPit(RectangularShape s) {
		stones = new ArrayList<>();
		this.s = s;
	}
	
	@Override
	public void draw(Graphics2D g) {
		// TODO Auto-generated method stub
		g.draw(s);
		for(Stone s: stones) {
			s.draw(g);
		}
	}

	@Override
	public void addStone(Stone s) {
		// TODO Auto-generated method stub
		stones.add(s);
	}

	@Override
	public void clear() {
		// TODO Auto-generated method stub
		stones.clear();
	}

}
