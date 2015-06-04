package component;


import java.awt.*;
import java.awt.geom.*;

public class Wall extends GameObject {

	private static double WALL_WIDTH = 2;

	public Wall(Point2D.Double loc, double dir, Map aMap) {
		super(loc, dir, aMap);
	}

	@Override
	public void conflict(GameObject other) {}

	@Override
	public void update() {}

	/*@Override
	public void paint(Graphics2D g) {
		Stroke s = g.getStroke();
		g.setStroke(new BasicStroke((float) WALL_WIDTH));
		Point2D.Double loc = getLocation();
		double dir = getDirection();
		g.drawLine((int)(loc.x),(int) (loc.y),(int) (loc.x+ Game.CELL_SIDE*Math.cos(dir)),(int) (loc.y+Game.CELL_SIDE*Math.sin(dir)));
		g.setStroke(s);
		g.fill(getBounds());
	}*/
	
	public Shape getBounds() {
		Point2D.Double loc = getLocation();
		double dir = getDirection();
		return new Rectangle2D.Double(
			loc.x-WALL_WIDTH/2*Math.sin(dir)-WALL_WIDTH/2*Math.cos(dir),
			loc.y-WALL_WIDTH/2*Math.cos(dir)-WALL_WIDTH/2*Math.sin(dir),
			WALL_WIDTH/2*Math.sin(dir)+(WALL_WIDTH/2+Game.CELL_SIDE)*Math.cos(dir),
			WALL_WIDTH/2*Math.cos(dir)+(WALL_WIDTH/2+Game.CELL_SIDE)*Math.sin(dir));
	}
}
