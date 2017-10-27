import java.awt.Graphics2D;
import java.awt.Shape;
import java.util.ArrayList;

/**
 * Class that draws Game pits
 * @author Bronsin Benyamin Pouran
 *
 */
public class GamePit implements Pit{
	private ArrayList<Stone> stones;
	private Shape s;

	public GamePit(int stoneAmount, Shape s) {
		stones = new ArrayList<>();
		this.s = s;
		for(int i = 0; i < stoneAmount; i++) {
			stones.add(new Stone(i * 2, 0, 3));
		}
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
