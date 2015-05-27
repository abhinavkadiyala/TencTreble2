package component;

import java.awt.*;
import java.awt.geom.Point2D;

public class Wall extends GameObject {

	private static void WALL_WIDTH = 5;

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
	public void paint(Graphics2D g) {
		Stroke s = g.getStroke();
		g.setStroke(new BasicStroke(WALL_WIDTH));
		Point2D.Double loc = getLocation();
		double dir = getDirection();
		g.drawLine(loc.x, loc.y, loc.x+Math.cos(dir), loc.y+Math.sin(dir))
	}

}
