package component;

import java.awt.Graphics;
import java.awt.geom.Point2D;

public class Wall extends GameObject {

	public Wall(Point2D.Double loc, double dir, Map aMap) {
		super(loc, dir, aMap);
	}

	@Override
	public void conflict(GameObject other) {
		// TODO Auto-generated method stub

	}

	@Override
	public void update() {
		// TODO Auto-generated method stub

	}

	@Override
	public void paint(Graphics g) {
		g.fillRect((int)getLocation().x, (int) getLocation().y, 5, 20);
		// TODO Auto-generated method stub

	}

}
