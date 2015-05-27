package component;

import java.awt.geom.*;
import java.util.*;
import java.awt.*;
import java.awt.event.*;

import javax.swing.*;


public abstract class GameObject {
	protected Point2D.Double myLoc;
	protected double myDirection;
	protected Map map;
	protected Image image;
	
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
		if (mp == null) map.remove(this);
		else mp.add(this);
		map = mp;
	}
	
	public void setImage(Image img)
	{
		image = img;
	}
	public abstract void conflict(GameObject other);
	public abstract void update(); //apparently it took in an int, delta time before
	
	public abstract void paint(Graphics2D g);

}
