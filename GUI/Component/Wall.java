package component;

import java.awt.*;
import java.awt.geom.Point2D;

public class Wall extends GameObject {

	private static double WALL_WIDTH = 5;

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
		g.setStroke(new BasicStroke((float) WALL_WIDTH));
		Point2D.Double loc = getLocation();
		double dir = getDirection();
		g.drawLine((int)loc.x,(int) loc.y,(int) (loc.x+Game.CELL_SIDE*Math.cos(dir)),(int) (loc.y+Game.CELL_SIDE*Math.sin(dir)));
		g.setStroke(s);
	}

}
