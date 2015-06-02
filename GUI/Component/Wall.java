package Component;

import java.awt.*;
import java.awt.geom.Line2D;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;
import java.awt.geom.Rectangle2D.Double;

public class Wall extends GameObject {

	private static double WALL_WIDTH = 5;
	private Line2D.Double line;

	public Wall(Point2D.Double loc, double dir, Map aMap) {
		super(loc, dir, aMap);
		line = new Line2D.Double((int)(loc.x - 0.5 * Game.CELL_SIDE*Math.cos(dir)),(int) (loc.y - 0.5 * Game.CELL_SIDE*Math.sin(dir)),(int) (loc.x+ 0.5* Game.CELL_SIDE*Math.cos(dir)),(int) (loc.y+ 0.5 *Game.CELL_SIDE*Math.sin(dir)));
	}

	@Override
	public void conflict(GameObject other) {}

	@Override
	public void update() {}
	public Rectangle2D.Double getRect(){
		Rectangle2D.Double rect = new Rectangle2D.Double(this.getLocation().x, this.getLocation().y, WALL_WIDTH, Game.CELL_SIDE);
		return rect;
	}
	@Override
	public void paint(Graphics2D g) {
		Stroke s = g.getStroke();
		g.setStroke(new BasicStroke((float) WALL_WIDTH));
		Point2D.Double loc = getLocation();
		double dir = getDirection();
		g.drawLine((int)(loc.x),(int) (loc.y),(int) (loc.x+ Game.CELL_SIDE*Math.cos(dir)),(int) (loc.y+Game.CELL_SIDE*Math.sin(dir)));
		g.setStroke(s);
	}

	@Override
	public Shape getBounds() {
		// TODO Auto-generated method stub
		return null;
	}

}
