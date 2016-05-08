package game.component;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Shape;
import java.awt.geom.Area;
import java.awt.geom.Point2D;

import game.Map;


public abstract class GameObject {
	protected Point2D.Double myLoc;
	protected double myDirection;
	protected Map map;
	protected Image image;
	
	@SuppressWarnings("unused")
	private GameObject()
	{
		myLoc = new Point2D.Double();
		myDirection = 0;
		map = null;
		image = null;
	}
	
	public GameObject(Point2D.Double loc, double dir, Map aMap)
	{
		myLoc = loc;
		myDirection = dir;
		map = aMap;
		if (map != null) map.add(this);
	}
	
	public Map getMap()
	{
		return map;
	}
	public Point2D.Double getLocation()
	{
		return myLoc;
	}
	public Point2D.Double setLocation(Point2D.Double loc) {
		Point2D.Double ol = myLoc;
		myLoc = loc;
		return ol;
	}
	public void translate(double x, double y) {
		myLoc.setLocation(myLoc.x+x, myLoc.y+y);
	}
	public double getDirection()
	{
		return myDirection;
	}
	public double setDirection(double newDirection)
	{
		double od = myDirection;
		myDirection = newDirection;
		return od;
	}
	public void setMap(Map mp)
	{
		if (map != null) map.remove(this);
		if (mp != null) mp.add(this);
		map = mp;
	}
	
	public abstract void conflict(GameObject other);
	public abstract void update(); //apparently it took in an int, delta time before
	
	public void paint(Graphics2D g) {
		g.setColor(getColor());
		g.fill(getBounds());
	}
	
	public Color getColor() {
		return Color.black;
	}

	public abstract Shape getBounds();
	public static boolean intersect(Shape s1, Shape s2) {
		Area a1 = new Area(s1);
		a1.intersect(new Area(s2));
		return !a1.isEmpty();
	}
}
